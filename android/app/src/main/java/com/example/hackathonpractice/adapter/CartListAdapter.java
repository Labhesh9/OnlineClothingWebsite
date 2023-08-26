package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.fragments.fragment_shopcart;
import com.example.hackathonpractice.utils.API;
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

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    Context context;
    List<Carts> cartsList;
    TextView txtSubtotal, txtDelivery, txtTax, txtTotalCart;
    LinearLayout lineSummary;

    Fragment test;
    public CartListAdapter(Context context, List<Carts> cartsList,Fragment test ) {
        this.context = context;
        this.cartsList = cartsList;
        this.test = test;
    }

    @NonNull
    @Override
    public CartListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_items,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.MyViewHolder holder, int position) {

    Carts carts = cartsList.get(position);
    Log.d("aaa", carts.toString());
    holder.txtNameShopCart.setText(carts.getName());
    holder.txtQuantityCart.setText(""+carts.getQuantity());
    holder.txtPriceShopCart.setText("₹" + carts.getPrice());
    holder.txtTotalPriceShopCart.setText("₹"  + carts.getQuantity()*carts.getPrice());
    Glide.with(context).load(API.BASE_URL + "/" + carts.getImageProduct()).into(holder.imgShopCart);


    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder {
        ImageView imgShopCart, imgdelete;
        TextView txtNameShopCart, txtPriceShopCart, txtSubCart, txtQuantityCart, txtAddCart,txtTotalPriceShopCart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShopCart =   itemView.findViewById(R.id.imgWistListProduct);
            txtNameShopCart = itemView.findViewById(R.id.txtNameListProduct);
            txtPriceShopCart = itemView.findViewById(R.id.txtPriceListProduct);
            txtSubCart = itemView.findViewById(R.id.txtSubCart);
            txtQuantityCart = itemView.findViewById(R.id.txtQuantityOrderProductsDetail);
            txtAddCart = itemView.findViewById(R.id.txtAddCart);
            imgdelete = itemView.findViewById(R.id.imgdeleteListProduct);
            txtTotalPriceShopCart = itemView.findViewById(R.id.txtTotalPriceOrderProductsDetail);
            txtSubCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Carts cart = cartsList.get(getAdapterPosition());
                    int temp = cart.getQuantity() - 1;
                    //  txtQuantity.setText( );
                    if (temp < 1)
                    {
                        temp =1;
                    }
                    cart.setQuantity(temp);
                    FuncUtils.UpdateQuantityShopCart(cart);
                   // txtQuantityCart.setText( ""  + temp);
                   // txtTotalPriceShopCart.setText("₹"  + temp*cart.getPrice());
                    ((fragment_shopcart)test).LoadShopCart();
                    ((fragment_shopcart)test).LoadSummary();
                  //  test.onResume();
                   // LoadSummary();


                }
            });

            imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Carts cart = cartsList.get(getAdapterPosition());
                    Log.d("aaa",cart.toString());
                    FuncUtils.deleteShopCart(cart);
                    cartsList.remove(getAdapterPosition());
                    //Carts newModifiedItem = cartsList.get(getAdapterPosition()); //getting object from the list
                    //newModifiedItem.setMySpecialStatus(true)
                   // LoadSummary();
                   // test.onResume();
                   // notifyItemChanged(getAdapterPosition());
                    ((fragment_shopcart)test).LoadShopCart();
                    ((fragment_shopcart)test).LoadSummary();
                    //notifyItemChanged(getAdapterPosition());
                }
            });


            txtAddCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Carts cart = cartsList.get(getAdapterPosition());
                    int temp = cart.getQuantity()  + 1;
                    //  txtQuantity.setText( );
                    if (temp > 10)
                    {
                        temp =10;
                    }
                    cart.setQuantity(temp);
                    FuncUtils.UpdateQuantityShopCart(cart);
                   // txtQuantityCart.setText( ""  + temp);
                   // txtTotalPriceShopCart.setText("₹"  + temp*cart.getPrice());
                    ((fragment_shopcart)test).LoadShopCart();
                    ((fragment_shopcart)test).LoadSummary();
                   // LoadSummary();

                }
            });


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Products products= productsList.get(getAdapterPosition());
//                    Intent intent = new Intent(context, ProductActivity.class);
//                    intent.putExtra("products",products);
//                    context.startActivity(intent);
//                }
//            });
        }
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
