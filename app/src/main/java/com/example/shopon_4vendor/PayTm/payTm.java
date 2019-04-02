package com.example.shopon_4vendor.PayTm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.shopon_4vendor.Product_view.productView;
import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.categories.activity.Choose_product_categories;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class payTm extends AppCompatActivity {

    EditText paytm_no;
    Button qr_code,paytm_final;
    final static  int gallery_req=2;
    String photoStringLink;
    Uri uri;
    StorageReference mStorageRef;
    ProgressDialog mprogress;
    int check;
    ImageView qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_tm);
        paytm_no=findViewById(R.id.paytm_no);
        qr_code=findViewById(R.id.qr_code);
        qr=findViewById(R.id.qr);
        paytm_final=findViewById(R.id.paytm_final);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mprogress=new ProgressDialog(this);
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    mprogress.setMessage("Uploading..");
                    mprogress.show();

                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,gallery_req);
                    Toast.makeText(payTm.this, "Picture is clicked", Toast.LENGTH_SHORT).show();
            }

        });

        paytm_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check!=0 && paytm_no.getText().toString().length()>1)
                {
                    storing_to_firebase();
                    Toast.makeText(payTm.this, "Successfully Submitted..", Toast.LENGTH_SHORT).show();
                    if(getIntent().getStringExtra("From").equals("productV"));
                    {
                        startActivity(new Intent(payTm.this, productView.class));
                    }
                    if(getIntent().getStringExtra("From").equals("Gallery"))
                    {
                        Intent i=new Intent(payTm.this, Choose_product_categories.class);
                        i.putExtra("From","Pay_tm");
                        startActivity(i);
                    }

                }
                else
                {
                    Toast.makeText(payTm.this, "Fill Details First...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        Toast.makeText(this, "in record code result"+requestCode, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "in result code result"+resultCode, Toast.LENGTH_SHORT).show();
        if(requestCode==gallery_req && resultCode==RESULT_OK)
        {
            uri=data.getData();
            StorageReference filepath=mStorageRef.child("photo").child(uri.getLastPathSegment().concat(".jpg"));
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            photoStringLink = uri.toString();
                        }
                    });

                    Toast.makeText(payTm.this, "upload Done..", Toast.LENGTH_SHORT).show();
                    // Drawable d=getResources().getDrawable(R.drawable.checked);
                    //click.setVisibility(View.INVISIBLE);



                    Uri imgUri=  Uri.parse(String.valueOf(uri));
                    // take_pic.setImageURI(null);
                    // take_pic.setImageURI(imgUri);

                    Glide.with(payTm.this).load(imgUri).asBitmap().centerCrop().into(new BitmapImageViewTarget(qr) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(payTm.this.getResources(), resource);
                            circularBitmapDrawable.setCircular(false);
                            qr.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                    qr_code.setVisibility(View.INVISIBLE);
                    mprogress.dismiss();
                    check=1;

                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void storing_to_firebase() {
        Firebase.setAndroidContext(this);

        Firebase database;
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        Firebase shop_detail = database.child("PayTm_Details");
        Firebase VendorLoginDeatail = shop_detail .child(currentuser);
        Firebase Image=VendorLoginDeatail.child("QR");
        Image.setValue(photoStringLink);
        Firebase no=VendorLoginDeatail.child("No");
         no.setValue(paytm_no.getText().toString());
    }
}
