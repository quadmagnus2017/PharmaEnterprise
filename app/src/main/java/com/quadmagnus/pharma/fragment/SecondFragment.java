package com.quadmagnus.pharma.fragment;

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

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.SignupActivity;

/**
 * Created by mohsin on 25/7/17.
 */

public class SecondFragment extends Fragment {

    TextView nextSecondFragment;
    EditText clinicName, mobileNumber, address, pincode;
    TextInputLayout inputLayoutClinicName, inputLayoutMobileNumber, inputLayoutAddress, inputlayoutPincode;

    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments

    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragmentFirst = new SecondFragment();
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
    /*    page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
*/
        Log.e("page", "" + page);
        Log.e("title ", "" + title);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ((SignupActivity) getActivity()).setindicator(0);
        nextSecondFragment = (TextView) view.findViewById(R.id.next_second_fragment);
        clinicName = (EditText) view.findViewById(R.id.input_clinic_name);
        mobileNumber = (EditText) view.findViewById(R.id.input_mobile_number);
        address = (EditText) view.findViewById(R.id.input_address);
        pincode = (EditText) view.findViewById(R.id.input_pincode);
        inputLayoutClinicName = (TextInputLayout) view.findViewById(R.id.input_layout_clinic_name);
        inputLayoutMobileNumber = (TextInputLayout) view.findViewById(R.id.input_layout_mobile);
        inputLayoutAddress = (TextInputLayout) view.findViewById(R.id.input_layout_address);
        inputlayoutPincode = (TextInputLayout) view.findViewById(R.id.input_layout_pincode);

        clinicName.addTextChangedListener(new SecondFragment.MyTextWatcher(clinicName));
        mobileNumber.addTextChangedListener(new SecondFragment.MyTextWatcher(mobileNumber));
        address.addTextChangedListener(new SecondFragment.MyTextWatcher(address));
        pincode.addTextChangedListener(new SecondFragment.MyTextWatcher(pincode));
        nextSecondFragment.setClickable(false);

        return view;


    }


    /**
     * Validate Form
     */
    private void submitForm() {

        if (!validateClinicName()) {
            nextSecondFragment.setClickable(false);
            nextSecondFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            return;
        }

        if (!validateMobileNumber()) {
            nextSecondFragment.setClickable(false);
            nextSecondFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            return;
        }
        if (!validateAddress()) {
            nextSecondFragment.setClickable(false);
            nextSecondFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            return;
        }
        if (!validatePincode()) {
            nextSecondFragment.setClickable(false);
            nextSecondFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            return;
        }
      /*  Intent intent = new Intent(this, FirstFragment.class);
        startActivity(intent);*/

        else {
            ((SignupActivity) getActivity()).setcheckindicator(15);
            nextSecondFragment.setClickable(true);
            nextSecondFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button_green));

        }
        nextSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity) getActivity()).changefragment(1);
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
        if (clinicName.getText().toString().trim().isEmpty()) {
            inputLayoutClinicName.setError(getString(R.string.err_msg_name));
          /*  requestFocus(FirstName);*/
            return false;
        } else {
            inputLayoutClinicName.setErrorEnabled(false);
            ((SignupActivity) getActivity()).setindicator(3);
        }

        return true;
    }

    private boolean validateMobileNumber() {


        if (mobileNumber.getText().toString().trim().isEmpty()) {
            inputLayoutMobileNumber.setError(getString(R.string.err_msg_mobile_number));
        /*    requestFocus(LastName);*/
            return false;
        }
        if (mobileNumber.getText().toString().trim().length() != 10) {
            inputLayoutMobileNumber.setError(getString(R.string.err_msg_mobile_number));
        /*    requestFocus(LastName);*/
            return false;

        }
        else{
            ((SignupActivity) getActivity()).setindicator(6);
            inputLayoutMobileNumber.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateAddress() {
        if (address.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(getString(R.string.err_msg_address));
        /*    requestFocus(LastName);*/
            return false;
        } else {
            ((SignupActivity) getActivity()).setindicator(9);
            inputLayoutAddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePincode() {
        if (pincode.getText().toString().trim().isEmpty()) {
            inputlayoutPincode.setError(getString(R.string.err_msg_pincode));
            /*requestFocus(Password);*/
            return false;
        }
        if (pincode.getText().toString().trim().length() != 6) {
            inputLayoutMobileNumber.setError(getString(R.string.err_msg_mobile_number));
        /*    requestFocus(LastName);*/
            return false;

        }
        else {
            ((SignupActivity) getActivity()).setindicator(12);
            inputlayoutPincode.setErrorEnabled(false);
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
                case R.id.input_clinic_name:
                    validateClinicName();
                    break;
                case R.id.input_address:
                    validateAddress();
                    break;
                case R.id.input_mobile_number:
                    Log.e("mobile number",""+mobileNumber.getText().toString().length());
                    validateMobileNumber();
                    break;
                case R.id.input_pincode:
                    validatePincode();

                    break;

            }
            submitForm();

        }

    }

}
