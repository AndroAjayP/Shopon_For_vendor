package com.example.shopon_4vendor.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4vendor.DiscreteScrollViewOptions;
import com.example.shopon_4vendor.StoringFinalDetail.Goods_detailing;
import com.example.shopon_4vendor.R;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;
public class ShopActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener {
    private List<Item> data;
    private Shop shop;
    public String str;
    private TextView currentItemName;
    private ImageView rateItemButton;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        currentItemName = findViewById(R.id.item_name);
        rateItemButton =  findViewById(R.id.item_btn_rate);

          shop = Shop.get();//yhi chahiye tha
        if(getIntent().getStringExtra("data").equals("Clothing"))
        {
            data = shop.getData("T-Shirts","Shirts","Jeans","Sports Wear","Trousers",R.drawable.tsh,R.drawable.shir,R.drawable.jeans,R.drawable.sporw,R.drawable.trou);
        }
        if(getIntent().getStringExtra("data").equals("CellPhones"))
        {
            data = shop.getData("Apple iPhones.","Samsung Galaxy Phones","HTC phones","LG phones","Oppo phones",R.drawable.apple,R.drawable.samsung,R.drawable.htc,R.drawable.lg,R.drawable.oppo);
        }
         if(getIntent().getStringExtra("data").equals("Electronics"))
        {
            data = shop.getData("Air conditioner","Air purifier","Blender","Ceiling fan","vacuum cleaner",R.drawable.ac,R.drawable.air_p,R.drawable.blender,R.drawable.cel_fan,R.drawable.vac_clea);
        }
         if(getIntent().getStringExtra("data").equals("Shoes"))
        {
            data = shop.getData("Sports Shoes","Casual Shoes","Formal Shoes","Sandals & Floaters","Running Shoes",R.drawable.spo_sho,R.drawable.cas_sho,R.drawable.form_sho,R.drawable.sandle,R.drawable.running_sho);
        }
         if(getIntent().getStringExtra("data").equals("Books"))
        {
            data = shop.getData("Entrance Exams","Academic","Literature & Fiction","Indian Writing","Children",R.drawable.entran_exam,R.drawable.aca_book,R.drawable.liter_book,R.drawable.indian_wri,R.drawable.chil_book);
        }
         if(getIntent().getStringExtra("data").equals("Handmade"))
        {
            data = shop.getData("Jewelry","Paintings","Sculptures","Dolls","Scarves",R.drawable.jwel,R.drawable.paint,R.drawable.sculpture,R.drawable.dolls,R.drawable.scarves);
        }
         if(getIntent().getStringExtra("data").equals("Health"))
        {
            data = shop.getData("Protein Supplements","Health Food","Snacks","Vitamin Supplements","Health Accesories",R.drawable.protein,R.drawable.health_food,R.drawable.snacks,R.drawable.vitamin_suple,R.drawable.health_accesories);
        }
         if(getIntent().getStringExtra("data").equals("Luggage"))
        {
            data = shop.getData("Travel Luggage","Handbags","School Bags","Garment Covers","Appliance Covers",R.drawable.travel_lug,R.drawable.hand_bag,R.drawable.school_bag,R.drawable.garment,R.drawable.appliance_cover);
        }
         if(getIntent().getStringExtra("data").equals("Music"))
        {
            data = shop.getData("Hip hop music","Classical music","Electronic dance music","Jazz","Reggae",R.drawable.hip_hop,R.drawable.classical_music,R.drawable.edm,R.drawable.jazz,R.drawable.raggea);
        }
         if(getIntent().getStringExtra("data").equals("MusicalInstrument"))
        {
            data = shop.getData("Wind Instruments","Keys & Synthesizers","Studio/Stage Equipment & Accessories","Electronic Instruments","Drums & Percussion",R.drawable.wild_instru,R.drawable.keys_synthe,R.drawable.stage_equip,R.drawable.electro_instr,R.drawable.dums);
        }
         if(getIntent().getStringExtra("data").equals("Sports"))
        {
            data = shop.getData("Cricket","Badminton","Cycling","Camping & Hiking","Football",R.drawable.cric,R.drawable.badminton,R.drawable.cycling,R.drawable.campig_hicking,R.drawable.football);
        }
         if(getIntent().getStringExtra("data").equals("Toys"))
        {
            data = shop.getData("Remote Control Toys","Learning & Educational Toys","Soft Toys","Puzzles","Art & Craft Toys",R.drawable.remote_control_toys,R.drawable.learning_toy,R.drawable.soft_toy,R.drawable.puzzle,R.drawable.art_and_craft_toys);
        }

        itemPicker =  findViewById(R.id.item_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new ShopAdapter(data));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        onItemChanged(data.get(0));

        findViewById(R.id.item_btn_rate).setOnClickListener(this);
        findViewById(R.id.item_btn_buy).setOnClickListener(this);
        findViewById(R.id.item_btn_comment).setOnClickListener(this);

        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.btn_smooth_scroll).setOnClickListener(this);
        findViewById(R.id.btn_transition_time).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_btn_rate:
                int realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                Item current = data.get(realPosition);
                shop.setRated(current.getId(), !shop.isRated(current.getId()));
                changeRateButtonState(current);
                break;
            case R.id.home:
                finish();
                break;
            case R.id.btn_transition_time:
                DiscreteScrollViewOptions.configureTransitionTime(itemPicker);
                break;
            case R.id.btn_smooth_scroll:
                DiscreteScrollViewOptions.smoothScrollToUserSelectedPosition(itemPicker, v);
                break;
            case R.id.item_btn_buy:    //on bye button click
                move();

            default:
                showUnsupportedSnackBar();
                break;
        }
    }
    private void onItemChanged(Item item) {
         str=item.getName();
        currentItemName.setText(item.getName());
        changeRateButtonState(item);
        Toast.makeText(this, "is clicked"+item.getName(), Toast.LENGTH_SHORT).show();

    }
    public void move()
    {

        if(getIntent().getStringExtra("data").equals("Clothing"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Clothing");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("CellPhones"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Cell Phones");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Electronics"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Elec");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Shoes"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Shoes");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Books"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Books");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Handmade"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Handmade");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Health"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Health");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Luggage"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Luggage");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Music"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Music");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("MusicalInstrument"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Musical Instruments");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Sports"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Sports");
            startActivity(i);
        }
        if(getIntent().getStringExtra("data").equals("Toys"))
        {
            Intent i=new Intent(ShopActivity.this, Goods_detailing.class);
            i.putExtra("item",str);
            i.putExtra("Type","Toys");
            startActivity(i);
        }
    }

    private void changeRateButtonState(Item item) {
        if (shop.isRated(item.getId())) {
            rateItemButton.setImageResource(R.drawable.ic_star_black_24dp);
            rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopRatedStar));
        } else {
            rateItemButton.setImageResource(R.drawable.ic_star_border_black_24dp);
            rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopSecondary));
        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(data.get(positionInDataSet));
    }

    private void showUnsupportedSnackBar() {
        Snackbar.make(itemPicker, R.string.msg_unsupported_op, Snackbar.LENGTH_SHORT).show();
    }
}
