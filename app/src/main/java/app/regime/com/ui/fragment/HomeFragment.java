package app.regime.com.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.regime.com.R;
import app.regime.com.api.ApiInterface;
import app.regime.com.api.RetroProvider;
import app.regime.com.model.Menu;
import app.regime.com.ui.adapter.HorizontalAdapter;
import app.regime.com.ui.adapter.ViewPagerAdapter;
import app.regime.com.utills.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Muhammad Sajjad on 4/17/2018.
 */

public class HomeFragment extends Fragment {
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<Integer> data;
    ViewPager viewPager;
    List<String> images;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        horizontal_recycler_view= (RecyclerView)view.findViewById(R.id.week_menu_recyleview);

        data = fill_with_data();
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());

        viewPager.setAdapter(viewPagerAdapter);

        horizontalAdapter=new HorizontalAdapter(data, getActivity());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        FetchMenu();
        return view;
    }

    public List<Integer> fill_with_data() {

        List<Integer> data = new ArrayList<>();

        data.add( R.drawable.breakfast_wednesday);
        data.add( R.drawable.breakfast_sunday);
        data.add( R.drawable.breakfast_wednesday);
        data.add( R.drawable.breakfast_sunday);
        data.add( R.drawable.breakfast_wednesday);
        data.add( R.drawable.breakfast_sunday);
        return data;
    }

    public void FetchMenu() {

     /*   Intent intent1 = new Intent(SignInActivity.this, MissOutActivity.class);
        startActivity(intent1);*/
        final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
        progressDialog.show();
        ApiInterface apiService =
                RetroProvider.getClient();
        Call<Menu> call = apiService.menuService();
        call.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {

                progressDialog.dismiss();
                  /*  JSONObject jsonObject = new JSONObject(response.body());

                    int status = jsonObject.getInt("status");
                    String code = jsonObject.getString("message");
                    JSONArray jsonArray=jsonObject.getJSONArray("data");


                    images=new ArrayList<>();*/
/*Menu menu=response.getre;
                    if (response.==200) {
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject userJson=jsonArray.getJSONObject(i);
                            images.add(userJson.getString("ItemImage"));
                        }
                        Log.d("SSS",""+images);

                    } else {
                        Toast.makeText(getActivity(), "" + images, Toast.LENGTH_SHORT).show();
                    }*/

            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.e("error", call.toString());
                progressDialog.dismiss();

            }

        });
    }
}
