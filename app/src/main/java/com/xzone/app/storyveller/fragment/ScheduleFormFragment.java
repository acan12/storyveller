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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
//import com.borax12.materialdaterangepicker.date.DatePickerDialog;
//import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.xzone.app.storyveller.R;

import java.util.Calendar;

/**
 * Created by arysuryawan on 11/18/15.
 */
public class ScheduleFormFragment extends Fragment {


    private final String[] labels = {"Schedule", "Itienary", "Publish"};
    private EditText et;

    private TextView dateTextView;
    private TextView timeTextView;

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

        StepsView mStepsView = (StepsView) rootView.findViewById(R.id.stepsView);
        mStepsView.setLabels(labels)
                .setBarColorIndicator(
                        this.getResources().getColor(R.color.bootstrap_brand_primary))
                .setProgressColorIndicator(this.getResources().getColor(R.color.orange))
                .setLabelColorIndicator(this.getResources().getColor(R.color.orange))
                .drawView();


        Spinner spinner = (Spinner) rootView.findViewById(R.id.categories_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.list_of_category, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // Find our View instances
        dateTextView = (TextView) rootView.findViewById(R.id.date_textview);
        timeTextView = (TextView) rootView.findViewById(R.id.time_textview);
        Button dateButton = (Button) rootView.findViewById(R.id.date_button);
        Button timeButton = (Button) rootView.findViewById(R.id.time_button);

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
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
            }


        });

        return rootView;
    }



//    @Override
//    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
//        String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
//        Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
//        dateTextView.setText(date);
//    }
//
//
//    @Override
//    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
//        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
//        String minuteString = minute < 10 ? "0"+minute : ""+minute;
//        String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
//        String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
//        String time = "You picked the following time: From - "+hourString+"h"+minuteString+" To - "+hourStringEnd+"h"+minuteStringEnd;
//
//        timeTextView.setText(time);
//    }

}
