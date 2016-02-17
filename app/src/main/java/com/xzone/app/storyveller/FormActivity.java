package com.xzone.app.storyveller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.xzone.app.storyveller.fragment.ScheduleFormFragment;

/**
 * Created by arysuryawan on 11/3/15.
 */
public class FormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        // show back actionbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment = new ScheduleFormFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "";
//        BootstrapButton date_button = (DatePickerDialog) view;
//        switch(view.getId()){
//            case R.id.date_start_button :
//                date = "Start at "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
//                date_button = (BootstrapButton) this.findViewById(R.id.date_start_button);
//                break;
//            case R.id.date_end_button :
//                date = "End at "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
//                date_button = (BootstrapButton) this.findViewById(R.id.date_end_button);
//                break;
//            default :
//                date = "Start Date";
//        }


//        date_button.setText(date);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String time = "Start Time: From - "+hourString+"h"+minuteString;

        BootstrapButton time_button = (BootstrapButton) this.findViewById(R.id.time_start_button);
        time_button.setText(time);
    }
}
