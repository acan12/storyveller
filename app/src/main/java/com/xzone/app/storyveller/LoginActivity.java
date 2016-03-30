package com.xzone.app.storyveller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * Created by arysuryawan on 3/30/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        BootstrapButton loginButton = (BootstrapButton) findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
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
