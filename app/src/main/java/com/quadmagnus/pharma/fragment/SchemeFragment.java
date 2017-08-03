package com.quadmagnus.pharma.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.adapter.ExpandableListAdapter;
import com.quadmagnus.pharma.model.Continent;
import com.quadmagnus.pharma.model.Country;
import com.quadmagnus.pharma.model.SchemeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mohsin on 30/6/17.
 */

public class SchemeFragment extends Fragment implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    View rootView;


    private SearchView search;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<Continent> continentList = new ArrayList<Continent>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scheme, container, false);

        setUpViews();
        setDimens();

        return rootView;
    }

    private void setUpViews() {
        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        // Searchview component
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) rootView.findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        // preparing list data
//        prepareListData();

       /* listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);*/


        //display the list
        displayList();


        //all click events of an expandablelistview
        expanadableClick();

    }


    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            expListView.expandGroup(i);
        }
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

        // Listview on child click listener
/*        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity(),
                        continentList.get(groupPosition)
                                + " : "
                                + continentList.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });*/


    }


    //method to expand all groups
    private void displayList() {

        //display the list
        loadSomeData();
        //create the adapter by passing your ArrayList data
        listAdapter = new ExpandableListAdapter(getActivity(), continentList);
        //attach the adapter to the list
        expListView.setAdapter(listAdapter);

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

    //load data to expandableListview
    private void loadSomeData() {

        ArrayList<Country> countryList = new ArrayList<Country>();
        Country country = new Country("Paracitamol ", "Paracitamol", 10000000);
        countryList.add(country);
        country = new Country("Matacin", "Decold", 20000000);
        countryList.add(country);
        country = new Country("Decold", "Decold", 50000000);
        countryList.add(country);

        Continent continent = new Continent("CIPLA", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("Pantacin", "Pantacin", 10000100);
        countryList.add(country);
        country = new Country("Disprin", "Disprin", 20000200);
        countryList.add(country);
        country = new Country("Paracitamol", "Paracitamol", 50000500);
        countryList.add(country);

        continent = new Continent("ZYDUS CADILA", countryList);
        continentList.add(continent);

    }


    /**
     * The user is attempting to close the SearchView.
     *
     * @return true if the listener wants to override the default behavior of clearing the
     * text field and dismissing it, false otherwise.
     */
    @Override
    public boolean onClose() {
        return false;
    }

    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param query the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String query) {

        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}
