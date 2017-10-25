package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @Bind(R.id.searchTitleTextView) TextView mSearchTitleTextView;
    @Bind(R.id.songTitleEditText) EditText mSongTitleEditText;
    @Bind(R.id.artistNameEditText) EditText mArtistNameEditText;
    @Bind(R.id.searchAPIButton) Button mSearchAPIButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mSearchTitleTextView.setTypeface(boolackFont);

        mSearchAPIButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String songTitle = mSongTitleEditText.getText().toString();
                String artistName = mArtistNameEditText.getText().toString();
                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                intent.putExtra("songTitle", songTitle);
                intent.putExtra("artistName", artistName);
                if (songTitle.equals("")){
                    Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);
                }
            }
        });

//        mSearchAPIButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v == mSearchAPIButton) {
//                    String songTitle = mSongTitleEditText.getText().toString();
//                    String artistName = mArtistNameEditText.getText().toString();

//                        if (songTitle.equals("")){
//                            Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_LONG).show();
//                        }
//
//
//                }
//
//            }
//        });
    }
}