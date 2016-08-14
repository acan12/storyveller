package com.xzone.app.storyveller;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import core.component.CustomTextWatcher;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.warning_message) TextView warningMessage;

    protected CustomTextWatcher customTextWatcher = new CustomTextWatcher(){

        @Override
        public void afterTextChanged(Editable s) {
            warningMessage.setText("");
        }
    };

}
