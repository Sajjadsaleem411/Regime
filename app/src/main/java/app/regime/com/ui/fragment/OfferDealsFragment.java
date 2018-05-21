package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.regime.com.R;
import app.regime.com.ui.Callback;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class OfferDealsFragment extends Fragment {
    Callback callback;
    TextView FullDayMeals,LunchOnly;
    Button ChooseFullDayMeal,ChooseLunchOnly;

    public OfferDealsFragment(Callback callback){
        this.callback=callback;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_offer_deals, container, false);
        ChooseFullDayMeal = (Button)view.findViewById(R.id.btn_full_day_meal);
        ChooseLunchOnly = (Button)view.findViewById(R.id.btn_lunch_only);
        FullDayMeals = (TextView)view.findViewById(R.id.full_day_meals);
        LunchOnly = (TextView)view.findViewById(R.id.lunch_only);

        FullDayMeals.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                FullDayMeals.setBackgroundColor(0x00000000);

                FullDayMeals.setBackgroundColor(R.color.yellow);
            }
        });

        LunchOnly.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                FullDayMeals.setBackgroundColor(0x00000000);

                FullDayMeals.setBackgroundColor(R.color.yellow);
            }
        });

        ChooseFullDayMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      startActivity(new Intent(OfferDeals.this,FullDayMealSecond.class));
            }
        });

        ChooseLunchOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.ChangeFragment("OfferDayFragment",null);
           //     startActivity(new Intent(OfferDeals.this,FullDayMealSecond.class));
            }
        });
        return view;
    }


}
