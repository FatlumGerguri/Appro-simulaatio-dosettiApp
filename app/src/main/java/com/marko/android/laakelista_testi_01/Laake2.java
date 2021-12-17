package com.marko.android.laakelista_testi_01;


    public class Laake2{

        private String laake;
        private boolean[] paivat = new boolean[21];


        public Laake2(String laake) {
            this.laake = laake;

        }
        public String getLaake() {
            return this.laake;

        }
        public void setLaake() {
            this.laake = laake;
        }


        public void muutaAika( int index, boolean val) {
            this.paivat[index] = val;
        }

        @Override
        public String toString() {return this.laake;}



        public void  tulostaLista(boolean paivat) {

        }
        public void getAika(int index) {
        }

    }

