package com.epicodus.djmusicmanager.ui;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

public class RecordFormDialogFragment extends DialogFragment implements OnEditorActionListener {

    @Bind(R.id.fragmentLabel) TextView mFragmentLabel;
    @Bind(R.id.cancelButton) Button mCancelButton;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.titleEditText) EditText mTitleEditText;
    @Bind(R.id.artistEditText) EditText mArtistEditText;

    public interface CreateRecordDialogListener {
        void onSubmitCreateDialog(Record mSong);
    }
    @Override
    public View onViewCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_record_form, container, false);
        getDialog().setTitle("Record Form Dialog");
        ButterKnife.bind(this, rootView);

        Typeface boolackFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Boolack.ttf");
        mFragmentLabel.setTypeface(boolackFont);

        mCancelButton.setOnEditorActionListener(this);
        mSubmitButton.setOnEditorActionListener(this);

        return rootView;
    }

    @Override
    public boolean onEditorAction(View v, int actionId, KeyEvent event){
        if (v == mSubmitButton){
            String songTitle = mTitleEditText.getText().toString();
            String artistName = mArtistEditText.getText().toString();
            Record mSong = new Record(songTitle, artistName);
            CreateRecordDialogListener listener = (CreateRecordDialogListener) getActivity();
            listener.onSubmitCreateDialog(mSong);
            dismiss();
            return true;
        } else if (v == mCancelButton){
            dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onSubmitCreateDialog(Record mSong){
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onClick(View v){
//        String songTitle = mTitleEditText.getText().toString();
//        String artistName = mArtistEditText.getText().toString();
//        Record mSong = new Record(songTitle, artistName);
//        if (v == mSubmitButton){
//            //pass mSong to the parent activity
//        }
//    }

    @Override
    public boolean onEditorAction(TextView v, int )
}
