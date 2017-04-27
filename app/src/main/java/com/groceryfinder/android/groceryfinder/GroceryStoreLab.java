package com.groceryfinder.android.groceryfinder;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by KaiMacBookAir on 4/24/17.
 */

//Temporary fix before api and databases are implemented into the app
// creates a list of 50 randomly generated grocerystore objects


public class GroceryStoreLab {
    private static GroceryStoreLab sGroceryStoreLab;

    private List<GroceryStore> mGroceryStores;

    public static GroceryStoreLab get(Context context) {
        if (sGroceryStoreLab == null) {
            sGroceryStoreLab = new GroceryStoreLab(context);
        }
        return sGroceryStoreLab;
    }

    private GroceryStoreLab(Context context) {
        mGroceryStores = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            GroceryStore store = new GroceryStore("Target", "Redmond", "98052", "id");
            mGroceryStores.add(store);
        }
    }

    public List<GroceryStore> getGroceryStores() {
        return mGroceryStores;
    }

    public GroceryStore getGroceryStore(UUID id) {
        for (GroceryStore store : mGroceryStores) {
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }
}
