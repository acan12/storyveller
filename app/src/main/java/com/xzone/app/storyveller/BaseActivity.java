package com.xzone.app.storyveller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.component.CustomTextWatcher;
import core.dao.UserDao;
import core.model.User;
import io.fabric.sdk.android.Fabric;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.warning_message) TextView warningMessage;

    protected void setup(Activity activity){
        Fabric.with(activity, new Crashlytics());
        FacebookSdk.sdkInitialize(activity);


        User user = UserDao.getUser(this);
        if(user.isLoginStatus()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
    }

    protected CustomTextWatcher customTextWatcher = new CustomTextWatcher(){

        @Override
        public void afterTextChanged(Editable s) {
            warningMessage.setText("");
        }
    };

}
