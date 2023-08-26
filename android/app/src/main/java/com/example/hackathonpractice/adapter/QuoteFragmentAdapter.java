package com.example.hackathonpractice.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hackathonpractice.fragments.MyFavoriteQuotesFragment;
import com.example.hackathonpractice.fragments.fragment_main;
import com.example.hackathonpractice.fragments.fragment_profiles;
import com.example.hackathonpractice.fragments.fragment_shopcart;
import com.example.hackathonpractice.fragments.fragment_wishlist;

public class    QuoteFragmentAdapter extends FragmentStateAdapter {

    public QuoteFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                Log.d("aaa", "fragment_main()");
               return new fragment_main();
               // return  new fragment_shopcart();
            case 1:
                Log.d("aaa", "fragment_shopcart()");
                return  new fragment_shopcart();
            case 2:
                return  new fragment_wishlist();
            case 3:
                return  new fragment_profiles();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
