package com.xzone.app.storyveller.fragment;

import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.support.v4.app.Fragment;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
//import com.borax12.materialdaterangepicker.date.DatePickerDialog;
//import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.xzone.app.storyveller.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by arysuryawan on 11/18/15.
 */
public class ScheduleFormFragment extends Fragment {


    private final String[] labels = {"Schedule", "Itienary", "Publish"};
    private EditText et;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_schedule_form, container, false);

//        StepsView mStepsView = (StepsView) rootView.findViewById(R.id.stepsView);
//        mStepsView.setLabels(labels)
//                .setBarColorIndicator(
//                        this.getResources().getColor(R.color.bootstrap_brand_primary))
//                .setProgressColorIndicator(this.getResources().getColor(R.color.orange))
//                .setLabelColorIndicator(this.getResources().getColor(R.color.orange))
//                .drawView();


        Spinner spinner = (Spinner) rootView.findViewById(R.id.categories_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.list_of_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Find our View instances
        final BootstrapButton dateStartButton = (BootstrapButton) rootView.findViewById(R.id.date_start_button);
        final BootstrapButton timeStartButton = (BootstrapButton) rootView.findViewById(R.id.time_start_button);
        final BootstrapButton dateEndButton = (BootstrapButton) rootView.findViewById(R.id.date_end_button);
        final BootstrapButton timeEndButton = (BootstrapButton) rootView.findViewById(R.id.time_end_button);

        // Show a datepicker when the dateButton is clicked - start & end schedule
        dateStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (DatePickerDialog.OnDateSetListener) getActivity(),
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show( getFragmentManager(), "Datepickerdialog");
                dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year, monthOfYear-1, dayOfMonth, 0,0);
                        dateStartButton.setText(new SimpleDateFormat("E,MMM d, yyyy ").format(c.getTime())  );
                    }
                });

            }
        });

        timeStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (TimePickerDialog.OnTimeSetListener) getActivity(),
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show( getFragmentManager(), "Timepickerdialog");
                tpd.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                        String AM_PM = (hourOfDay < 12) ? "AM" : "PM";

                        timeStartButton.setText(hourOfDay+":"+minute+" "+AM_PM);
                    }
                });
            }


        });


        dateEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (DatePickerDialog.OnDateSetListener) getActivity(),
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show( getFragmentManager(), "Datepickerdialog");
                dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year, monthOfYear-1, dayOfMonth, 0,0);
                        dateEndButton.setText(new SimpleDateFormat("E,MMM d, yyyy ").format(c.getTime())  );
                    }
                });

            }
        });

        timeEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (TimePickerDialog.OnTimeSetListener) getActivity(),
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show( getFragmentManager(), "Timepickerdialog");
                tpd.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                        String AM_PM = (hourOfDay < 12) ? "AM" : "PM";

                        timeEndButton.setText(hourOfDay+":"+minute+" "+AM_PM);

                    }
                });
            }


        });

        return rootView;
    }





}
