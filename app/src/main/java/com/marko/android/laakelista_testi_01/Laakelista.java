package com.marko.android.laakelista_testi_01;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Laakelista {
    private static final Laakelista ourInstance = new Laakelista();
    private ArrayList<Laake> laakkeet;
    private String [] viikonPaivat;

    public static Laakelista getInstance() {
        return ourInstance;
    }

    //sama nimi metodilla kuin luokan nimi
    private Laakelista() {
        //laakkeet = noudaLaakelista();
        laakkeet = new ArrayList<>();

        laakkeet.add(new Laake("Aspiriini"));
        laakkeet.add(new Laake("Burana"));
        laakkeet.add(new Laake("Rautalisä"));
        laakkeet.add(new Laake("B12"));
        laakkeet.add(new Laake("Kalsiumlisä"));

        viikonPaivat = new String [7];
        viikonPaivat[0] = "Maanantai";
        viikonPaivat[1] = "Tiistai";
        viikonPaivat[2] = "Keskiviikko";
        viikonPaivat[3] = "Torstai";
        viikonPaivat[4] = "Perjantai";
        viikonPaivat[5] = "Lauantai";
        viikonPaivat[6] = "Sunnuntai";


    }
/*
    private ArrayList<Laake> noudaLaakelista() {
        return
    }

 */

    public ArrayList<Laake> getLaakkeet() {
        return this.laakkeet;
    }

    public Laake getLaake(int indeksi) {
        return this.laakkeet.get(indeksi);
    }

    public String[] getViikko () {
        return this.viikonPaivat;
    }

    public String getpaiva(int indeksi) {
        return this.viikonPaivat[indeksi];
    }
 }
