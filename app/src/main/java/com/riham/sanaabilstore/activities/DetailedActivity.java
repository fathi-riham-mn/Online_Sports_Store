package com.riham.sanaabilstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.models.NewProductsModel;
import com.riham.sanaabilstore.models.PopularProductsModel;
import com.riham.sanaabilstore.models.ShowAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class DetailedActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView detailedImg;
    TextView name,description,rating,price,quantity;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    int totalQuantity = 1;
    int totalPrice = 0;

    //New product model
    NewProductsModel newProductsModel =null;

    //Popular product model
    PopularProductsModel popularProductsModel =null;

    //Show All
    ShowAllModel showAllModal=null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        final Object obj =getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductsModel){
            newProductsModel = (NewProductsModel)obj;
        }else if (obj instanceof PopularProductsModel){
            popularProductsModel =(PopularProductsModel) obj;
        }else if (obj instanceof ShowAllModel){
            showAllModal =(ShowAllModel) obj;
        }

        detailedImg=findViewById(R.id.detailed_img);
        name=findViewById(R.id.detailed_name);
        description=findViewById(R.id.detailed_desc);
        rating=findViewById(R.id.my_rate);
        price=findViewById(R.id.detailed_price);
        addToCart=findViewById(R.id.add_to_card);
        buyNow=findViewById(R.id.buy_now);
        quantity=findViewById(R.id.quantity);
        addItems=findViewById(R.id.add_item);
        removeItems=findViewById(R.id.remove_item);


        //New Product
        if (newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            description.setText(newProductsModel.getDescription());
            rating.setText(newProductsModel.getRating());
            price.setText(String.valueOf(newProductsModel.getPrice()));

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }

        //Popular Product
        if (popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            description.setText(popularProductsModel.getDescription());
            rating.setText(popularProductsModel.getRating());
            price.setText(String.valueOf(popularProductsModel.getPrice()));

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }

        //Show All
       if (showAllModal != null){
            Glide.with(getApplicationContext()).load(showAllModal.getImg_url()).into(detailedImg);
            name.setText(showAllModal.getName());
            description.setText(showAllModal.getDescription());
            rating.setText(showAllModal.getRating());
            price.setText(String.valueOf(showAllModal.getPrice()));

           totalPrice = showAllModal.getPrice() * totalQuantity;
        }

       //Buy Now
       buyNow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);

               if (newProductsModel != null){
                   intent.putExtra("item", newProductsModel);
               }
               if (popularProductsModel != null){
                   intent.putExtra("item", popularProductsModel);
               }
               if (showAllModal != null){
                   intent.putExtra("item", showAllModal);
               }
               startActivity(intent);
           }
       });



       //add to card
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

       addItems.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (totalQuantity < 10){
                   totalQuantity++;
                   quantity.setText(String.valueOf(totalQuantity));
               }

               if (newProductsModel != null){
                   totalPrice = newProductsModel.getPrice() * totalQuantity;
               }

               if (popularProductsModel != null){
                   totalPrice = popularProductsModel.getPrice() * totalQuantity;
               }

               if (showAllModal != null){
                   totalPrice = showAllModal.getPrice() * totalQuantity;
               }

           }
       });

       removeItems.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (totalQuantity > 1){
                   totalQuantity--;
                   quantity.setText(String.valueOf(totalQuantity));
               }

           }
       });
    }

    private void addToCart() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate  = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat(  "HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);


        firestore.collection("AddToCard").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this,"Added To A Card",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
    }
}