package com.marko.android.laakelista_testi_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PaivaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva);

        Intent entti = getIntent();

        int i = entti.getIntExtra(MainActivity.EXTRA_PAIVAINDEKSI, 0);

        String paiva = Laakelista.getInstance().getpaiva(i);

        //etsitään activitysta paivan nimelle tarkoitettu paikka, ja asetetaan sille nimi
        TextView pNimi = findViewById(R.id.textViewPaivaNimi);
        pNimi.setText(paiva);

        //Aamu ViewAdapter
        ListView aamuView = findViewById(R.id.listviewAamu);
        aamuView.setAdapter(new ArrayAdapter<Laake>(
                this,
                android.R.layout.simple_list_item_1,
                Laakelista.getInstance().getLaakkeet()

        ));


        //etitään activitysta textView elementit, aamu, päivä, ilta
        TextView aamuText = findViewById(R.id.textViewAamu);
        TextView paivaText = findViewById(R.id.textViewPaiva);
        TextView iltaText = findViewById(R.id.textViewIlta);

    }
    //metodi, joka aktivoituu kun aamu/päivä/ilta -textview klikataan
    public void aikaTekstiKlicked(View v) {
        int id = v.getId();
        if(id == R.id.textViewAamu) {
            Log.d("Logi", "Klikattiin AAMUA");
        } else if (id == R.id.textViewPaiva){
            Log.d("Logi", "klikattiin PÄIVÄÄ");
        } else if (id == R.id.textViewIlta) {
            Log.d("Logi", "klikattiin ILTAA");
        }


    }




}