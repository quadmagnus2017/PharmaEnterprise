package com.quadmagnus.pharma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quadmagnus.pharma.NewMainActivity;
import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.SignupActivity;


/**
 * Created by mohsin on 25/7/17.
 */

public class ThirdFragmentRetailer extends Fragment {

    private String title;
    private int page;

    TextView submitThirdFragment;
    EditText drugLicenseNumber, cstNumber, panNumber, gstNumber, udrNumber;
    TextInputLayout inputLayoutDrugLicenseNumber, inputLayoutCstNumber, inputLayoutPanNumber,
            inputLayoutGstNumber, inputLayoutUdrNumber;

    // newInstance constructor for creating fragment with arguments

    public static ThirdFragment newInstance(int page, String title) {
        ThirdFragment fragmentFirst = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
*/
        Log.e("page", "" + page);
        Log.e("title ", "" + title);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_retailer, container, false);


        drugLicenseNumber = (EditText) view.findViewById(R.id.input_drug_license_number);
        cstNumber = (EditText) view.findViewById(R.id.input_cst);
        panNumber = (EditText) view.findViewById(R.id.input_pan_number);
        gstNumber = (EditText) view.findViewById(R.id.input_gst);
        udrNumber = (EditText) view.findViewById(R.id.input_udr);

        inputLayoutDrugLicenseNumber = (TextInputLayout) view.findViewById(R.id.input_layout_drug_license_number);
        inputLayoutCstNumber = (TextInputLayout) view.findViewById(R.id.input_layout_cst);
        inputLayoutPanNumber = (TextInputLayout) view.findViewById(R.id.input_layout_pan_number);
        inputLayoutGstNumber = (TextInputLayout) view.findViewById(R.id.input_layout_gst);
        inputLayoutUdrNumber = (TextInputLayout) view.findViewById(R.id.input_layout_udr);
        submitThirdFragment = (TextView) view.findViewById(R.id.submit_third_fragment);

        drugLicenseNumber.addTextChangedListener(new ThirdFragmentRetailer.MyTextWatcher(drugLicenseNumber));
        cstNumber.addTextChangedListener(new ThirdFragmentRetailer.MyTextWatcher(cstNumber));
        panNumber.addTextChangedListener(new ThirdFragmentRetailer.MyTextWatcher(panNumber));
        gstNumber.addTextChangedListener(new ThirdFragmentRetailer.MyTextWatcher(gstNumber));
        udrNumber.addTextChangedListener(new ThirdFragmentRetailer.MyTextWatcher(udrNumber));


        return view;
    }

    /**
     * Validate Form
     */
    private void submitForm() {

        if (!validateClinicName()) {
            submitThirdFragment.setClickable(false);
            submitThirdFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            return;
        }

      /*  Intent intent = new Intent(this, FirstFragment.class);
        startActivity(intent);*/
        else {
            ((SignupActivity) getActivity()).setcheckindicator(15);
            submitThirdFragment.setClickable(true);
            submitThirdFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button_green));
        }
        submitThirdFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewMainActivity.class);
                startActivity(i);
            }
        });
        Toast.makeText(getContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private boolean validateClinicName() {
        if (drugLicenseNumber.getText().toString().trim().isEmpty()) {
            inputLayoutDrugLicenseNumber.setError(getString(R.string.err_msg_name));
          /*  requestFocus(FirstName);*/
            return false;
        } else {
            inputLayoutDrugLicenseNumber.setErrorEnabled(false);
            ((SignupActivity) getActivity()).setindicator(3);
        }

        return true;
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }


        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_drug_license_number:
                    validateClinicName();

                    break;


            }
            submitForm();

        }

    }

}
