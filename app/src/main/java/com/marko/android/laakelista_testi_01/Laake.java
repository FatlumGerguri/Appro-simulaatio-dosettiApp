package com.marko.android.laakelista_testi_01;

import android.util.Log;

import java.util.ArrayList;


public class Laake {
    private String nimi;
    private boolean[] paivat;

    public Laake(String nimi) {
        this.nimi = nimi;
        this.paivat = new boolean[21];

        //alustetaan listaan 21 falsea...
        for(int i = 1; i < 20; i++){
            this.paivat[i] = false;
        }
        //TESTING - ma aamu, ma, ilta, ti aamy = true;
        this.paivat[0] = true;
        this.paivat[2] = true;
        this.paivat[3] = true;
    }


    public String getNimi() {
        return nimi;

    }

    public void setNimi(String uusinimi) {
        this.nimi = uusinimi;
    }


    //Muuttaa listassa kohdat true tai false
    //onko päivä aikataulussa vai ei..
    public void lisaaAika(int index){
        this.paivat[index] = true;
    }
    public void poistaAika(int index) {
        this.paivat[index] = false;
    }

    public String toString(){
        return this.nimi;
    }

    public void tulostaLista() {
        System.out.println("TULOSTETAAN LISTA;");
        System.out.println(this.paivat);
    }

}
