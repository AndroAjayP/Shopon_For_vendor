package com.example.shopon_4vendor.Product_view;

public class  View_inflator  {
    String  Product_descripyion,imagurl,name,Price,User_uid,Mobile,Address,Vendor_uid;

    public  View_inflator ()
    {

    }

    public View_inflator(String product_descripyion, String imagurl, String name, String price, String user_uid, String mobile, String address, String vendor_uid) {
        Product_descripyion = product_descripyion;
        this.imagurl = imagurl;
        this.name = name;
        this.Price = price;
        User_uid = user_uid;
        Mobile = mobile;
        Address = address;
        Vendor_uid = vendor_uid;
    }

    public String getProduct_descripyion() {
        return Product_descripyion;
    }

    public void setProduct_descripyion(String product_descripyion) {
        Product_descripyion = product_descripyion;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getUser_uid() {
        return User_uid;
    }

    public void setUser_uid(String user_uid) {
        User_uid = user_uid;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getVendor_uid() {
        return Vendor_uid;
    }

    public void setVendor_uid(String vendor_uid) {
        Vendor_uid = vendor_uid;
    }
}
