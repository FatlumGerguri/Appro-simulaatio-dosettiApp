package com.marko.android.laakelista_testi_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaivaActivity extends AppCompatActivity {

    public static final String EXTRA_LAAKENIMI = "LAAKENIMI";
    private ListView laakeListaView;
    private ArrayAdapter<Laake> adapteri;
    private ArrayList<Laake> laakkeet = new ArrayList<Laake>();
    private int paivanIndeksi;
    private String laakkeenNimi;
    Date currentDate = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    String time = timeFormat.format(currentDate);
    int T = Integer.parseInt(time);
    Intent entti;

    @Override
    protected void onStart() {
        super.onStart();

        entti = getIntent();

        paivanIndeksi = entti.getIntExtra(ViikkoActivity.EXTRA_PAIVAINDEKSI, 0);

        String paiva = Laakelista.getInstance().getpaiva(paivanIndeksi);

        //Haetaan päivänaika
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        String time = timeFormat.format(currentDate);
        int T = Integer.parseInt(time);
        Log.d("Tag", timeFormat.format(currentDate));
        //katsotaan mikä päivänaika on ja tarkoituksena on päivittää sen ajankohdan lista heti alussa
        if (T <12 ) {
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeAamulaakkeet(paivanIndeksi));
            Log.d("Log1", "haettiin aamulaakkeet" + paivanIndeksi);
        } else if (T <18) {
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haePaivalaakkeet(paivanIndeksi));
            Log.d("Log1", "haettiin paivaaakkeet" + paivanIndeksi);
        } else {
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeIltalaakkeet(paivanIndeksi));
            Log.d("Log1", "haettiin iltalaakkeet" + paivanIndeksi);
        }
        //ilmoitetaan että data on muuttunut -> päivittää listanäytön ajantasalle
        adapteri.notifyDataSetChanged();
        Log.d("Logi", "Tullaan OnStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva);

        Intent entti = getIntent();

        paivanIndeksi = entti.getIntExtra(ViikkoActivity.EXTRA_PAIVAINDEKSI, 0);

        String paiva = Laakelista.getInstance().getpaiva(paivanIndeksi);

        //etsitään activitysta paivan nimelle tarkoitettu paikka, ja asetetaan sille nimi
        TextView pNimi = findViewById(R.id.textViewPaivaNimi);
        pNimi.setText(paiva);

        //Aamu ViewAdapter
        laakeListaView = findViewById(R.id.listviewAamu);
        paivitaLaakelista();
        adapteri = new ArrayAdapter<Laake>(
                this,
                android.R.layout.simple_list_item_1,
                laakkeet

                //Laakelista.getInstance().haeMaPaivalaakkeet()
        );
        Log.d("log1", "ladattiin listaan" + laakkeet);
        laakeListaView.setAdapter(adapteri);
        //toiminto: päivän klikkaaminen vie lääkenäkymään
        laakeListaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //intent seuraavaan näkymään
                Intent laakeActivityyn = new Intent(PaivaActivity.this, LaakeActivity.class);
                //laitetaan päivän indeksi arvoksi..
                //haetaan "siitä listasta mistä pitää" hakea lääkkeen nimi, indeksin perusteella
                //ja lähetetään arvo eteenpäin lääkkeelle
                laakkeenNimi = laakkeet.get(position).getNimi();
                Log.d("Logi"," Paiva Activity: laakkeen nimi on: "+  laakkeenNimi);
                laakeActivityyn.putExtra(EXTRA_LAAKENIMI, laakkeenNimi);
                startActivity(laakeActivityyn);
            }
        });

        //etitään activitysta textView elementit, aamu, päivä, ilta
        TextView aamuText = findViewById(R.id.textViewAamu);
        TextView paivaText = findViewById(R.id.textViewPaiva);
        TextView iltaText = findViewById(R.id.textViewIlta);

    }
    //metodi, joka aktivoituu kun aamu/päivä/ilta -textview klikataan
    public void aikaTekstiKlicked(View v) {
        // päivien, ma,ti,ke.. indeksit alkaa nollasta ja kasvaa aina yhdellä..
        //halutaan löytää katsoa oikean päivän kohdalta, joten kerrotaan indeksin arvo kolmella
        //koska joka päivällä on kolme arvoa -> esim keskiviikon aamu/päivä/ilta on silloin 2*3 = 6, 6+1, 6+2
        int indeksi = paivanIndeksi * 3;
        Log.d("Logi", "Päivän indeksi: " + indeksi);
        int id = v.getId();
        if(id == R.id.textViewAamu) {
            Log.d("Logi", "Klikattiin AAMUA");
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeAamulaakkeet(indeksi));
            Log.d("logi", "laakkeet" + laakkeet + "ja" + Laakelista.getInstance().haeAamulaakkeet(indeksi));
        } else if (id == R.id.textViewPaiva){
            Log.d("Logi", "klikattiin PÄIVÄÄ");
            //INDEKSI+1  pelkkä indeksi osoittaisi aamuun, indeksi+1 osoittaa päivälle
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haePaivalaakkeet(indeksi+1));
        } else if (id == R.id.textViewIlta) {
            Log.d("Logi", "klikattiin ILTAA");
            //indeksi+2, soittaa iltaan
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeIltalaakkeet(indeksi+2));
        }
        //ilmoitetaan että näkyvä lista pitää päivittää
        adapteri.notifyDataSetChanged();
    }

    public void paivitaLaakelista() {
        entti = getIntent();
        int indeksi = paivanIndeksi * 3;
        if(T<= 12) {
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeAamulaakkeet(indeksi));
            Log.d("logi", "laakkeet" + laakkeet + "ja" + Laakelista.getInstance().haeAamulaakkeet(indeksi));
        } else if (T<= 18){
            //INDEKSI+1  pelkkä indeksi osoittaisi aamuun, indeksi+1 osoittaa päivälle
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haePaivalaakkeet(indeksi+1));
        } else {
            //indeksi+2, soittaa iltaan
            laakkeet.clear(); laakkeet.addAll(Laakelista.getInstance().haeIltalaakkeet(indeksi+2));
        }
        //ilmoitetaan että näkyvä lista pitää päivittää
    }

}