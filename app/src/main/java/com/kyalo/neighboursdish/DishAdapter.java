package com.kyalo.neighboursdish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kyalo.neighboursdish.data_models.Dish;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private List<Dish> dishList;
    private Context mContext;

    public static class DishViewHolder extends RecyclerView.ViewHolder{

        private Dish dish;

        private ImageView ivDishImage;
        private TextView tvDishName;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDishImage = itemView.findViewById(R.id.iv_dish_image);
            tvDishName = itemView.findViewById(R.id.tv_dish_name);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailedDishActivity.class);
                    intent.putExtra(DetailedDishActivity.EXTRA_NAME, dish.getName());
                    intent.putExtra(DetailedDishActivity.EXTRA_DETAILS, dish.getDetails());
                    intent.putExtra(DetailedDishActivity.EXTRA_PIECES, dish.getPieces());
                    intent.putExtra(DetailedDishActivity.EXTRA_PRICE, dish.getPrice());
                    intent.putExtra(DetailedDishActivity.EXTRA_WEIGHT, dish.getWeight());
                    intent.putExtra("url", dish.getImageUrl());

                    BitmapDrawable drawable = (BitmapDrawable) ivDishImage.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                    intent.putExtra(DetailedDishActivity.EXTRA_IMAGE, bs.toByteArray());


                    view.getContext().startActivity(intent);
                }
            });
        }

        public void setDishData(Dish dish, Context mContext){
            this.dish = dish;
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference islandRef = storageRef.child(dish.getImageUrl());


            Glide.with(mContext).load(islandRef).into(ivDishImage);


            //ivDishImage.setImageBitmap(dish.());
            tvDishName.setText(dish.getName());
        }

    }

    public DishAdapter(List<Dish> dishList, Context mContext) {
        this.dishList = dishList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dish_item, viewGroup, false);

        return new DishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder dishViewHolder, int i) {
        dishViewHolder.setDishData(dishList.get(i), mContext);
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }
}
