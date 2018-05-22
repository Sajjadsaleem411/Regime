package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

@SuppressLint("ValidFragment")
public class FullMealDetailFragment extends Fragment {
    FragmentContact fragmentContact;

    public FullMealDetailFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_meal_detail_fragment, container, false);

        return view;
    }
}