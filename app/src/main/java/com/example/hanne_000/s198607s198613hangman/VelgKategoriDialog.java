package com.example.hanne_000.s198607s198613hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by hanne_000 on 15.09.2015.
 */
public class VelgKategoriDialog extends DialogFragment{
    private DialogClickListener callback;

    ArrayList mSelectedItems;
    interface DialogClickListener {
        public void onYesClick();
        public void  onFinishClick();
        public void onNoClick();


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
    public static VelgKategoriDialog newInstance(int Title) {
        VelgKategoriDialog frag = new VelgKategoriDialog();
        Bundle args = new Bundle();
        args.putInt("tittel", Title);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mSelectedItems = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.kategori).setMultiChoiceItems(R.array.kategorier, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                {
                    mSelectedItems.add(which);
                }
                else if(mSelectedItems.contains(which))
                {
                    mSelectedItems.remove(Integer.valueOf(which));
                }

            }
        }).setPositiveButton(R.string.ikkeok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                callback.onFinishClick();
            }


        }).setNegativeButton(R.string.spill, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton)
            {
                Spill.resetCat();
                Spill.updateCategories(mSelectedItems);
                callback.onNoClick();
            }


        });

        return builder.create();
    }
}
