package com.example.hackathonpractice.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathonpractice.R;
import com.example.hackathonpractice.adapter.CartListAdapter;
import com.example.hackathonpractice.adapter.OrderListAdapter;
import com.example.hackathonpractice.adapter.ProductListAdapter;
import com.example.hackathonpractice.adapter.QuoteFavoRtiteListAdapter;
import com.example.hackathonpractice.adapter.QuoteListAdapter;
import com.example.hackathonpractice.adapter.ReceiptListAdapter;
import com.example.hackathonpractice.adapter.WistListAdapter;
import com.example.hackathonpractice.entity.AddressUser;
import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.entity.OrderProducts;
import com.example.hackathonpractice.entity.OrderProductsDetail;
import com.example.hackathonpractice.entity.Products;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.entity.WistList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuncUtils {

    public static void getAllQuote(List<Quote> quoteList, Context context, QuoteListAdapter quoteListAdapter) {
        quoteList.clear();
        int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllQuotes(uid).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                {
                    JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        Quote quote = new Quote();
                        quote.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        quote.setAuthor(jsonElement.getAsJsonObject().get("author").getAsString());
                        quote.setText(jsonElement.getAsJsonObject().get("text").getAsString());
                        quote.setUser_id(jsonElement.getAsJsonObject().get("user_id").getAsInt());
                        quote.setEdit(jsonElement.getAsJsonObject().get("edit").getAsString());
                        quote.setImgaeheart(jsonElement.getAsJsonObject().get("imgaeheart").getAsString());
                        quoteList.add(quote);
                    }
                    quoteListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    public  static void getAllMyFavorite(List<Quote> quoteList, Context context, QuoteFavoRtiteListAdapter quoteListAdapter) {
        quoteList.clear();
        int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllMyFavoriteQuotes(uid).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                {
                    JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                    for (JsonElement jsonElement: jsonArray) {

                        Quote quote = new Quote();
                        quote.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        quote.setAuthor(jsonElement.getAsJsonObject().get("author").getAsString());
                        quote.setText(jsonElement.getAsJsonObject().get("text").getAsString());
                        quote.setUser_id(jsonElement.getAsJsonObject().get("user_id").getAsInt());
                        quote.setEdit(jsonElement.getAsJsonObject().get("edit").getAsString());
                        quote.setImgaeheart(jsonElement.getAsJsonObject().get("imgaeheart").getAsString());

                        quoteList.add(quote);
                    }

                    quoteListAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }


    public  static void getUserQuote(List<Quote> quoteList, Context context, QuoteListAdapter quoteListAdapter) {
        quoteList.clear();
        int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllQuotesById(uid).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                    for (JsonElement jsonElement: jsonArray) {
                        Quote quote = new Quote();
                        quote.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        quote.setText(jsonElement.getAsJsonObject().get("text").getAsString());
                        quote.setAuthor(jsonElement.getAsJsonObject().get("author").getAsString());
                        quote.setImgaeheart(jsonElement.getAsJsonObject().get("imgaeheart").getAsString());
                        quote.setEdit(jsonElement.getAsJsonObject().get("edit").getAsString());
                        quoteList.add(quote);
                    }
                    quoteListAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static void getAllProductWithPrice(List<Products> productsList, Context context, ProductListAdapter productListAdapter) {
        productsList.clear();
       // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllProductWithPrice().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        Products products = new Products();
                        products.setNameProduct(jsonElement.getAsJsonObject().get("name").getAsString());
                       products.setPriceProduct(jsonElement.getAsJsonObject().get("originalPrice").getAsString());
                        products.setImageProduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());

                        productsList.add(products);
                    }
                    productListAdapter.notifyDataSetChanged();
                 }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    public static void getAllProductWithPriceByAgetypesId(List<Products> productsList, Context context, ProductListAdapter productListAdapter, int id) {
        productsList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllProductWithPriceByAgetypesId(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        Products products = new Products();
                        products.setIdProduct(jsonElement.getAsJsonObject().get("id").getAsInt());
                        products.setNameProduct(jsonElement.getAsJsonObject().get("name").getAsString());
                        products.setPriceProduct(jsonElement.getAsJsonObject().get("originalPrice").getAsString());
                        products.setImageProduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());
                        products.setDescription(jsonElement.getAsJsonObject().get("description").getAsString());
                        productsList.add(products);
                    }
                    productListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public static void addCart(Carts cart) {
        int productId = cart.getProductId();
        RetrofitClient.getInstance().getApi().getbyproductid(productId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonObject jsonObject = response.body().getAsJsonObject().get("data").getAsJsonObject();
                        cart.setId(jsonObject.getAsJsonObject().get("id").getAsInt());
                        int temp = cart.getQuantity();
                        cart.setQuantity(temp + jsonObject.getAsJsonObject().get("quantity").getAsInt());
                        RetrofitClient.getInstance().getApi().UpdateQuantityShopCart(cart).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });
                }
                else {
                        RetrofitClient.getInstance().getApi().AddCart(cart).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //  Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });




    }


    public static void deleteShopCart(Carts cart) {
        Log.d("aaa","Cart delet:" + cart.toString());
        RetrofitClient.getInstance().getApi().deleteShopCart(cart.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //  Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static void LoadShopCart(List<Carts> cartsList, Context context, CartListAdapter cartListAdapter) {

        cartsList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllShopCart().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

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
                    cartListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    public static void UpdateQuantityShopCart(Carts carts) {

        // productsList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().UpdateQuantityShopCart(carts).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                JsonObject temp = response.body().getAsJsonObject();
//                String success = temp.get("status").getAsString();
//
//                if( success.equals("success"))
//                {
//                    Log.d("aaa","Get data ---- " + success.toString() );
//                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    public static void LoadSummary(TextView txtSubtotal, TextView txtDelivery, TextView txtTax, TextView txtTotalCart, LinearLayout lineSummary, TextView txtOderSummray){


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
                    txtOderSummray.setText("Order Summary");
                }
                else
                {
                    lineSummary.setVisibility(View.INVISIBLE );
                    txtOderSummray.setText("No products in your Cart");
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });







    }


    public static void LoadAddressById(TextView txtShipName, TextView txtShipAddress, TextView txtShipEmail, TextView txtShipPhone, int id){


        RetrofitClient.getInstance().getApi().getAddressById(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();
                List<Carts> cartsList = new ArrayList<>();
                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonObject jsonObject = temp.get("data").getAsJsonObject();


                       // AddressUser carts = new AddressUser();

                        //carts.setId(jsonObject.getAsJsonObject().get("id").getAsInt());
                        txtShipName.setText("Ship name: "+jsonObject.getAsJsonObject().get("shipName").getAsString());
                        txtShipAddress.setText("Ship Address: "+jsonObject.getAsJsonObject().get("shipAddress").getAsString());
                        txtShipEmail.setText("Ship Email: "+jsonObject.getAsJsonObject().get("shipEmail").getAsString());
                        txtShipPhone.setText("Ship Phone Name: "+jsonObject.getAsJsonObject().get("shipPhoneNumber").getAsString());



                }




            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });







    }



    public static void addOrderProductDetail(OrderProductsDetail orderProductsDetail) {

        RetrofitClient.getInstance().getApi().AddOrderProductSetails(orderProductsDetail).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
//                    JsonObject jsonObject = response.body().getAsJsonObject().get("data").getAsJsonObject();
//                    cart.setId(jsonObject.getAsJsonObject().get("id").getAsInt());
//                    int temp = cart.getQuantity();
//                    cart.setQuantity(temp + jsonObject.getAsJsonObject().get("quantity").getAsInt());
//                    RetrofitClient.getInstance().getApi().UpdateQuantityShopCart(cart).enqueue(new Callback<JsonObject>() {
//                        @Override
//                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                        }
//                    });
//                }
//                else {
//                    RetrofitClient.getInstance().getApi().AddCart(cart).enqueue(new Callback<JsonObject>() {
//                        @Override
//                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                        }
//                    });
//                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //  Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });




    }



    public static void addOrderProducts(OrderProducts orderProducts) {
       // int productId = cart.getProductId();
        RetrofitClient.getInstance().getApi().AddOrderProducts(orderProducts).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonObject jsonObject = response.body().getAsJsonObject().get("data").getAsJsonObject();
                    int orderId = jsonObject.getAsJsonObject().get("insertId").getAsInt();
                    RetrofitClient.getInstance().getApi().getAllShopCart().enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            JsonObject temp = response.body().getAsJsonObject();
                            String success = temp.get("status").getAsString();

                            if( success.equals("success"))
                            {
                                Log.d("aaa","Get data ---- " + success.toString() );
                                JsonArray jsonArray = temp.get("data").getAsJsonArray();

                                for (JsonElement jsonElement:jsonArray)
                                {
                                    OrderProductsDetail orderProductsDetail = new OrderProductsDetail();
                                    orderProductsDetail.setOrderId(orderId);
                                    orderProductsDetail.setProductId(jsonElement.getAsJsonObject().get("productId").getAsInt());
                                    orderProductsDetail.setQuantity(jsonElement.getAsJsonObject().get("quantity").getAsInt());
                                    orderProductsDetail.setRealPrice(jsonElement.getAsJsonObject().get("originalPrice").getAsInt());

                                    addOrderProductDetail(orderProductsDetail);
                                    Carts carts = new Carts();
                                    carts.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                                    deleteShopCart(carts);
                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //  Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });




    }


    public static void LoadOrderProducts(List<OrderProducts> orderProductsList, Context context, OrderListAdapter orderListAdapter, int userid) {

        orderProductsList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        RetrofitClient.getInstance().getApi().getAllOrderProducts(userid).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        OrderProducts orderProduct  = new OrderProducts();
                        orderProduct.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        //orderProduct.setStatusId(jsonElement.getAsJsonObject().get("statusId").getAsString());
                        orderProduct.setVoucherId(jsonElement.getAsJsonObject().get("voucherId").getAsInt());
                        orderProduct.setAddressUserId(jsonElement.getAsJsonObject().get("addressUserId").getAsInt());
                       // orderProduct.setNote(jsonElement.getAsJsonObject().get("note").getAsString());
                        orderProduct.setIsPaymentOnline(jsonElement.getAsJsonObject().get("isPaymentOnline").getAsInt());
                        orderProduct.setCodeOrder(jsonElement.getAsJsonObject().get("codeOrder").getAsString());

                        orderProductsList.add(orderProduct);
                    }
                    orderListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public static void LoadOrderProductsDetail(List<OrderProductsDetail> orderProductsDetailList, Context context, ReceiptListAdapter receiptListAdapter, OrderProducts orderProducts) {

        orderProductsDetailList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        Log.d("ddd",orderProducts.toString() );
        RetrofitClient.getInstance().getApi().getProductSetailsByOrderId(orderProducts.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        OrderProductsDetail orderProductsDetail  = new OrderProductsDetail();
                        orderProductsDetail.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        //orderProduct.setStatusId(jsonElement.getAsJsonObject().get("statusId").getAsString());
                        orderProductsDetail.setQuantity(jsonElement.getAsJsonObject().get("quantity").getAsInt());
                        orderProductsDetail.setRealPrice(jsonElement.getAsJsonObject().get("realPrice").getAsInt());
                        // orderProduct.setNote(jsonElement.getAsJsonObject().get("note").getAsString());
                        orderProductsDetail.setName(jsonElement.getAsJsonObject().get("name").getAsString());
                        orderProductsDetail.setOriginalPrice(jsonElement.getAsJsonObject().get("originalPrice").getAsInt());
                        orderProductsDetail.setImageproduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());

                        orderProductsDetailList.add(orderProductsDetail);
                    }
                    receiptListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }



    public static void LoadSumaryOrderProductsDetail(TextView txtSubTotalDetailOrder,TextView txtShipDelivery, TextView txtTaxOrder, TextView txtTotalOrder, OrderProducts orderProducts) {

       // orderProductsDetailList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        Log.d("ddd",orderProducts.toString() );
        RetrofitClient.getInstance().getApi().getProductSetailsByOrderId(orderProducts.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();
                List<OrderProductsDetail> orderProductsDetailList = new ArrayList<>();
                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        OrderProductsDetail orderProductsDetail  = new OrderProductsDetail();
                        orderProductsDetail.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        //orderProduct.setStatusId(jsonElement.getAsJsonObject().get("statusId").getAsString());
                        orderProductsDetail.setQuantity(jsonElement.getAsJsonObject().get("quantity").getAsInt());
                        orderProductsDetail.setRealPrice(jsonElement.getAsJsonObject().get("realPrice").getAsInt());
                        // orderProduct.setNote(jsonElement.getAsJsonObject().get("note").getAsString());
                        orderProductsDetail.setName(jsonElement.getAsJsonObject().get("name").getAsString());
                        orderProductsDetail.setOriginalPrice(jsonElement.getAsJsonObject().get("originalPrice").getAsInt());
                        orderProductsDetail.setImageproduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());

                        orderProductsDetailList.add(orderProductsDetail);
                    }

                    if (orderProductsDetailList.size() > 0) {
                        int subtotal =0;
                        for ( OrderProductsDetail orderProductsDetail: orderProductsDetailList ) {
                            subtotal = subtotal + orderProductsDetail.getQuantity()*orderProductsDetail.getRealPrice();
                            Log.d("aaa", "cart: ====" + orderProductsDetail.toString());
                        }
                        txtSubTotalDetailOrder.setText("₹"  + subtotal);
                        txtShipDelivery.setText("₹" + 0);
                        int tax = (subtotal*10)/100;
                        txtTaxOrder.setText("₹" + tax);
                        int total = subtotal + tax;
                        txtTotalOrder.setText("₹" + total);
                       // lineSummary.setVisibility(View.VISIBLE);
                       // txtOderSummray.setText("Order Summary");
                    }
//                    else
//                    {
//                        lineSummary.setVisibility(View.INVISIBLE );
//                        txtOderSummray.setText("No products in your Cart");
//                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    public static void addWistList(WistList wistList) {
        //int productId = wistList.getProductId();
        RetrofitClient.getInstance().getApi().AddWistList(wistList).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //  Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public static void LoadWishList(List<WistList> wistListList, Context context, WistListAdapter wistListAdapter, int userId) {

        wistListList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
       // Log.d("ddd", wistList.toString());
        RetrofitClient.getInstance().getApi().getAllWithListWithUser(userId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    Log.d("aaa","Get data ---- " + success.toString() );
                    JsonArray jsonArray = temp.get("data").getAsJsonArray();
                    for (JsonElement jsonElement:jsonArray)
                    {
                        WistList wistList1  = new WistList();
                        wistList1.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
                        wistList1.setNameProduct(jsonElement.getAsJsonObject().get("name").getAsString());
                        wistList1.setProductId(jsonElement.getAsJsonObject().get("productId").getAsInt());
                        wistList1.setPriceProduct(jsonElement.getAsJsonObject().get("originalPrice").getAsInt());
                        wistList1.setDescriptionProduct(jsonElement.getAsJsonObject().get("description").getAsString());
                        wistList1.setImageProduct(jsonElement.getAsJsonObject().get("imageproduct").getAsString());


                        wistListList.add(wistList1);
                    }
                    wistListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public static void LoadWisListIcon(ImageView icon , int productId) {

        int  check = 0;
       // wistListList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        // Log.d("ddd", wistList.toString());
        RetrofitClient.getInstance().getApi().GetWistListByProductId(productId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {


                }
                else {
                    icon.setImageResource(R.drawable.heart_icon_blank);
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });



    }


    public static void ToogleWisList(ImageView icon , WistList wistList) {

        int  check = 0;
        // wistListList.clear();
        // int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
        // Log.d("ddd", wistList.toString());
        RetrofitClient.getInstance().getApi().GetWistListByProductId(wistList.getProductId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject temp = response.body().getAsJsonObject();
                String success = temp.get("status").getAsString();

                if( success.equals("success"))
                {
                    icon.setImageResource(R.drawable.heart_icon_blank);
                    WistList wistList1 = new WistList();
                    JsonObject jsonObject = response.body().getAsJsonObject().get("data").getAsJsonObject();
                    wistList1.setId(jsonObject.getAsJsonObject().get("id").getAsInt());
                    Log.d("abc", wistList1.toString());
                    RetrofitClient.getInstance().getApi().DeleteWistListById(wistList1.getId()).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });


                }
                else {
                    icon.setImageResource(R.drawable.heart_icon);
                    //WistList wistList = new WistList();
                    //JsonObject jsonObject = response.body().getAsJsonObject().get("data").getAsJsonObject();
                    //wistList.setId(jsonObject.getAsJsonObject().get("id").getAsInt());
                    addWistList(wistList);
//                    RetrofitClient.getInstance().getApi().AddWistList(wistList).enqueue(new Callback<JsonObject>() {
//                        @Override
//                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                        }
//                    });
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });



    }



}
