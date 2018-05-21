package app.regime.com.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.regime.com.R;

public class LanguageActivity extends AppCompatActivity {

    Dialog dialog;
    Button btn_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        btn_language=(Button)findViewById(R.id.btn_language);
        btn_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }
    void showDialog(){
        dialog=new Dialog(this);

        dialog.setContentView(R.layout.language_dialog);

        Button restart=(Button)dialog.findViewById(R.id.ok);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(LanguageActivity.this,MainActivity.class));
                //whatever code you want to execute on restart
            }
        });
        dialog.show();
    }
}
