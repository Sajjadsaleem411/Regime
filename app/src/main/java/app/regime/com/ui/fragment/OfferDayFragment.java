package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class OfferDayFragment extends Fragment {
    TextView FullDayMeals, LunchMeals, DayView, PriceView, TotalAmount;
    Button Confirm;
    LinearLayout ChooseDate;
    RelativeLayout DaySelect;
    FragmentContact fragmentContact;

    public OfferDayFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_deals, container, false);
        Confirm = (Button) view.findViewById(R.id.confirm);
        FullDayMeals = (TextView) view.findViewById(R.id.full_day_meals);
        LunchMeals = (TextView) view.findViewById(R.id.lunch_only);
        DayView = (TextView) view.findViewById(R.id.day_view);
        PriceView = (TextView) view.findViewById(R.id.price_view);
        TotalAmount = (TextView) view.findViewById(R.id.total_amount);
        ChooseDate = (LinearLayout) view.findViewById(R.id.linear_layout);
        DaySelect = (RelativeLayout) view.findViewById(R.id.relative_layout);


        FullDayMeals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        LunchMeals.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

            }
        });

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(OfferDay.this,FullDayMeal.class));
                fragmentContact.ChangeFragment("FullDayMealsFragment",null);
            }
        });

        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     startActivity(new Intent(OfferDay.this,Calender.class));
                showDialog();
            }
        });

        DaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.calender_dialog);
        CalendarView calendarView = (CalendarView)dialog.findViewById(R.id.calender_view);
        ImageView CrossImg =(ImageView)dialog.findViewById(R.id.cross_img);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1)+"/"+i2+"/"+i;
            }
        });

        CrossImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}