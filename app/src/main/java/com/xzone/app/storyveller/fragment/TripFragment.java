package com.xzone.app.storyveller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.xzone.app.storyveller.TimelineDetailActivity;
import com.xzone.app.storyveller.TimelineEditActivity;
import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.adapter.BoardAdapter;

/**
 * Created by arysuryawan on 9/18/15.
 */
public class TripFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public TripFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trips, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // click listener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("EVENT", "trigger from click!");

                Intent intent = new Intent(getActivity(), TimelineDetailActivity.class);
                intent.putExtra("dodol", "xx");
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d("EVENT", "trigger from long click!");
            }
        }));


        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BoardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimelineEditActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}


