package com.quadmagnus.pharma.fragment;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.Utility.CustomEditText;
import com.quadmagnus.pharma.adapter.ClassicSearchAdapter;
import com.quadmagnus.pharma.adapter.ExpandableListAdapterClassic;
import com.quadmagnus.pharma.model.classicsearchmodel.ClassicSearchModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class OneFragment extends Fragment {

    // List view
    public static ListView lv;
    // Listview Adapter
//    ArrayAdapter<String> adapter;


    ClassicSearchAdapter adapter;

    ArrayList<ClassicSearchModel> arraylist = new ArrayList<ClassicSearchModel>();
    // Search EditText
    CustomEditText inputSearch;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    LinearLayout llText;
    TextView txtTitleClassic;


    ExpandableListAdapterClassic listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    // Listview Data
    String products[] = {"Acetaminophen", "Adderall", "Alprazolam", "Amitriptyline", "Amlodipine",
            "Amoxicillin", "Ativan", "Atorvastatin", "Azithromycin", "Ciprofloxacin",
            "Citalopram", "Clindamycin", "Clonazepam", "Codeine", "Cyclobenzaprine"};

    RelativeLayout rl_main;

    View rooView;

    public OneFragment() {
        // Required empty public constructor


    }


    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static OneFragment newInstance(int page, String title) {
        OneFragment fragmentFirst = new OneFragment();
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

        rooView = inflater.inflate(R.layout.fragment_one, container, false);
        rl_main = (RelativeLayout) rooView.findViewById(R.id.rl_main);
        setUpViews();


        // Pass results to ListViewAdapter Class
        adapter = new ClassicSearchAdapter(getActivity(), arraylist);
        // Binds the Adapter to the ListView
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
//                lv.setVisibility(View.VISIBLE);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub


                /*if (lv.getVisibility() != View.VISIBLE)
                    lv.setVisibility(View.VISIBLE);
*/

                rl_main.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);

                String text = inputSearch.getText().toString().toUpperCase(Locale.getDefault());
                adapter.filter(text);

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassicSearchModel classicsearchmodel = (ClassicSearchModel) parent.getItemAtPosition(position);
                String stringName = classicsearchmodel.getName();


                rl_main.setVisibility(View.GONE);

                llText.setVisibility(View.VISIBLE);
                txtTitleClassic.setVisibility(View.VISIBLE);

                inputSearch.setText(stringName.trim());
                txtTitleClassic.setText(stringName);

                lv.setVisibility(View.GONE);
                expListView.setVisibility(View.VISIBLE);


                hideKeyBoard();
            }
        });
        return rooView;
    }


    //to setUp Views
    private void setUpViews() {

        Log.e("One", "setUpViews");

        llText = (LinearLayout) rooView.findViewById(R.id.ll_text);
        lv = (ListView) rooView.findViewById(R.id.list_view);
        inputSearch = (CustomEditText) rooView.findViewById(R.id.inputSearch);
        txtTitleClassic = (TextView) rooView.findViewById(R.id.txt_title_classic);


        // get the listview
        expListView = (ExpandableListView) rooView.findViewById(R.id.lvExpClassic);


        //add data to listview

        for (int i = 0; i < products.length; i++) {

            ClassicSearchModel wp = new ClassicSearchModel(products[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }


        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapterClassic(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expanadableClick();

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
                        expListView.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        inputSearch.setText("");
                        llText.setVisibility(View.GONE);

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

    /*
        * Preparing the list data
        */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Cipla");
        listDataHeader.add("Zydus Cadila..");


        // Adding group child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Cipla");
        listDataHeader.add("Zydus Cadila..");


        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Aceclofenac");
        top250.add("Acetaminophen");
        top250.add("The Godfather: Part II");
        top250.add("Adderall");
        top250.add("Ibuprofen");
        top250.add("Zoloft");
        top250.add("Xanax");


        List<String> Cipla = new ArrayList<String>();
        Cipla.add(" Meloxicam");
        Cipla.add("Doxycycline");
        Cipla.add("Gabapentin");
        Cipla.add("Cymbalta");
        Cipla.add("Codeine");

        List<String> Zydus = new ArrayList<String>();
        Zydus.add("Loratadine");
        Zydus.add("Lexapro");
        Zydus.add("Omeprazole");
        Zydus.add("Wellbutrin");
        Zydus.add("Xanax");
        Zydus.add("Zoloft");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Cipla);
        listDataChild.put(listDataHeader.get(2), Zydus);

    }


    public int GetDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    public void setDimens() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Display display2 = getActivity().getWindowManager().getDefaultDisplay();
        int width2 = display2.getWidth();  // deprecated
        int height2 = display2.getHeight();  // deprecated
        //expListView.setIndicatorBounds(width2-GetDipsFromPixel(35), width2-GetDipsFromPixel(5)); //not works
        expListView.setIndicatorBounds(expListView.getRight() - 40, expListView.getWidth()); //not works

    }

    private void expanadableClick() {

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    expListView.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            int previousItem = -1;

            @Override
            public void onGroupCollapse(int groupPosition) {

                if (groupPosition != previousItem)
                    expListView.collapseGroup(previousItem);
                previousItem = groupPosition;

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

    @Override
    public void onResume() {
        super.onResume();
    }


}
