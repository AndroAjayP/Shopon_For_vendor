package com.example.shopon_4vendor.categories.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4vendor.R;
import com.example.shopon_4vendor.categories.model.inflating_from_firebase;
import com.example.shopon_4vendor.shop.ShopActivity;

import java.util.ArrayList;

public class Adapter_for_converting extends RecyclerView.Adapter<Adapter_for_converting.MyViewHolder>{
    Context context;
    String value;
    private ArrayList<inflating_from_firebase>  object;


    public Adapter_for_converting(Context c, ArrayList<inflating_from_firebase> Object)
    {
         context=c;
         object=Object;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.malls_view_design,viewGroup,false);
        return new MyViewHolder(v,context,object);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

       // Toast.makeText(context, "value of i is "+i, Toast.LENGTH_SHORT).show();
              myViewHolder.pro_name.setText(object.get(i).getPro());
    }

    @Override
    public int getItemCount()
    {
        //Toast.makeText(context, "Size is"+object.size(), Toast.LENGTH_SHORT).show();
        return object.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        TextView pro_name;
        RelativeLayout parentLayout;
        Context context;
        private ArrayList<inflating_from_firebase>  object;
        MyViewHolder(@NonNull View itemView,Context context,ArrayList<inflating_from_firebase>  object) {
            super(itemView);
            this.object=object;
            this.context=context;
            itemView.setOnClickListener(this);
            pro_name=itemView.findViewById(R.id.pro_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            inflating_from_firebase record=this.object.get(position);
             String data=record.getPro();
               Intent i=new Intent(this.context, ShopActivity.class);
              Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
            if(data.equals("Clothing"))
            {
                Toast.makeText(context, "Clothing", Toast.LENGTH_SHORT).show();
                  i.putExtra("data","Clothing");
                  this.context.startActivity(i);
            }
            if(data.equals("CellPhones"))
            {
                Toast.makeText(context, "CellPhones", Toast.LENGTH_SHORT).show();
                i.putExtra("data","CellPhones");
                this.context.startActivity(i);
            }
            if(data.equals("Electronics"))
            {
                Toast.makeText(context, "Electronics", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Electronics");
                this.context.startActivity(i);
            }
            if(data.equals("Shoes"))
            {
                Toast.makeText(context, "Shoes", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Shoes");
                this.context.startActivity(i);
            }
            if(data.equals("Books"))
            {
                Toast.makeText(context, "Books", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Books");
                this.context.startActivity(i);
            }
            if(data.equals("Handmade"))
            {
                Toast.makeText(context, "Handmade", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Handmade");
                this.context.startActivity(i);
            }
            if(data.equals("Health"))
            {
                Toast.makeText(context, "Health", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Health");
                this.context.startActivity(i);
            }
            if(data.equals("Luggage"))
            {
                Toast.makeText(context, "Luggage", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Luggage");
                this.context.startActivity(i);
            }
            if(data.equals("Music"))
            {
                Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Music");
                this.context.startActivity(i);
            }
            if(data.equals("MusicalInstrument"))
            {
                Toast.makeText(context, "MusicalInstrument", Toast.LENGTH_SHORT).show();
                i.putExtra("data","MusicalInstrument");
                this.context.startActivity(i);
            }
            if(data.equals("Sports"))
            {
                Toast.makeText(context, "Sports", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Sports");
                this.context.startActivity(i);
            }
            if(data.equals("Toys"))
            {
                Toast.makeText(context, "Toys", Toast.LENGTH_SHORT).show();
                i.putExtra("data","Toys");
                this.context.startActivity(i);
            }



        }
    }
}
