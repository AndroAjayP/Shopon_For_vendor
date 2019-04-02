package com.example.shopon_4vendor.Product_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopon_4vendor.R;

public class orderView extends AppCompatActivity {
    TextView desc,price,mobile,address,name;
    ImageView imageurl;

    String Description,Price_value,Address,Name,ImageUrl,Mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        desc=findViewById(R.id.description);
        price=findViewById(R.id.order_price);
        mobile=findViewById(R.id.mobile_no);
        name=findViewById(R.id.Name_of_user);
        address=findViewById(R.id.Address_of_user);
        imageurl=findViewById(R.id.order_image);

        Description=getIntent().getStringExtra("desc");
        Price_value=getIntent().getStringExtra("price");
        Address=getIntent().getStringExtra("address");
        Name=getIntent().getStringExtra("name");
        ImageUrl=getIntent().getStringExtra("image");
        Mobile=getIntent().getStringExtra("mobile");


        desc.setText(Description);
        price.setText(Price_value);
        mobile.setText(Mobile);
        name.setText(Name);
        address.setText(Address);

        Glide.with(orderView.this)
                .load(ImageUrl)
                .into(imageurl);

    }

}
