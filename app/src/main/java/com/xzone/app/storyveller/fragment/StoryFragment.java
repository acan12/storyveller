package com.xzone.app.storyveller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.StoryDetailActivity;
import com.xzone.app.storyveller.adapter.StoryAdapter;

/**
 * Created by arysuryawan on 9/18/15.
 */
public class StoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public StoryFragment(){

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
        View rootView = inflater.inflate(R.layout.fragment_story, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new StoryAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new BaseFragment.RecyclerTouchListener(getActivity(), mRecyclerView, new BaseFragment.RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
