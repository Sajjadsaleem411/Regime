package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

@SuppressLint("ValidFragment")
public class FullDayMealsFragment extends Fragment {
    LinearLayout BreakFast,MainCourses,Salads,Soups,Desserts;
    View BreakFastView,MainCoursesView,SaladsView,SoupsView,DessertsView;
    Button btn_select_meal;
    FragmentContact fragmentContact;

    public FullDayMealsFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_day_meal_fragment, container, false);
        BreakFast = (LinearLayout)view.findViewById(R.id.braekfast);
        MainCourses = (LinearLayout)view.findViewById(R.id.main_courses);
        Salads = (LinearLayout)view.findViewById(R.id.salads);
        Soups = (LinearLayout)view.findViewById(R.id.soups);
        Desserts = (LinearLayout)view.findViewById(R.id.dessert);

        BreakFastView = (View)view.findViewById(R.id.braekfast_view);
        MainCoursesView = (View)view.findViewById(R.id.main_courses_view);
        SaladsView = (View)view.findViewById(R.id.salads_view);
        SoupsView = (View)view.findViewById(R.id.soups_view);
        DessertsView = (View)view.findViewById(R.id.dessert_view);
        btn_select_meal=(Button)view.findViewById(R.id.btn_select_meal);

        BreakFast.setVisibility(View.VISIBLE);
        MainCoursesView.setVisibility(View.GONE);
        SaladsView.setVisibility(View.GONE);
        SoupsView.setVisibility(View.GONE);
        DessertsView.setVisibility(View.GONE);

        btn_select_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContact.ChangeFragment("FullMealDetailFragment",null);
            }
        });

        BreakFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BreakFastView.setVisibility(View.VISIBLE);

                MainCoursesView.setVisibility(View.GONE);
                SaladsView.setVisibility(View.GONE);
                SoupsView.setVisibility(View.GONE);
                DessertsView.setVisibility(View.GONE);

            }
        });

        MainCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainCoursesView.setVisibility(View.VISIBLE);
                BreakFastView.setVisibility(View.GONE);
                SaladsView.setVisibility(View.GONE);
                SoupsView.setVisibility(View.GONE);
                DessertsView.setVisibility(View.GONE);
            }
        });

        Salads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaladsView.setVisibility(View.VISIBLE);
                MainCoursesView.setVisibility(View.GONE);
                BreakFastView.setVisibility(View.GONE);
                SoupsView.setVisibility(View.GONE);
                DessertsView.setVisibility(View.GONE);
            }
        });

        Soups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoupsView.setVisibility(View.VISIBLE);
                SaladsView.setVisibility(View.GONE);
                MainCoursesView.setVisibility(View.GONE);
                BreakFastView.setVisibility(View.GONE);
                DessertsView.setVisibility(View.GONE);
            }
        });

        Desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DessertsView.setVisibility(View.VISIBLE);
                SoupsView.setVisibility(View.GONE);
                SaladsView.setVisibility(View.GONE);
                MainCoursesView.setVisibility(View.GONE);
                BreakFastView.setVisibility(View.GONE);
            }
        });
        return view;
    }
}