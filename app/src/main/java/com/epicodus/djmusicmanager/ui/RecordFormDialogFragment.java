package com.epicodus.djmusicmanager.ui;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordFormDialogFragment extends DialogFragment {

    @Bind(R.id.fragmentLabel) TextView mFragmentLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_record_form, container, false);
        getDialog().setTitle("Record Form Dialog");
        ButterKnife.bind(this, rootView);

        Typeface boolackFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Boolack.ttf");
        mFragmentLabel.setTypeface(boolackFont);
        return rootView;
    }
}
