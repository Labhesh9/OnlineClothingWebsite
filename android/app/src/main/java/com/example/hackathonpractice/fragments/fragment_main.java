package com.example.hackathonpractice.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.ProductListAdapter;
import com.example.hackathonpractice.adapter.QuoteListAdapter;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_main extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recycleViewProducts;

    ProductListAdapter productListAdapter;

    List<Products> productsList;

    ImageView maleImage, femaleImage, imgBabys, imgSport, imgKids;
    int current_cat=1;


    public fragment_main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_main.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_main newInstance(String param1, String param2) {
        fragment_main fragment = new fragment_main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        maleImage = view.findViewById(R.id.imgMale);
        femaleImage = view.findViewById(R.id.imgFemale);
        imgBabys = view.findViewById(R.id.imgBabys);
        imgSport = view.findViewById(R.id.imgSport);
        imgKids = view.findViewById(R.id.imgKids);
        recycleViewProducts = view.findViewById(R.id.recycleViewProducts);
        productsList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(getContext(), productsList) ;
        recycleViewProducts.setAdapter(productListAdapter);
        recycleViewProducts.setLayoutManager(new GridLayoutManager(getContext(),1));
        //recycleViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

        maleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,1);
                current_cat =1;
            }
        });

        femaleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,6);
                current_cat =6;
            }
        });

        imgBabys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,3);
                current_cat =3;
            }
        });

        imgKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,4);
                current_cat =4;
            }
        });

        imgSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,5);
                current_cat =5;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
       // FuncUtils.getAllProductWithPrice(productsList,getContext(),productListAdapter);
        FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,current_cat);
    }


//    public void maleClick(View view) {
//        FuncUtils.getAllProductWithPriceByAgetypesId(productsList,getContext(),productListAdapter,1);
//    }
}