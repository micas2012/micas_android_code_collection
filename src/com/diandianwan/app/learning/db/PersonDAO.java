package com.diandianwan.app.learning.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private DBOpenHelper helper;

    public PersonDAO(Context context) {
        helper = new DBOpenHelper(context);
    }


    public void remit(int from, int to, int amount) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 开启事务
        db.beginTransaction();
        try {
            db.execSQL("UPDATE person SET balance=balance-? WHERE id=?", new Object[]{amount, from});
            db.execSQL("UPDATE person SET balance=balance+? WHERE id=?", new Object[]{amount, to});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 结束事务, 将事务成功点前面的代码提交
        db.endTransaction();

        db.close();
    }

    public void insert(Person p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 准备数据
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("balance", p.getBalance());

        // 通过ContentValues中的数据拼接SQL语句, 执行插入操作, id为表中的一个列名
        db.insert("person", "id", values);

        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 执行删除操作, 在person表中删除id为指定值的记录
        db.delete("person", "id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void update(Person p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 要更新的数据
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("balance", p.getBalance());

        // 更新person表中id为指定值的记录
        db.update("person", values, "id=?", new String[]{String.valueOf(p.getId())});

        db.close();
    }

    public Person query(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        // 执行查询: 不去重复, 表是person, 查询name和balance两列, Where条件是"id=?", 占位符是id, 不分组, 没有having, 不排序, 没有分页
        Cursor c = db.query(false, "person", new String[]{"name", "balance"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);

        Person p = null;
        // 判断Cursor是否有下一条记录
        if (c.moveToNext())
            // 从Cursor中获取数据, 创建Person对象
            p = new Person(id, c.getString(0), c.getInt(1));

        // 释放资源
        c.close();
        db.close();
        return p;
    }

    public List<Person> queryAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        // 查询所有记录, 倒序
        Cursor c = db.query(false, "person", new String[]{"id", "name", "balance"}, null, null, null, null, "id DESC", null);

        List<Person> persons = new ArrayList<Person>();
        while (c.moveToNext())
            persons.add(new Person(c.getInt(0), c.getString(1), c.getInt(2)));
        c.close();
        db.close();
        return persons;
    }

    public List<Person> queryPage(int pageNum, int capacity) {
        // 开始索引
        String start = String.valueOf((pageNum - 1) * capacity);
        // 查询的个数
        String length = String.valueOf(capacity);

        SQLiteDatabase db = helper.getReadableDatabase();

        // 翻页查询
        Cursor c = db.query(false, "person", new String[]{"id", "name", "balance"}, null, null, null, null, null, start + "," + length);

        List<Person> persons = new ArrayList<Person>();
        while (c.moveToNext())
            persons.add(new Person(c.getInt(0), c.getString(1), c.getInt(2)));
        c.close();
        db.close();
        return persons;
    }

    public int queryCount() {
        SQLiteDatabase db = helper.getReadableDatabase();

        // 查询记录条数
        Cursor c = db.query(false, "person", new String[]{"COUNT(*)"}, null, null, null, null, null, null);

        c.moveToNext();
        int count = c.getInt(0);
        c.close();
        db.close();
        return count;
    }

}