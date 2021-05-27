package com.kyalo.neighboursdish.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kyalo.neighboursdish.DishAdapter;
import com.kyalo.neighboursdish.FirebaseDB;
import com.kyalo.neighboursdish.R;
import com.kyalo.neighboursdish.data_models.Dish;
import com.kyalo.neighboursdish.data_models.Seller;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DishesFragment extends Fragment {

    private List<Dish> dishList;
    private DishAdapter dishAdapter;
    private RecyclerView rc;

    public DishesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* RecyclerView adaptation starts */
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dishes, container, false);

        RecyclerView rc = rootView.findViewById(R.id.rc_dishes);
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));

        dishList = new ArrayList<>();

        dishAdapter = new DishAdapter(dishList, getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rc.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        rc.addItemDecoration(dividerItemDecoration);
        rc.setAdapter(dishAdapter);

        loadDishes();
        /* RecyclerView adaptation ends */

        return rootView;
    }

    private void loadDishes() {
        FirebaseDB.getSellersRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<Seller> sellersList = new ArrayList<>();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    sellersList.add(data.getValue(Seller.class));
                }

                // Add all the dishes of each seller to the list to show the buyer
                for(Seller seller: sellersList){
                    if(seller.dishList == null || seller.dishList.isEmpty()) continue;

                    dishList.addAll(seller.dishList.values());
                }

                dishAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
