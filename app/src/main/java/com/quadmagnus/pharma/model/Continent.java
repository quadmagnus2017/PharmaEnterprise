package com.quadmagnus.pharma.model;

import java.util.ArrayList;

/**
 * Created by mohsin on 5/7/17.
 */

public class Continent {


    private String name;
    private ArrayList<Country> countryList = new ArrayList<Country>();

    public Continent(String name, ArrayList<Country> countryList) {
        this.name = name;
        this.countryList = countryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }
}
