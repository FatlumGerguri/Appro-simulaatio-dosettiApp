package com.marko.android.laakelista_testi_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PAIVAINDEKSI = "indeksii";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //etsitään lista viikonpäivistä
        ListView viikkoView = findViewById(R.id.paivatListView);

        //laitetaan viikonpäivät listanäkymään
        viikkoView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                //haetaan viikonpäivät laakelista-singletonilta
                Laakelista.getInstance().getViikko()
        ));


        //toiminto: päivän klikkaaminen vie päivänäkymään
        viikkoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //intent seuraavaan näkymään
                Intent deepRoot = new Intent(MainActivity.this, PaivaActivity.class);

                //laitetaan päivän indeksi arvoksi..
                deepRoot.putExtra(EXTRA_PAIVAINDEKSI, position);
                startActivity(deepRoot);
            }
        });


    }


}