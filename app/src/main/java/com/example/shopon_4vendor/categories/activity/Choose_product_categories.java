package com.example.shopon_4vendor.categories.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.shopon_4vendor.Product_view.productView;
import com.example.shopon_4vendor.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Choose_product_categories extends AppCompatActivity {
    private Button submit_ok;
    boolean c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    CheckBox clothing, cell_phone, electronics, shoes, books, handmade, health, sports, toys, luggage, music, musical_instrument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product_categories);
        Firebase.setAndroidContext(this);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        // if(currentFirebaseUser.getUid()==)
        checked_or_not();
        button_click();
    }

    private void button_click() {


        submit_ok = findViewById(R.id.submit_ok);
        clothing = findViewById(R.id.Clothing);
        cell_phone = findViewById(R.id.Cell_Phones);
        electronics = findViewById(R.id.Electronics);
        shoes = findViewById(R.id.Shoes);
        books = findViewById(R.id.Books);
        handmade = findViewById(R.id.Handmade);
        health = findViewById(R.id.Health);
        sports = findViewById(R.id.Sports);
        toys = findViewById(R.id.Toys);
        luggage = findViewById(R.id.Luggage);
        music = findViewById(R.id.Music);
        musical_instrument = findViewById(R.id.Musical_Instruments);
        submit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Firebase database;

                database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                Firebase f = database.child("Product_catalog");
                Firebase s = f.child(currentFirebaseUser.getUid());


                if(clothing.isChecked())
                {
                    Firebase a = s.child("a");
                    Firebase b = a.child("pro");
                    Firebase UID=a.child("UID");
                    b.setValue("Clothing");
                    UID.setValue(currentFirebaseUser.getUid());
                    c1=true;
                    Toast.makeText(Choose_product_categories.this, "Clothing is clicked", Toast.LENGTH_SHORT).show();
                }
                if (cell_phone.isChecked())
                {
                    Firebase a = s.child("b");
                    Firebase b = a.child("pro");
                    Firebase UID=a.child("UID");
                    b.setValue("CellPhones");
                    UID.setValue(currentFirebaseUser.getUid());
                    c2=true;
                    Toast.makeText(Choose_product_categories.this, "Cell phone is clicked", Toast.LENGTH_SHORT).show();
                }
                if(electronics.isChecked())
                {
                    Firebase a = s.child("c");
                    Firebase c = a.child("pro");
                    Firebase UID=a.child("UID");
                    c.setValue("Electronics");
                    UID.setValue(currentFirebaseUser.getUid());
                    c3=true;
                    Toast.makeText(Choose_product_categories.this, "Electronic si clicked", Toast.LENGTH_SHORT).show();
                }
                if(shoes.isChecked())
                {  Firebase a = s.child("d");
                    Firebase d = a.child("pro");
                    Firebase UID=a.child("UID");
                    d.setValue("Shoes");
                    UID.setValue(currentFirebaseUser.getUid());
                    c4=true;

                }
                if(books.isChecked())
                {
                    Firebase a = s.child("e");
                    Firebase e = a.child("pro");
                    Firebase UID=a.child("UID");
                    e.setValue("Books");
                    UID.setValue(currentFirebaseUser.getUid());
                    c5=true;
                }
                if(handmade.isChecked())
                {
                    Firebase a = s.child("f");
                    Firebase fire = a.child("pro");
                    Firebase UID=a.child("UID");
                    fire.setValue("Handmade");
                    UID.setValue(currentFirebaseUser.getUid());
                    c6=true;
                }
                if(health.isChecked())
                {
                    Firebase a = s.child("g");
                    Firebase g = a.child("pro");
                    Firebase UID=a.child("UID");
                    g.setValue("Health");
                    UID.setValue(currentFirebaseUser.getUid());
                    c7=true;
                }
                if(sports.isChecked())
                {
                    Firebase a = s.child("h");
                    Firebase h = a.child("pro");
                    Firebase UID=a.child("UID");
                    h.setValue("Sports");
                    UID.setValue(currentFirebaseUser.getUid());
                    c8=true;
                }
                if(toys.isChecked())
                {
                    Firebase a = s.child("i");
                    Firebase i = a.child("pro");
                    Firebase UID=a.child("UID");
                    i.setValue("Toys");
                    UID.setValue(currentFirebaseUser.getUid());
                    c9=true;
                }
                if(luggage.isChecked())
                {
                    Firebase a = s.child("j");
                    Firebase j = a.child("pro");
                    Firebase UID=a.child("UID");
                    j.setValue("Luggage");
                    UID.setValue(currentFirebaseUser.getUid());
                    c10=true;
                }
                if(music.isChecked())
                {
                    Firebase a = s.child("k");
                    Firebase k = a.child("pro");
                    Firebase UID=a.child("UID");
                    k.setValue("Music");
                    UID.setValue(currentFirebaseUser.getUid());
                    c11=true;
                }
                if(musical_instrument.isChecked())
                {
                    Firebase a = s.child("l");
                    Firebase l = a.child("pro");
                    Firebase UID=a.child("UID");
                    l.setValue("MusicalInstrument");
                    UID.setValue(currentFirebaseUser.getUid());
                    c12=true;
                }
                if(c1 || c2 || c3 || c4 || c5 || c6 || c7 || c8 || c9 || c10 || c11 || c12)
                {
                    if(getIntent().getStringExtra("From").equals("Home"))
                    {
                        Toast.makeText(Choose_product_categories.this, "Added Successfully..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Choose_product_categories.this, productView.class));
                    }
                    else
                    {
                        Intent i=new Intent(Choose_product_categories.this,Home.class);
                        //   i.putExtra("uid",currentFirebaseUser.getUid());
                          startActivity(i);
                    }


                }
                else
                {
                    Toast.makeText(Choose_product_categories.this, "Please Select any..!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checked_or_not() {
        cell_phone = findViewById(R.id.Cell_Phones);
        cell_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(Choose_product_categories.this, "is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


