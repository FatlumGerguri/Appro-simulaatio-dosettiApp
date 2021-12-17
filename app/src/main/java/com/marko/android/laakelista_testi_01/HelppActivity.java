package com.marko.android.laakelista_testi_01;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HelppActivity extends AppCompatActivity {


    private void openActivity2() {
        Intent intent =new Intent(this,EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpp);

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        ImageButton settingButton = (ImageButton) findViewById(R.id.buttonSetting);

        Date currentDate = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        String time = timeFormat.format(currentDate);
        int T = Integer.parseInt(time);
        Log.d("Tag", timeFormat.format(currentDate));


        if (T == 6 || T <= 12){
            textView3.setText("AAMULÄÄKKEET!");
        }else if(T == 13 || T <= 18){
            textView3.setText("PÄIVÄLÄÄKKEET!");
        }else{
            textView3.setText("ILTÄLÄÄKKEET!");
        }


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }

}