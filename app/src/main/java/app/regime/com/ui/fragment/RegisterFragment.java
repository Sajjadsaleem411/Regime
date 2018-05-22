package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.regime.com.R;
import app.regime.com.ui.FragmentContact;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

@SuppressLint("ValidFragment")
public class RegisterFragment extends Fragment {
    FragmentContact callBack;
    EditText FirstName,MiddleName,LastName,Mobile,Email,Password,ConfirmPassword;
    TextView TermsConditions;
    RadioButton Male,Female,Makkah,Madinah,Jeddah;
    CheckBox CheckTermsConditions;
    Button AddLocation;
    String mFirstName="";
    String mLastName="";
    String mMiddleName="";
    String mMobile="";
    String mEmail="";
    String mPassword="";
    String mConfirmPassword="";
    public RegisterFragment(FragmentContact back){
        callBack=back;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText FirstName = (EditText)view.findViewById(R.id.first_name_text);
        final EditText MiddleName =(EditText)view.findViewById(R.id.middle_name_text);
        final EditText LastName = (EditText)view.findViewById(R.id.last_name_text);
        final EditText Email =(EditText)view.findViewById(R.id.email_text);
        final EditText Password = (EditText)view.findViewById(R.id.pass_text);
        final EditText ConfirmPassword =(EditText)view.findViewById(R.id.confirm_pass_text);
        final EditText Mobile = (EditText)view.findViewById(R.id.mobile_text);
        TermsConditions = (TextView)view.findViewById(R.id.terms_conditions);
        CheckTermsConditions = (CheckBox) view.findViewById(R.id.check_terms);
        Male = (RadioButton)view.findViewById(R.id.radio_male);
        Female = (RadioButton)view.findViewById(R.id.radio_female);
        Jeddah = (RadioButton)view.findViewById(R.id.radio_jeddah);
        Makkah = (RadioButton)view.findViewById(R.id.radio_makkah);
        Madinah = (RadioButton)view.findViewById(R.id.radio_madinah);
        AddLocation = (Button)view.findViewById(R.id.Locationbtn);


        final String abc = Email.getEditableText().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        AddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.ChangeFragment("LocationFragment",null);

/*
                mEmail = Email.getText().toString();
                mPassword = Password.getText().toString();
                mFirstName = FirstName.getText().toString();
                mMiddleName = MiddleName.getText().toString();
                mLastName = LastName.getText().toString();
                mMobile = Mobile.getText().toString();
                mConfirmPassword = ConfirmPassword.getText().toString();

                boolean checkemail = validateEmail(Email , "Please Enter Email" , "Invalid Email");
                boolean check = validateForNull(Password , "Please Enter Password");
                boolean checkfirstname = validateForNull(FirstName , "Please Enter First Name");
                boolean checkmiddlename = validateForNull(MiddleName , "Please Enter Middle Name");
                boolean checklastname = validateForNull(LastName , "Please Enter Last Name");
                boolean checkmobile = validateForNull(Mobile , "Please Enter Mobile No");
                boolean checkconfirmpassword = validateForNull(ConfirmPassword , "Please Enter Confirm Password");


                if(checkemail ==true && check==true)
                {
                    callBack.ChangeFragment("LocationFragment",null);
                }
*/

            }
        });
        return view;
    }
    private boolean validateEmail(EditText p_editText, String p_nullMsg, String p_invalidMsg)
    {
        boolean m_isValid = false;
        try
        {
            if (p_editText != null)
            {
                if(validateForNull(p_editText,p_nullMsg))
                {
                    Pattern m_pattern = Pattern.compile("([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})");
                    Matcher m_matcher = m_pattern.matcher(p_editText.getText().toString().trim());
                    if (!m_matcher.matches()&& p_editText.getText().toString().trim().length() > 0)
                    {
                        m_isValid = false;
                        p_editText.setError(p_invalidMsg);
                    }
                    else
                    {

                        m_isValid = true;

                    }
                }
                else
                {
                    m_isValid = false;
                }
            }
            else
            {
                m_isValid = false;
            }
        }
        catch(Throwable p_e)
        {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    private boolean validateForNull(EditText p_editText, String p_nullMsg)
    {
        boolean m_isValid = false;
        try
        {
            if (p_editText != null && p_nullMsg != null)
            {
                if (TextUtils.isEmpty(p_editText.getText().toString().trim()))
                {
                    p_editText.setError(p_nullMsg);
                    m_isValid = false;
                }
                else
                {
                    m_isValid = true;
                }
            }
        }
        catch(Throwable p_e)
        {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }
}