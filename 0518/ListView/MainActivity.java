package com.example.user.myapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {
    TextView mText;
    ListView mList;
    String [] data = {
            "list1", "list2", "list3", "list4", "list5", "list6", "list7", "list8"
    };
    String [] subdata = {
            "listA", "listB", "listC", "listD", "listE", "listF", "listG", "listH"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mText = findViewById(R.id.textView);
//        mList = findViewById(android.R.id.list);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < data.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("str1", data[i]);
            map.put("str2", subdata[i]);
            list.add(map);
        }

        String[] key = {"str1", "str2"};
        int[] id = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(
                this, list, android.R.layout.simple_list_item_2, key, id
        );
        setListAdapter(adapter);
    }
//        mList = findViewById(R.id.listView);
//
//        MyAdapter myAdapter = new MyAdapter();
//        mList.setAdapter(myAdapter);

/*        //어레이어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mText.setText(data[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */

    //    class MyAdapter extends BaseAdapter {
//        String [] namse = {"ㅁ", "ㄴ", "ㅇ", "ㄹ"};
//
//        @Override
//        public int getCount() {
//            return namse.length;
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return namse[i];
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            TextView textView = new TextView(getApplicationContext());
//            textView.setText(namse[i]);
//            return textView;
//        }
//    }
}
