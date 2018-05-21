package app.regime.com.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.regime.com.R;

public class RegisterActivity extends AppCompatActivity {

    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login=(Button)findViewById(R.id.btn_register_login);
        register=(Button)findViewById(R.id.btn_register_account);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(RegisterActivity.this,MainActivity.class);

               intent.putExtra("Fragment","RegisterFragment");

                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LanguageActivity.class));
            }
        });


    }
}
