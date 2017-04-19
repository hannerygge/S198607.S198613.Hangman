package com.example.hanne_000.s198607s198613hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hanne_000 on 15.09.2015.
 */
public class NewgameDialog extends DialogFragment
{
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

    public static NewgameDialog newInstance(int Title) {
        NewgameDialog frag = new NewgameDialog();
        Bundle args = new Bundle();
        args.putInt("tittel", Title);
        frag.setArguments(args);
        return frag;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(Spill.Win())
        {
            return new AlertDialog.Builder(getActivity()).setTitle(R.string.won).setMessage(R.string.fortsett).setPositiveButton(R.string.ikkeok, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            Hangman.updateCon();
                            callback.onFinishClick();
                        }
                    }
            ).setNegativeButton(R.string.buttonnewgame, new DialogInterface.OnClickListener() {
                    public void onClick (DialogInterface dialog,int whichButton){
                        Hangman.antallConVTemp++;
                        callback.onNoClick();
                    }
                }

                ).

                create();
            }
            else
        {
            return new AlertDialog.Builder(getActivity()).setTitle(R.string.lost).setMessage(R.string.fortsett).setPositiveButton(R.string.ikkeok, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            Hangman.updateCon();
                            callback.onFinishClick(); //hvis du vil den skal avslutte programmet
                        }
                    }
            ).setNegativeButton(R.string.buttonnewgame, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Hangman.updateCon();
                    callback.onNoClick();
                }
            }).create();
        }

    }
}