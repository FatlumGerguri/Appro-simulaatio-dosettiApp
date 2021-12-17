package com.marko.android.laakelista_testi_01;

import android.util.Log;

import java.util.ArrayList;


public class Laake {
    private String nimi;
    private boolean[] paivat;

    public Laake(String nimi) {
        this.nimi = nimi;
        this.paivat = new boolean[21];

        //for testing purposes
        this.paivat[0] = true;
        this.paivat[2] = true;
        this.paivat[3] = true;
    }
    public Laake(String nimi, boolean[]ajat){
        this.nimi = nimi;
        this.paivat = ajat;
    }


    public String getNimi() {
        return nimi;

    }

    public void setNimi(String uusinimi) {
        this.nimi = uusinimi;
    }


    //Muuttaa listassa kohdat true tai false
    //onko päivä aikataulussa vai ei..
    public void muutaAika(int index, boolean val){
            this.paivat[index] = val;
    }

    public String toString(){
        return this.nimi;
    }

    public void tulostaLista() {
        System.out.println("TULOSTETAAN LISTA;");
        System.out.println(this.paivat);
    }

    public boolean[] getPaivat() {
        return paivat;
    }

        //palauttaa ajan true/false, esim. switchille joka hauaa tietää onko kyseinen aika on/off..
    public boolean getAika(int i) {
        return this.paivat[i];
    }


}
