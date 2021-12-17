package com.marko.android.laakelista_testi_01;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {


    EditText username, password;
    Button login;

    private String Correct_username;
    private String Correct_password;


    public void openActivityX(){
        Intent ine = new Intent(EditProfileActivity.this, Kayttajanprofiili.class);
        startActivity(ine);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);

        SharedPreferences sharePrefe = getSharedPreferences("userPreferences", MODE_PRIVATE);
        Correct_username = sharePrefe.getString("Käyttäjäm nimi", "");
        Correct_password = sharePrefe.getString("Salasana", "");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(EditProfileActivity.this, "Empty data provided", Toast.LENGTH_LONG).show();
                }else if(username.getText().toString().equals(Correct_username)){
                    // check password
                    if(password.getText().toString().equals(Correct_password)){
                        Toast.makeText(EditProfileActivity.this, "successful login", Toast.LENGTH_LONG).show();
                        openActivityX();
                    }else {
                        Toast.makeText(EditProfileActivity.this, "Invalid username/password", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(EditProfileActivity.this, "Invalid username/password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}