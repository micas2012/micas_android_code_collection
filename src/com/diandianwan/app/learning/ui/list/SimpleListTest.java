package com.diandianwan.app.learning.ui.list;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * A list view example where the
 * data for the list comes from an array of strings.
 * ListActivity is just a listview only as all its ui activity.
 * <p/>
 * ListView is a subclass of ViewGroup.
 */
public class SimpleListTest extends ListActivity {

    private String[] mTestStrings = {
            "测试100", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9", "test10", "test11"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        // By default, ArrayAdapter creates a view for each array item by calling
        // toString() on each item and placing the contents in a TextView.

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mTestStrings));

        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SimpleListTest.this);
                TextView t = new TextView(SimpleListTest.this);
                t.setText("hi from " + ((TextView) view).getText());
                builder.setView(t);
                builder.create();
                builder.show();

            }
        });

        // the above equals: ListView listView = (ListView) findViewById(R.id.listview);
        // listView.setAdapter(adapter);
    }


}
