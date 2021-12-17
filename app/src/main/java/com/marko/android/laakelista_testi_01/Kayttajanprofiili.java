package com.marko.android.laakelista_testi_01;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Kayttajanprofiili extends AppCompatActivity {
    String nimi, salasana;
    EditText name, pass;
    Button tallenus;
    RadioGroup rdgroup;
    RadioButton rdHelpo;
    RadioButton rdViikko;

    // Tämö activyty menee HelppAcitivy mode
    public void openActivityone(){
        Intent i = new Intent(Kayttajanprofiili.this, HelppActivity.class);
        startActivity(i);
    }
    // Tämä menee ViikkoActivity mode
    public void openActivitytwo(){
        Intent i = new Intent(Kayttajanprofiili.this, ViikkoActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayttajanprofiili);

        // Tässä ovat kirjoitetut tiedot
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        rdHelpo = (RadioButton)findViewById(R.id.radioButton);
        rdViikko = (RadioButton)findViewById(R.id.radioButton2);

        // Painikke
        tallenus = (Button) findViewById(R.id.button);


        //Tallenta kirjiutettut tiedot xml-tiedostoon
        SharedPreferences sharePref = getSharedPreferences("userPreferences", MODE_PRIVATE);
        nimi = sharePref.getString("Käyttäjäm nimi", "");
        salasana = sharePref.getString("Salasana", "");

        name.setText(nimi);
        pass.setText(salasana);

        tallenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().isEmpty() ||  (!pass.getText().toString().isEmpty()))
                {
                    if(tallenus.isClickable())
                    {
                        // Tarkistaa jos kirjoitettu tiedot ovat oikeat niin tallenta xml-tiedostoon
                        SharedPreferences.Editor editor = sharePref.edit();
                        editor.putString("Käyttäjäm nimi", name.getText().toString());
                        editor.putString("Salasana", pass.getText().toString());
                        if(rdHelpo.isChecked()){
                            editor.putInt("Arvo", 1);
                            openActivityone();
                        }else if (rdViikko.isChecked()){
                            editor.putInt("Arvo", 2);
                            openActivitytwo();
                        }

                        editor.commit();
                    }
                }
                else
                {
                    //Ilmoittaa jos käyttäjä ei anneta oikeat tiedot
                    Toast.makeText(Kayttajanprofiili.this,"Syötä molemmat kentät.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}