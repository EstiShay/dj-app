package com.epicodus.djmusicmanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.subtitleTextView) TextView mSubtitleTextView;
    private String[] songs = new String[] {"The Lady is a Tramp by Ella Fitzgerald", "Fine Brown " +
            "Frame by Lou Rawls", "Leaving on a Jet Plane by HB Radke and the Jet City Swingers",
   "Blue Suit Boogie by Indigo Swing", "Scratching Circles by JD McPherson", "On Revival Day by " +
            "Laverne Baker", "Lavender Coffin by Lionel Hampton and His Orchestra", "Santa Maria " +
            "(Del Buen Ayre) by Gotan Project", "Home by Marc Brussard", "Cold Turkey by Anthony " +
            "David", "Sweet Little Angel by Big Mama Thornton", "Bump and Grind by Jimmy " +
            "Thackery", "John the Revelator by Govt Mule", "John the Revelator by Lee Roy Parnell"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAppNameTextView.setTypeface(boolackFont);
        Typeface PTCFont = Typeface.createFromAsset(getAssets(), "fonts/PTC55F.ttf");
        mSubtitleTextView.setTypeface(PTCFont);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        mListView.setAdapter(adapter);


        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View thisView){
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View thisView){
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
