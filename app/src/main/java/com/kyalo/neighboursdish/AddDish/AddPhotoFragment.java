package com.kyalo.neighboursdish.AddDish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kyalo.neighboursdish.R;

import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhotoFragment extends Fragment {

    EditText editTextDishName ,edit_dish_description;
    RelativeLayout rel_photo;
    ImageView imageViewPhoto;
    Button next_button;

    public AddPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_photo_dish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextDishName = view.findViewById(R.id.editTextDishName);
        edit_dish_description = view.findViewById(R.id.edit_dish_description);
        imageViewPhoto = view.findViewById(R.id.imageViewPhoto);
        next_button = view.findViewById(R.id.next_button);
        rel_photo = view.findViewById(R.id.rel_photo);
        rel_photo.setOnClickListener((View v) -> {
            EasyImage.openChooserWithDocuments(this.getActivity(), "select photo", 0);

        });

        next_button.setOnClickListener(v -> {
            ((AddDishActivity) this.requireActivity()).dish.setName(editTextDishName.getText().toString());
            ((AddDishActivity) this.requireActivity()).dish.setDetails(edit_dish_description.getText().toString());

            ((AddDishActivity) this.requireActivity()).nextFragment(1);
        });
    }



}
