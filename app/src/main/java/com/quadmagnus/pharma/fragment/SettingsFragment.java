package com.quadmagnus.pharma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quadmagnus.pharma.PrivatePolicyActivity;
import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.TermsConditionAcitivity;


public class SettingsFragment extends Fragment {


    RelativeLayout rlTermsCondiction, rlPrivatePolicy;
    View rlView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rlView = inflater.inflate(R.layout.fragment_settings, container, false);

        //initView
        rlTermsCondiction = (RelativeLayout) rlView.findViewById(R.id.rl_terms);
        rlPrivatePolicy = (RelativeLayout) rlView.findViewById(R.id.rl_private_policy);

        rlTermsCondiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsConditionAcitivity.class);
                startActivity(intent);
            }
        });

        rlPrivatePolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrivatePolicyActivity.class);
                startActivity(intent);


            }
        });

        return rlView;

    }


}
