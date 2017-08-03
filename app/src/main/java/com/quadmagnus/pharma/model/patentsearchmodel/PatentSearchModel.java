package com.quadmagnus.pharma.model.patentsearchmodel;

/**
 * Created by mohsin on 6/7/17.
 */

public class PatentSearchModel {

    private String name, quantity, company;

    public PatentSearchModel(String name, String quantity, String company) {
        this.name = name;
        this.quantity = quantity;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
