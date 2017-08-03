package com.quadmagnus.pharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;

/**
 * Created by mohsin on 8/7/17.
 */

public class RegisterDoctorActivity extends BaseActivity {


    Button btnRegister;
    private NestedScrollView NestedScroll;
    private TextInputLayout inputLayoutUploadPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_doctors);
        setUpViews();
        focusOnView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDoctorActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private final void focusOnView() {
        NestedScroll.post(new Runnable() {
            @Override
            public void run() {
                NestedScroll.scrollTo(0, inputLayoutUploadPhoto.getBottom());
            }
        });
    }

    private void setUpViews() {
        btnRegister = (Button) findViewById(R.id.btn_register);
        NestedScroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        inputLayoutUploadPhoto = (TextInputLayout) findViewById(R.id.input_layout_upload_photo);
    }
}
