package com.groceryfinder.android.groceryfinder;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by KaiMacBookAir on 4/24/17.
 */

public class GroceryStore {

    @SerializedName("Storename")
    private String name;
    @SerializedName("Address")
    private String address;
    @SerializedName("Zip")
    private String zip;
    @SerializedName("City")
    private String city;
    private String storeId;
    private UUID id;
    private String cost;

    public GroceryStore(String name, String address, String zip, String storeId) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.storeId = storeId;
        this.cost = "$$$";
    }

    public GroceryStore(String name) {
        this(name, "", "", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public UUID getId () {
        return id;
    }

    public String getDistanceFrom(String location) {
        return "5";
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
