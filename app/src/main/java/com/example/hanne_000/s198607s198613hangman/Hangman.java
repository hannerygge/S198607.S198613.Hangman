package com.example.hanne_000.s198607s198613hangman;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Hangman extends AppCompatActivity implements ReglerDialog.DialogClickListener, VelgKategoriDialog.DialogClickListener, SpråkDialog.DialogClickListener{

    Button nyttSpillKnapp;
    Button reglerKnapp;
    Button exitKnapp;
    Button språkKnapp;
    public static int antallVinn;
    public static int antallTap;
    public static int antallConVinn;
    public static int antallConVTemp;
    public static int teller = 0;


    public void restart(){
        Intent i = getIntent();
        finish();
        startActivity(i);
    }

    public static void updateCon(){
        if(antallConVinn < antallConVTemp)
            antallConVinn = antallConVTemp;
        Hangman.antallConVTemp = 0;
    }



    @Override
    public void onYesClick()
    {

        return;

    }

    public  void onFinishClick()
    {
        restart();
        return;
    }
    @Override
    public void onNoClick()
    {
        Intent i = new Intent(Hangman.this, Spill.class);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        onClickNyttSpillButton();
        onClickRegelButton();
        onClickLanguageButton();
        onClickExitButton();
        TextView tv = (TextView)findViewById(R.id.winscore);
        tv.setText(getString(R.string.antallvinn) + " " + antallVinn);
        tv.setTextColor(Color.WHITE);
        TextView tv1 = (TextView) findViewById(R.id.lostscore);
        tv1.setText(getString(R.string.antalltap)+ " " + antallTap);
        tv1.setTextColor(Color.WHITE);
        TextView tv2 = (TextView) findViewById(R.id.conscore);
        tv2.setText(getString(R.string.antallsammenhengendevinn)+ " " + antallConVinn);
        tv2.setTextColor(Color.WHITE);
        updateCon();

    }

    public void onClickNyttSpillButton()
    {
        nyttSpillKnapp = (Button)findViewById(R.id.nyttspill);
        nyttSpillKnapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment dialog = new VelgKategoriDialog();
                dialog.show(getFragmentManager(), "Fortsett");
                teller = 0;


            }
        });

    }



    public void onClickRegelButton()
    {
        reglerKnapp = (Button)findViewById(R.id.regler);
        reglerKnapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment dialog = new ReglerDialog();
                dialog.show(getFragmentManager(), "Avslutt");
            }
        });

    }

    public void onClickLanguageButton()
    {
        språkKnapp = (Button)findViewById(R.id.sprak);
        språkKnapp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                DialogFragment dialog = new SpråkDialog();
                dialog.show(getFragmentManager(), "Avslutt");
            }
        });

    }

    public void onClickExitButton()
    {
        exitKnapp = (Button)findViewById(R.id.avslutt);
        exitKnapp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hangman, menu);
        return true;
    }


}
