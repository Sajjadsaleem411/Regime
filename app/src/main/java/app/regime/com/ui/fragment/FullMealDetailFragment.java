package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
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
import app.regime.com.model.Deal;
import app.regime.com.model.Item;
import app.regime.com.ui.FragmentContact;
import app.regime.com.ui.adapter.ExpandableListAdapter;
import app.regime.com.utills.CommonUtils;

@SuppressLint("ValidFragment")
public class FullMealDetailFragment extends Fragment implements ExpandableListAdapter.UpdateList {
    FragmentContact fragmentContact;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    List<Category> categories = new ArrayList<>();
    ArrayList<Category>[] AllDaysSelectCategory;
    Bundle bundle;
    Button reset, order;

    public FullMealDetailFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_meal_detail_fragment, container, false);
        AllDaysSelectCategory = (ArrayList<Category>[]) getArguments().getSerializable("Cat_List");
        bundle = getArguments();
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        reset = (Button) view.findViewById(R.id.btn_reset);
        order = (Button) view.findViewById(R.id.btn_confirm_order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderAPICall();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentContact.ChangeFragment("OfferDealsFragment", null);
            }
        });
        listAdapter = new ExpandableListAdapter(getContext(), AllDaysSelectCategory[0], "Details", this,true);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++)
            expListView.expandGroup(i);
        //   GetMeals();
        return view;
    }

    @Override
    public void ClickChildView(int index, int child,boolean flag) {

    }

    public void OrderAPICall() {


        final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
        progressDialog.show();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", 0);
        RequestParams params = new RequestParams();
        params.add("no_of_days", bundle.getString("no_of_days"));
        params.add("amount", bundle.getString("amount"));
        params.add("date", bundle.getString("date"));
        params.add("items", String.valueOf(getitemJson()));
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-access-key", "UUAU-13T6-10R9-L6R5");
//        client.addHeader("x-access-token", sharedPreferences.getString("token", ""));
        client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo1MjEsIm5hbWUiOiJhYmJhcyIsImlhdCI6MTUxODU5ODkwNH0.Tc36x6e_DNgVSb9PnLyQuXYLjEpBWVgmuju0IXgcUoI");

        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        client.post("https://regim.herokuapp.com/api/profile/confirmOrderBooking", params, new JsonHttpResponseHandler() {
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
                    String msg = jsonObject.getString("message");
                    if (status == 200) {
                        fragmentContact.ChangeFragment("HomeFragment", null);
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

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

    JSONArray getitemJson() {
        JSONArray jsonArray = new JSONArray();
        for (ArrayList<Category> categoryArrayList : AllDaysSelectCategory) {
            categories = categoryArrayList;
            JSONObject object = new JSONObject();
            try {

                for (Category category : categories) {
                  //  object.put(category.getCategoryName(), category.items.get(0).getName());

                    if (category.items.size()== 1) {
                        object.put(category.getCategoryName(), category.items.get(0).getName());
                    } else {
                        for (int i = 1; i <= category.items.size(); i++) {
                            object.put(category.getCategoryName()+""+i, category.items.get(i-1).getName());

                        }
                    }
                    object.put("Date", category.getDate());

                }
            } catch (Exception e) {

            }
            jsonArray.put(object);
        }
        return jsonArray;
    }
}