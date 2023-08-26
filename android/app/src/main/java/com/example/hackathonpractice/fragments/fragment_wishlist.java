package com.example.hackathonpractice.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.WistListAdapter;
import com.example.hackathonpractice.entity.WistList;
import com.example.hackathonpractice.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_wishlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_wishlist extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recycleWistList;

    List<WistList> wistListList;

    WistListAdapter wistListAdapter;


    int user_id;
    public fragment_wishlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_wishlist.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_wishlist newInstance(String param1, String param2) {
        fragment_wishlist fragment = new fragment_wishlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user_id = getActivity().getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        recycleWistList = view.findViewById(R.id.recycleWistList);

        wistListList = new ArrayList<>();
        wistListAdapter = new WistListAdapter(getContext(),wistListList);
        recycleWistList.setAdapter(wistListAdapter);
        recycleWistList.setLayoutManager(new GridLayoutManager(getContext(),1));





    }

    @Override
    public void onResume() {
        super.onResume();
        WistList wistList = new WistList();
        wistList.setUserId(user_id);
        FuncUtils.LoadWishList(wistListList,getContext(),wistListAdapter,user_id);

    }
}