package com.example.shopon_4vendor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopon_4vendor.Product_view.productView;
import com.example.shopon_4vendor.signup_login.userClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

public class Spalsh_screen extends AppCompatActivity {
    Handler handler = new Handler();
    ProgressBar loading;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Paper.init(Spalsh_screen.this);
            String emailkey = Paper.book().read(userClass.emailkey);
            String passkey = Paper.book().read(userClass.passkey);
            if (!(TextUtils.isEmpty(emailkey) && TextUtils.isEmpty(passkey))) {
                Allowacces(emailkey, passkey);
            }
            else
            {
                loading.setVisibility(View.GONE);
                startActivity(new Intent(Spalsh_screen.this,WelcomeActivity.class));
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        loading=findViewById(R.id.splash_loading);
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }
    private void Allowacces(String emailKey, String passKey) {

        final String Extracted_email = "";
        final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        (firebaseAuth.signInWithEmailAndPassword(emailKey, passKey))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loading.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(Spalsh_screen.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Spalsh_screen.this, productView.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            i.putExtra("Email_id", Extracted_email);
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Spalsh_screen.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


}
