package com.diandianwan.app.learning.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Demo to show how to use sqlite in android.
 * show basics.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private Context context;

    public DBOpenHelper(Context context) {
        this(context, "person.db", null, 2);

    }

    public DBOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    public DBOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, dbName, factory, version, errorHandler);
        this.context = context;
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL("CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20))");
//
//    }

    /**
     * Using the versions to judge if there is a need to upgrade
     * 第一件是代码里判断新数据库的版本号是不是比旧数据库版本号大，
     * 因为onUpdategrade()方法只要两个版本不一致时都会被调用，
     * 所以也有可能导致回滚到旧版本的情况。我们的代码不希望看到这种情况，
     * 但是实际上应该考虑这种情况并且加上相应的处理代码。
     * <p/>
     * 第二件是在case分支语句中没有break。这是因为每个分支中的SQL语句都是简单的从一个
     * 版本更新(updates)到下一个版本，这样的话，如果要从版本1更新(upgrade)到版本3的时候，
     * 首先会执行从版本1更新(upgrade)到版本2的脚本，然后再执行从版本2更新(upgrade)到版本3的脚本。
     * 如果数据库已经是版本2了，那么只会执行版本2到版本3的更新(upgrade)脚本。
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // just an example in here.

        if (newVersion > oldVersion) {
            switch (oldVersion) {
                case 1:
                    executeSQLScript(db, "update_v2.sql");
                case 2:
                    executeSQLScript(db, "update_v3.sql");

            }

        }

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        executeSQLScript(database, "create.sql");
    }

    /**
     * A way to help decrease the sql statements maintain complexity.
     *
     * @param database
     * @param dbname
     */
    private void executeSQLScript(SQLiteDatabase database, String dbname) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream;

        try {
            inputStream = assetManager.open(dbname);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            String[] createScript = outputStream.toString().split(";");
            for (String aCreateScript : createScript) {
                String sqlStatement = aCreateScript.trim();
                // TODO You may want to parse out comments here
                if (sqlStatement.length() > 0) {
                    database.execSQL(sqlStatement + ";");
                }
            }
        } catch (IOException e) {
            // TODO Handle Script Failed to Load
        } catch (SQLException e) {
            // TODO Handle Script Failed to Execute
        }
    }
}
