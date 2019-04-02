package com.example.shopon_4vendor.signup_login;

public class Storing_to_firebase {

    String Brand;
    String Size;
    String Color;
    String Description;
    String price;
    String Imagurl;
    String Model;
    String  UID;



    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }



    public String getBrand() {
        return Brand;
    }


    public String getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
    }

    public String getImagurl() {
        return Imagurl;
    }

    public Storing_to_firebase(String imagurl) {
        Imagurl = imagurl;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return price;
    }


    public String getModel() {
        return Model;
    }

    public Storing_to_firebase(String brand, String size, String color, String description, String price, String imageUrl,String Model,String uid) {
        Brand = brand;
        Size = size;
        Color = color;
        Description = description;
        this.price = price;
        Imagurl=imageUrl;
        this.Model=Model;
        UID=uid;
    }
}
