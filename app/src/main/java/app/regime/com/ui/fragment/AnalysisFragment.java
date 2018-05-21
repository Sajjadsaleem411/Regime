package app.regime.com.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;

import app.regime.com.R;
import app.regime.com.ui.adapter.HorizontalAdapter;
import app.regime.com.ui.adapter.ViewPagerAdapter;

/**
 * Created by Administrator on 4/17/2018.
 */



public class AnalysisFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_analysis, container, false);

        return view;
    }


}
