package com.example.shopon_4vendor.StoringFinalDetail;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.shopon_4vendor.PayTm.payTm;
import com.example.shopon_4vendor.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Gallery_image_click extends AppCompatActivity implements View.OnClickListener {
    ImageView take_pic;
    final static  int gallery_req=2;
    ProgressDialog mprogress;
    int check;
    String photoStringLink;
    Uri uri;
    TextView image_text;
    FirebaseAuth firebaseAuth;
    StorageReference mStorageRef;
    Button click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image_click);

        firebaseAuth = FirebaseAuth.getInstance();
        image_text=findViewById(R.id.image_text);
        mprogress=new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        take_pic=findViewById(R.id.Take_picture);
        click=findViewById(R.id.click);
        check=0;
        click.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(check==0)
        {

            mprogress.setMessage("Uploading..");
            mprogress.show();

            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,gallery_req);
            Toast.makeText(Gallery_image_click.this, "Picture is clicked", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Gallery_image_click.this, "image is  clicked", Toast.LENGTH_SHORT).show();
            storing_to_firebase();
            Intent i=new Intent(Gallery_image_click.this, payTm.class);
            i.putExtra("From","Gallery");
            startActivity(i);
        }
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

                    Toast.makeText(Gallery_image_click.this, "upload Done..", Toast.LENGTH_SHORT).show();
                   // Drawable d=getResources().getDrawable(R.drawable.checked);
                    //click.setVisibility(View.INVISIBLE);



                    Uri imgUri=  Uri.parse(String.valueOf(uri));
                   // take_pic.setImageURI(null);
                   // take_pic.setImageURI(imgUri);
                    image_text.setVisibility(View.GONE);
                    Glide.with(Gallery_image_click.this).load(imgUri).asBitmap().centerCrop().into(new BitmapImageViewTarget(take_pic) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(Gallery_image_click.this.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            take_pic.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                    click.setText("Save");
                    mprogress.dismiss();
                    check=1;

                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void storing_to_firebase() {
        Firebase.setAndroidContext(this);

        Firebase database;
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        Firebase shop_detail = database.child("Shop_Detail");
        Firebase VendorLoginDeatail = shop_detail .child(currentuser);
        Firebase Image=VendorLoginDeatail.child("image");
        Image.setValue(photoStringLink);
    }


}
