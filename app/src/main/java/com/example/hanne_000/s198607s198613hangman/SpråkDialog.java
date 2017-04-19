package com.example.hanne_000.s198607s198613hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by hanne_000 on 16.09.2015.
 */
public class SpråkDialog extends DialogFragment
{

    Locale locale;
    private DialogClickListener callback;

    interface DialogClickListener {
        public void onYesClick();

        public void onNoClick();

        public  void onFinishClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }

    public static SpråkDialog newInstance(int Title) {
        SpråkDialog frag = new SpråkDialog();
        Bundle args = new Bundle();
        args.putInt("tittel", Title);
        frag.setArguments(args);
        return frag;
    }


    public void changeLanguage(String lang)
    {

        Configuration config = getActivity().getBaseContext().getResources().getConfiguration();
        locale = new Locale(lang);
        Locale.setDefault(locale);
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());



    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

          if(Locale.getDefault().getLanguage().contentEquals("nb"))
          {
              return new AlertDialog.Builder(getActivity()).setTitle(R.string.språk).setMessage(R.string.språkspm).setPositiveButton(R.string.ikkeok, new DialogInterface.OnClickListener(){
                          public void onClick(DialogInterface dialog, int whichButton)
                          {
                              callback.onYesClick();
                          }
                      }
              ).setNegativeButton(R.string.language, new DialogInterface.OnClickListener() {
                          public void onClick (DialogInterface dialog,int whichButton){
                              changeLanguage("en");

                              callback.onFinishClick();

                          }
                      }

              ).

                      create();
          }
            else
          {
              return new AlertDialog.Builder(getActivity()).setTitle(R.string.språk).setMessage(R.string.språkspm).setPositiveButton(R.string.ikkeok, new DialogInterface.OnClickListener(){
                          public void onClick(DialogInterface dialog, int whichButton)
                          {
                              callback.onYesClick();
                          }
                      }
              ).setNegativeButton(R.string.language, new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int whichButton) {

                              changeLanguage("nb");
                              callback.onFinishClick();
                          }
                      }

              ).

                      create();
          }



        }



}
