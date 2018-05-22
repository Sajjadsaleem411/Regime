package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.regime.com.R;
import app.regime.com.ui.Callback;

/**
 * Created by Muhammad Sajjad on 5/22/2018.
 */

@SuppressLint("ValidFragment")
public class SignInFragment extends Fragment {
    Callback callback;

    public SignInFragment(Callback callback) {
        this.callback = callback;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        return view;
    }
}