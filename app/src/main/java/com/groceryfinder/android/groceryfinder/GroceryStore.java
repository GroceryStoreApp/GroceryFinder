package com.groceryfinder.android.groceryfinder;

/**
 * Created by KaiMacBookAir on 4/24/17.
 */

public class GroceryStore {

    private String name;
    private String address;
    private String zip;
    private String storeId;

    public GroceryStore(String name, String address, String zip, String storeId) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.storeId = storeId;
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
}
