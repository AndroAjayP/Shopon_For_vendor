package com.example.shopon_4vendor.categories.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.categories.adapter.Adapter_for_converting;
import com.example.shopon_4vendor.categories.model.inflating_from_firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DatabaseReference mDatabaseRef;
    RecyclerView recyclerView;
    ArrayList<inflating_from_firebase> arrayList;
    Adapter_for_converting adapter_for_converting;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        recyclerView=findViewById(R.id.Malls_list);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Product_catalog").child(currentFirebaseUser.getUid());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loading=findViewById(R.id.loading);


        arrayList = new ArrayList<inflating_from_firebase>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    inflating_from_firebase obj = dataSnapshot1.getValue(inflating_from_firebase.class);
                    arrayList.add(obj);
                }
                adapter_for_converting = new Adapter_for_converting(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter_for_converting);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
