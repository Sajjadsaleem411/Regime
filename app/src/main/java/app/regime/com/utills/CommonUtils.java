package app.regime.com.utills;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

}
