package com.quadmagnus.pharma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.quadmagnus.pharma.DraftOrderActivity;
import com.quadmagnus.pharma.MyOrderActivity;
import com.quadmagnus.pharma.NewMainActivity;
import com.quadmagnus.pharma.NewOrderActivity;
import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.SchemeActivity;
import com.quadmagnus.pharma.SearchActivity;
import com.quadmagnus.pharma.adapter.SlidingImageAdapter;
import com.special.ResideMenu.ResideMenu;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements View.OnClickListener {


    private ResideMenu resideMenu;

    //view pager Item
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {
            R.drawable.banner1, R.drawable.banner3,
            R.drawable.banner1, R.drawable.banner3};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    ImageView rlNewOrder, rlMyOrder, rlDraftOrder, rlAddDistridbutor, rlScheme, rlSearch;
    View rootView;

//    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home1, container, false);
        setUpViews();
        bindPageAdapter();
        return rootView;
    }

    private void bindPageAdapter() {

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        CirclePageIndicator indicator = (CirclePageIndicator)
                rootView.findViewById(R.id.indicator);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(), ImagesArray));
        indicator.setViewPager(mPager);


        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);
        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 4000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }

        });

        mPager.setOffscreenPageLimit(ImagesArray.size());
    }

    private void setUpViews() {
        NewMainActivity parentActivity = (NewMainActivity) getActivity();
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        resideMenu = parentActivity.getResideMenu();


        // add gesture operation's ignored views
        RelativeLayout coordinatorlayout = (RelativeLayout) rootView.findViewById(R.id.cordinate_layout1);
        resideMenu.addIgnoredView(coordinatorlayout);

        //Menu Intialization
        rlNewOrder = (ImageView) rootView.findViewById(R.id.rl_new_order1);
        rlMyOrder = (ImageView) rootView.findViewById(R.id.rl_my_order1);
        rlDraftOrder = (ImageView) rootView.findViewById(R.id.rl_draft_order1);
     /*   rlAddDistridbutor = (ImageView) rootView.findViewById(R.id.rl_add_distributor);*/
        rlScheme = (ImageView) rootView.findViewById(R.id.rl_scheme1);
        rlSearch = (ImageView) rootView.findViewById(R.id.rl_search1);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);


        rlNewOrder.setOnClickListener(this);
        rlMyOrder.setOnClickListener(this);
        rlDraftOrder.setOnClickListener(this);
     /*   rlAddDistridbutor.setOnClickListener(this);*/
        rlScheme.setOnClickListener(this);
        rlSearch.setOnClickListener(this);




    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //New Order
            case R.id.rl_new_order1:
               /* changeFragment(new NewOrderFragment());*/
                Intent i = new Intent(getActivity(), NewOrderActivity.class);
                startActivity(i);
                break;

            //My Order
            case R.id.rl_my_order1:
            /*    changeFragment(new MyOrderFragment());*/
                Intent j = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(j);
                break;

            //Draft Order
            case R.id.rl_draft_order1:
          /*      changeFragment(new DraftOrderFragment());*/
                Intent k = new Intent(getActivity(), DraftOrderActivity.class);
                startActivity(k);
                break;

        /*    //Add Distributor
            case R.id.rl_add_distributor:
                changeFragment(new AddDistributorFragment());
                break;
*/
            //Scheme
            case R.id.rl_scheme1:
                /*changeFragment(new SchemeFragment());*/
                Intent l = new Intent(getActivity(), SchemeActivity.class);
                startActivity(l);
                break;

            //Search
            case R.id.rl_search1:
                /*changeFragment(new SearchFragment());*/
                Intent m = new Intent(getActivity(), SearchActivity.class);
                startActivity(m);
                break;
        }
    }


    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cordinate_layout1, targetFragment, "fragment");
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}
