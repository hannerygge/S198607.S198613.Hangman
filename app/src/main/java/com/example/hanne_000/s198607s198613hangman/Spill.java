package com.example.hanne_000.s198607s198613hangman;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by hanne_000 on 01.09.2015.
 */
public class Spill extends AppCompatActivity implements ReglerDialog.DialogClickListener, NewgameDialog.DialogClickListener, TomtForOrdDialog.DialogClickListener{


    private int total_Lives = 6;
    private int current_lives;
    private ArrayList<String> GuessedLetters = new ArrayList<>();
    private String word;
    private int length;
    static private int NumCategory = 5;
    private String[] GuessWords;
    TextView Lives, RightWord;
    static boolean[] W;
    LinearLayout layout;
    static boolean[] Cat = new boolean[NumCategory];
    static ArrayList<String> RandomWords;
    Random rand = new Random(System.currentTimeMillis());
    final String engalfabet = "abcdefghijklmnopqrstuvwxyz";
    final String noralfabet = "abcdefghijklmnopqrstuvwxyzæøå";
    static String[] Cat0, Cat1, Cat2, Cat3, Cat4;


    public static final int WEIGHT = 50;
    public static final int HEIGHT = 50;


    public ArrayList<String> getWords() {

        ArrayList<String> Categories = new ArrayList<>();


        String[][] StrArr = {Cat0, Cat1, Cat2, Cat3, Cat4};

        for (int i = 0; i < StrArr.length; i++) {
            if (Cat[i] == true)
                for (String s : StrArr[i]) {
                    Categories.add(s);
                }

        }

        return Categories;
    }

    public void getNewWord() {
        int selected = rand.nextInt(RandomWords.size()-1);
        word = RandomWords.get(selected);
        Log.w("ord", RandomWords.toString());
        RandomWords.remove(selected);
        length = word.length();
        W = new boolean[length];
        resetBoolWord(); // resets the boolean values for W
        showWord(); //updates the shown word (typical "_ _ _ _" )
    }

    public static void resetCat() {
        for (boolean k : Cat) {
            k = false;
        }
    }


   /* @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {

        }
    }*/

    public void guess(String L) { // skriv ut hvilket ord du gjettet på
        boolean found = false;
        GuessedLetters.add(L);
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) == L.charAt(0)) {
                W[i] = true;
                found = true;
            }
            if (word.charAt(i) == ' ') {
                W[i] = true;
            }
        }
        if (!found) {
            current_lives--;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (current_lives == 5) {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde2));
                } else if (current_lives == 4) {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde3));
                } else if (current_lives == 3) {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde4));
                } else if (current_lives == 2) {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde5));
                } else if (current_lives == 1) {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde6));
                } else {
                    findViewById(R.id.test1).setBackground(getResources().getDrawable(R.drawable.bilde7));
                }
            } else { // disse bildene må byttes ut med bilder til landscape
                if (current_lives == 5) {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende2));
                } else if (current_lives == 4) {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende3));
                } else if (current_lives == 3) {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende4));
                } else if (current_lives == 2) {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende5));
                } else if (current_lives == 1) {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende6));
                } else {
                    findViewById(R.id.toppen).setBackground(getResources().getDrawable(R.drawable.liggende7));
                }

            }

        }
        showWord();
        if (Win()) {
            //Lives.setText("yayayayayayayayayay");
            /*TextView WIN = new TextView(this);
            WIN.setText("YOU WON!");
            layout.addView(WIN);
            */
            Hangman.antallVinn++;
            DialogFragment dialog = new NewgameDialog();
            dialog.show(getFragmentManager(), "Avslutt");


        }
        if (current_lives == 0) {

            Hangman.antallTap++;
            Lost();

            DialogFragment dialog = new NewgameDialog();
            dialog.show(getFragmentManager(), "Avslutt");

        }
    }


    public boolean Lost() {
        if (current_lives != 0)
            return false;
        else return true;
    }


    public static boolean Win() {
        for (int i = 0; i < W.length; i++) {
            if (W[i] == false) {
                return false;
            }
        }
        return true;
    }

    public void showWord() {
        RightWord.setText("");
        for (int i = 0; i < length; i++) {
            if (W[i] == true) {
                RightWord.setText(RightWord.getText() + "" + word.charAt(i));
            } else if (word.charAt(i) == ' ') {
                RightWord.setText(RightWord.getText() + " ");
            } else {
                RightWord.setText(RightWord.getText() + "_");
            }
            RightWord.setText(RightWord.getText() + " ");
            RightWord.setTextColor(Color.WHITE);
            RightWord.setTextSize(15);
        }
    }

    public void resetBoolWord() {
        for (int i = 0; i < W.length; i++) {
            W[i] = false;
        }
    }

    public static boolean moreWords(){
        if(RandomWords.isEmpty())
            return false;
        return true;
    }

    @Override
    public void onYesClick() {

        return;
    }

    @Override
    public void onNoClick() {
        finish();
        Intent i = new Intent(this, Spill.class);
        startActivity(i);
    }

    @Override
    public void onFinishClick()
    {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spill_hangman);


        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(WEIGHT, HEIGHT);
        ll.setMargins(1, 1, 1, 1);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (Locale.getDefault().getLanguage().contentEquals("en")) {
                for (int i = 0; i < 8; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje1);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);

                        }
                    });


                }

                for (int i = 8; i < 16; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje2);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);


                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });


                }
                for (int i = 16; i < 24; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje3);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 24; i < engalfabet.length(); i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje4);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }
            } else {
                for (int i = 0; i < 8; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje1);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 8; i < 16; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje2);
                    final Button button = new Button(this);

                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }
                for (int i = 16; i < 24; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje3);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);


                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 24; i < noralfabet.length(); i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje4);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }
            }
        } else {
            if (Locale.getDefault().getLanguage().contentEquals("en")) {
                for (int i = 0; i < 8; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje1);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);

                        }
                    });


                }

                for (int i = 8; i < 16; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje2);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);


                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });


                }
                for (int i = 16; i < 24; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje3);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 24; i < engalfabet.length(); i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje4);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + engalfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + engalfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }
            } else {
                for (int i = 0; i < 8; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje1);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 8; i < 16; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje2);
                    final Button button = new Button(this);

                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }
                for (int i = 16; i < 24; i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje3);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }

                for (int i = 24; i < noralfabet.length(); i++) {
                    LinearLayout buttonlayout = (LinearLayout) findViewById(R.id.linje4);
                    final Button button = new Button(this);
                    final int j = i;
                    int k = i;
                    button.setId(k);

                    String bokstav = "" + noralfabet.charAt(i);
                    button.setText(bokstav);
                    button.setTextSize(12);
                    button.setPadding(5, 5, 5, 5);


                    buttonlayout.addView(button, ll);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            guess("" + noralfabet.charAt(j));
                            button.setEnabled(false);
                        }
                    });

                }


            }
        }


        Cat0 = (getResources().getStringArray(R.array.execution));
        Cat1 = (getResources().getStringArray(R.array.torture));
        Cat2 = (getResources().getStringArray(R.array.death));
        Cat3 = (getResources().getStringArray(R.array.weapon));
        Cat4 = (getResources().getStringArray(R.array.decease));


        current_lives = total_Lives;

        layout = (LinearLayout) findViewById(R.id.ordlayout);

        Lives = new TextView(this);
        layout.addView(Lives);

        RightWord = new TextView(this);
        RightWord.setText("");
        layout.addView(RightWord);

        if (Hangman.teller++ == 0) {
            RandomWords = getWords();

        }
        if(!moreWords())
        {
            DialogFragment dialog = new TomtForOrdDialog();
            dialog.show(getFragmentManager(),"Fortsett");
        }
        else {
            getNewWord();
        }


    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //her som skal bevar state når du bytter mellom landscape og portrait
    }

    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); //her som skal bevar state når du bytter mellom landscape og portrait



        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spill_hangman, menu);
        return true;
    }

    public static void updateCategories(ArrayList item) {
        for (Object k : item) {
            Cat[(int) k] = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.regel:
                DialogFragment dialog = new ReglerDialog();
                dialog.show(getFragmentManager(), "Avslutt");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}