package com.quadmagnus.pharma.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.SignupActivity;

/**
 * Created by mohsin on 25/7/17.
 */

public class FirstFragment extends Fragment {

    private String title,validated;
    private int page,val;
    TextView nextFirstFragment;
    EditText FirstName, LastName, UserName, Password, ConfirmPassword;
    TextInputLayout inputLayoutFirstname, inputLayoutLastName, inputLayoutUserName, inputlayoutPassword,
            inputLayoutConfirmPassword;
    ImageView  addButton;
    CircularImageView profilePhoto;
    final int REQUEST_CODE_GALLERY = 999;
    // newInstance constructor for creating fragment with arguments

    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
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
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ((SignupActivity)getActivity()).setindicator(0);

        nextFirstFragment = (TextView) view.findViewById(R.id.next_first_fragment);
        FirstName = (EditText) view.findViewById(R.id.input_firstname);
        LastName = (EditText) view.findViewById(R.id.input_lastname);
        UserName = (EditText) view.findViewById(R.id.input_email);
        Password = (EditText) view.findViewById(R.id.input_password);
        ConfirmPassword = (EditText) view.findViewById(R.id.input_confirm_password);
        inputLayoutFirstname = (TextInputLayout) view.findViewById(R.id.input_layout_fname);
        inputLayoutLastName = (TextInputLayout) view.findViewById(R.id.input_layout_lname);
        inputLayoutUserName = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputlayoutPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword = (TextInputLayout) view.findViewById(R.id.input_layout_confirm_password);
        profilePhoto = (CircularImageView) view.findViewById(R.id.profile_image1);
        addButton = (ImageView) view.findViewById(R.id.add_button);

        FirstName.addTextChangedListener(new MyTextWatcher(FirstName));
        LastName.addTextChangedListener(new MyTextWatcher(LastName));
        UserName.addTextChangedListener(new MyTextWatcher(UserName));
        Password.addTextChangedListener(new MyTextWatcher(Password));
        ConfirmPassword.addTextChangedListener(new MyTextWatcher(ConfirmPassword));
        FirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        UserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        ConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nextFirstFragment.setClickable(false);

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE_GALLERY);
            }
        });


        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            Log.e("picturepath==>>>",""+picturePath);
            Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(picturePath), 200,200, true);
            profilePhoto.setImageBitmap(bitmap);
            addButton.setVisibility(View.GONE);
       /*     btn_set.setEnabled(true);*/
            cursor.close();
        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    /**
     * Validate Form
     */
    private void submitForm() {

        if (!validateEmail()) {
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            nextFirstFragment.setClickable(true);
            return;
        }

        if(!validateFirstName()){
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            nextFirstFragment.setClickable(true);
            return;
        }
        if(!validateLastName()){
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            nextFirstFragment.setClickable(true);
            return;
        }
        if (!validatePassword()) {
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            nextFirstFragment.setClickable(true);
            return;
        }
        if (!validateConfirmPassword()) {
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button));
            nextFirstFragment.setClickable(true);
            return;
        }
      /*  Intent intent = new Intent(this, FirstFragment.class);
        startActivity(intent);*/
        else {
            val = 1;
            ((SignupActivity) getActivity()).setcheckindicator(15);
            nextFirstFragment.setClickable(true);
            nextFirstFragment.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_button_green));

        }
        nextFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()) {
                    Toast.makeText(getContext(), R.string.err_msg_email, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!validateFirstName()){
                    Toast.makeText(getContext(), R.string.err_msg_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!validateLastName()){
                    Toast.makeText(getContext(), R.string.err_msg_Last_name, Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!validatePassword()) {
                    Toast.makeText(getContext(), R.string.err_msg_password, Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!validateConfirmPassword()) {
                    Toast.makeText(getContext(), R.string.err_msg_confirm_password, Toast.LENGTH_SHORT).show();

                    return;
                }
                else{
                    ((SignupActivity) getActivity()).changefragment(1);
                }
            }
        });
        Toast.makeText(getContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private boolean validateFirstName() {
        if (FirstName.getText().toString().trim().isEmpty()) {
            inputLayoutFirstname.setError(getString(R.string.err_msg_name));
          /*  requestFocus(FirstName);*/
            return false;
        } else {
            inputLayoutFirstname.setErrorEnabled(false);
            ((SignupActivity)getActivity()).setindicator(3);
        }

        return true;
    }
    private boolean validateLastName() {
        if (LastName.getText().toString().trim().isEmpty()) {
            inputLayoutLastName.setError(getString(R.string.err_msg_name));
        /*    requestFocus(LastName);*/
            return false;
        } else {
            ((SignupActivity)getActivity()).setindicator(6);
            inputLayoutLastName.setErrorEnabled(false);
        }

        return true;
    }



    private boolean validateEmail() {
        String email = UserName.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutUserName.setError(getString(R.string.err_msg_email));
           /* requestFocus(UserName);*/
            return false;
        } else {
            ((SignupActivity)getActivity()).setindicator(9);
            inputLayoutUserName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (Password.getText().toString().trim().isEmpty()) {
            inputlayoutPassword.setError(getString(R.string.err_msg_password));
            /*requestFocus(Password);*/
            return false;
        } else {
            ((SignupActivity)getActivity()).setindicator(12);
            inputlayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPassword() {

        if (!(Password.getText().toString()).equals(ConfirmPassword.getText().toString())) {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_confirm_password));
            /*requestFocus(ConfirmPassword);*/
            return false;
        } else {
            ((SignupActivity)getActivity()).setindicator(15);
            inputLayoutConfirmPassword.setErrorEnabled(false);

        }

        return true;

    }

    private static boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return !TextUtils.isEmpty(email) && email.matches(emailPattern);
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
                case R.id.input_firstname:
                    validateFirstName();
                    break;
                case R.id.input_lastname:
                    validateLastName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_confirm_password:
                    validateConfirmPassword();

                    break;
            }

            submitForm();


        }

    }



}
