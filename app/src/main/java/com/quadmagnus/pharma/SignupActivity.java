package com.quadmagnus.pharma;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.quadmagnus.pharma.Utility.NonSwipeableViewPager;
import com.quadmagnus.pharma.fragment.FirstFragment;
import com.quadmagnus.pharma.fragment.SecondFragment;
import com.quadmagnus.pharma.fragment.SecondFragmentInstitute;
import com.quadmagnus.pharma.fragment.SecondFragmentRetailer;
import com.quadmagnus.pharma.fragment.ThirdFragment;
import com.quadmagnus.pharma.fragment.ThirdFragmentInstitute;
import com.quadmagnus.pharma.fragment.ThirdFragmentRetailer;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class SignupActivity extends AppCompatActivity {

    StepperIndicator indicator;
    NonSwipeableViewPager pager;
    String type;
    public static int int_items = 3;
    private static final int RC_REQUEST_READ = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        Log.e("type====>>>", "" + intent.getStringExtra("type"));
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up1);
      /*  toolbar.setTitle("Sign Up");
        toolbar.setTitleMarginStart(50);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.icn_back_arrow);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }*/
        toolbar.setTitle("Sign Up");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMargin(25, 25, 25, 25);
        toolbar.setBackgroundColor(getResources().getColor(
                R.color.colorSignIn));
        toolbar.setNavigationIcon(R.drawable.icn_back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();  // to go back  finish() will do your work.
                //mActionBar.setDisplayHomeAsUpEnabled(true);
                //mActionBar.setDisplayShowHomeEnabled(true);
            }
        });
        pager = (NonSwipeableViewPager) findViewById(R.id.pager);
        assert pager != null;
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        indicator = (StepperIndicator) findViewById(R.id.stepper_indicator);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);
        indicator.setStepCount(3);
        indicator.setAnimIndicatorRadius(25);


        indicator.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorSignIn));
        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                pager.setCurrentItem(step, true);

            }
        });
        doReadTask();


        if (pager.getCurrentItem() == 1 || pager.getCurrentItem() == 2 || pager.getCurrentItem() == 3) {
            indicator.setAnimIndicatorRadius(0);
        }




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_REQUEST_READ)
    public void doReadTask() {
        String perm = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (!EasyPermissions.hasPermissions(this, perm)) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_sms),
                    RC_REQUEST_READ, perm);
        } else {
            Toast.makeText(this, "TODO: Read Calendar", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean setindicator(float n) {
        pager.getCurrentItem();
        indicator.setAnimIndicatorRadius(n);
        return true;
    }

    public boolean setcheckindicator(float n) {
        indicator.setAnimIndicatorRadius(n);
        return true;
    }


    public boolean changefragment(int a) {
        int b = pager.getCurrentItem();
        pager.setCurrentItem(b + a);
        indicator.setAnimIndicatorRadius(0);
        return true;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    indicator.setAnimIndicatorRadius(0);
                    return new FirstFragment();
                case 1:
                    indicator.setAnimIndicatorRadius(0);
                    if (type.equals("doc")) {
                        return new SecondFragment();
                    } else if (type.equals("ret")) {
                        return new SecondFragmentRetailer();
                    } else if (type.equals("ins")) {
                        return new SecondFragmentInstitute();
                    }
                case 2:
                    indicator.setAnimIndicatorRadius(0);
                    if (type.equals("doc")) {
                        return new ThirdFragment();
                    } else if (type.equals("ret")) {
                        return new ThirdFragmentRetailer();
                    } else if (type.equals("ins")) {
                        return new ThirdFragmentInstitute();
                    }
            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            indicator.setAnimIndicatorRadius(25);
            return super.getItemPosition(object);

        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Common Details";
                case 1:
                    return "Personal Details";
                case 2:
                    return "Professional Details";
            }
            return null;
        }
    }
}
