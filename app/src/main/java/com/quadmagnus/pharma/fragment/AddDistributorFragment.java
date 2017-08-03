package com.quadmagnus.pharma.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quadmagnus.pharma.R;

/**
 * Created by mohsin on 4/7/17.
 */

public class AddDistributorFragment extends Fragment {


    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_distributor, container, false);
        setUpViews();
        return rootView;
    }

    private void setUpViews() {
    }
}
