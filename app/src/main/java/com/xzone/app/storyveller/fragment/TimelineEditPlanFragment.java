package com.xzone.app.storyveller.fragment;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;

import com.xzone.app.storyveller.ItienaryDetailActivity;
import com.xzone.app.storyveller.TimelineDetailActivity;
import com.xzone.app.storyveller.component.PanelButton;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.adapter.ItienaryAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by arysuryawan on 11/18/15.
 */
public class TimelineEditPlanFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private final String[] labels = {"Schedule", "Itienary", "Publish"};
    private EditText et;
    private Button buttonSlide;

    private static final String SLIDING_FRAGMENT_TAG = "sliding_fragment";
    private View rootView;


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
        rootView = inflater.inflate(R.layout.fragment_edit_timelines, container, false);



        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ItienaryAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new BaseFragment.RecyclerTouchListener(getActivity(), mRecyclerView, new BaseFragment.RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final View backgroundSlideForm = (View) rootView.findViewById(R.id.background_slide_form);
                backgroundSlideForm.setVisibility(View.VISIBLE);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, true);
                mRecyclerView.setLayoutFrozen(true);
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));




        final BootstrapButton dateStartButton = (BootstrapButton) rootView.findViewById(R.id.date_start_button);
        final BootstrapButton timeStartButton = (BootstrapButton) rootView.findViewById(R.id.time_start_button);
        final BootstrapButton dateEndButton = (BootstrapButton) rootView.findViewById(R.id.date_end_button);
        final BootstrapButton timeEndButton = (BootstrapButton) rootView.findViewById(R.id.time_end_button);
        final BootstrapButton button_schedule = (BootstrapButton) rootView.findViewById(R.id.button_add_schedule);
        final BootstrapButton button_itienary = (BootstrapButton) rootView.findViewById(R.id.button_add_itienary);

        final BootstrapButton button_close1 = (BootstrapButton) rootView.findViewById(R.id.close1_form_button);
        final BootstrapButton button_close2 = (BootstrapButton) rootView.findViewById(R.id.close2_form_button);

        button_schedule.setOnClickListener(this);
        button_itienary.setOnClickListener(this);

        button_close1.setOnClickListener(this);
        button_close2.setOnClickListener(this);


        Spinner spinner = (Spinner) rootView.findViewById(R.id.transportation_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.list_of_transportation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


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
                dpd.show(getFragmentManager(), "Datepickerdialog");
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
                tpd.show(getFragmentManager(), "Timepickerdialog");
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

    @Override
    public void onClick(View v) {
        final View backgroundSlideForm = (View) rootView.findViewById(R.id.background_slide_form);

        switch (v.getId()){
            case R.id.button_add_schedule:
                backgroundSlideForm.setVisibility(View.VISIBLE);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, true);
                mRecyclerView.setLayoutFrozen(true);

                break;
            case R.id.button_add_itienary:
                backgroundSlideForm.setVisibility(View.VISIBLE);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, true);
                mRecyclerView.setLayoutFrozen(true);
                break;

            case R.id.close1_form_button:
                backgroundSlideForm.setVisibility(View.GONE);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, false);
                mRecyclerView.setLayoutFrozen(false);
                break;

            case R.id.close2_form_button:
                backgroundSlideForm.setVisibility(View.GONE);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, false);
                mRecyclerView.setLayoutFrozen(false);
                break;

            default:
                backgroundSlideForm.setVisibility(View.GONE);
                PanelButton.showPanel(rootView,
                        R.id.panel_schedule, false);
                PanelButton.showPanel(rootView,
                        R.id.panel_itienary, false);
        }


    }


}



