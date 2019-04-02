package com.example.shopon_4vendor.StoringFinalDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.signup_login.Storing_to_firebase;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Goods_detailing extends AppCompatActivity {
    EditText name, price, brand, model, size, color, desc;
    ImageView back_btn, click, setPhoto;
    Button submit;
    Uri uri;
    TextView textCamera;
    FrameLayout model_frame, brand_frmae;
    final static int gallery_req = 2;
    private StorageReference mstorage;
    private DatabaseReference mDatabase;
    ProgressDialog mprogress;
    String getDownloaurl, photoStringLink;
    boolean check;
    FirebaseUser currentFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detailing);
         currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        DatabaseReference databaseReference;
        Firebase.setAndroidContext(this);
        mprogress = new ProgressDialog(this);
        mstorage = FirebaseStorage.getInstance().getReference();

        price = findViewById(R.id.price);
        brand = findViewById(R.id.brand);
        model = findViewById(R.id.Model_no);
        size = findViewById(R.id.size);
        color = findViewById(R.id.color);
        desc = findViewById(R.id.desc);
        textCamera = findViewById(R.id.textCamera);
        setPhoto = findViewById(R.id.setPhoto);
        click = findViewById(R.id.click);
        submit = findViewById(R.id.Submit);


        brand.setText(getIntent().getStringExtra("item"));
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        model_frame = findViewById(R.id.model_frame);
        brand_frmae = findViewById(R.id.brand_frame);
        makeInvisible();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogress.setMessage("Uploading..");
                mprogress.show();

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, gallery_req);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   savetoFirebase();
                   if(check)
                   {
                       startActivity(new Intent(Goods_detailing.this, Succesfully.class));
                   }

                     else
                   {
                       Toast.makeText(Goods_detailing.this, "Check karlo kuch chuta hai..", Toast.LENGTH_SHORT).show();
                   }



            }

        });


    }

    private void savetoFirebase() {
        String Brand;
        String Name;
        String Size;
        String Color;
        String Description;
        String Price;
        String Model;

        Brand = brand.getText().toString().trim();
        Price = price.getText().toString().trim();
        Size = size.getText().toString().trim();
        Color = color.getText().toString().trim();
        Description = desc.getText().toString().trim();
        Model = model.getText().toString().trim();

        if (!(TextUtils.isEmpty(Brand) ||  TextUtils.isEmpty(Price) || TextUtils.isEmpty(photoStringLink) || TextUtils.isEmpty(Description) )) {
            Storing_to_firebase storing_to_firebase = new Storing_to_firebase( Brand, Size, Color, Description, Price, photoStringLink, Model,  currentFirebaseUser.getUid());
            mDatabase.push().setValue(storing_to_firebase);
            check=true;
        }


    }

    private void makeInvisible() {
        if (getIntent().getStringExtra("Type").equals("Clothing")) {
            Toast.makeText(this, "model is invisible", Toast.LENGTH_SHORT).show();
            model_frame.setVisibility(View.GONE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Clothing");
        }
        if (getIntent().getStringExtra("Type").equals("Cell Phones")) {
           // brand_frmae.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("CellPhones");
        }
        if (getIntent().getStringExtra("Type").equals("Elec")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Electronics");
        }
        if (getIntent().getStringExtra("Type").equals("Shoes")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Shoes");
        }
        if (getIntent().getStringExtra("Type").equals("Books")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Books");
        }
        if (getIntent().getStringExtra("Type").equals("Handmade")) {

            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Handmade");
        }
        if (getIntent().getStringExtra("Type").equals("Health")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Health");
        }
        if (getIntent().getStringExtra("Type").equals("Luggage")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Luggage");
        }
        if (getIntent().getStringExtra("Type").equals("Music")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Music");
        }
        if (getIntent().getStringExtra("Type").equals("Musical Instruments")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Musical Instrument");
        }
        if (getIntent().getStringExtra("Type").equals("Sports")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Sports");
        }
        if (getIntent().getStringExtra("Type").equals("Toys")) {
            model_frame.setVisibility(View.INVISIBLE);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(currentFirebaseUser.getUid()).child("Toys");
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == gallery_req && resultCode == RESULT_OK) {
            uri = data.getData();
            StorageReference filepath = mstorage.child("photo").child(uri.getLastPathSegment().concat(".jpg"));
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    getDownloaurl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    Toast.makeText(Goods_detailing.this, "upload Done..", Toast.LENGTH_SHORT).show();
                    Drawable d = getResources().getDrawable(R.drawable.checked);
                    //click.setVisibility(View.INVISIBLE);
                    click.setImageDrawable(d);
                    textCamera.setText("uploaded");
                    mprogress.dismiss();

                    Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            photoStringLink = uri.toString();
                        }
                    });


                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

   