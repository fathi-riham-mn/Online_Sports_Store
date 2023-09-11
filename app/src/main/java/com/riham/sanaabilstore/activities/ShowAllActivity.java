package com.riham.sanaabilstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.adapters.ShowAllAdapter;
import com.riham.sanaabilstore.models.ShowAllModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;

    Toolbar toolbar;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        String type = getIntent().getStringExtra("type");

        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);

        if (type == null || type.isEmpty()){
            firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Ball")){
            firestore.collection("ShowAll").whereEqualTo("type","Ball").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Bat")){
            firestore.collection("ShowAll").whereEqualTo("type","Bat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Bag")){
            firestore.collection("ShowAll").whereEqualTo("type","Bag").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Game Board")){
            firestore.collection("ShowAll").whereEqualTo("type","Game Board").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Weight")){
            firestore.collection("ShowAll").whereEqualTo("type","Weight").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Shoe")){
            firestore.collection("ShowAll").whereEqualTo("type","Shoe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Shocks")){
            firestore.collection("ShowAll").whereEqualTo("type","Shocks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("Racket")){
            firestore.collection("ShowAll").whereEqualTo("type","Racket").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc:task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }



    }
}