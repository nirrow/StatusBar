package com.nirrow.statusbar;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int themeid = getIntent().getIntExtra("themeid", R.style.AppTheme);
        setTheme(themeid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        AppCompatTextView textView = findViewById(R.id.api);
        textView.setText("API : " + Build.VERSION.SDK_INT);
        boolean fitSingleView = getIntent().getBooleanExtra("fitSysWinOnSingleView", false);
        if (fitSingleView) {
            textView.setFitsSystemWindows(true);
        }
        String explain = getIntent().getStringExtra("explain");
        AppCompatTextView tvExplain = findViewById(R.id.explain);
        tvExplain.setText(explain);
    }
}
