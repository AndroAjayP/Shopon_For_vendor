package com.example.shopon_4vendor.Product_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4vendor.PayTm.payTm;
import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.categories.activity.Choose_product_categories;
import com.example.shopon_4vendor.categories.activity.Home;
import com.example.shopon_4vendor.offersAndStock_mgt.offers;
import com.example.shopon_4vendor.offersAndStock_mgt.stockMgt;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class productView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mDatabaseRef;
    RecyclerView recyclerView;
    ArrayList<View_inflator> arrayList;
    View_Adapter  adapter_for_converting;
    String quary_string;
    ProgressBar loading;
    static String orderBy = "Address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Customer Orders");
        recyclerView = findViewById(R.id.Malls_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        loading=findViewById(R.id.loading);

        arrayList = new ArrayList<View_inflator>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    View_inflator obj = dataSnapshot1.getValue(View_inflator.class);
                    arrayList.add(obj);
                }
                adapter_for_converting = new View_Adapter(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter_for_converting);
                loading.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(productView.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(productView.this, Home.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_view, menu);
        MenuItem item = menu.findItem(R.id.orders_product_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                firebaseUserSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.orders_product_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.additem) {
            startActivity(new Intent(productView.this, Home.class));

        } else if (id == R.id.pcategories) {
            Intent i=new Intent(productView.this, Choose_product_categories.class);
            i.putExtra("From","Home");
            startActivity(i);

        } else if (id == R.id.Offers) {

            //Toast.makeText(this, "Thoda intajar ka mja lijiye...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(productView.this, offers.class));

        } else if (id == R.id.stock) {

            //Toast.makeText(this, "Thoda intajar ka mja lijiye...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(productView.this, stockMgt.class));
        } else if (id == R.id.payTM_paymnemt) {
            Intent i=new Intent(productView.this, payTm.class);
            i.putExtra("From","productV");
            startActivity(i);

        } else if (id == R.id.nav_send) {

            Toast.makeText(this, "Thoda intajar ka mja lijiye...", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void firebaseUserSearch(String searchText) {

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        Query firebaseSearchQuery = mDatabaseRef.orderByChild(orderBy).startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<View_inflator, UsersViewHolder> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<View_inflator, productView.UsersViewHolder>(

                View_inflator.class,
                R.layout.view_design,
                productView.UsersViewHolder.class,
                firebaseSearchQuery
        ) {

            @Override
            protected void populateViewHolder(productView.UsersViewHolder viewHolder, final View_inflator model, final int position) {
                viewHolder.setDetails(getApplicationContext(),model.getAddress(), model.getProduct_descripyion(), model.getImagurl(), model.getPrice());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String description = model.getProduct_descripyion();
                        String priceNM = model.getPrice();
                        String imageNM = model.getImagurl();
                        String address=model.getAddress();
                        String mobile=model.getMobile();
                        String name=model.getName();
                        Intent i = new Intent(productView.this, orderView.class);
                        i.putExtra("desc", description);
                        i.putExtra("price", priceNM);
                        i.putExtra("image", imageNM);
                        i.putExtra("address",address);
                        Toast.makeText(productView.this, "Address is "+address, Toast.LENGTH_SHORT).show();
                        i.putExtra("mobile",mobile);
                        i.putExtra("name",name);
                        Toast.makeText(productView.this, "Name is "+name, Toast.LENGTH_SHORT).show();
                        productView.this.startActivity(i);

                    }
                });
            }
        };
        recyclerView.setAdapter(FirebaseRecyclerAdapter);


    }
public static class UsersViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public UsersViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

    }
    public void setDetails(Context ctx ,String address,String description,String pro_image, String price){
        TextView Description,Price,Address;
        ImageView Pro_image;



        Address=mView.findViewById(R.id.Address);
        Pro_image= mView.findViewById(R.id.pro_image);
        //Price=mView.findViewById(R.id.price);
        Description=mView.findViewById(R.id.description);

        Address.setText(address);
        //Price.setText(price);
        Description.setText(description);

        Glide.with(ctx).load(pro_image).into(Pro_image);


    }



}


}
