package com.epicodus.djmusicmanager.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.djmusicmanager.Constants;
import com.epicodus.djmusicmanager.MainActivity;
import com.epicodus.djmusicmanager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.subtitleTextView) TextView mSubtitleTextView;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.loginButton) Button mLoginButton;
    String mEmail;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mRecentEmail = mSharedPreferences.getString(Constants.PREFERENCES_LOGIN_EMAIL, null);
        if (mRecentEmail != null){
            mEmailEditText.setText(mRecentEmail);
        }

        mRegisterTextView.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);

        Typeface boolackFont = Typeface.createFromAsset(getAssets(), "fonts/Boolack.ttf");
        mAppNameTextView.setTypeface(boolackFont);
        Typeface PTCFont = Typeface.createFromAsset(getAssets(), "fonts/PTC55F.ttf");
        mSubtitleTextView.setTypeface(PTCFont);
        mEmailEditText.setTypeface(PTCFont);
        mPasswordEditText.setTypeface(PTCFont);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        createAuthProgressDialog();
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view){
        mEmail = mEmailEditText.getText().toString().trim();
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        } else if (view == mLoginButton){
            addToSharedPreferences(mEmail);
            loginWithPassword();
        }
    }

    private void createAuthProgressDialog(){
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("...your excellent music collection");
        mAuthProgressDialog.setCancelable(false);
    }

    private void loginWithPassword(){
//        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (mEmail.equals("")){
            mEmailEditText.requestFocus();
            mEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")){
            mPasswordEditText.requestFocus();
            mPasswordEditText.setError("Password is required");
            return;
        }
        mAuthProgressDialog.show();

        mAuth.signInWithEmailAndPassword(mEmail, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
            mAuthProgressDialog.dismiss();
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login failed - try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    private void addToSharedPreferences(String mEmail){
        mEditor.putString(Constants.PREFERENCES_LOGIN_EMAIL, mEmail).apply();
    }
}
