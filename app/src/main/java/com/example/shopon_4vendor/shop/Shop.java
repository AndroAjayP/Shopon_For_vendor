package com.example.shopon_4vendor.shop;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.shopon_4vendor.signup_login.App;

import java.util.Arrays;
import java.util.List;



public class Shop  {

    private static final String STORAGE = "shop";

    static Shop get() {
        return new Shop();
    }
    private SharedPreferences storage;

    public Shop() {
     storage = App.getInstance().getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
    }


    List<Item> getData(String str1,String str2,String str3,String str4,String str5,int img1,int img2,int img3,int img4,int img5) {
        return Arrays.asList(
                new Item(1, str1,  img1),
                new Item(2, str2,  img2),
                new Item(3, str3,  img3),
                new Item(4, str4,  img4),
                new Item(5, str5,  img5));
    }

    boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }


}
