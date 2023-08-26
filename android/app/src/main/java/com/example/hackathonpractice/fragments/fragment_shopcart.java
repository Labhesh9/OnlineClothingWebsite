package com.example.hackathonpractice.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.CartListAdapter;
import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.entity.OrderProducts;
import com.example.hackathonpractice.utils.FuncUtils;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_shopcart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_shopcart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView  recleViewShopcarts;

   int addressId;
    CartListAdapter cartListAdapter;

    TextView txtSubtotal,txtTax,txtDelivery, txtTotalCart;
    TextView txtShipName, txtShipAddress, txtShipEmail, txtShipPhone, txtOderSummray;
    List<Carts> cartsList;
    LinearLayout lineSummary;
    Button btnOrder ;
    int user_id;
    public fragment_shopcart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_shopcart.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_shopcart newInstance(String param1, String param2) {
        fragment_shopcart fragment = new fragment_shopcart();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recleViewShopcarts = view.findViewById(R.id.recleViewShopcarts);
        txtSubtotal = view.findViewById(R.id.txtSubtotal);
        txtTax = view.findViewById(R.id.txtTax);
        txtDelivery = view.findViewById(R.id.txtDelivery);
        txtTotalCart = view.findViewById(R.id.txtTotalCart);

        txtShipName = view.findViewById(R.id.txtShipName);
        txtShipAddress = view.findViewById(R.id.txtShipAddress);
        txtShipEmail = view.findViewById(R.id.txtShipEmail);
        txtShipPhone = view.findViewById(R.id.txtShipPhone);
        txtOderSummray = view.findViewById(R.id.txtOderSummray);

        btnOrder = view.findViewById(R.id.btnOrder);
        addressId=1;
        user_id = getActivity().getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        lineSummary = view.findViewById(R.id.lineSummary);
        recleViewShopcarts.setPadding(0,16,0,16 );
        cartsList = new ArrayList<>();

        cartListAdapter = new CartListAdapter(getContext(),cartsList, fragment_shopcart.this);
        recleViewShopcarts.setAdapter(cartListAdapter);
        recleViewShopcarts.setLayoutManager(new GridLayoutManager(getContext(),1));

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderProducts orderProducts = new OrderProducts();
                orderProducts.setAddressUserId(addressId);
                orderProducts.setUserId(user_id);
                orderProducts.setCodeOrder(UUID.randomUUID().toString());
               // id = UUID.randomUUID().toString();
                FuncUtils.addOrderProducts(orderProducts);

                //FragmentTransaction ft = getFragmentManager().beginTransaction();
               // ft.detach(fragment_shopcart.this).attach(fragment_shopcart.this).commit();
                cartListAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopcart, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
       // LoadShopCart();
        FuncUtils.LoadShopCart(cartsList,getContext(),cartListAdapter);
        FuncUtils.LoadSummary(txtSubtotal, txtDelivery, txtTax, txtTotalCart,lineSummary, txtOderSummray);
        FuncUtils.LoadAddressById(txtShipName, txtShipAddress, txtShipEmail, txtShipPhone, addressId);
       // LoadSummary();


    }

    public void LoadShopCart()
    {
        FuncUtils.LoadShopCart(cartsList,getContext(),cartListAdapter);
    }

    public  void LoadSummary(){


        RetrofitClient.getInstance().getApi().getAllShopCart().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();
                List<Carts> cartsList = new ArrayList<>();
                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        Carts carts = new Carts();
                        carts.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        carts.setName(jsonElement.getAsJsonObject().get("name").getAsString());
                        carts.setPrice(jsonElement.getAsJsonObject().get("originalPrice").getAsInt());
                        carts.setImageProduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());
                        carts.setQuantity(jsonElement.getAsJsonObject().get("quantity").getAsInt());
                        cartsList.add(carts);
                    }

                }

                if (cartsList.size() > 0) {
                    int subtotal =0;
                    for ( Carts cart: cartsList ) {
                        subtotal = subtotal + cart.getQuantity()*cart.getPrice();
                        Log.d("aaa", "cart: ====" + cart.toString());
                    }
                    txtSubtotal.setText("₹"  + subtotal);
                    txtDelivery.setText("₹" + 0);
                    int tax = (subtotal*10)/100;
                    txtTax.setText("₹" + tax);
                    int total = subtotal + tax;
                    txtTotalCart.setText("₹" + total);
                    lineSummary.setVisibility(View.VISIBLE);
                }
                else
                {
                    lineSummary.setVisibility(View.INVISIBLE );
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });







    }
}