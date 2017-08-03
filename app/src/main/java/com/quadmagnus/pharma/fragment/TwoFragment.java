package com.quadmagnus.pharma.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.Utility.CustomEditText;
import com.quadmagnus.pharma.adapter.ClassicSearchAdapter;
import com.quadmagnus.pharma.adapter.PatentSearchAdapter;
import com.quadmagnus.pharma.model.classicsearchmodel.ClassicSearchModel;
import com.quadmagnus.pharma.model.patentsearchmodel.PatentSearchModel;

import java.util.ArrayList;
import java.util.Locale;


public class TwoFragment extends Fragment {


    RelativeLayout rlDetails;
    CustomEditText inputSearch;
    TextView txtTitle, txtCompany, txtQuantity, txtScName;
    // List view
    public static ListView lv;
    PatentSearchAdapter adapter;
    ArrayList<PatentSearchModel> arraylist = new ArrayList<PatentSearchModel>();

    View rootView;

    // Listview Data
    String products[] = {"Acetaminophen", "Adderall", "Alprazolam", "Amitriptyline", "Amlodipine",
            "Amoxicillin", "Ativan", "Atorvastatin", "Azithromycin", "Ciprofloxacin",
            "Citalopram", "Clindamycin", "Clonazepam", "Codeine", "Cyclobenzaprine"};


    String comapany[] = {"Cipla", "Zydus", "SunPharma", "Cipla", "Zydus",
            "SunPharma", "Zydus", "SunPharma", "Zydus", "Cipla",
            "SunPharma", "Cipla", "SunPharma", "Zydus", "Cipla"};


    String quantity[] = {"20 GM", "25 GM", "30 GM", "50 GM", "100 GM",
            "125 GM", "150 GM", "200 GM", "20 GM", "250 GM",
            "35 GM", "100 GM", "500 GM", "40 GM", "60 GM"};


    public TwoFragment() {
        // Required empty public constructor
    }


    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static TwoFragment newInstance(int page, String title) {
        TwoFragment fragmentFirst = new TwoFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_two, container, false);

        setUpViews();

        // Pass results to ListViewAdapter Class
        adapter = new PatentSearchAdapter(getActivity(), arraylist);
        // Binds the Adapter to the ListView
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PatentSearchModel patentsearchmodel = (PatentSearchModel) parent.getItemAtPosition(position);

                String stringName = patentsearchmodel.getName();
                String stringQuantity = patentsearchmodel.getQuantity();
                String stringCompany = patentsearchmodel.getCompany();


                txtTitle.setText(stringName);
                txtQuantity.setText(stringQuantity);
                txtCompany.setText(stringCompany);

                lv.setVisibility(View.GONE);
                inputSearch.setText(stringName.trim());
                rlDetails.setVisibility(View.VISIBLE);
                hideKeyBoard();
            }
        });


        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub


                if (lv.getVisibility() != View.VISIBLE)
                    lv.setVisibility(View.VISIBLE);
                String text = inputSearch.getText().toString().toUpperCase(Locale.getDefault());
                adapter.filter(text);

            }
        });

        return rootView;
    }

    private void setUpViews() {

        Log.e("Two", "setUpViews");

        rlDetails = (RelativeLayout) rootView.findViewById(R.id.rl_details);
        inputSearch = (CustomEditText) rootView.findViewById(R.id.inputSearch);
        lv = (ListView) rootView.findViewById(R.id.list_view_patent);

        txtTitle = (TextView) rootView.findViewById(R.id.txt_title_patent);
        txtCompany = (TextView) rootView.findViewById(R.id.txt_comapny_patent);
        txtQuantity = (TextView) rootView.findViewById(R.id.txt_package_patent);
        txtScName = (TextView) rootView.findViewById(R.id.txt_sc_name_patent);


        for (int i = 0; i < products.length; i++) {
            PatentSearchModel wp = new PatentSearchModel(products[i], comapany[i], quantity[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        inputSearchClickEvent();

    }

    private void inputSearchClickEvent() {

        inputSearch.setDrawableClickListener(new CustomEditText.DrawableClickListener() {


            public void onClick(DrawablePosition target) {
                switch (target) {
                    case LEFT:
                        //Do something here
//                        Toast.makeText(getActivity(), "LEFT", Toast.LENGTH_SHORT).show();
                        break;

                    case RIGHT:
                        //Do something here
//                        Toast.makeText(getActivity(), "Right", Toast.LENGTH_SHORT).show();

                        lv.setVisibility(View.GONE);
                        inputSearch.setText("");
                        rlDetails.setVisibility(View.GONE);

                        if (TextUtils.isEmpty(inputSearch.getText())) {
                            lv.setVisibility(View.GONE);
                        }

                        hideKeyBoard();
                        break;

                    default:
                        break;
                }
            }

        });
    }


    public void hideKeyBoard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
