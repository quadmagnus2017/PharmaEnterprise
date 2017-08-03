package com.quadmagnus.pharma.fragment;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import com.quadmagnus.pharma.adapter.CompanySearchAdapter;
import com.quadmagnus.pharma.adapter.CompanySearchRecyclerAdapter;
import com.quadmagnus.pharma.adapter.ExpandableListAdapterProduct;
import com.quadmagnus.pharma.model.companysearchmodel.CompanySearchModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ThreeFragment extends Fragment {

    // List view
    public static ListView lv;
    CustomEditText inputSearch;
    //Textview
    LinearLayout llText;
    TextView txtTitleProduct;
    RecyclerView rv;

    ArrayList<CompanySearchModel> arraylist = new ArrayList<CompanySearchModel>();

    ExpandableListAdapterProduct listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    CompanySearchAdapter adapter;
    CompanySearchRecyclerAdapter adapter1;

    // Listview Data
    String products[] = {"Acetaminophen", "Adderall", "Alprazolam", "Amitriptyline", "Amlodipine",
            "Amoxicillin", "Ativan", "Atorvastatin", "Azithromycin", "Ciprofloxacin",
            "Citalopram", "Clindamycin", "Clonazepam", "Codeine", "Cyclobenzaprine"};

    View rootView;


    RelativeLayout rl_main;

//    String products[] = {"1", "3", "2", "4", "5"};


    public ThreeFragment() {
        // Required empty public constructor


    }

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static ThreeFragment newInstance(int page, String title) {
        ThreeFragment fragmentFirst = new ThreeFragment();
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
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_three, container, false);
        rl_main = (RelativeLayout) rootView.findViewById(R.id.rl_main);
        setUpViews();

        Log.e("onCreateView", "Three");



        // Pass results to ListViewAdapter Class
        adapter = new CompanySearchAdapter(getActivity(), arraylist);
       /* adapter1 = new CompanySearchRecyclerAdapter(arraylist);*/
        // Binds the Adapter to the ListView

       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());*/
        lv.setAdapter(adapter);
       /* rv.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();*/
        adapter.notifyDataSetChanged();



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


                /*if (lv.getVisibility() != View.VISIBLE)
                    lv.setVisibility(View.VISIBLE);*/

                rl_main.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);
                /*rv.setVisibility(View.VISIBLE);*/

                String text = inputSearch.getText().toString().toUpperCase(Locale.getDefault());
                adapter.filter(text);
                /*adapter1.filter(text);*/

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanySearchModel companysearchmodel = (CompanySearchModel) parent.getItemAtPosition(position);
                String stringName = companysearchmodel.getName();


                llText.setVisibility(View.VISIBLE);
                txtTitleProduct.setVisibility(View.VISIBLE);


                inputSearch.setText(stringName.trim());
                txtTitleProduct.setText(stringName);

                rl_main.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                rv.setVisibility(View.GONE);

                expListView.setVisibility(View.VISIBLE);

                hideKeyBoard();
            }
        });

      /*  rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), rv, ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/
        return rootView;
    }

    private void setUpViews() {

        Log.e("Three", "setUpViews");

        // get the listview
        lv = (ListView) rootView.findViewById(R.id.list_view_comapny);
        rv = (RecyclerView) rootView.findViewById(R.id.recycler_view_comapny);
        txtTitleProduct = (TextView) rootView.findViewById(R.id.txt_title_product);
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExpProduct);

        llText = (LinearLayout) rootView.findViewById(R.id.ll_text_three);
        inputSearch = (CustomEditText) rootView.findViewById(R.id.inputSearch);


        for (int i = 0; i < products.length; i++) {
            CompanySearchModel wp = new CompanySearchModel(products[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapterProduct(getActivity(), listDataHeader, listDataChild);
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

                        lv.setVisibility(View.GONE);
                        /*rv.setVisibility(View.GONE);*/
                        inputSearch.setText("");
                        llText.setVisibility(View.GONE);
                        expListView.setVisibility(View.GONE);

                        if (TextUtils.isEmpty(inputSearch.getText())) {
                            lv.setVisibility(View.GONE);
                            /*rv.setVisibility(View.GONE);*/
                        }
                        hideKeyBoard();
                        break;

                    default:
                        break;
                }
            }

        });
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding child data
        listDataHeader.add("Acadia Pharmaceuticals");
        listDataHeader.add("Cipla");
        listDataHeader.add("Zydus Cadila");


        // Adding group child data
        listDataHeader.add("SunPharma");
        listDataHeader.add("Cipla");
        listDataHeader.add("Sustenx");


        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Aceclofenac");
        top250.add("Acetaminophen");
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
        listDataChild.put(listDataHeader.get(3), top250);
        listDataChild.put(listDataHeader.get(4), Cipla);
        listDataChild.put(listDataHeader.get(5), Zydus);

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


}

