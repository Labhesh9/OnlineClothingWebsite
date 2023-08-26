package com.example.hackathonpractice.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.QuoteListAdapter;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.FuncUtils;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuotesListFragment extends Fragment {

    RecyclerView recycleViewMobileList;

    QuoteListAdapter quoteListAdapter;

    List<Quote> quoteList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleViewMobileList = view.findViewById(R.id.recycleViewMobileList);
        quoteList = new ArrayList<>();
        quoteListAdapter = new QuoteListAdapter(getContext(), quoteList);
        recycleViewMobileList.setAdapter(quoteListAdapter);
        recycleViewMobileList.setLayoutManager(new GridLayoutManager(getContext(),1));
       // getAllQuote();

    }




    @Override
    public void onResume() {
        super.onResume();
        FuncUtils.getAllQuote(quoteList,getContext(),quoteListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_list, container, false);
    }
}