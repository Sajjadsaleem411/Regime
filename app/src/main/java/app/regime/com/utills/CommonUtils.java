package app.regime.com.utills;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.regime.com.R;

/**
 * Created by Muhammad Sajjad on 5/19/2018.
 */

public class CommonUtils {
    private static final String TAG = "CommonUtils";
    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static SimpleDateFormat Dateformat = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static boolean validateEmail(EditText p_editText, String p_nullMsg, String p_invalidMsg)
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


    public static boolean validateForNull(EditText p_editText, String p_nullMsg)
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
