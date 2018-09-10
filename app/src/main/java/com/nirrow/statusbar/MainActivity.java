package com.nirrow.statusbar;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    private TypedArray themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        themes = getResources().obtainTypedArray(R.array.themes_value);
        listAdapter = new ArrayAdapter<String>(this, R.layout.content_item, R.id.content);
        listAdapter.addAll(getResources().getStringArray(R.array.themes_name));
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, StatusBarActivity.class);
                intent.putExtra("themeid", themes.getResourceId(i, R.style.AppTheme));
                String[] explains = getResources().getStringArray(R.array.explain);
                if (i < explains.length && i >= 0) {
                    intent.putExtra("explain", getResources().getStringArray(R.array.explain)[i]);
                }
                if (i == 3) {
                    intent.putExtra("fitSysWinOnSingleView", true);
                }
                startActivity(intent);
            }
        });
    }
}
