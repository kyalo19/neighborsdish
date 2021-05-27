package com.kyalo.neighboursdish;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class DetailedDishActivity extends AppCompatActivity {
    public static final String EXTRA_NAME="EXTRA_NAME";
    public static final String EXTRA_DETAILS ="EXTRA_DETAILS";
    public static final String EXTRA_PIECES ="EXTRA_PIECES";
    public static final String EXTRA_PRICE ="EXTRA_PRICE";
    public static final String EXTRA_WEIGHT ="EXTRA_WEIGHT";
    public static final String EXTRA_IMAGE ="EXTRA_IMAGE";
    TextView dish_price_value;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_dish);

        //get values from intent
        String DishNameData =(String) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_NAME);
        String DishDetailsData =(String)getIntent().getExtras().get(EXTRA_DETAILS);
        int DishPiecesData = getIntent().getIntExtra(EXTRA_PIECES,0);

        double DishPriceData =getIntent().getDoubleExtra(EXTRA_PRICE,0f);
        int DishWeightData =getIntent().getIntExtra(EXTRA_WEIGHT, 0);

        dish_price_value = findViewById(R.id.dish_price_value);

        String url =(String)getIntent().getExtras().get("url");

        ImageView dish_image = findViewById(R.id.dish_image);
        com.google.firebase.storage.FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        assert url != null;
        StorageReference islandRef = storageRef.child(url);


        Glide.with(getApplicationContext()).load(islandRef).into(dish_image);

      // Bitmap DishImageData = BitmapFactory.decodeByteArray(
               //getIntent().getByteArrayExtra("byteArray"),0, Objects.requireNonNull(getIntent().getByteArrayExtra("byteArray")).length);


        // Populate the views with data
        TextView tv_DishName = findViewById(R.id.dish_name);
        tv_DishName.setText(DishNameData);

        //ImageView iv_dishImage= new ImageView(this);
        //iv_dishImage.setImageBitmap(DishImageData);

        TextView tv_DishDetails = findViewById(R.id.dish_details);
        tv_DishDetails.setText(DishDetailsData);


        TextView tv_DishPiecesOrWeight = findViewById(R.id.dish_weight_or_pieces_value);


        if(DishPiecesData!=0){
            tv_DishPiecesOrWeight.setText(DishPiecesData+"");
        }

        if(DishWeightData!=0){
            tv_DishPiecesOrWeight.setText(DishWeightData+"");
        }

        Button checkout = findViewById(R.id.checkout_dish);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailedDishActivity.this, "Checked out successfully", Toast.LENGTH_SHORT).show();
            }
        });

        dish_price_value.setText(DishPriceData+"");

    }

}
