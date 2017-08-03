package com.quadmagnus.pharma;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by mohsin on 29/6/17.
 */

public class SelectTypeActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout llDoctor, llRetailer, llInstitute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initViews();


    }

    private void initViews() {
        llDoctor = (LinearLayout) findViewById(R.id.ll_doctor);
        llRetailer = (LinearLayout) findViewById(R.id.ll_retailer);
        llInstitute = (LinearLayout) findViewById(R.id.ll_institute);

        llDoctor.setOnClickListener(this);
        llRetailer.setOnClickListener(this);
        llInstitute.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.ll_doctor:
                intent = new Intent(this, SignupActivity.class);
                intent.putExtra("type","doc");
                startActivity(intent);
                break;

            case R.id.ll_retailer:
                intent = new Intent(this, SignupActivity.class);
                intent.putExtra("type","ret");

                startActivity(intent);
                break;

            case R.id.ll_institute:
                intent = new Intent(this, SignupActivity.class);
                intent.putExtra("type","ins");
                startActivity(intent);
                break;
        }

    }
}
