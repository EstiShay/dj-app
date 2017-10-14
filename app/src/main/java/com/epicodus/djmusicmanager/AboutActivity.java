package com.epicodus.djmusicmanager;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.aboutTitleTextView)
    TextView mAboutTitleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAboutTitleTextView.setTypeface(boolackFont);
    }
}
