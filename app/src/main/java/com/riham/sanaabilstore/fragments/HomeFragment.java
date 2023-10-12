package com.riham.sanaabilstore.fragments;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.activities.ShowAllActivity;
import com.riham.sanaabilstore.adapters.CategoryAdapter;
import com.riham.sanaabilstore.adapters.NewProductsAdapter;
import com.riham.sanaabilstore.adapters.PopularProductsAdapter;
import com.riham.sanaabilstore.models.CategoryModel;
import com.riham.sanaabilstore.models.NewProductsModel;
import com.riham.sanaabilstore.models.PopularProductsModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView catRecyclerview,newProductRecyclerview,popularRecyclerview;
    TextView catSeeAll,newSeeAll,popSeeAll;

    ProgressDialog progressDialog;
    LinearLayout linearLayout;


    //category RecyclerView
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //newProduct RecyclerView
    NewProductsAdapter newProductsAdapter ;
    List<NewProductsModel> newProductsModelList;

    //popular RecyclerView
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList ;

    //FireStore
    FirebaseFirestore db ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();

        catRecyclerview = root.findViewById(R.id.rec_category);
        newProductRecyclerview= root.findViewById(R.id.new_product_rec);
        popularRecyclerview= root.findViewById(R.id.popular_rec);
        progressDialog= new ProgressDialog(getActivity());


        catSeeAll= root.findViewById(R.id.category_see_all);
        newSeeAll= root.findViewById(R.id.newProducts_see_all);
        popSeeAll= root.findViewById(R.id.popular_see_all);


        catSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        newSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        linearLayout= root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);

        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.onboard1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.onboard2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.onboard3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.onboard4, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.onboard5, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.onboard6, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome To Sanaabil Sports");
        progressDialog.setMessage("Please Wait.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //category

        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList= new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel= document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        //newProduct

        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList= new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getActivity(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductsModel newProductsModel= document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //popularProducts
        popularRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularProductsModelList= new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getActivity(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel= document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }
}