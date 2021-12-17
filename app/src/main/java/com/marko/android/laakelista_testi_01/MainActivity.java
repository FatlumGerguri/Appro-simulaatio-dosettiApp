package com.marko.android.laakelista_testi_01;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    String arvoNimi;
    String arvoSalas;
    int arvo;


    public void kayttajaProfiili(){
        Log.d("Profiili", "Käyttäjän profiili");
        if(arvo == 0){
            // Arvo menee Käyttäjän profiiliin
            Intent intent = new Intent(this, Kayttajanprofiili.class);
            startActivity(intent);
        }else if (arvo == 1){
            // Arvo menee HelppAcitivy
            Intent intent = new Intent(this, HelppActivity.class);
            startActivity(intent);
        }else if(arvo == 2){
            // Arvo menee ViikkoActivity
            Intent intent = new Intent(this, ViikkoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        TarkistaKayttajan();

        Log.d("logi" , "Luke Arvo" + arvo);
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayttajaProfiili();
            }
        });
        Log.d("logi", "createee");

        SharedPreferences prefGet = getSharedPreferences("TallennetutLaakkeet", Activity.MODE_PRIVATE);
        String laakeListaa = prefGet.getString("Laakelista", "");
        Log.d("logi", laakeListaa);
        if(laakeListaa == "") {
            Laakelista.getInstance().noudaLista(laakeListaa);
        }
        Laakelista.getInstance().noudaLista(laakeListaa);


        Log.d("logi", "sgregrrg");

    }
    // Check which Arvo - radiobutton is selected
    public void TarkistaKayttajan(){
        SharedPreferences sharePref = getSharedPreferences("userPreferences", Activity.MODE_PRIVATE);
        arvo = sharePref.getInt("Arvo", 0);
    }
    //Tallenna lääkeet tuone TallennettuLaakkeet xml tiedostoon
    protected void onStart(){
        super.onStart();

        SharedPreferences prefGet = getSharedPreferences("TallennetutLaakkeet", Activity.MODE_PRIVATE);
        String laakeListaa = prefGet.getString("Laakelista", "");
        Log.d("logi", laakeListaa);

    }

}