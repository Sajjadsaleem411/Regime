package app.regime.com.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.regime.com.R;
import app.regime.com.ui.adapter.HorizontalAdapter;
import app.regime.com.ui.adapter.ViewPagerAdapter;


/**
 * Created by Muhammad Sajjad on 4/17/2018.
 */

public class HomeFragment extends Fragment {
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<Integer> data;
    ViewPager viewPager;
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
}
