package com.soopercode.neutralisator;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MediaPlayer player;
    private AsyncProgress async;

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

    // on Neutralize Button click
    public void neutralize(View view) {

        //play random song
        Random random = new Random();
        List<OttoContent.Song> songs = OttoContent.getInstance().getSongs();
        int songIndex = random.nextInt(songs.size() - 1);
        Log.wtf(TAG, String.valueOf(songIndex));
        player = MediaPlayer.create(this, songs.get(songIndex).getResId());

        int songDurationMillis = player.getDuration();
        async = new AsyncProgress(this);
        async.execute(songDurationMillis);
        player.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player != null){
            player.release();
        }
        if(async != null){
            async.cancel(true);
        }

    }


    /************** menu stuff - for later use ***************/

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
