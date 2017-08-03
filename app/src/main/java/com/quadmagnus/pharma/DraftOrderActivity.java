package com.quadmagnus.pharma;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by premsagar on 2/8/17.
 */

public class DraftOrderActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_order);

        toolbar = (Toolbar) findViewById(R.id.toolbar_draftorder);
        initToolbar();
    }
    private void initToolbar() {

        toolbar.setTitle("Draft Order");
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
    }
}
