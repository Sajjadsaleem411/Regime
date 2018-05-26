package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.regime.com.R;
import app.regime.com.model.Category;
import app.regime.com.model.Item;
import app.regime.com.ui.FragmentContact;
import app.regime.com.ui.adapter.OfferDealAdapter;
import app.regime.com.utills.CommonUtils;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class OfferDealsFragment extends Fragment {
    FragmentContact fragmentContact;
    TextView FullDayMeals,LunchOnly;
    Button ChooseFullDayMeal,ChooseLunchOnly;
    RecyclerView listViewFullDayMeal,listViewLunchOnlyMeal;
    List<Category> categories = new ArrayList<>();
    OfferDealAdapter offerDealAdapter;

    public OfferDealsFragment(FragmentContact fragmentContact){
        this.fragmentContact = fragmentContact;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_offer_deals, container, false);
        ChooseFullDayMeal = (Button)view.findViewById(R.id.btn_full_day_meal);
        ChooseLunchOnly = (Button)view.findViewById(R.id.btn_lunch_only);
        listViewFullDayMeal = (RecyclerView) view.findViewById(R.id.listViewFullDayMeal);
        listViewLunchOnlyMeal = (RecyclerView) view.findViewById(R.id.listViewLunchOnlyMeal);

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
                            String str = data.getString("Items");
                            if(str!=null&&!str.equals("No Menu Avaliable")) {
                                JSONArray itemsJson = new JSONArray(str);

                                for (int j = 0; j < itemsJson.length(); j++) {

                                    JSONObject temp = itemsJson.getJSONObject(j);
                                    category.items.add(new Item(temp.getString("item" + (j + 1))));
                                }
                            }
                            categories.add(category);
                        }

                        offerDealAdapter = new OfferDealAdapter(getActivity(), categories);
                        listViewFullDayMeal.setAdapter(offerDealAdapter);
                        listViewFullDayMeal.setLayoutManager(new LinearLayoutManager(getActivity()));
                        listViewLunchOnlyMeal.setAdapter(offerDealAdapter);
                        listViewLunchOnlyMeal.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Exception",""+e);
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


        ChooseFullDayMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      startActivity(new Intent(OfferDeals.this,FullDayMealSecond.class));
            }
        });

        ChooseLunchOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentContact.ChangeFragment("OfferDayFragment",null);
                //     startActivity(new Intent(OfferDeals.this,FullDayMealSecond.class));
            }
        });
        return view;
    }


}
