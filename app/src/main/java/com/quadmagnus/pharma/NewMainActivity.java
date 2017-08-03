package com.quadmagnus.pharma;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.quadmagnus.pharma.Utility.BadgeDrawable;
import com.quadmagnus.pharma.fragment.CartFragment;
import com.quadmagnus.pharma.fragment.ContactToFragment;
import com.quadmagnus.pharma.fragment.FeedBackFragment;
import com.quadmagnus.pharma.fragment.HomeFragment;
import com.quadmagnus.pharma.fragment.NotificationFragment;
import com.quadmagnus.pharma.fragment.ProfileFragment;
import com.quadmagnus.pharma.fragment.SettingsFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

/**
 * Created by mohsin on 29/6/17.
 */

public class NewMainActivity extends FragmentActivity implements View.OnClickListener {


    private ResideMenu resideMenu;
    private NewMainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    private ResideMenuItem itemFeedBack;
    private ResideMenuItem itemContactTo;
    private ResideMenuItem itemNotification;
    private ResideMenuItem itemOrder;

    private Button btnCart;
    private TextView txtTitle, txtBadgeCartCount, txtBadgeNotificationCount;

    private FrameLayout mainFrameLayout;

    Fragment myFragment;

    /**
     * Called when the activity is first created.S
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        mContext = this;
        setUpMenu();


        if (savedInstanceState == null)
            changeFragment(new HomeFragment());

        if (hasNavBar(getApplicationContext())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int navBarHeight = getNavigationBarHeight();
                Log.e("navBarHeight ", "" + navBarHeight);
                findViewById(R.id.main_fragment).setPadding(0, 0, 0, navBarHeight);
            }
        }

    }

    private void setUpMenu() {

        mainFrameLayout = (FrameLayout) findViewById(R.id.main_fragment);

        /*button Cart*/
        btnCart = (Button) findViewById(R.id.btn_cart);
        //badge count
        txtBadgeCartCount = (TextView) findViewById(R.id.txt_bagde_cart);
        txtBadgeNotificationCount = (TextView) findViewById(R.id.txt_badge_notification);

        txtTitle = (TextView) findViewById(R.id.txt_title);
        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);


        // create menu items;
        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "My Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Setting");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Logout");


        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

        // create menu items;

        itemNotification = new ResideMenuItem(this, R.drawable.icon_calendar, "Notification");
        itemOrder = new ResideMenuItem(this, R.drawable.ic_message, "Order");

        itemFeedBack = new ResideMenuItem(this, R.drawable.icon_home, "FeedBack");
        itemContactTo = new ResideMenuItem(this, R.drawable.icon_profile, "Connect To ");


        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        itemFeedBack.setOnClickListener(this);
        itemContactTo.setOnClickListener(this);
        itemNotification.setOnClickListener(this);
        itemOrder.setOnClickListener(this);


        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);


        resideMenu.addMenuItem(itemContactTo, ResideMenu.DIRECTION_RIGHT);
        /*resideMenu.addMenuItem(itemNotification, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemOrder, ResideMenu.DIRECTION_RIGHT);
       */
        resideMenu.addMenuItem(itemFeedBack, ResideMenu.DIRECTION_RIGHT);

        /*resideMenu.addMenuItem(itemTerms, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemPrivacy, ResideMenu.DIRECTION_RIGHT);*/


        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.rl_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.rl_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);


            }
        });

        //Notificaiton Click Event
        findViewById(R.id.btn_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new NotificationFragment());
            }
        });

        //Cart Click Event
        findViewById(R.id.btn_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(NewMainActivity.this, NotificationFragment.class);
                startActivity(intent);*/
                changeFragment(new CartFragment());
            }
        });

        /*animation*/


    }

    /*  @Override
      public boolean dispatchTouchEvent(MotionEvent ev) {
          return resideMenu.dispatchTouchEvent(ev);
      }
  */


    @Override
    public void onClick(View view) {

        if (view == itemHome) {
            changeFragment(new HomeFragment());
            txtTitle.setText("Pharma EnterPrise");
            itemNotification.setIcon(R.drawable.ic_message);
        } else if (view == itemProfile) {
            txtTitle.setText("Profile");
            changeFragment(new ProfileFragment());
            itemNotification.setIcon(R.drawable.icon_settings);
        } else if (view == itemCalendar) {
            txtTitle.setText("Setting");
            changeFragment(new SettingsFragment());
        } else if (view == itemSettings) {
            changeFragment(new SettingsFragment());
        } else if (view == itemContactTo) {
            changeFragment(new ContactToFragment());
        } /*else if (view == itemNotification) {
            changeFragment(new TermsFragment());
        } else if (view == itemOrder) {
            changeFragment(new PrivacyFragment());
        }*/ else if (view == itemFeedBack) {
            changeFragment(new FeedBackFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();


        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment, targetFragment, "fragment");
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(targetFragment.getClass().getName());
        transaction.commit();

        myFragment = targetFragment;

    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu() {
        return resideMenu;
    }


    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        return true;
    }


    private int getNavigationBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

  /*  public boolean hasNavBar(Resources resources) {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }*/


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean hasNavBar(Context context) {
        Point realSize = new Point();
        Point screenSize = new Point();
        boolean hasNavBar = false;
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        realSize.x = metrics.widthPixels;
        realSize.y = metrics.heightPixels;
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        if (realSize.y != screenSize.y) {
            int difference = realSize.y - screenSize.y;
            int navBarHeight = 0;
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                navBarHeight = resources.getDimensionPixelSize(resourceId);
            }
            if (navBarHeight != 0) {
                if (difference == navBarHeight) {
                    hasNavBar = true;
                }
            }

        }
        return hasNavBar;

    }

    @Override
    public void onBackPressed() {

        isCurrentFragment(myFragment);

    }

    public Fragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        Log.e("tag===>>", "" + tag);
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public boolean isCurrentFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            Log.e("HomeFragment", "HomeFragment");
            openCloseDiolog();
//            this.finishAffinity();
            return true;
        } else
            Log.e("else", "else");
        Intent intent = new Intent(this, NewMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return false;
    }

    private void openCloseDiolog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}


