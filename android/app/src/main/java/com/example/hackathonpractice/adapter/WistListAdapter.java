package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.hackathonpractice.activity.ProductActivity;
import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.entity.WistList;
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

public class WistListAdapter extends RecyclerView.Adapter<WistListAdapter.MyViewHolder> {

    Context context;
    List<WistList> wistListList;
    TextView txtSubtotal, txtDelivery, txtTax, txtTotalCart;
    LinearLayout lineSummary;

    public WistListAdapter(Context context, List<WistList> wistListList) {
        this.context = context;
        this.wistListList = wistListList;
    }

    @NonNull
    @Override
    public WistListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_list_items,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WistListAdapter.MyViewHolder holder, int position) {

    WistList wistList = wistListList.get(position);
    Log.d("aaa", wistList.toString());
    holder.txtNameListProduct.setText(wistList.getNameProduct());

    holder.txtPriceListProduct.setText("₹" + wistList.getPriceProduct());

    Glide.with(context).load(API.BASE_URL + "/" + wistList.getImageProduct()).into(holder.imgWistListProduct);


    }

    @Override
    public int getItemCount() {
        return wistListList.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder {
        ImageView imgWistListProduct, imgdeleteListProduct;
        TextView txtNameListProduct, txtPriceListProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWistListProduct =   itemView.findViewById(R.id.imgWistListProduct);
            txtNameListProduct = itemView.findViewById(R.id.txtNameListProduct);
            txtPriceListProduct = itemView.findViewById(R.id.txtPriceListProduct);

            imgdeleteListProduct = itemView.findViewById(R.id.imgdeleteListProduct);



            imgdeleteListProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WistList wistList = wistListList.get(getAdapterPosition());
                    Log.d("aaa",wistList.toString());
                    //FuncUtils.deleteShopCart(cart);
                    wistListList.remove(getAdapterPosition());

                    FuncUtils.ToogleWisList(imgdeleteListProduct,wistList);
                    notifyDataSetChanged();
                    //Carts newModifiedItem = cartsList.get(getAdapterPosition()); //getting object from the list
                    //newModifiedItem.setMySpecialStatus(true)
                   // LoadSummary();
                   // test.onResume();
                   // notifyItemChanged(getAdapterPosition());
//                    ((fragment_shopcart)test).LoadShopCart();
//                    ((fragment_shopcart)test).LoadSummary();
                    //notifyItemChanged(getAdapterPosition());
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WistList wistList = wistListList.get(getAdapterPosition());
                    Products products = new Products();
                    products.setImageProduct(wistList.getImageProduct());
                    products.setNameProduct(wistList.getNameProduct());
                    products.setIdProduct(wistList.getProductId());
                    products.setPriceProduct( String.valueOf(wistList.getPriceProduct()) );
                    products.setDescription( String.valueOf(wistList.getDescriptionProduct()) );

                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("products",products);
                    context.startActivity(intent);
                }
            });
        }
    }



}
