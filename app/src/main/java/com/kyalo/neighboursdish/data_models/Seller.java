package com.kyalo.neighboursdish.data_models;

import java.util.HashMap;
import java.util.Map;

public class Seller {


    public String name;
    public String city;
    public String address;
    public Map<String, Dish> dishList;

    public Seller(){}

    public Seller(String name, String city, String address, Map<String, Dish> dishList) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.dishList = dishList;
    }

    public Seller(String city, String address, HashMap<Object, Object> objectObjectHashMap) {
    }

    public <V, K> Seller(String displayName, String s, String s1, HashMap<K,V> kvHashMap) {
    }

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/

}

