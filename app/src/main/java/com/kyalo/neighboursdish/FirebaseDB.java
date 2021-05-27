package com.kyalo.neighboursdish;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kyalo.neighboursdish.data_models.Dish;
import com.kyalo.neighboursdish.data_models.Seller;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDB {

    private static DatabaseReference ref;
    private FirebaseDB(){}

    public static DatabaseReference getRef(){
        if (ref == null) {
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            ref = database.getReference();
        }
        return ref;
    }

    public static DatabaseReference getSellersRef(){
        return getRef().child("Sellers");
    }

    public static void setSellerProfile(Seller seller){
        getSellersRef().child(seller.name).setValue(seller);
    }

    public static void updateSellerProfile(String name, String address, String city){
        Map<String, Object> tempProfileMap = new HashMap<>();
        tempProfileMap.put("address", address);
        tempProfileMap.put("city", city);
        getSellersRef().child(name).updateChildren(tempProfileMap);
    }

    public static void addDish(String userName, Dish dish){
        Map<String, Object> tempDishMap = new HashMap<>();
        tempDishMap.put(dish.getName(), dish);
        FirebaseDB.getSellersRef().child(userName).child("dishList").updateChildren(tempDishMap);
    }

}


