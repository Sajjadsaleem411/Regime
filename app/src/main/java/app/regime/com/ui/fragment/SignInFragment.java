package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.regime.com.R;
import app.regime.com.api.ApiInterface;
import app.regime.com.api.RetroProvider;
import app.regime.com.ui.FragmentContact;
import app.regime.com.utills.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.regime.com.utills.CommonUtils.validateEmail;
import static app.regime.com.utills.CommonUtils.validateForNull;

/**
 * Created by Muhammad Sajjad on 5/22/2018.
 */

@SuppressLint("ValidFragment")
public class SignInFragment extends Fragment {
    FragmentContact fragmentContact;
    EditText Email,Password;
    TextView Code,TermsnCondition;
    Button Continue;
    String mEmail="";
    String mPass="";
    public SignInFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        final EditText Email = (EditText)view.findViewById(R.id.Email_text);
        final EditText Password = (EditText)view.findViewById(R.id.Pass_text);
        // Code = (TextView)findViewById(R.id.Text1);
        // TermsnCondition = (TextView)findViewById(R.id.Text2);
        Continue = (Button) view.findViewById(R.id.Continue_to_menue);

//        TermsnCondition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEmail = Email.getText().toString();
                mPass = Password.getText().toString();

                boolean checkemail = validateEmail(Email , "Please Enter Email" , "Invalid Email");
                boolean check = validateForNull(Password , "Please Enter Password");


                if(checkemail ==true && check==true)
                {
                    login(mEmail,mPass);
               //     startActivity(new Intent(SignIn.this,OfferDeals.class));
                }




            }
        });

        return view;
    }
    public void login(final String email, final String pass) {

     /*   Intent intent1 = new Intent(SignInActivity.this, MissOutActivity.class);
        startActivity(intent1);*/
        final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
        progressDialog.show();
        ApiInterface apiService =
                RetroProvider.getClient();
        Call<String> call = apiService.signIn(email, pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {

                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response.body());

                    int status = jsonObject.getInt("status");
                    String code = jsonObject.getString("message");
                    JSONArray jsonArray=jsonObject.getJSONArray("user");

                    JSONObject userJson=jsonArray.getJSONObject(0);


                    if (status==200) {
                        fragmentContact.ChangeFragment("HomeFragment",null);
/*
                        //    editor.putString("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySUQiOjIxLCJ1c2VyTmFtZSI6Ik1hbGlrIiwiaWF0IjoxNTIyMzk0MDIxfQ.dcjqht3pzVb3MWlzuWJnOh7rrk8tn7Rg1lhO1vd60xY");
                        editor.putString("token", userJson.getString("token"));
                        editor.putBoolean("login", true);
                        if(!isCheck&&!sharedPreferences.getString("email","").equals(email)){
                            editor.putBoolean("remember", false);

                        }
                        editor.putString("email", email);
                        editor.putString("password", pass);

                        editor.apply();

                        Intent intent1 = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
*/

                    } else {
                        Toast.makeText(getActivity(), "" + code, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("error", call.toString());
                progressDialog.dismiss();
            }
        });
    }


}