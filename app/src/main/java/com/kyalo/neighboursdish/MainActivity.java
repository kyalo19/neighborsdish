package com.kyalo.neighboursdish;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kyalo.neighboursdish.Fragments.DishesFragment;
import com.kyalo.neighboursdish.Fragments.FavoritesFragment;
import com.kyalo.neighboursdish.Fragments.MyOrdersFragment;
import com.kyalo.neighboursdish.Fragments.SearchFragment;
import com.kyalo.neighboursdish.Fragments.SellerFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private List<Fragment> fragmentsInMemory = new ArrayList<>();
    private Fragment defaultFragment;
    public FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    Fragment selectedBottomNavFragment = null;

                    switch (item.getItemId()) {

                        case R.id.bottom_nav_seller:
                            selectedBottomNavFragment = new SellerFragment();
                            break;

                        case R.id.bottom_nav_dishes:
                            selectedBottomNavFragment = new DishesFragment();
                            break;

                        case R.id.bottom_nav_search:
                            selectedBottomNavFragment = new SearchFragment();
                            break;

                        case R.id.bottom_nav_my_orders:
                            selectedBottomNavFragment = new MyOrdersFragment();
                            break;

                        case R.id.bottom_nav_favorites:
                            selectedBottomNavFragment = new FavoritesFragment();
                            break;
                    }

                    // We store all active Fragments in a list, to avoid starting the same Fragment twice
                    if (!fragmentsInMemory.contains(selectedBottomNavFragment)) {
                        fragmentsInMemory.add(selectedBottomNavFragment);
                    }

                    if (selectedBottomNavFragment != null) {
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.main_activity_fragment, selectedBottomNavFragment);
                        ft.commit();
                    }

                    return true;
                });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(getSupportFragmentManager().getFragments().isEmpty()){
            Fragment main = new DishesFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.main_activity_fragment, main);
            ft.commit();
        }

    }

    protected void onPause() {
        super.onPause();
        if(!getSupportFragmentManager().getFragments().isEmpty()){
            Fragment main = new DishesFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(main);
            ft.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");


    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (resultCode == RESULT_OK) {
            // Successfully signed in
            user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();

            // Add user to Firebase Database
            for (Fragment fragment : fragmentsInMemory) {
                if (fragment instanceof SellerFragment) {
                    ((SellerFragment) fragment).loadUserData(user);
                }
            }

        } else if (resultCode == RESULT_CANCELED) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


    };
}