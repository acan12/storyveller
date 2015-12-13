package com.xzone.app.storyveller.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;;
import com.anton46.stepsview.StepsView;
import com.xzone.app.storyveller.R;

/**
 * Created by arysuryawan on 11/18/15.
 */
public class ScheduleFormFragment extends Fragment {

    private final String[] labels = {"Schedule", "Itienary", "Publish"};


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
        View rootView = inflater.inflate(R.layout.fragment_schedule_form, container, false);

        StepsView mStepsView = (StepsView) rootView.findViewById(R.id.stepsView);
        mStepsView.setLabels(labels)
                .setBarColorIndicator(
                        getContext().getResources().getColor(R.color.bootstrap_brand_primary))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.orange))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.orange))
                .drawView();

        return rootView;
    }



}
