package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class OfferDayFragment extends Fragment {
    TextView FullDayMeals, LunchMeals, DayView, PriceView, TotalAmount,tv_amount_title,tv_date;
    Button Confirm;
    LinearLayout ChooseDate;
    RelativeLayout DaySelect;
    FragmentContact fragmentContact;
    ImageView day_spinner;

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
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        PriceView = (TextView) view.findViewById(R.id.price_view);
        TotalAmount = (TextView) view.findViewById(R.id.total_amount);
        tv_amount_title = (TextView) view.findViewById(R.id.tv_total_amount_title);
        ChooseDate = (LinearLayout) view.findViewById(R.id.linear_layout);
        DaySelect = (RelativeLayout) view.findViewById(R.id.relative_layout);
        day_spinner = (ImageView) view.findViewById(R.id.img_day_spinner);


        day_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner_Days();

            }
        });

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
                Bundle bundle=new Bundle();
                bundle.putString("days",DayView.getText().toString());
                bundle.putString("Amount",TotalAmount.getText().toString());
                bundle.putString("Date",tv_date.getText().toString());
                fragmentContact.ChangeFragment("FullDayMealsFragment", bundle);
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
        CalendarView calendarView = (CalendarView) dialog.findViewById(R.id.calender_view);
        ImageView CrossImg = (ImageView) dialog.findViewById(R.id.cross_img);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;

                tv_date.setText(date);
                dialog.dismiss();
               // Toast.makeText(getContext(), "select date " + date, Toast.LENGTH_SHORT).show();
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

    public void Spinner_Days() {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        b.setTitle("How many days?");
        final String[] types = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};

        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                DayView.setText(types[which]);
                int total= Integer.parseInt(DayView.getText().toString())*Integer.parseInt(PriceView.getText().toString());
                TotalAmount.setText(""+total);
                tv_amount_title.setText(""+total);
            //    Toast.makeText(getContext(), types[which], Toast.LENGTH_SHORT).show();

            }

        });

     //   b.show();

        AlertDialog alertDialog = b.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1400, 800);
    }
}