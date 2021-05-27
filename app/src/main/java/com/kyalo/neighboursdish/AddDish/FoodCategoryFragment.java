package com.kyalo.neighboursdish.AddDish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kyalo.neighboursdish.FirebaseDB;
import com.kyalo.neighboursdish.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodCategoryFragment extends Fragment {

    GridView gridView;
    AdapterCategories adapterCategories;
    Button next_button;

    public FoodCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next_button = view.findViewById(R.id.next_button);
//        for (int i = 0; i < arrBoolean.length; i++) {
//            arrBoolean[i] = false;
//        }
//
//        ImageView imageView1 = view.findViewById(R.id.imageView1);
//        LinearLayout tableCell1 = view.findViewById(R.id.tableCell1);
//
//        imageView1.setOnClickListener(v -> {
//            if(arrBoolean[0]){
//                arrBoolean[0]=false;
//                tableCell1.setBackgroundResource(R.color.white);
//            }else{
//                arrBoolean[0] = true;
//                tableCell1.setBackgroundResource(R.drawable.rec_background_with_orange_border);
//            }
//        });


        gridView = view.findViewById(R.id.listvieww);

        adapterCategories = new AdapterCategories(requireActivity());
        gridView.setAdapter(adapterCategories);

        adapterCategories.add(new ObjectDishCategory("italian", R.drawable.pizza, false));
        adapterCategories.add(new ObjectDishCategory("Thai", R.drawable.noodles, false));
        adapterCategories.add(new ObjectDishCategory("Japanese", R.drawable.sushi, false));
        adapterCategories.add(new ObjectDishCategory("Fish", R.drawable.fish, false));
        adapterCategories.add(new ObjectDishCategory("Meat", R.drawable.steak, false));
        adapterCategories.add(new ObjectDishCategory("Seafood", R.drawable.shrimp, false));
        adapterCategories.add(new ObjectDishCategory("Traditional", R.drawable.turkey, false));
        adapterCategories.add(new ObjectDishCategory("Vegeterian", R.drawable.salad, false));
        adapterCategories.add(new ObjectDishCategory("Cakes", R.drawable.cake, false));
        adapterCategories.add(new ObjectDishCategory("Cupcakes", R.drawable.cupcake, false));
        adapterCategories.add(new ObjectDishCategory("Breads", R.drawable.bread, false));

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            if (adapterCategories.getItem(position).state) {
                adapterCategories.getItem(position).state = false;
                adapterCategories.notifyDataSetChanged();
            } else {
                adapterCategories.getItem(position).state = true;
                adapterCategories.notifyDataSetChanged();
            }
        });

        next_button.setOnClickListener(v -> {
            List listOfChosen = new ArrayList();
            for (int i = 0; i < adapterCategories.getCount(); i++) {
                if (adapterCategories.getItem(i).state){
                    listOfChosen.add(adapterCategories.getItem(i).name);
                }
            }
            ((AddDishActivity) this.getActivity()).dish.setCategory(listOfChosen);

            String userName = ((AddDishActivity) this.getActivity()).userName;
            FirebaseDB.addDish(userName, ((AddDishActivity) this.getActivity()).dish);

            ((AddDishActivity) this.getActivity()).nextFragment(3);



        });
    }
}

