package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;
import com.example.hackathonpractice.activity.OrderActivity;
import com.example.hackathonpractice.activity.ProductActivity;
import com.example.hackathonpractice.entity.OrderProducts;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.utils.API;
import com.example.hackathonpractice.utils.FuncUtils;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder>{

    Context context;
    List<OrderProducts> orderProductsList;


    public OrderListAdapter(Context context, List<OrderProducts> orderProducts) {
        this.context = context;
        this.orderProductsList = orderProducts;
    }

    @NonNull
    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_list,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, int position) {

        OrderProducts orderProducts = orderProductsList.get(position);
        holder.txtCode.setText("Order code: " + orderProducts.getCodeOrder());
        holder.txtStatus.setText(orderProducts.getStatusId());
        FuncUtils.LoadAddressById(holder.txtName,holder.txtAddress,holder.txtEmail,holder.txtTelephone,orderProducts.getAddressUserId());
//        holder.txtAddress.setText("₹" + products.getPriceProduct());
//        Glide.with(context).load(API.BASE_URL + "/" + products.getImageProduct()).into(holder.imgProductItems);


    }

    @Override
    public int getItemCount() {
        return orderProductsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtCode, txtAddress,txtEmail, txtTelephone, txtStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCode =   itemView.findViewById(R.id.txtCode);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtTelephone = itemView.findViewById(R.id.txtTelephone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OrderProducts orderProducts= orderProductsList.get(getAdapterPosition());
                    //Toast.makeText(context, "aaaa", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtra("orderProducts",orderProducts);
                    context.startActivity(intent);

                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    OrderProducts orderProducts= orderProductsList.get(getAdapterPosition());
//                    Intent intent = new Intent(context, OrderActivity.class);
//                    //intent.putExtra("orderProducts",orderProducts);
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}
