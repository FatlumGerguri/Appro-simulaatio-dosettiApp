package com.marko.android.laakelista_testi_01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViikkoActivity extends AppCompatActivity {

    public static final String EXTRA_PAIVAINDEKSI = "indeksii";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viikko);

        tallenna();

        //etsitään lista viikonpäivistä
        ListView viikkoView = findViewById(R.id.paivatListView);

        //laitetaan viikonpäivät listanäkymään
        viikkoView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                //haetaan viikonpäivät laakelista-singletonilta
                Laakelista.getInstance().getViikko()
        ));

        Button uusiLaakeNappi = findViewById(R.id.button_lisaa_Laake);

        uusiLaakeNappi.setOnClickListener(v -> {
            Log.d("logi", "klikattiin lisaaLaake");
            lisaaUusiLaake();
        });



        Button profiiliButton = findViewById(R.id.button_profiili);
        profiiliButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  meneProfiiliin();
                                              }
                                          });


                //toiminto: päivän klikkaaminen vie päivänäkymään
                viikkoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //intent seuraavaan näkymään
                        Intent paivaNakymaan = new Intent(ViikkoActivity.this, PaivaActivity.class);

                        //laitetaan päivän indeksi arvoksi..
                        paivaNakymaan.putExtra(EXTRA_PAIVAINDEKSI, position);
                        startActivity(paivaNakymaan);
                    }

                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tallenna();

    }

    public void lisaaUusiLaake() {
        Log.d("Logi", "LisaaUusiLaakePainettiin");

        Intent lisaaNakymaan = new Intent(ViikkoActivity.this, LisaaLaakeActivity.class);
        startActivity(lisaaNakymaan);
    }

    public void meneProfiiliin() {
        Log.d("logi", "klikattiin profiilinappia");
        Intent profiiliSivulle = new Intent(this, Kayttajanprofiili.class);
        startActivity(profiiliSivulle);
    }

    public void tallenna() {
        String tallennettavat =  Laakelista.getInstance().tallennaLista();
        Log.d("gson", "Viikonsaama lista " + tallennettavat);
        SharedPreferences prefSave = getSharedPreferences("TallennetutLaakkeet", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefSave.edit();
        prefEditor.putString("Laakelista", tallennettavat);
        prefEditor.commit();
    }


}

