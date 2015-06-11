package com.soopercode.neutralisator;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MediaPlayer player;

    private static final int[] SONGS = {
            R.raw.otto_hexen_3_10s,
            R.raw.otto_hexenklo_10s,
            R.raw.otto_hexman_10s,
            R.raw.otto_hexen_4_15s,
            R.raw.otto_imwagen_2_15s,
            R.raw.otto_lebtnoch_1_20s,
            R.raw.otto_lebtnoch_2_20s,
            R.raw.otto_imwagen_1_20s,
            R.raw.otto_hexen_1_20s,
            R.raw.otto_hexen_2_20s,
            R.raw.otto_mitsahne_25s
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
            actionBar.setTitle(R.string.actionbar_label);
        }
    }

    public void neutralize(View view) {

        //play random song
        Random random = new Random();
        int chosenSong = random.nextInt(10);
        Log.wtf(TAG, String.valueOf(chosenSong));
        player = MediaPlayer.create(this, SONGS[chosenSong]);

        int songDuration = player.getDuration();
        player.start();
        new AsyncProgress(this).execute(songDuration);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player !=null) player.release();
    }


    /************** for later use ***************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
