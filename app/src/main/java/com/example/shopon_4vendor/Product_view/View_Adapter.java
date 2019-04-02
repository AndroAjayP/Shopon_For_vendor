package com.example.shopon_4vendor.Product_view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4vendor.R;

import java.util.ArrayList;

public class View_Adapter extends RecyclerView.Adapter<View_Adapter.Holder> {
    Context context;
    private ArrayList<View_inflator> object;

    public View_Adapter(Context c, ArrayList<View_inflator> Object) {
        context = c;
        object = Object;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_design, viewGroup, false);
        return new Holder(v, context, object);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder myViewHolder, int i) {

        myViewHolder.Address.setText(object.get(i).getAddress());
        myViewHolder.description.setText(object.get(i).getProduct_descripyion());

        //  myViewHolder.model.setText(object.get(i).model);

        Glide.with(context)
                .load(object.get(i).getImagurl())
                .into(myViewHolder.pro_image);
    }

    @Override
    public int getItemCount() {
        //Toast.makeText(context, "Size is"+object.size(), Toast.LENGTH_SHORT).show();
        return object.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,price,description,user_uid,Mobile,Address;
        ImageView pro_image;
        RelativeLayout parentLayout;
        Context context;
        private ArrayList<View_inflator> object;

        Holder(@NonNull View itemView, Context context, ArrayList<View_inflator> object) {
            super(itemView);
            this.object = object;
            this.context = context;
            itemView.setOnClickListener(this);
            pro_image= itemView.findViewById(R.id.pro_image);
            price=itemView.findViewById(R.id.price);
            //model=itemView.findViewById(R.id.model);
            Address=itemView.findViewById(R.id.Address);
            description=itemView.findViewById(R.id.description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            View_inflator  record = this.object.get(position);
            String priceNM = record.getPrice();
            String imageNM=record.getImagurl();
            String desc=record.getProduct_descripyion();
            String address=record.getAddress();
            String mobile=record.getMobile();
            String name=record.getName();


            Intent i = new Intent(this.context, orderView.class);
            i.putExtra("desc", desc);
            i.putExtra("price", priceNM);
            Toast.makeText(context, "Price  is "+priceNM, Toast.LENGTH_SHORT).show();
            i.putExtra("image", imageNM );
            i.putExtra("mobile",mobile);
            i.putExtra("address",address);

            i.putExtra("name",name);
            Toast.makeText(context, "Name is "+name, Toast.LENGTH_SHORT).show();
            context.startActivity(i);

        }
    }
}


