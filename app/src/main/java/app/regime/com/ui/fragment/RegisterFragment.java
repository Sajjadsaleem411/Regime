package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class RegisterFragment extends Fragment {
    FragmentContact callBack;
    EditText FirstName, MiddleName, LastName, Mobile, Email, Password, ConfirmPassword;
    TextView TermsConditions, textViewArea;
    RadioButton Male, Female, Makkah, Madinah, Jeddah;
    CheckBox CheckTermsConditions;
    Button AddLocation;
    String mFirstName = "";
    String mLastName = "";
    String mMiddleName = "";
    String mMobile = "";
    String mEmail = "";
    String mPassword = "";
    String mConfirmPassword = "";
    RelativeLayout areaSelect;

    public RegisterFragment(FragmentContact back) {
        callBack = back;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText FirstName = (EditText) view.findViewById(R.id.first_name_text);
        final EditText MiddleName = (EditText) view.findViewById(R.id.middle_name_text);
        final EditText LastName = (EditText) view.findViewById(R.id.last_name_text);
        final EditText Email = (EditText) view.findViewById(R.id.email_text);
        final EditText Password = (EditText) view.findViewById(R.id.pass_text);
        final EditText ConfirmPassword = (EditText) view.findViewById(R.id.confirm_pass_text);
        final EditText Mobile = (EditText) view.findViewById(R.id.mobile_text);
        TermsConditions = (TextView) view.findViewById(R.id.terms_conditions);
        textViewArea = (TextView) view.findViewById(R.id.textViewArea);
        CheckTermsConditions = (CheckBox) view.findViewById(R.id.check_terms);
        Male = (RadioButton) view.findViewById(R.id.radio_male);
        Female = (RadioButton) view.findViewById(R.id.radio_female);
        Jeddah = (RadioButton) view.findViewById(R.id.radio_jeddah);
        Makkah = (RadioButton) view.findViewById(R.id.radio_makkah);
        Madinah = (RadioButton) view.findViewById(R.id.radio_madinah);
        AddLocation = (Button) view.findViewById(R.id.Locationbtn);
        areaSelect = (RelativeLayout)view.findViewById(R.id.areaSelect);
        areaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner_Days();
            }
        });

        final String abc = Email.getEditableText().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        AddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callBack.ChangeFragment("LocationFragment",null);

                mEmail = Email.getText().toString();
                mPassword = Password.getText().toString();
                mFirstName = FirstName.getText().toString();
                mMiddleName = MiddleName.getText().toString();
                mLastName = LastName.getText().toString();
                mMobile = Mobile.getText().toString();
                mConfirmPassword = ConfirmPassword.getText().toString();

                boolean checkemail = validateEmail(Email, "Please Enter Email", "Invalid Email");
                boolean check = validateForNull(Password, "Please Enter Password");
                boolean checkfirstname = validateForNull(FirstName, "Please Enter First Name");
                //  boolean checkmiddlename = validateForNull(MiddleName , "Please Enter Middle Name");
                //  boolean checklastname = validateForNull(LastName , "Please Enter Last Name");
                //   boolean checkmobile = validateForNull(Mobile , "Please Enter Mobile No");
                boolean checkconfirmpassword = validateForNull(ConfirmPassword, "Please Enter Confirm Password");

                /*callBack.ChangeFragment("LocationFragment", null);*/

                if (checkemail == true && check == true && checkfirstname && checkconfirmpassword) {
                    if (mPassword.equals(mConfirmPassword)) {
                        if (CheckTermsConditions.isChecked())
                            Register();
                        else

                            Toast.makeText(getActivity(), "" + "Please check term and condition", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getActivity(), "" + "Password not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }


    public void Register() {

     /*   Intent intent1 = new Intent(SignInActivity.this, MissOutActivity.class);
        startActivity(intent1);*/


        final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
        progressDialog.show();
        ApiInterface apiService =
                RetroProvider.getClient();
        Call<String> call = apiService.register(mFirstName, mEmail, mPassword);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {

                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response.body());

                    int status = jsonObject.getInt("status");
                    String msg = jsonObject.getString("message");

                    if (status == 200) {
                        callBack.ChangeFragment("LocationFragment", null);
                        //        fragmentContact.ChangeFragment("HomeFragment",null);
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
                        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
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

    public void Spinner_Days() {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        b.setTitle("which area to select?");
        final String[] types = {"King Abdul Aziz Airport", "Al Marwa", "Al Safa", "Al Nuzha", "Al Naeem", "Al Nahda", "Al Zahra", "Al Selama", "Al Bawade", "Al Rabwa",
                "Al Khaldeyya", "Al Rawda", "Al Safa", "Al Rehab", "Al Aziziyya", "Al Andalus", "Al Hamra", "Al Naseem", "Al Balad"};

        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textViewArea.setText(types[which]);
                dialog.dismiss();

                //    Toast.makeText(getContext(), types[which], Toast.LENGTH_SHORT).show();

            }

        });

        //   b.show();

        AlertDialog alertDialog = b.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1400, 800);
    }
}