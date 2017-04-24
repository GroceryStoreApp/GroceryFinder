package com.groceryfinder.android.groceryfinder;

import android.support.v4.app.Fragment;

/**
 * Created by KaiMacBookAir on 4/21/17.
 */

public class GroceryListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new GroceryListFragment().newInstance();
    }
}
