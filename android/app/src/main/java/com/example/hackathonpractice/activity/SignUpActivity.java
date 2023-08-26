package com.example.hackathonpractice.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.hackathonpractice.R;
import com.example.hackathonpractice.entity.Users;
import com.example.hackathonpractice.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private static final String tag ="dddd";
    ImageView signup_back_buttom;
    TextView signup_title_text;

    TextInputLayout editFirstname,editLastname,editConfirmPassword, editEmail, editUsername, phone, editPassword;
    Button signup_next_button, signup_login_button;

    Animation transition_back_arrow_btn, transition_title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        transition_back_arrow_btn = AnimationUtils.loadAnimation(this, R.anim.transition_back_arrow_btn);
        transition_title_text = AnimationUtils.loadAnimation(this, R.anim.transition_title_text);
        Mapping();
        signup_back_buttom.setAnimation(transition_back_arrow_btn);
        signup_title_text.setAnimation(transition_title_text);
        signup_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!ValidationFirstname() | ValidationLastname() | !ValidationUsername() | !ValidationEmail() | !ValidationPhone() | !ValidationPassword() | !ValidateConfirmPassword())
//                {
//                    return;
//                }
                Toast.makeText(SignUpActivity.this, "Test", Toast.LENGTH_SHORT).show();
               Users users = new Users();
               users.setFirst_name(editFirstname.getEditText().getText().toString());
               users.setLast_name(editLastname.getEditText().getText().toString());
               users.setEmail(editEmail.getEditText().getText().toString());
               users.setPassword(editPassword.getEditText().getText().toString());
               users.setPhone(phone.getEditText().getText().toString());
               users.setUname(editUsername.getEditText().getText().toString());
                Log.e("aaa",users.toString());
                RetrofitClient.getInstance().getApi().registerUser(users) .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                          String check = response.body().getAsJsonObject().get("status").getAsString();
                          if(check.equals("success"))
                          {
                              Toast.makeText(SignUpActivity.this, "New user is inserted", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                              startActivity(intent);
                              finish();
                          }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Something went gone", Toast.LENGTH_SHORT).show();
                    }
                });





            }
        });
    }

    private void Mapping() {
        signup_back_buttom = findViewById(R.id.signup_back_buttom);
        signup_title_text = findViewById(R.id.signup_title_text);
        signup_next_button = findViewById(R.id.signup_next_button);
        signup_login_button = findViewById(R.id.signup_login_button);

        editFirstname = findViewById(R.id.editFirstname) ;
        editEmail = findViewById(R.id.editEmail) ;
        editUsername = findViewById(R.id.editUsername) ;
        phone = findViewById(R.id.phone) ;
        editPassword = findViewById(R.id.editPassword) ;
        editLastname = findViewById(R.id.editLastname) ;
        editConfirmPassword = findViewById(R.id.editConfirmPassword) ;



    }

    private Boolean ValidationFirstname(){

        String val = editFirstname.getEditText().getText().toString();
        if (val.isEmpty()){
            editFirstname.setError("Firstname cannot be empty");
            return false;
        }
        else
        {
            editFirstname.setError(null);
            return true;
        }

    }

    private Boolean ValidationLastname(){

        String val = editLastname.getEditText().getText().toString();
        if (val.isEmpty()){
            editLastname.setError("Lastname cannot be empty");
            return false;
        }
        else
        {
            editLastname.setError(null);
            return true;
        }

    }

    private Boolean ValidationEmail(){
        String val = editEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty())
        {
            editEmail.setError("Username cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern))
        {
            editEmail.setError("Invalid email address");
            return false;
        }
        else
        {
            editEmail.setError(null);
            return true;
        }
    }

    private Boolean ValidationUsername(){
        String val = editUsername.getEditText().getText().toString();
        String noWhiteSpace = "(?=\\s+$)";
        if (val.isEmpty())
        {
            editUsername.setError("Username cannot be empty");
            return false;
        }
        else if (val.length()>=15) {
            editUsername.setError("Username cannot be to long");
            return false;
        }
//        } else if(!val.matches(noWhiteSpace) ){
//            editUsername.setError("White space are not allowed.");
//            return false;
//        }

        else
        {
            editUsername.setError(null);
            return true;
        }
    }

    private Boolean ValidationPhone(){
        String val = phone.getEditText().getText().toString();

        if (val.isEmpty())
        {
            phone.setError("Phone cannot be empty");
            return false;
        }

        else
        {
            phone.setError(null);
            return true;
        }
    }

    private Boolean ValidationPassword(){
        String val = editPassword.getEditText().getText().toString();
        String passwordPattern = "^" +
                       // "(?=.*[0-9]" + // at least 1 digit
                        // "(?=.*[a-z]" + // at least 1 lower case letter
                        // "(?=.*[A-Z])" + // at least 1 upper case letter
                         "(?=.*[a-zA-Z])" + //any letter
                        "(?=.*[@#$%^&+=])" + // at least 1 special character
                        "(?=.*\\s+$)" + //no white space
                        ".{4,}" + //at least 4 character
                                "$";
        if (val.isEmpty()) {
            editPassword.setError("Email cannot be empty");
            return false;
        }
//        } else if (!val.matches(passwordPattern)) {
//            editPassword.setError("Invalid password");
//            return false;
//        }

        else
        {
            editPassword.setError(null);
            return true;
        }
    }
    private Boolean ValidateConfirmPassword(){
        String val = editConfirmPassword.getEditText().getText().toString();
        String passwordPattern = "^" +
                // "(?=.*[0-9]" + // at least 1 digit
                // "(?=.*[a-z]" + // at least 1 lower case letter
                // "(?=.*[A-Z])" + // at least 1 upper case letter
                "(?=.*[a-zA-Z])" + //any letter
                "(?=.*[@#$%^&+=])" + // at least 1 special character
                "(?=.*\\s+$)" + //no white space
                ".{4,}" + //at least 4 character
                "$";
        if (val.isEmpty()) {
            editConfirmPassword.setError("Email cannot be empty");
            return false;
        }
//        } else if (!val.matches(passwordPattern)) {
//            editConfirmPassword.setError("Invalid password");
//            return false;
//        }
        else if (!val.equals(editPassword.getEditText().getText().toString()))
        {
            editConfirmPassword.setError("Confirm password must be match with password");
            return false;
        }

        else
        {
            editConfirmPassword.setError(null);
            return true;
        }
    }
    public void callNextSignupScreen(View view) {

//        Intent intent = new Intent(getApplicationContext(),Signup2ndClass.class);
//        Pair[] pairs= new Pair[4];
//        pairs[0] = new Pair<View,String>(signup_back_buttom,"transition_back_arrow_btn");
//        pairs[1] = new Pair<View,String>(signup_title_text,"transition_title_text");
//        pairs[2] = new Pair<View,String>(signup_next_button,"transition_next_btn");
//        pairs[3] = new Pair<View,String>(signup_login_button,"transition_login_btn");
//
//        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
//        startActivity(intent, activityOptions.toBundle());

    }
}