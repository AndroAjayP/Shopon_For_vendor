package com.example.shopon_4vendor.offersAndStock_mgt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class stockMgt extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    String[] categories = { "Clothing", "CellPhones", "Electronics", "Shoes", "Books","Handmade","Health","Luggage","Music","MusicalInstrument","Sports","Toys"};
    String[] Clothing=    {"T-Shirts","Shirts","Jeans","Sports Wear","Trousers"};
    String[] CellPhones={"Apple iPhones","Samsung Galaxy Phones","HTC phones","LG phones","Oppo phones"};
    String[]  Electronics={"Air conditioner","Air purifier","Blender","Ceiling fan","vacuum cleaner"};
    String[] Shoes={"Sports Shoes","Casual Shoes","Formal Shoes","Sandals & Floaters","Running Shoes"};
    String[] Books={"Entrance Exams","Academic","Literature & Fiction","Indian Writing","Children"};
    String[]  Handmade={"Jewelry","Paintings","Sculptures","Dolls","Scarves"};
    String[] Health={"Protein Supplements","Health Food","Snacks","Vitamin Supplements","Health Accesories"};
    String[] Luggage={"Travel Luggage","Handbags","School Bags","Garment Covers","Appliance Covers"};
    String[] Music={"Hip hop music","Classical music","Electronic dance music","Jazz","Reggae"};
    String[]  MusicalInstrument={"Wind Instruments","Keys & Synthesizers","Studio/Stage Equipment & Accessories","Electronic Instruments","Drums & Percussion"};
    String[] Sports ={"Cricket","Badminton","Cycling","Camping & Hiking","Football"};
    String[]  Toys={"Remote Control Toys","Learning A Educational Toys","Soft Toys","Puzzles","Art A Craft Toys"};
    ImageView poster;
    String selected_categories;
    ArrayAdapter sub_categories;
    Button submit;
    ImageView stock_in,stock_out;
    TextView stock_text,stockOut_text;
    static  int check=2;
    static String sub_categories_value;


    Firebase database;
    Firebase s ;
    FirebaseUser currentFirebaseUser;
    Firebase sub_categoris;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_mgt);
        poster=findViewById(R.id.poster);
        submit=findViewById(R.id.submit_btn);
        stock_text=findViewById(R.id.stock_text);
        stock_in=findViewById(R.id.stock_in);

        stockOut_text=findViewById(R.id.stockOut_text);
        stock_out=findViewById(R.id.stock_out);
        final Spinner categories =  findViewById(R.id.Categories);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, this.categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(aa);
        categories.setOnItemSelectedListener(this);


        Firebase.setAndroidContext(this);
        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Firebase offers_deatil = database.child("stock");
        s  = offers_deatil.child(currentFirebaseUser.getUid());




        stock_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stock_out.setVisibility(View.GONE);
                stockOut_text.setVisibility(View.GONE);
                check=1;

            }
        });
        stock_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stock_in.setVisibility(View.GONE);
                stock_text.setVisibility(View.GONE);
                check=0;

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check!=2&&selected_categories.length()>1&&sub_categories!=null)
                {
                    sub_categoris.setValue(String.valueOf(check));
                    //storing_to_firebase(selected_categories,sub_categories_value, String.valueOf(check));
                    Toast.makeText(stockMgt.this, "bollean value is "+String.valueOf(check), Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(stockMgt.this, "sub categories is "+sub_categories, Toast.LENGTH_SHORT).show();
                    Toast.makeText(stockMgt.this, "All Field is successfully submitted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(stockMgt.this, "Kuch to chhuta hai...ab ye malum aapko karna hai", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, "is selected "+categories[i], Toast.LENGTH_SHORT).show();
        Spinner sub_catgories = findViewById(R.id.sub_categories);
        selected_categories=categories[i];
        if(selected_categories.length()>1)
        {
            sub_categories = null;
            if(selected_categories.equals("Clothing"))
            {
                sub_categories= new ArrayAdapter(this,android.R.layout.simple_spinner_item, Clothing);
                sub_categories_value=Clothing[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
                sub_categoris=  s.child(Clothing[i]);

            }
            if(selected_categories.equals("CellPhones"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, CellPhones);
                sub_categories_value=CellPhones[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=   s.child(CellPhones[i]);

            }
            if(selected_categories.equals("Electronics"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Electronics);
                sub_categories_value=Electronics[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=  s.child(Electronics[i]);

            }
            if(selected_categories.equals("Shoes"))
            {
                sub_categories= new ArrayAdapter(this,android.R.layout.simple_spinner_item, Shoes);
                sub_categories_value=Shoes[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
            sub_categoris=  s.child(Shoes[i]);
            }
            if(selected_categories.equals("Books"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Books);
                sub_categories_value=Books[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
               sub_categoris= s.child(Books[i]);
            }
            if(selected_categories.equals("Handmade"))
            {
                new ArrayAdapter(this,android.R.layout.simple_spinner_item, Handmade);
                sub_categories_value=Handmade[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=  s.child(Handmade[i]);

            }
            if(selected_categories.equals("Health"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Health);
                sub_categories_value=Health[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=  s.child(Health[i]);

            }
            if(selected_categories.equals("Luggage"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Luggage);
                sub_categories_value=Luggage[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=  s.child(Luggage[i]);

            }
            if(selected_categories.equals("Music"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Music);
                sub_categories_value=Music[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
               sub_categoris=  s.child(Music[i]);

            }
            if(selected_categories.equals("MusicalInstrument"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, MusicalInstrument);
                sub_categories_value=MusicalInstrument[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
              sub_categoris=  s.child(MusicalInstrument[i]);

            }
            if(selected_categories.equals("Sports"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Sports);
                sub_categories_value=Sports[i];
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
            sub_categoris=  s.child(Sports[i]);

            }
            if(selected_categories.equals("Toys"))
            {
                sub_categories=  new ArrayAdapter(this,android.R.layout.simple_spinner_item, Toys);
                Toast.makeText(this, "Sub categories is "+sub_categories_value, Toast.LENGTH_SHORT).show();
             sub_categoris=  s.child(Sports[i]);

            }
            if(null != sub_categories)
            {
                sub_categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sub_catgories.setAdapter(sub_categories);
            }


        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void storing_to_firebase(String cat,String sub_cat,String stock) {
        Firebase.setAndroidContext(this);

        Firebase database;

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


        Firebase offers_deatil = database.child("stock");
        Firebase s = offers_deatil.child(currentFirebaseUser.getUid());


      /*  if(sub_categories_value.equals("T-Shirts"))
        {
            s.child("T-Shirts").setValue(stock);

        }  if(sub_categories_value.equals("Shirts"))
        {
            s.child("Shirts").setValue(stock);

        }
        if(sub_categories_value.equals("Jeans"))
        {
            s.child("Jeans").setValue(stock);

        }  if(sub_categories_value.equals("Sports Wear"))
        {
            s.child("Sports Wear").setValue(stock);
        }
        if(sub_categories_value.equals("Trousers"))
        {
            s.child("Trousers").setValue(stock);
        }
        if(sub_categories_value.equals("Oppo phones"))
        {
            s.child("Oppo phones").setValue(stock);
        }
        if(sub_categories_value.equals("LG phones"))
        {
            s.child("LG phones").setValue(stock);
        }
        if(sub_categories_value.equals("HTC phones"))
        {
            s.child("HTC phones").setValue(stock);
        }
        if(sub_categories_value.equals("Samsung Galaxy Phones"))
        {
            s.child("Samsung Galaxy Phones").setValue(stock);

        }  if(sub_categories_value.equals("Apple iPhones"))
        {
            s.child("Apple iPhones").setValue(stock);
        }  if(sub_categories_value.equals("Air conditioner"))
        {
            s.child("Air conditioner").setValue(stock);
        }
        if(sub_categories_value.equals("Air purifier"))
        {
            s.child("Air purifier").setValue(stock);

        }  if(sub_categories_value.equals("Blender"))
        {
            s.child("Blender").setValue(stock);

        }  if(sub_categories_value.equals("Ceiling fan"))
        {
            s.child("Ceiling fan").setValue(stock);
        }  if(sub_categories_value.equals("vacuum cleaner"))
        {
            s.child("vacuum cleaner").setValue(stock);
        }
        if(sub_categories_value.equals("Running Shoes"))
        {
            s.child("Running Shoes").setValue(stock);

        }  if(sub_categories_value.equals("Sandals & Floaters"))
        {
            s.child("Sandals & Floaters").setValue(stock);

        }  if(sub_categories_value.equals("Formal Shoes"))
        {
            s.child("Formal Shoes").setValue(stock);
        }
        if(sub_categories_value.equals("Casual Shoes"))
        {
            s.child("Casual Shoes").setValue(stock);

        }
        if(sub_categories_value.equals("Sports Shoes"))
        {
            s.child("Sports Shoes").setValue(stock);

        }  if(sub_categories_value.equals("Entrance Exams"))
        {
            s.child("Entrance Exams").setValue(stock);

        }
        if(sub_categories_value.equals("Academic"))
        {
            s.child("Academic").setValue(stock);
        }
        if(sub_categories_value.equals("Literature & Fiction"))
        {
            s.child("Literature & Fiction").setValue(stock);
        }
        if(sub_categories_value.equals("Indian Writing"))
        {
            s.child("Indian Writing").setValue(stock);

        }  if(sub_categories_value.equals("Children"))
        {
            s.child("Children").setValue(stock);
        }  if(sub_categories_value.equals("Scarves"))
        {
            s.child("Scarves").setValue(stock);
        }  if(sub_categories_value.equals("Dolls"))
        {
            s.child("Dolls").setValue(stock);

        }  if(sub_categories_value.equals("Sculptures"))
        {
            s.child("Sculptures").setValue(stock);

        }  if(sub_categories_value.equals("Paintings"))
        {
            s.child("Paintings").setValue(stock);

        }  if(sub_categories_value.equals("Jewelry"))
        {
            s.child("Jewelry").setValue(stock);
        }
        if(sub_categories_value.equals("Protein Supplements"))
        {
            s.child("Protein Supplements").setValue(stock);
        }
        if(sub_categories_value.equals("Health Food"))
        {

        }  if(sub_categories_value.equals("Snacks"))
        {
            s.child("Snacks").setValue(stock);
        }
        if(sub_categories_value.equals("Vitamin Supplements"))
        {
            s.child("Vitamin Supplements").setValue(stock);
        }
        if(sub_categories_value.equals("Health Accesories"))
        {
            s.child("Health Accesories").setValue(stock);
        }
        if(sub_categories_value.equals("Appliance Covers"))
        {
            s.child("Appliance Covers").setValue(stock);
        }
        if(sub_categories_value.equals("Garment Covers"))
        {
            s.child("Garment Covers").setValue(stock);
        }
        if(sub_categories_value.equals("School Bags"))
        {
            s.child("School Bags").setValue(stock);
        }
        if(sub_categories_value.equals("Handbags"))
        {
            s.child("Handbags").setValue(stock);
        }
        if(sub_categories_value.equals("Travel Luggage"))
        {
            s.child("Travel Luggage").setValue(stock);
        }
        if(sub_categories_value.equals("Hip hop music"))
        {
            s.child("Hip hop music").setValue(stock);
        }
        if(sub_categories_value.equals("Classical music"))
        {
            s.child("Classical music").setValue(stock);
        }

        if(sub_categories_value.equals("Electronic dance music"))
        {
            s.child("Electronic dance music").setValue(stock);
        }
        if(sub_categories_value.equals("Jazz"))
        {
            s.child("Jazz").setValue(stock);
        }
        if(sub_categories_value.equals("Reggae"))
        {
            s.child("Reggae").setValue(stock);
        }
        if(sub_categories_value.equals("Drums & Percussion"))
        {
            s.child("Drums & Percussion").setValue(stock);
        }
        if(sub_categories_value.equals("Electronic Instruments"))
        {
            s.child("Electronic Instruments").setValue(stock);
        }
        if(sub_categories_value.equals("Studio/Stage Equipment & Accessories"))
        {
            s.child("Studio/Stage Equipment & Accessories").setValue(stock);
        }
        if(sub_categories_value.equals("Keys & Synthesizers"))
        {
            s.child("Keys & Synthesizers").setValue(stock);

        }if(sub_categories_value.equals("Wind Instruments"))
        {
            s.child("Wind Instruments").setValue(stock);
        }
        if(sub_categories_value.equals("Cricket"))
        {
            s.child("Cricket").setValue(stock);
        }
        if(sub_categories_value.equals("Badminton"))
        {
            s.child("Badminton").setValue(stock);
        }if(sub_categories_value.equals("Cycling"))
        {
            s.child("Cycling").setValue(stock);

        }if(sub_categories_value.equals("Camping & Hiking"))
        {
            s.child("Camping & Hiking").setValue(stock);
        }
        if(sub_categories_value.equals("Football"))
        {
            s.child("Football").setValue(stock);
        }
        if(sub_categories_value.equals("Art & Craft Toys"))
        {
            s.child("Art & Craft Toys").setValue(stock);
        }
        if(sub_categories_value.equals("Puzzles"))
        {
            s.child("Puzzles").setValue(stock);

        }if(sub_categories_value.equals("Soft Toys"))
        {
            s.child("Soft Toys").setValue(stock);

        }if(sub_categories_value.equals("Learning & Educational Toys"))
        {
            s.child("Learning & Educational Toys").setValue(stock);

        }
        if(sub_categories_value.equals("Remote Control Toys"))
        {
            s.child(" Remote Control Toys").setValue(stock);
        }*/

    }
}
