package com.xzone.app.storyveller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.api.Api;
import core.component.FontManager;
import core.component.ProgressAction;
import core.dao.UserDAO;
import core.parser.UserParser;
import core.util.SecurityUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by arysuryawan on 8/13/16.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.user_email)
    EditText userEmail;
    @BindView(R.id.user_first_name)
    EditText userFirstName;
    @BindView(R.id.user_last_name)
    EditText userLastName;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.user_password_confirm)
    EditText userPasswordConfirm;
    @BindView(R.id.button_register)
    BootstrapButton buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        warningMessage.setTypeface(FontManager.getTypeface(this, FontManager.FONTAWESOME));
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                String email = userEmail.getText().toString();
                String firstname = userFirstName.getText().toString();
                String lastname = userLastName.getText().toString();
                String password = userPassword.getText().toString();
                String passwordConfirm = userPasswordConfirm.getText().toString();

                try {

                    if (!SecurityUtil.isValidPassword(password, passwordConfirm))
                        throw new Exception();
                } catch (Exception e) {
                    warningMessage.setText(getResources().getString(R.string.invalid_registration_field));
                    return;
                }

                UserDAO.deleteUser(this); // delete user

                Api.registerUser(email, firstname, lastname, password, passwordConfirm, this, callbackRegister);
                ProgressAction.onProgressStart(10000, this);
                break;
        }
    }


    private Api.ApiCallback callbackRegister = new Api.ApiCallback() {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {

                try {
                    StringBuffer json = new StringBuffer(response.body().string());
                    UserParser.setUserParser(json.toString(), getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }

            } else {

            }

        }
    };
}
