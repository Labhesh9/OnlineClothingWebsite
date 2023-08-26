package com.example.hackathonpractice.adapter;

import android.content.Context;
import android.content.Intent;import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathonpractice.R;

import com.example.hackathonpractice.activity.EditQuotesActivity;
import com.example.hackathonpractice.entity.FavaoriteQuotes;
import com.example.hackathonpractice.entity.Quote;
import com.example.hackathonpractice.utils.API;
import com.example.hackathonpractice.utils.FuncUtils;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteListAdapter extends RecyclerView.Adapter<QuoteListAdapter.MyViewHolder> {
    Context context;
    List<Quote> quoteList;
    private static  int tmp=0;
    public QuoteListAdapter(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.quote_list_items,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Quote quote = quoteList.get(position);
        Log.e("aaa", quote.toString());
        holder.textText.setText(quote.getText());
        holder.textAuthor.setText(quote.getAuthor());
     //   holder.textPrice.setText(""+ quote.getPrice());
        if(quote.getEdit().equals("1"))
        {
            holder.imagedeletetquotes.setVisibility(View.INVISIBLE);
           // holder.imagedeletetquotes.setEnabled(false);
            holder.imageeditquotes.setVisibility(View.VISIBLE);

        }
        else if (quote.getEdit().equals("2"))
        {
            holder.imagedeletetquotes.setVisibility(View.VISIBLE);
            holder.imageeditquotes.setVisibility(View.VISIBLE);
            //holder.imagedeletetquotes.setEnabled(true);
            //holder.imagequotes.setEnabled(false);
        }
        else {
            holder.imagedeletetquotes.setVisibility(View.INVISIBLE);
            holder.imageeditquotes.setVisibility(View.INVISIBLE);
        }

        if(!quote.getImgaeheart().equals("0"))
        {
            if(quote.getImgaeheart().equals("2"))
            {
                holder.imageheart.setVisibility(View.INVISIBLE);
            }
            else
            {
                Glide.with(context).load(API.BASE_URL + "/heart.jpeg").into(holder.imageheart);
            }

        }

        else {
                Glide.with(context).load(API.BASE_URL + "/heartblank.png").into(holder.imageheart);
        }




    }



    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textText,textAuthor;
        ImageView imageheart, imageeditquotes, imagedeletetquotes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageheart=itemView.findViewById(R.id.imageheart);
            textText = itemView.findViewById(R.id.textText);
            textAuthor = itemView.findViewById(R.id.textAuthor);
            imageeditquotes = itemView.findViewById(R.id.imageeditquotes);
            imagedeletetquotes = itemView.findViewById(R.id.imagedeletetquotes);
           // textPrice = itemView.findViewById(R.id.textPriceMobile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Intent intent = new Intent(context, AddQuoteActivity.class);
//                    intent.putExtra("mobile", quoteList.get(getAdapterPosition()));
//                    Toast.makeText(context, "Item " + getAdapterPosition() +"is clicked", Toast.LENGTH_SHORT).show();
//                    context.startActivity(intent);
                }
            });

            imageeditquotes.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Quote quote= quoteList.get(getAdapterPosition());
                    Intent intent = new Intent(context, EditQuotesActivity.class);
                    intent.putExtra("quota",quote);
                    context.startActivity(intent);
                }
            });


            imagedeletetquotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
                    int quoteid = quoteList.get(getAdapterPosition()).getId();

                    Quote quote = new Quote();
                    quote.setId(quoteid);
                    quote.setUser_id(uid);
                    Log.e("aaaa",""+quote.toString());
                    RetrofitClient.getInstance().getApi().DeleteQuote(quote.getId()).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                          //  JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();

//                            if(jsonArray.size()==0)
//                            {
//                                Toast.makeText(context, "You can not delete quote", Toast.LENGTH_SHORT).show();
//                            }
                           // imagedeletetquotes.setVisibility(View.INVISIBLE);
                           // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            quoteList.remove(getAdapterPosition());
                            notifyDataSetChanged();
                           // getUserQuote();n
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });

                  //  getUserQuote();

                }
            });

            imageheart.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {
                    int  uid = context.getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",0);
                    int quoteid = quoteList.get(getAdapterPosition()).getId();
                     FavaoriteQuotes favaoriteQuotes = new FavaoriteQuotes(uid,quoteid);
                    //  insertFavorite(favaoriteQuotes);
//
                    RetrofitClient.getInstance().getApi().CheckFavoriteQuote(favaoriteQuotes).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                            //tmp = jsonArray.size();
                            if (jsonArray.size() > 0 )
                            {
                               // Toast.makeText(context, "Deletde", Toast.LENGTH_SHORT).show();
                                deleteFavorite(favaoriteQuotes);
                                Glide.with(context).load(API.BASE_URL + "/heartblank.png").into(imageheart);
                               // quoteList.remove(getAdapterPosition());
                                //notifyDataSetChanged();
                               // getAllQuote();
                               //
                            }
                            else
                            {
                                insertFavorite(favaoriteQuotes);
                               // getAllQuote();
                              //  notifyDataSetChanged();
                                Glide.with(context).load(API.BASE_URL +"/heart.jpeg").into(imageheart);

                            }
                           // getAllMyFavorite();
                           // notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(context, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });




                    // notifyDataSetChanged();
                }




            });

        }

    }


    private void getAllMyFavorite() {

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

                    notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void deleteFavorite(FavaoriteQuotes favaoriteQuotes) {
        Log.e("aaaa",favaoriteQuotes.toString());
        RetrofitClient.getInstance().getApi().DeleteFavoriteQuote(favaoriteQuotes.getUser_id(),favaoriteQuotes.getQuote_id()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "Somthing went gone", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void insertFavorite( FavaoriteQuotes favaoriteQuotes) {
        RetrofitClient.getInstance().getApi().InsertFavoriteQuotes(favaoriteQuotes).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    private void getUserQuote() {
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

                }



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "Something went gone", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getAllQuote() {
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
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
