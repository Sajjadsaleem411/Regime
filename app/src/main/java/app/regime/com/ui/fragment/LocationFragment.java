package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class LocationFragment extends Fragment {
    FragmentContact callBack;
    TextView AddressView;
    EditText AddressText;
    Button Confirm,AddAnotherLocation;
    public LocationFragment(FragmentContact back){
        callBack=back;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_location, container, false);
        Confirm =(Button)view.findViewById(R.id.Confirm);
        AddAnotherLocation =(Button)view.findViewById(R.id.AddLocation);
        AddressView = (TextView)view.findViewById(R.id.address_view);
        AddressText = (EditText)view.findViewById(R.id.address_text);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.ChangeFragment("OfferDealsFragment",null);
            }
        });

        AddAnotherLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAnotherLocation.setVisibility(View.GONE);
                /*startActivity(new Intent(LocationMap.this,LocationMapSecond.class));*/
            }
        });
        return view;
    }


}
