package com.example.shopon_4vendor.signup_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.StoringFinalDetail.Gallery_image_click;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.paperdb.Paper;

public class signupActivity extends AppCompatActivity {

    Button sign;
    FirebaseAuth firebaseAuth;
    StorageReference mStorageRef;
    TextView login,gallery_text;
    EditText email, c_pass, password, stornm, add;
    String Extracted_email = "";
    ProgressDialog mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);

        mprogress=new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        email = findViewById(R.id.email);
       // categories=findViewById(R.id.categories);
        Paper.init(signupActivity.this);
        password = findViewById(R.id.password);
        c_pass=findViewById(R.id.c_pass);
        stornm = findViewById(R.id.Full_name);
        add = findViewById(R.id.add);
        gallery_text=findViewById(R.id.TakePicture);



        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(signupActivity.this,MainActivity.class));
            }
        });
        sign = findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!c_pass.getText().toString().equals(password.getText().toString())||email.getText().toString().length() < 1 || password.getText().toString().length() < 1 || stornm.getText().toString().length() < 1 || add.getText().toString().length() < 1 ) {


                    if (stornm.getText().toString().length() < 1) {
                        stornm.setError("Store Name is Empty");
                    }

                    if (add.getText().toString().length() < 1) {
                        add.setError("Address is Empty");
                    }
                    if(!c_pass.getText().toString().equals(password.getText().toString()))
                    {
                        c_pass.setError("password not matched");
                    }

                    if (email.getText().toString().length() < 1) {
                        email.setError("Email is empty");
                    }
                    if (password.getText().toString().length() < 1) {
                        password.setError("password is empty");
                    }
                } else {

                    final ProgressDialog progressDialog = ProgressDialog.show(signupActivity.this, "Please wait...", "Processing...", true);
                    (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();

                                    if (task.isSuccessful()) {


                                        Toast.makeText(signupActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(signupActivity.this, Gallery_image_click.class);
                                        storing_to_firebase();
                                      //  i.putExtra("CATEGORIES IS", mSpinner.getSelectedItemsAsString());
                                        startActivity(i);
                                    } else {
                                        Log.e("ERROR", task.getException().toString());
                                        Toast.makeText(signupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                    Paper.book().write(userClass.emailkey, email.getText().toString());
                    Paper.book().write(userClass.passkey, password.getText().toString());
                }
            }

        });
    }




    public void storing_to_firebase() {
        Firebase.setAndroidContext(this);

        Firebase database;

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(signupActivity.this, "id is " + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i <= email.getText().toString().length() && email.getText().toString().charAt(i) != '@'; i++) {
            Extracted_email = Extracted_email + email.getText().toString().charAt(i);

        }
        Toast.makeText(this, "" + Extracted_email, Toast.LENGTH_SHORT).show();

        Firebase shop_detail = database.child("Shop_Detail");
        Firebase s = shop_detail.child(currentFirebaseUser.getUid());

        Firebase Shop_name = s.child("Shop_name");
        Shop_name.setValue(stornm.getText().toString());

        Firebase Address = s.child("Address");
        Address.setValue(add.getText().toString());
        s.child("UID").setValue(currentFirebaseUser.getUid());


    }




}