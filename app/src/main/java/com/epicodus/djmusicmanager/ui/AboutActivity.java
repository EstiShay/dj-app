package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutTitleTextView) TextView mAboutTitleTextView;
    @Bind(R.id.aboutTextView) TextView mAboutTextView;
    @Bind(R.id.featuresTextView) TextView mFeaturesTextView;

    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.addButton) Button mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAboutTitleTextView.setTypeface(boolackFont);
        Typeface PTCFont = Typeface.createFromAsset(getAssets(), "fonts/PTC55F.ttf");
        mAboutTextView.setTypeface(PTCFont);
        mFeaturesTextView.setTypeface(PTCFont);

        mAboutButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAboutButton){
            Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mSearchButton) {
            Intent intent = new Intent(AboutActivity.this, SearchActivity.class);
            startActivity(intent);
        } else if (v == mAddButton) {
            Toast.makeText(AboutActivity.this, "Feature coming soon!", Toast.LENGTH_LONG).show();
        }
    }
}
