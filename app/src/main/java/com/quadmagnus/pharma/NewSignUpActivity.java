package com.quadmagnus.pharma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by mohsin on 25/7/17.
 */

public class NewSignUpActivity extends BaseActivity implements View.OnClickListener {

    FragmentPagerAdapter adapterViewPager;
    Button btnSubmitt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_up);
        initUiWidget();


    }

    private void initUiWidget() {
          /* Inside the activity */
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up);
        setSupportActionBar(toolbar);

        btnSubmitt = (Button) findViewById(R.id.btn_submitt);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
   /*     adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());*/
        vpPager.setAdapter(adapterViewPager);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitt:
        }
    }

  /*  public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(0, "first");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return SecondFragment.newInstance(1, "second");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ThirdFragment.newInstance(2, "third");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }*/
}
