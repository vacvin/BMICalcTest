package vacvin.myapplication1;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class HistoryActivity extends ListActivity {
    private DB mDbHelper;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getListView().setEmptyView(findViewById(R.id.empty));
        setAdapter();
    }

    private void setAdapter() {
        // remove throws SQLException
        mDbHelper = new DB(this);
        mDbHelper.open();

        fillData();
    }

    private void fillData() {
        mCursor = mDbHelper.getAll();
        //String[] from_column = new String[]{DB.KEY_height,DB.KEY_weight,DB.KEY_BMI, DB.KEY_CREATED};
        String[] from_column = new String[]{DB.KEY_BMI, DB.KEY_CREATED};
        //int[] to_layout = new int[]{android.R.id.text1, android.R.id.text2};
        int[] to_layout = new int[]{R.id.value_BMI, R.id.value_Date};

        //SimpleCursorAdapter adapter =
        //        new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
        //                mCursor, from_column, to_layout, 0);

        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, R.layout.list_row,
                        mCursor, from_column, to_layout, 0);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDbHelper.delete(id);
                fillData();
            }
        });
    }
}
