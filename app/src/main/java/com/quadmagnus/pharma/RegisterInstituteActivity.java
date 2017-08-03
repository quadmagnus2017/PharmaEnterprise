package com.quadmagnus.pharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;

/**
 * Created by mohsin on 29/6/17.
 */

public class RegisterInstituteActivity extends BaseActivity {


    private Button btnSignUp;
    private NestedScrollView NestedScroll;
    private TextInputLayout inputLayoutUploadPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_registration);
        setUpViews();

        performActionSignUp();
        focusOnView();
//        setUpToolBar("SignUp");
    }


    private final void focusOnView() {
        NestedScroll.post(new Runnable() {
            @Override
            public void run() {
                NestedScroll.scrollTo(0, inputLayoutUploadPhoto.getBottom());
            }
        });
    }


    private void performActionSignUp() {

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterInstituteActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setUpViews() {
        btnSignUp = (Button) findViewById(R.id.btn_register);
        NestedScroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        inputLayoutUploadPhoto = (TextInputLayout) findViewById(R.id.input_layout_upload_photo);
    }

}
