package com.epicodus.djmusicmanager.ui;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.djmusicmanager.Constants;
import com.epicodus.djmusicmanager.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordFormDialogFragment extends DialogFragment implements View.OnClickListener {

    @Bind(R.id.fragmentLabel) TextView mFragmentLabel;
    @Bind(R.id.cancelButton) Button mCancelButton;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.songTitleEditText) EditText mSongTitleEditText;

    private DatabaseReference mSearchedSongReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_record_form, container, false);
        getDialog().setTitle("Record Form Dialog");
        ButterKnife.bind(this, rootView);

        Typeface boolackFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Boolack.ttf");
        mFragmentLabel.setTypeface(boolackFont);

        mCancelButton.setOnClickListener(this);
        mSubmitButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v){
        String songTitle = mSongTitleEditText.getText().toString();
        if (v == mSubmitButton){
            saveSongToFirebase(songTitle);
        }
    }

    public void saveSongToFirebase(String songTitle) {
        mSearchedSongReference.setValue(songTitle);
    }
}
