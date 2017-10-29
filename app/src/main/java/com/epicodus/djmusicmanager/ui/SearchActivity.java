package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.Constants;
import com.epicodus.djmusicmanager.R;
import com.epicodus.djmusicmanager.models.Record;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchTitleTextView) TextView mSearchTitleTextView;
    @Bind(R.id.songTitleEditText) EditText mSongTitleEditText;
    @Bind(R.id.artistNameEditText) EditText mArtistNameEditText;
    @Bind(R.id.searchAPIButton) Button mSearchAPIButton;
    @Bind(R.id.searchYouTubeButton) Button mSearchYouTubeButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mSearchTitleTextView.setTypeface(boolackFont);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSearchAPIButton.setOnClickListener(this);
        mSearchYouTubeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        String songTitle = mSongTitleEditText.getText().toString();
        String artistName = mArtistNameEditText.getText().toString();
        Record mSong = new Record(songTitle, artistName);
        if (v == mSearchAPIButton) {
            Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
            intent.putExtra("songTitle", songTitle);
            intent.putExtra("artistName", artistName);
            if (songTitle.equals("")){
                Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_SHORT).show();
            } else {
                addToSharedPreferences(songTitle);
                startActivity(intent);
            }
        } else if (v == mSearchYouTubeButton) {
            if (songTitle.equals("")) {
                Toast.makeText(SearchActivity.this, "Please enter song title", Toast.LENGTH_SHORT).show();
            } else if (!artistName.equals("")) {
                String searchStr = (songTitle.replaceAll(" ", "+") + "+" + artistName.replaceAll(" ", "+"));
                Uri webpage = Uri.parse("http://www.youtube.com/results?search_query=" + searchStr);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            } else {
                String searchTitle = songTitle.replaceAll(" ", "+");
                Uri webpage = Uri.parse("http://www.youtube.com/results?search_query=" + searchTitle);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        }
    }

    private void addToSharedPreferences(String songTitle){
        mEditor.putString(Constants.PREFERENCES_TITLE_KEY, songTitle).apply();
    }


}
