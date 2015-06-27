package com.example.sp_root.twentytiles7words;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sp_root.twentytiles7words.util.SystemUiHider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = false;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    public static Typeface teTextFont;
    private Controller controller;

    ArrayList<TextView> clues;
    ArrayList<TextView> letters;

    TextView textView1;

    Button clearGuess;
    Button guessWord;
    Button languageButton;
    TextView selectedLanguage;
    Button newGameButton;
    Language language;
    static AssetManager assetManager;
    String[] files;
    ArrayList<Button> tileButtons;

    boolean[] guessedCorrectly = new boolean[7];

    private ArrayList<String> tiles;
    private ArrayList<ArrayList<String>> wordsAndClues;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        //TODO
        // //final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        //File file = new File("TE");
        assetManager = getAssets();
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = readAsset("EN/words.txt");
        //AssetManager assets = getAssets();
        //File a = new File(assets);
        // boolean exists = file.exists();
        //boolean isDir = file.isDirectory();
        teTextFont = Typeface.createFromAsset(getAssets(), "TE/Gidugu.ttf");

        //Load buttons & textViews here
        clues = new ArrayList<TextView>();
        clues.add((TextView) this.findViewById(R.id.clueOne));
        clues.add((TextView) this.findViewById(R.id.clueTwo));
        clues.add((TextView) this.findViewById(R.id.clueThree));
        clues.add((TextView) this.findViewById(R.id.clueFour));
        clues.add((TextView) this.findViewById(R.id.clueFive));
        clues.add((TextView) this.findViewById(R.id.clueSix));
        clues.add((TextView) this.findViewById(R.id.clueSeven));

        letters = new ArrayList<TextView>();
        letters.add((TextView) this.findViewById(R.id.lettersOne));
        letters.add((TextView) this.findViewById(R.id.lettersTwo));
        letters.add((TextView) this.findViewById(R.id.lettersThree));
        letters.add((TextView) this.findViewById(R.id.lettersFour));
        letters.add((TextView) this.findViewById(R.id.lettersFive));
        letters.add((TextView) this.findViewById(R.id.lettersSix));
        letters.add((TextView) this.findViewById(R.id.lettersSeven));

        textView1 = (TextView) this.findViewById(R.id.builtWord);
        clearGuess = (Button) this.findViewById(R.id.clearGuess);
        clearGuess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                textView1.setText("");

                reloadTiles(false);

            }
        });
        guessWord = (Button) this.findViewById(R.id.guessWord);
        guessWord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String guess = textView1.getText().toString();
                for (int i = 0; i < wordsAndClues.size(); i++) {
                    if (!guessedCorrectly[i]) {
                        if (guess.equals(wordsAndClues.get(i).get(0))) {
                            guessedCorrectly[i] = true;
                            //check for win
                            boolean won = true;
                            for (int j = 0; j < guessedCorrectly.length; j++) {
                                if (!guessedCorrectly[j]) {
                                    won = false;
                                }
                            }
                            if (won) {
                                makeToast("Congratulations, You won!");
                            }
                            for (Button b : tileButtons) {
                                if (b.getVisibility() == View.INVISIBLE) {
                                    b.setText("");
                                }
                            }
                        }
                    }
                }
                textView1.setText("");
                loadClues();
                reloadTiles(false);
            }
        });

        tileButtons = new ArrayList<Button>();
        tileButtons.add((Button) this.findViewById(R.id.tile0));
        tileButtons.add((Button) this.findViewById(R.id.tile1));
        tileButtons.add((Button) this.findViewById(R.id.tile2));
        tileButtons.add((Button) this.findViewById(R.id.tile3));
        tileButtons.add((Button) this.findViewById(R.id.tile4));
        tileButtons.add((Button) this.findViewById(R.id.tile5));
        tileButtons.add((Button) this.findViewById(R.id.tile6));
        tileButtons.add((Button) this.findViewById(R.id.tile7));
        tileButtons.add((Button) this.findViewById(R.id.tile8));
        tileButtons.add((Button) this.findViewById(R.id.tile9));
        tileButtons.add((Button) this.findViewById(R.id.tile10));
        tileButtons.add((Button) this.findViewById(R.id.tile11));
        tileButtons.add((Button) this.findViewById(R.id.tile12));
        tileButtons.add((Button) this.findViewById(R.id.tile13));
        tileButtons.add((Button) this.findViewById(R.id.tile14));
        tileButtons.add((Button) this.findViewById(R.id.tile15));
        tileButtons.add((Button) this.findViewById(R.id.tile16));
        tileButtons.add((Button) this.findViewById(R.id.tile17));
        tileButtons.add((Button) this.findViewById(R.id.tile18));
        tileButtons.add((Button) this.findViewById(R.id.tile19));

        languageButton = (Button) this.findViewById(R.id.languageButton);
        languageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                switch (selectedLanguage.getText().toString()) {
                    case "Telugu":
                        selectedLanguage.setText("English");
                        break;
                    default:
                        selectedLanguage.setText("Telugu");
                }
            }
        });

        selectedLanguage = (TextView) this.findViewById(R.id.selectedLanguage);
        newGameButton = (Button) this.findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                newGame();
            }
        });
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                //TODO
                                mControlsHeight = contentView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            //TODO
                            /*contentView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);*/
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            //TODO
                            contentView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(0);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }*/
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //TODO
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        newGame();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void makeToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void newGame() {
        /**
         * Initialize things
         */
        controller = Controller.instance();
        controller.setFullscreenActivity(this);
        switch (selectedLanguage.getText().toString()) {
            case "Telugu":
                try {
                    language = new Telugu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    language = new English();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        }
        controller.setLanguage(language);
        try {
            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        font = language.getFont();
        wordsAndClues = controller.getWordsAndClues();
        tiles = controller.getTiles();
        for (int i = 0; i < tileButtons.size(); i++) {
            tileButtons.get(i).setText(tiles.get(i));
        }
        loadClues();
        reloadTiles(true);
    }

    /*
     * Re-loads the tiles to the tile panel optionally putting them in a
	 * different order.
	 */
    private void reloadTiles(boolean shuffle) {
        if (shuffle) {
            Collections.shuffle(tileButtons);
        }
        for (int i = 0; i < tileButtons.size(); i++) {
            if ("".equals(tileButtons.get(i).getText().toString())) {
                tileButtons.get(i).setVisibility(View.INVISIBLE);
            } else {

                Button b = tileButtons.get(i);
                b.setTypeface(language.font);
                b.setText(tileButtons.get(i).getText().toString());
                b.setVisibility(View.VISIBLE);
                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Button b = (Button) v;
                        String buttonText = b.getText().toString();
                        if ("".equals(buttonText)) {
                            //do nothing button is already used
                        } else {
                            textView1.setText(textView1.getText() + buttonText);
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        }
    }

    /*
     * Load the clues to the screen.
	 */
    private void loadClues() {
        for (int i = 0; i < clues.size(); i++) {
            clues.get(i).setText(wordsAndClues.get(i).get(1));
            if (!guessedCorrectly[i]) {
                letters.get(i).setText("(" + language.splitToLogicalCharacters(wordsAndClues.get(i).get(0)).size() + ") Letters");
            } else {
                letters.get(i).setText(wordsAndClues.get(i).get(0));
            }
        }
    }

    /**
     *
     */
    public static InputStream assetToInputStream(String path) {
        InputStream is = null;
        try {
            is = assetManager.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * Reads the text of an asset. Should not be run on the UI thread.
     *
     * @param path The path to the asset.
     * @return The plain text of the asset
     */
    public static String readAsset(String path) {
        String contents = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = assetManager.open(path);
            reader = new BufferedReader(new InputStreamReader(is));
            contents = reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents += '\n' + line;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return contents;
    }
}
