package com.xzone.app.storyveller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xzone.app.storyveller.DetailActivity;
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
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // click listener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("EVENT", "trigger from click!");

                Intent intent = new Intent(getActivity(), DetailActivity.class);
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

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}


