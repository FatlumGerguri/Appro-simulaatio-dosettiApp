package com.marko.android.laakelista_testi_01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Laakelista {
    private static final Laakelista ourInstance = new Laakelista();
    private ArrayList<Laake> laakkeet;
    private String [] viikonPaivat;
    //lista, jota vaihdetaan/päivitetään sen mukaan mitä halutaan näyttää ohjelmassa.
    private ArrayList<Laake> kayttoLista;  //<- vaan joku numero, jonka perusteella haetaan oikea lista.. if xx haeAamulista(paivaI) --Paivalista/Iltalista...
    String jsonLaakelista;

    public static Laakelista getInstance() {
        return ourInstance;
    }

    //TODO lue/lue laakelista gson/json...
    // ja tallennus myös

    //sama nimi metodilla kuin luokan nimi
    private Laakelista() {
        //laakkeet = noudaLaakelista();
        laakkeet = new ArrayList<>();

        viikonPaivat = new String[]{"maanantai","tiistai", "keskiviikko", "torstai", "perjantai", "lauantai", "sunnuntai" };
    }


    //palauttaa kaikki lääkkeet
    public ArrayList<Laake> getLaakkeet() {
        return this.laakkeet;
    }

    //hakee lääkkeen indeksin perusteella
    public Laake getLaake(int indeksi) {
        return this.laakkeet.get(indeksi);
    }

    //hakee viikonpäivät viikonpäivälistaa varten
    public String[] getViikko () {
        return this.viikonPaivat;
    }

    //hakee tietyn päivän nimen listalta
    public String getpaiva(int indeksi) {
        return this.viikonPaivat[indeksi];
    }


    public int etsiPaikkaListasta(String nimi){
        int i =999 ;
            for (Laake l:laakkeet
                 ) { if (l.getNimi().equals(nimi)) {
                     return laakkeet.indexOf(l);
            }
        }
        return i;
    }

    public void lisaaLaake(String nimi){
        this.laakkeet.add(new Laake(nimi));
    }

    public void lisaaLaake(String nimi, boolean[] ajat) {
        this.laakkeet.add(new Laake(nimi, ajat));
    }

    public ArrayList<Laake> haeAamulaakkeet(int paivaI) {
        ArrayList<Laake>  lista = new ArrayList<>();
        for(int i = 0; i <this.laakkeet.size(); i++) {
            if(laakkeet.get(i).getAika(paivaI) == true) {
                lista.add(this.laakkeet.get(i));
                Log.d("Logi", "lisättiin" + this.laakkeet.get(i).getNimi());
            }

        }
        return lista;
    }
    public ArrayList<Laake> haePaivalaakkeet(int paivaI) {
        ArrayList<Laake>  lista = new ArrayList<>();

        for(int i = 0; i < this.laakkeet.size(); i++) {
            if (laakkeet.get(i).getAika(1)) {
                lista.add(laakkeet.get(i));
            }
        }
        return lista;
    }
    public ArrayList<Laake> haeIltalaakkeet(int paivaI) {
        ArrayList<Laake>  lista = new ArrayList<>();

        for(int i = 0; i < this.laakkeet.size(); i++) {
            if (laakkeet.get(i).getAika(paivaI)) {
                lista.add(laakkeet.get(i));
            }
        }
        return lista;

    }

    public void noudaLista(String lista) {
        Gson gson  = new Gson();
        TypeToken<List<Laake>> token = new TypeToken<List<Laake>>() {};
        ArrayList<Laake> laakeLista = gson.fromJson(lista, token.getType());
        Log.d("gson", "uusi jsonilista: " + laakeLista);
        if (laakeLista == null) {
            laakeLista = new ArrayList<Laake>();
            Log.d("gson", "luotiinkin uusa: " + laakeLista);
        }
        this.laakkeet = laakeLista;
    }



    public String tallennaLista() {
        Gson gson = new Gson();
        jsonLaakelista = gson.toJson(this.laakkeet);
        Log.d("gson", jsonLaakelista);

        TypeToken<List<Laake>> token = new TypeToken<List<Laake>>() {};
        List<Laake> laakeLista = gson.fromJson(jsonLaakelista, token.getType());
        Log.d("gson", "uusi jsonilista" + laakeLista);
        return jsonLaakelista;
    }

    //tarkistetaan onko aktiivisia päiviä, jos on, ei poisteta lääkettä
    public void tarkistaLaake(int index) {
        for (int i = 0; i < 21; i++) {
            if (this.laakkeet.get(index).getAika(i) == true) {
                Log.d("Logi", "Löytyi true");
                tallennaLista();
                return;
            }
        }
        this.laakkeet.remove(index);
        Log.d("Logi", "lääkkeellä kohdassa" + index + " ei ollut aikoja, poistettiin");
        tallennaLista();
    }

 }
