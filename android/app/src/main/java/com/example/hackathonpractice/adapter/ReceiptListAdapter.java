package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.OrderProductsDetail;
import com.example.hackathonpractice.utils.API;

import java.util.List;

public class ReceiptListAdapter extends RecyclerView.Adapter<ReceiptListAdapter.MyViewHolder>{

    Context context;
    List<OrderProductsDetail> orderProductsDetailList ;

    public ReceiptListAdapter(Context context, List<OrderProductsDetail> orderProductsDetailList) {
        this.context = context;
        this.orderProductsDetailList = orderProductsDetailList;
    }

    @NonNull
    @Override
    public ReceiptListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.receipt_list_items,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptListAdapter.MyViewHolder holder, int position) {

        OrderProductsDetail orderProductsDetail = orderProductsDetailList.get(position);
        Log.e("ddd",orderProductsDetail.toString());
        Glide.with(context).load(API.BASE_URL + "/" + orderProductsDetail.getImageproduct()).into(holder.imgOrderProductDetail);
       holder.txtNameOrderProductdetail.setText(orderProductsDetail.getName());
        holder.txtPriceOrderProductsDetail.setText( "₹" + orderProductsDetail.getRealPrice());
        holder.txtQuantityOrderProductsDetail.setText("" + orderProductsDetail.getQuantity());
        holder.txtTotalPriceOrderProductsDetail.setText("₹" + orderProductsDetail.getQuantity()*orderProductsDetail.getRealPrice());

//        holder.txtCode.setText("Order code: " + orderProducts.getCodeOrder());
//        holder.txtStatus.setText(orderProducts.getStatusId());
//        FuncUtils.LoadAddressById(holder.txtName,holder.txtAddress,holder.txtEmail,holder.txtTelephone,orderProducts.getAddressUserId());
//        holder.txtAddress.setText("₹" + products.getPriceProduct());
//        Glide.with(context).load(API.BASE_URL + "/" + products.getImageProduct()).into(holder.imgProductItems);


    }

    @Override
    public int getItemCount() {
        return orderProductsDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameOrderProductdetail, txtPriceOrderProductsDetail, txtTotalPriceOrderProductsDetail, txtQuantityOrderProductsDetail ;
        ImageView imgOrderProductDetail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOrderProductDetail =   itemView.findViewById(R.id.imgWistListProduct);
            txtNameOrderProductdetail = itemView.findViewById(R.id.txtNameListProduct);
            txtPriceOrderProductsDetail = itemView.findViewById(R.id.txtPriceListProduct);
            txtTotalPriceOrderProductsDetail = itemView.findViewById(R.id.txtTotalPriceOrderProductsDetail);
            txtQuantityOrderProductsDetail = itemView.findViewById(R.id.txtQuantityOrderProductsDetail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    OrderProductsDetail orderProducts= orderProductsDetailList.get(getAdapterPosition());
//                    //Toast.makeText(context, "aaaa", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(context, OrderActivity.class);
//                    intent.putExtra("orderProducts",orderProducts);
//                    context.startActivity(intent);

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
