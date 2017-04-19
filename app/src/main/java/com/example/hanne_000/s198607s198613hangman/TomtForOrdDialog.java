package com.example.hanne_000.s198607s198613hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hanne_000 on 18.09.2015.
 */
public class TomtForOrdDialog extends DialogFragment

{

    private DialogClickListener callback;
    interface DialogClickListener
    {
        public void onYesClick();
        public void onNoClick();
        public void onFinishClick();
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            callback = (DialogClickListener) getActivity();
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }
    public static TomtForOrdDialog newInstance(int Title)
    {
        TomtForOrdDialog frag = new TomtForOrdDialog();
        Bundle args = new Bundle();
        args.putInt("tittel", Title);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.tomt).setMessage(R.string.tilbaketilmeny).setNegativeButton(R.string.ikkeok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                Hangman.updateCon();
                callback.onFinishClick();
            }
        }).create();
    }

}

