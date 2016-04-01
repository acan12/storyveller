package com.xzone.app.storyveller.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.melnykov.fab.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.xzone.app.storyveller.ItienaryDetailActivity;
import com.xzone.app.storyveller.TimelineDetailActivity;
import com.xzone.app.storyveller.TimelineEditActivity;
import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.adapter.ItienaryAdapter;

/**
 * Created by arysuryawan on 9/18/15.
 */
public class TimelineFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TimelineFragment(){

    }

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
        View rootView = inflater.inflate(R.layout.fragment_timelines, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ItienaryAdapter();
        mRecyclerView.setAdapter(mAdapter);


        // using pull_to_refresh
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            Log.d("DEBUG", "Hellow world inside refresh layout");

            swipeRefreshLayout.setRefreshing(false);
            }
        });


//        BootstrapButton button = (BootstrapButton) rootView.findViewById(R.id.reserve_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ReservationActivity.class);
//                startActivity(intent);
//            }
//        });

        final View backgroundFabMenus = (View) rootView.findViewById(R.id.background_fab_menus);
        FloatingActionsMenu fam = (FloatingActionsMenu) rootView.findViewById(R.id.multiple_actions_left);
        fam.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                backgroundFabMenus.setVisibility(View.VISIBLE);
                mRecyclerView.setLayoutFrozen(true);
            }

            @Override
            public void onMenuCollapsed() {
                backgroundFabMenus.setVisibility(View.GONE);
                mRecyclerView.setLayoutFrozen(false);
            }
        });

        FloatingActionButton fabMenuModify = (FloatingActionButton) fam.findViewById(R.id.button_modify);
        fabMenuModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimelineEditActivity.class);
                startActivity(intent);
            }
        });



        mRecyclerView.addOnItemTouchListener(new BaseFragment.RecyclerTouchListener(getActivity(), mRecyclerView, new BaseFragment.RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ItienaryDetailActivity.class);
                getActivity().startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));

        return rootView;
    }

}
