package com.xzone.app.storyveller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.astuetz.PagerSlidingTabStrip;
import com.xzone.app.storyveller.fragment.NavigationDrawerFragment;
import com.xzone.app.storyveller.fragment.ArticleFragment;
import com.xzone.app.storyveller.fragment.TimelineFragment;
import com.xzone.app.storyveller.fragment.TripFragment;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.FragmentDrawerListener{

    private Toolbar mToolbar;
    private NavigationDrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routesLogin();


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        final PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        tabs.setTextColor(Color.WHITE);





    }

    private void routesLogin(){
        Intent intent = getIntent();

        if(!intent.getBooleanExtra("login_valid", false)) {
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new TimelineFragment();
                title = getString(R.string.title_timeline);
                break;
            case 1:
                fragment = new ArticleFragment();
                title = getString(R.string.title_articles);
                break;
            case 2:
                fragment = new TripFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Trip", "Story" };

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0: return new TripFragment();
                case 1: return new ArticleFragment();

                default: return new TripFragment();
            }
        }

    }

}
