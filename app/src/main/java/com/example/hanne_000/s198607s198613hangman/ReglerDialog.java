package com.example.hanne_000.s198607s198613hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hanne_000 on 14.09.2015.
 */
public class ReglerDialog extends DialogFragment

    {

        private DialogClickListener callback;
        interface DialogClickListener
        {
            public void onYesClick();
            public void onNoClick();
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
    public static ReglerDialog newInstance(int Title)
    {
        ReglerDialog frag = new ReglerDialog();
        Bundle args = new Bundle();
        args.putInt("tittel", Title);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.buttonrules).setMessage(R.string.regler).setNegativeButton(R.string.ikkeok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                callback.onYesClick();
            }
        }).create();
    }

}
