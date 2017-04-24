package com.groceryfinder.android.groceryfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KaiMacBookAir on 4/21/17.
 */

public class GroceryListFragment extends Fragment {

    private RecyclerView mGroceryRecyclerView;

    public static GroceryListFragment newInstance() {
        return new GroceryListFragment();
    }

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grocery_list_fragment, container, false);

        mGroceryRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_grocery_list);
        mGroceryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    //Viewholder holds a textview with information about the grocery stores
    //Textview will be changed to a more detailed view with information about the stores
    private class GroceryStoreHolder extends RecyclerView.ViewHolder {

        public TextView mGroceryStoreTextView;

        public GroceryStoreHolder(View itemView) {
            super(itemView);

            mGroceryStoreTextView = (TextView) itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new GroceryStoreHolder(view);
        }

        @Override
        public void onBindViewHolder(GroceryStoreHolder holder, int position) {
            GroceryStore groceryStore = mGroceryStoreList.get(position);
            holder.mGroceryStoreTextView.setText(groceryStore.getName());
        }

        @Override
        public int getItemCount() {
            return mGroceryStoreList.size();
        }
    }
}
