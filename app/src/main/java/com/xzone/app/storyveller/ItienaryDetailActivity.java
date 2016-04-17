package com.xzone.app.storyveller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.fragment.TimelineFragment;

/**
 * Created by arysuryawan on 4/1/16.
 */
public class ItienaryDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itienary_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Toba Lake");
        setSupportActionBar(mToolbar);

        // show back actionbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//
//        Fragment fragment = new TimelineFragment();
//
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container_body, fragment);
//            fragmentTransaction.commit();
//
//        }
    }
}