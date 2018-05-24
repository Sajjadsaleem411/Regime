package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.regime.com.R;
import app.regime.com.model.Category;
import app.regime.com.model.Deal;
import app.regime.com.ui.FragmentContact;
import app.regime.com.ui.adapter.ExpandableListAdapter;
import app.regime.com.utills.CommonUtils;

@SuppressLint("ValidFragment")
public class FullDayMealsFragment extends Fragment {
    LinearLayout BreakFast, MainCourses, Salads, Soups, Desserts;
    View BreakFastView, MainCoursesView, SaladsView, SoupsView, DessertsView;
    Button btn_select_meal;
    FragmentContact fragmentContact;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Deal deal = new Deal();

    public FullDayMealsFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_day_meal_fragment, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        BreakFast = (LinearLayout) view.findViewById(R.id.braekfast);
        MainCourses = (LinearLayout) view.findViewById(R.id.main_courses);
        Salads = (LinearLayout) view.findViewById(R.id.salads);
        Soups = (LinearLayout) view.findViewById(R.id.soups);
        Desserts = (LinearLayout) view.findViewById(R.id.dessert);

        BreakFastView = (View) view.findViewById(R.id.braekfast_view);
        MainCoursesView = (View) view.findViewById(R.id.main_courses_view);
        SaladsView = (View) view.findViewById(R.id.salads_view);
        SoupsView = (View) view.findViewById(R.id.soups_view);
        DessertsView = (View) view.findViewById(R.id.dessert_view);
        btn_select_meal = (Button) view.findViewById(R.id.btn_select_meal);

        BreakFast.setVisibility(View.VISIBLE);
        MainCoursesView.setVisibility(View.GONE);
        SaladsView.setVisibility(View.GONE);
        SoupsView.setVisibility(View.GONE);
        DessertsView.setVisibility(View.GONE);

        GetMeals();

        btn_select_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContact.ChangeFragment("FullMealDetailFragment", null);
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


    public void GetMeals() {

     /*   Intent intent1 = new Intent(SignInActivity.this, MissOutActivity.class);
        startActivity(intent1);*/
        final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
        progressDialog.show();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", 0);
        RequestParams params = new RequestParams();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-access-key", "UUAU-13T6-10R9-L6R5");
//        client.addHeader("x-access-token", sharedPreferences.getString("token", ""));
        client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo1MjEsIm5hbWUiOiJhYmJhcyIsImlhdCI6MTUxODU5ODkwNH0.Tc36x6e_DNgVSb9PnLyQuXYLjEpBWVgmuju0IXgcUoI");

        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        client.get("https://regim.herokuapp.com/api/profile/orderDeatils?currentDate=2018-05-21", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.e("response UpdateProfile", "start");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = response;
                    int status = jsonObject.getInt("status");
                    if (status == 200) {
                        deal.categories = new ArrayList<>();
                        JSONArray message = jsonObject.getJSONArray("categories");
                        for (int i = 0; i < message.length(); i++) {
                            JSONObject data = message.getJSONObject(i);
                            Category category = new Category();
                            category.setCategoryName(data.getString("CategoryName"));
                            category.items = new ArrayList<>();
                         //   JSONArray items = data.getJSONArray("Items");
/*
                            for (int j=0;j<items.length();j++){

                                JSONObject temp = items.getJSONObject(i);
                                category.items.add(temp.getString("item"));
                            }*/
                            category.items.add("ddddddddd");
                            category.items.add("ddddddddd");
                            category.items.add("ddddddddd");
                            category.items.add("ddddddddd");
                            category.items.add("ddddddddd");

                            deal.categories.add(category);

                        }

                        listAdapter = new ExpandableListAdapter(getContext(), deal.categories,"Meals");

                        // setting list adapter
                        expListView.setAdapter(listAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
            }
        });
    }
}