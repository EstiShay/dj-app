package com.epicodus.djmusicmanager;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.adapters.FirebaseRecordViewHolder;
import com.epicodus.djmusicmanager.models.Record;
import com.epicodus.djmusicmanager.ui.AboutActivity;
import com.epicodus.djmusicmanager.ui.RecordFormDialogFragment;
import com.epicodus.djmusicmanager.ui.SearchActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.addButton) Button mAddButton;
//    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.subtitleTextView) TextView mSubtitleTextView;
//    private String[] songs = new String[] {"The Lady is a Tramp by Ella Fitzgerald", "Fine Brown " +
//            "Frame by Lou Rawls", "Leaving on a Jet Plane by HB Radke and the Jet City Swingers",
//   "Blue Suit Boogie by Indigo Swing", "Scratching Circles by JD McPherson", "On Revival Day by " +
//            "Laverne Baker", "Lavender Coffin by Lionel Hampton and His Orchestra", "Santa Maria " +
//            "(Del Buen Ayre) by Gotan Project", "Home by Marc Brussard", "Cold Turkey by Anthony " +
//            "David", "Sweet Little Angel by Big Mama Thornton", "Bump and Grind by Jimmy " +
//            "Thackery", "John the Revelator by Govt Mule", "John the Revelator by Lee Roy Parnell"};

    private DatabaseReference mTrackReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.tracksRecyclerView) RecyclerView mTracksRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAppNameTextView.setTypeface(boolackFont);
        Typeface PTCFont = Typeface.createFromAsset(getAssets(), "fonts/PTC55F.ttf");
        mSubtitleTextView.setTypeface(PTCFont);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
//        mListView.setAdapter(adapter);

        mTrackReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SONGS);
        setUpFirebaseAdapter();

        mAboutButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Record, FirebaseRecordViewHolder>(Record.class, R.layout.song_list_item, FirebaseRecordViewHolder.class, mTrackReference) {
            @Override
            protected void populateViewHolder(FirebaseRecordViewHolder viewHolder, Record model, int position) {
                viewHolder.bindRecord(model);
            }
        };
        mTracksRecyclerview.setHasFixedSize(true);
        mTracksRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mTracksRecyclerview.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mAboutButton){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mSearchButton) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        } else if (v == mAddButton) {
            RecordFormDialogFragment recordFormDialogFragmentDialogFragment = new RecordFormDialogFragment();
            recordFormDialogFragmentDialogFragment.show(getSupportFragmentManager(), "Record Form Dialog");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

//    Need onResume() to reload RecyclerView to include newly created record
}
