package com.groceryfinder.android.groceryfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaiMacBookAir on 4/21/17.
 */

public class GroceryListFragment extends Fragment {

    private static final String TAG = "GroceryListFragment";

    private RecyclerView mGroceryRecyclerView;
    //private GroceryStoreAdapter mAdapter;
    private List<GroceryStore> mStores = new ArrayList<>();

    private String address;
    private String zip = "99205";

    public static GroceryListFragment newInstance() {
        return new GroceryListFragment();
    }

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        new FetchStoresTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grocery_list_fragment, container, false);

        mGroceryRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_grocery_list);
        mGroceryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //updateUI();

        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mGroceryRecyclerView.setAdapter(new GroceryStoreAdapter(mStores));
        }
    }

    /*
    private void updateUI() {
        GroceryStoreLab lab = GroceryStoreLab.get(getActivity());
        List<GroceryStore> stores = lab.getGroceryStores();

        mAdapter = new GroceryStoreAdapter(stores);
        mGroceryRecyclerView.setAdapter(mAdapter);
    }
    */

    //Viewholder holds a textview with information about the grocery stores
    //Textview will be changed to a more detailed view with information about the stores
    private class GroceryStoreHolder extends RecyclerView.ViewHolder {

        private GroceryStore mGroceryStore;

        private TextView mStoreTextView;
        private TextView mAddressTextView;
        private TextView mDistanceTextView;
        private TextView mCostTextView;
        private TextView mZipTextView;

        public GroceryStoreHolder(View itemView) {
            super(itemView);

            mStoreTextView = (TextView) itemView.findViewById(R.id.list_layout_title);
            mAddressTextView = (TextView) itemView.findViewById(R.id.list_layout_address);
            mDistanceTextView = (TextView) itemView.findViewById(R.id.list_layout_distance);
            mCostTextView = (TextView) itemView.findViewById(R.id.list_layout_cost);
            mZipTextView = (TextView) itemView.findViewById(R.id.list_layout_zip);
        }

        public void bindStore(GroceryStore store) {
            mGroceryStore = store;

            mStoreTextView.setText(mGroceryStore.getName());
            mAddressTextView.setText(mGroceryStore.getCity());
            mDistanceTextView.setText(mGroceryStore.getDistanceFrom("temporary"));
            mCostTextView.setText(mGroceryStore.getCost());
            mZipTextView.setText(mGroceryStore.getZip());
        }
    }

    private class GroceryStoreAdapter extends RecyclerView.Adapter<GroceryStoreHolder> {

        private List<GroceryStore> mGroceryStoreList;

        public GroceryStoreAdapter(List<GroceryStore> stores) {
            mGroceryStoreList = stores;
        }

        @Override
        public GroceryStoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.grocery_store_list_layout, parent, false);
            return new GroceryStoreHolder(view);
        }

        @Override
        public void onBindViewHolder(GroceryStoreHolder holder, int position) {
            GroceryStore groceryStore = mGroceryStoreList.get(position);
            holder.bindStore(groceryStore);
        }

        @Override
        public int getItemCount() {
            return mGroceryStoreList.size();
        }
    }

    private class FetchStoresTask extends AsyncTask<Void, Void, List<GroceryStore>> {
        @Override
        protected List<GroceryStore> doInBackground(Void... params) {
            return new GroceryFetcher().fetchStores(zip);
        }

        @Override
        protected void onPostExecute(List<GroceryStore> stores) {
            mStores = stores;
            setupAdapter();
        }
    }
}
