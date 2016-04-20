package com.xzone.app.storyveller;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by arysuryawan on 3/30/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String HOME_ACTIVITIES = "com.xzone.app.storyveller.MainActivity";
    private CallbackManager mCallbackManager;
    private LoginButton fbLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this);

        setContentView(R.layout.activity_login_page);

        mCallbackManager = CallbackManager.Factory.create();

        BootstrapButton manualLoginButton = (BootstrapButton) findViewById(R.id.button_login);
        manualLoginButton.setOnClickListener(this);

        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("", "fb onSuccess");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        System.out.println("----------- init fb login");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_login:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("login_valid", true);
                startActivity(intent);
                break;
        }
    }

}
