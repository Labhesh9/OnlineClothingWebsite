package com.example.hackathonpractice.utils;

import com.example.hackathonpractice.entity.Carts;
import com.example.hackathonpractice.entity.FavaoriteQuotes;
import com.example.hackathonpractice.entity.OrderProducts;
import com.example.hackathonpractice.entity.OrderProductsDetail;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.entity.Users;
import com.example.hackathonpractice.entity.WistList;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
    String BASE_URL ="http://192.168.101.239:4000/";

    @GET("/api/user/{user_id}")
    Call<JsonObject> getUserById(@Path("user_id") int user_id);

    @GET("/api/products/getAllProductWithPrice")
    Call<JsonObject> getAllProductWithPrice();

    @GET("/api/products/getAllProductWithPrice/{id}")
    Call<JsonObject> getAllProductWithPriceByAgetypesId(@Path("id") int id);

    @POST("/api/wishlists")
    Call<JsonObject>  AddWistList(@Body WistList wistList);

    @GET("/api/wishlists/userid/{userId}")
    Call<JsonObject> getAllWithListWithUser(@Path("userId") int userId);

    @GET("/api/wishlists/getbyproductid/{id}")
    Call<JsonObject>  GetWistListByProductId(@Path("id") int id);

    @DELETE("/api/wishlists/{id}")
    Call<JsonObject>  DeleteWistListById(@Path("id") int id);

    @POST("/api/shopcarts")
    Call<JsonObject>  AddCart(@Body Carts carts);

    @POST("/api/orderProducts")
    Call<JsonObject>  AddOrderProducts(@Body OrderProducts orderProducts);

    @GET("/api/orderProducts/userid/{userid}")
    Call<JsonObject>  getAllOrderProducts(@Path("userid") int userid);

    @POST("/api/orderProductDetails")
    Call<JsonObject>  AddOrderProductSetails(@Body OrderProductsDetail orderProductsDetail);

    @GET("/api/orderProductDetails/orderid/{id}")
    Call<JsonObject>  getProductSetailsByOrderId(@Path("id") int id);

    @PUT("/api/shopcarts")
    Call<JsonObject>  UpdateQuantityShopCart(@Body Carts carts);

    @GET("/api/shopcarts")
    Call<JsonObject> getAllShopCart();

    @DELETE("/api/shopcarts/{id}")
    Call<JsonObject> deleteShopCart(@Path("id") int id);

    @GET("/api/shopcarts/getbyproductid/{id}")
    Call<JsonObject> getbyproductid(@Path("id") int id);

    @GET("/api/addressUsers")
    Call<JsonObject> getAllAddress();

    @GET("/api/addressUsers/{id}")
    Call<JsonObject> getAddressById(@Path("id") int id);
    @POST("/api/users/login")
    Call<JsonObject> loginUser(@Body Users user);

    @POST("/api/users")
    Call<JsonObject> registerUser(@Body Users user);

    @GET("/mobile")
    Call<JsonObject> getAllMobiles();

    @GET("/quote/getAll/{id}")
    Call<JsonObject> getAllQuotes(@Path("id") int id);

    @GET("/quote/{id}")
    Call<JsonObject> getAllQuotesById(@Path("id") int id);

    @POST("/quote/insert")
    Call<JsonObject>  InsertQuote(@Body Quote quote);

    @PUT("/quote")
    Call<JsonObject>  UpdateQuote(@Body Quote quote);

    @DELETE("/quote/{id}")
    Call<JsonObject>  DeleteQuote(@Path("id") int id);

    @GET("/favoritequote/{id}")
    Call<JsonObject>  getAllMyFavoriteQuotes(@Path("id") int id);

    @POST("/favoritequote/insert")
    Call<JsonObject>  InsertFavoriteQuotes(@Body FavaoriteQuotes favaoriteQuotes);



    @POST("/favoritequote/check")
    Call<JsonObject>  CheckFavoriteQuote(@Body FavaoriteQuotes favaoriteQuotes);


    @DELETE("/favoritequote/{user_id}/{quote_id}")
    Call<JsonObject>  DeleteFavoriteQuote(@Path("user_id") int user_id,@Path("quote_id") int quote_id);



    @POST("/mobile/insert")
    Call<JsonObject>  InsertOrders(@Body FavaoriteQuotes favaoriteQuotes);


    @GET("/order/{id}")
    Call<JsonObject> getOrdersById(@Path("id") int id);


}
