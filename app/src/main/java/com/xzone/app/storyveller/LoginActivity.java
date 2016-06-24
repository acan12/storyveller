package com.xzone.app.storyveller;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.dao.UserDAO;
import core.model.User;

/**
 * Created by arysuryawan on 3/30/16.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.user_email) EditText userEmail;
    @BindView(R.id.user_password) EditText userPassword;
    @BindView(R.id.button_login) BootstrapButton manualLoginButton;
    @BindView(R.id.button_facebook_login) BootstrapButton fbLoginButton;
    @BindView(R.id.button_google_login) BootstrapButton gLoginButton;

    @BindView(R.id.facebook_login) LoginButton fblogin;



    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login_page);

        ButterKnife.bind(this);

        mCallbackManager = CallbackManager.Factory.create();

        manualLoginButton.setOnClickListener(this);
        fbLoginButton.setOnClickListener(this);
        gLoginButton.setOnClickListener(this);

        signInFacebook(fblogin);
    }


    private void signInFacebook(LoginButton fblogin){

        mCallbackManager = CallbackManager.Factory.create();
        fblogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        fblogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {

                final AccessToken accessToken = loginResult.getAccessToken();

                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback(){

                    @Override
                    public void onCompleted(JSONObject userJson, GraphResponse response) {

                        String url = "https://graph.facebook.com/"+userJson.optString("id")+"/picture?type=small";

                        User account = User.getInstance();

                        account.setUid(userJson.optString("id"));
                        account.setEmail(userJson.optString("email"));
                        account.setDisplayName(userJson.optString("name"));
                        account.setAvatar(url);
                        account.setProvider("facebook");

                        UserDAO.saveUser(account, getApplicationContext());
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

}
