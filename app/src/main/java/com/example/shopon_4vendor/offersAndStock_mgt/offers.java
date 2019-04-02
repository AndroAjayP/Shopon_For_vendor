package com.example.shopon_4vendor.offersAndStock_mgt;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class offers extends AppCompatActivity {


    String[] categories = {"Clothing", "CellPhones", "Electronics", "Shoes", "Books", "Handmade", "Health", "Luggage", "Music", "MusicalInstrument", "Sports", "Toys"};
    String[] Clothing = {"T-Shirts", "Shirts", "Jeans", "Sports Wear", "Trousers"};
    String[] CellPhones = {"Apple iPhones", "Samsung Galaxy Phones", "HTC phones", "LG phones", "Oppo phones"};
    String[] Electronics = {"Air conditioner", "Air purifier", "Blender", "Ceiling fan", "vacuum cleaner"};
    String[] Shoes = {"Sports Shoes", "Casual Shoes", "Formal Shoes", "Sandals & Floaters", "Running Shoes"};
    String[] Books = {"Entrance Exams", "Academic", "Literature & Fiction", "Indian Writing", "Children"};
    String[] Handmade = {"Jewelry", "Paintings", "Sculptures", "Dolls", "Scarves"};
    String[] Health = {"Protein Supplements", "Health Food", "Snacks", "Vitamin Supplements", "Health Accesories"};
    String[] Luggage = {"Travel Luggage", "Handbags", "School Bags", "Garment Covers", "Appliance Covers"};
    String[] Music = {"Hip hop music", "Classical music", "Electronic dance music", "Jazz", "Reggae"};
    String[] MusicalInstrument = {"Wind Instruments", "Keys & Synthesizers", "Studio/Stage Equipment & Accessories", "Electronic Instruments", "Drums & Percussion"};
    String[] Sports = {"Cricket", "Badminton", "Cycling", "Camping & Hiking", "Football"};
    String[] Toys = {"Remote Control Toys", "Learning A Educational Toys", "Soft Toys", "Puzzles", "Art A Craft Toys"};

    ////////////////////////////////variable_declaration

    ImageView poster;
    String selected_categories;
    final static int gallery_req = 2;
    ProgressDialog mprogress;
    int check;
    String photoStringLink = "1";
    ArrayAdapter sub_categories;
    Uri uri;
    StorageReference mStorageRef;
    Button submit;
    EditText offer_in_percentage;
    static String sub_categories_value;
    Spinner sub_catgories;

    /////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);


        //////////////////////////////////////find_id_here
        poster = findViewById(R.id.poster);
        submit = findViewById(R.id.submit_btn);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mprogress = new ProgressDialog(this);
        final Spinner Categories = findViewById(R.id.Categories);
        offer_in_percentage = findViewById(R.id.offers);
        sub_catgories = findViewById(R.id.sub_categories);


        /////////////////////////////////////////////////


        ///////////////////////////////adapter

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Categories.setAdapter(aa);
        Categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(offers.this, "selected categories is"+categories[i], Toast.LENGTH_SHORT).show();
                selected_categories=categories[i];

                if(selected_categories.equals("Clothing"))
                {
                    sub_categories= new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Clothing);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("CellPhones"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, CellPhones);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Electronics"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Electronics);
                    sub_categories_value=selected_categories;


                }
                if(selected_categories.equals("Shoes"))
                {
                    sub_categories= new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Shoes);
                    sub_categories_value=selected_categories;
                }
                if(selected_categories.equals("Books"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Books);
                    sub_categories_value=selected_categories;
                }
                if(selected_categories.equals("Handmade"))
                {
                    new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Handmade);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Health"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Health);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Luggage"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Luggage);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Music"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Music);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("MusicalInstrument"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, MusicalInstrument);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Sports"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Sports);
                    sub_categories_value=selected_categories;

                }
                if(selected_categories.equals("Toys"))
                {
                    sub_categories=  new ArrayAdapter(offers.this,android.R.layout.simple_spinner_item, Toys);
                    sub_categories_value=selected_categories;

                }
                if(null != sub_categories)
                {
                    sub_categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sub_catgories.setAdapter(sub_categories);
                    sub_categories_value=selected_categories;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        ArrayAdapter aaa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.Clothing);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_catgories.setAdapter(aa);
        sub_catgories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(offers.this, "sub_categoris is "+sub_categories_value ,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //////////////////////////////////////


    }

}