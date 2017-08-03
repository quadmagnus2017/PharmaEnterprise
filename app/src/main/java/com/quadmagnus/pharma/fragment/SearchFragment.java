package com.quadmagnus.pharma.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quadmagnus.pharma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsin on 4/7/17.
 */

public class SearchFragment extends Fragment {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_custom_view_icon_text_tabs, container, false);
        setUpViews();
        return rootView;
    }


    private void setUpViews() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled :", "" + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected=", "" + position);
                switch (position) {

                    case 0:
                        OneFragment.lv.setVisibility(View.GONE);
                        ThreeFragment.lv.setVisibility(View.GONE);

                        break;

                    case 2:
                        OneFragment.lv.setVisibility(View.GONE);
                        ThreeFragment.lv.setVisibility(View.GONE);
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText("Classic");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_classic, 0, 0);
        tabOne.setBackground(getResources().getDrawable(R.drawable.background_button));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Patented");
        tabTwo.setBackground(getResources().getDrawable(R.drawable.background_button));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_patented, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabThree.setText("Company");
        tabThree.setBackground(getResources().getDrawable(R.drawable.background_button));
        tabThree.setPadding(0, 5, 0, 0);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_company, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            /*Log.e("get Position===>>", "" + mFragmentList.get(position));
            return mFragmentList.get(position);*/

            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return OneFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return TwoFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ThreeFragment.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return 3;

        }

        public void addFrag(Fragment fragment, String title) {
            Log.e("get title====", "" + title);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }


}
