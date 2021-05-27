package com.kyalo.neighboursdish.AddDish;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kyalo.neighboursdish.R;
import com.kyalo.neighboursdish.data_models.Dish;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Objects;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class AddDishActivity extends AppCompatActivity {

    ImageView imageViewPhoto;
    private StorageReference mStorageRef;
    int number_of_next_dish;
    String userUid;
    String userName;
    Dish dish;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        dish = new Dish();


        number_of_next_dish = getIntent().getIntExtra("numOfNextDish", 0);
        userUid = getIntent().getStringExtra("userUid");
        userName = getIntent().getStringExtra("userName");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.publishAirdropViewPager);


        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        Button next_button = findViewById(R.id.next_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

//        publishAirdropBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(SalesMainPageActivity.this, newAirdropForm.class);
//            startActivity(intent);
//        });

    }

//            EasyImage.openChooserWithDocuments(this, "select photo", 0);


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onImagesPicked(@NonNull List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                //Handle the images
                Bitmap myBitmap = BitmapFactory.decodeFile(Objects.requireNonNull(imagesFiles).get(0).getAbsolutePath());


                StorageReference mountainsRef = mStorageRef.child(userUid).child("product" + number_of_next_dish + ".jpg");
                dish.setImageUrl(userUid + "/product" + number_of_next_dish + ".jpg");

                for(Fragment fragment: getSupportFragmentManager().getFragments()){
                    if(fragment instanceof AddPhotoFragment){

                        ((AddPhotoFragment)fragment).imageViewPhoto.setImageBitmap(myBitmap);
                        ((AddPhotoFragment)fragment).imageViewPhoto.setDrawingCacheEnabled(true);
                        ((AddPhotoFragment)fragment).imageViewPhoto.buildDrawingCache();
                    }
                }



                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(exception -> {
                    // Handle unsuccessful uploads
                    Toast.makeText(AddDishActivity.this, "upload failed: "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                }).addOnSuccessListener(taskSnapshot -> {


                    Toast.makeText(AddDishActivity.this, "upload success", Toast.LENGTH_SHORT).show();

                });

            }
        });
    }


    //Tell a view pager about its pages using a fragment pager adapter,this is the inner class for this adapter.
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount(){
            return 3;
        }

        @NonNull
        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new AddPhotoFragment();
                case 1:
                    return new ByGramsOrByPiecesFragment();
                case 2:
                    return new FoodCategoryFragment();
            }
            return null;
        }






    }

    public void nextFragment(int item){
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(id, fragment);
//        ft.commit();
        if(item==2){
            finish();
        }else{
            pager.setCurrentItem(item);
        }
    }

}

