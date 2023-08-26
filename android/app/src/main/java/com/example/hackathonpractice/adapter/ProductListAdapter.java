package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;
import com.example.hackathonpractice.activity.EditQuotesActivity;
import com.example.hackathonpractice.activity.ProductActivity;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.API;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    Context context;
    List<Products> productsList;

    public ProductListAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_items,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.MyViewHolder holder, int position) {

    Products products = productsList.get(position);
    holder.txtNameProducts.setText(products.getNameProduct());

    holder.txtPriceProducts.setText("₹" + products.getPriceProduct());
    Glide.with(context).load(API.BASE_URL + "/" + products.getImageProduct()).into(holder.imgProductItems);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder {
        ImageView imgProductItems;
        TextView txtNameProducts, txtPriceProducts;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductItems =   itemView.findViewById(R.id.imgProductItems);
            txtNameProducts = itemView.findViewById(R.id.txtNameProducts);
            txtPriceProducts = itemView.findViewById(R.id.txtPriceProducts);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Products products= productsList.get(getAdapterPosition());
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("products",products);
                    context.startActivity(intent);
                }
            });
        }
    }
}
