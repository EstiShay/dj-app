package com.epicodus.djmusicmanager.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.epicodus.djmusicmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.subtitleTextView) TextView mSubtitleTextView;
    @Bind(R.id.emailEditText) TextView mEmailEditText;
    @Bind(R.id.passwordEditText) TextView mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAppNameTextView.setTypeface(boolackFont);
        Typeface PTCFont = Typeface.createFromAsset(getAssets(), "fonts/PTC55F.ttf");
        mSubtitleTextView.setTypeface(PTCFont);
        mEmailEditText.setTypeface(PTCFont);
        mPasswordEditText.setTypeface(PTCFont);
    }

    @Override
    public void onClick(View view){
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
