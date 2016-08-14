package com.xzone.app.storyveller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.stetho.common.ExceptionUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.api.Api;
import core.component.CustomTextWatcher;
import core.component.FontManager;
import core.component.ProgressAction;
import core.component.UnderlineTextView;
import core.dao.UserDao;
import core.model.User;
import io.fabric.sdk.android.Fabric;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by arysuryawan on 3/30/16.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.user_email) EditText userEmail;
    @BindView(R.id.user_password) EditText userPassword;
    @BindView(R.id.button_login) BootstrapButton manualLoginButton;
    @BindView(R.id.register_link) UnderlineTextView registerLink;
    @BindView(R.id.warning_message) TextView warningMessage;

//    @BindView(R.id.button_facebook_login) BootstrapButton fbLoginButton;
//    @BindView(R.id.button_google_login) BootstrapButton gLoginButton;

    @BindView(R.id.facebook_login) LoginButton fblogin;



    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup(this);
        setContentView(R.layout.activity_login_page);
        ButterKnife.bind(this);



        mCallbackManager = CallbackManager.Factory.create();

        warningMessage.setTypeface(FontManager.getTypeface(this, FontManager.FONTAWESOME));
        manualLoginButton.setOnClickListener(this);
        registerLink.setOnClickListener(this);

//        fbLoginButton.setOnClickListener(this);
//        gLoginButton.setOnClickListener(this);

//        signInFacebook(fblogin);

        userEmail.addTextChangedListener(customTextWatcher);
        userPassword.addTextChangedListener(customTextWatcher);

    }


    private void manualSignIn() throws Exception {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        User user = UserDao.getUser(this);
        if(user.getEmail().equals(email) && UserDao.isPasswordMatch(password, this)){

            Api.loginUser(email, password, this, loginCallback);
            ProgressAction.onProgressStart(10000, this);

            return;
        }
        throw new Exception();


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

                        account.setUid(Integer.parseInt(userJson.optString("id")));
                        account.setEmail(userJson.optString("email"));
                        account.setDisplayName(userJson.optString("name"));
                        account.setAvatar(url);
                        account.setProvider("facebook");

                        UserDao.saveUser(account, null, getApplicationContext());
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
                try {
                    manualSignIn();
                } catch (Exception e) {
                    warningMessage.setText(getResources().getString(R.string.invalid_email_or_password));
                }
                break;

            case R.id.register_link:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }



    private Api.ApiCallback loginCallback = new Api.ApiCallback(){
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if(response.isSuccessful()){

                UserDao.addLoginStatus(true, getApplicationContext()); // set loginstatus to true

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
