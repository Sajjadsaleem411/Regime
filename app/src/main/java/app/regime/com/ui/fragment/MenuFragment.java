package app.regime.com.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import app.regime.com.R;
import app.regime.com.ui.adapter.GridViewAdapter;

/**
 * Created by Administrator on 4/17/2018.
 */

public class MenuFragment extends Fragment {
    public static String[] osNameList = {
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
            "BREAKFAST", "BREAKFAST",
    };
    public static int[] osImages = {
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new GridViewAdapter(getActivity(), osNameList, osImages));
        return view;
    }


}
