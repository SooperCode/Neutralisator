package com.soopercode.neutralisator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ria on 6/12/15.
 */
public class OttoContent {

    private static final OttoContent INSTANCE = new OttoContent();
    private final List<Song> SONGS = new ArrayList<>();

    private OttoContent(){
        SONGS.add(new Song("Hexen 3", R.raw.otto_hexen_3_10s));
        SONGS.add(new Song("Hexenklo", R.raw.otto_hexenklo_10s));
        SONGS.add(new Song("Hexenman", R.raw.otto_hexman_10s));
        SONGS.add(new Song("Hexen 4", R.raw.otto_hexen_4_15s));
        SONGS.add(new Song("Im Wagen vor mir 2", R.raw.otto_imwagen_2_15s));
        SONGS.add(new Song("Die Alte Waldhexe", R.raw.otto_lebtnoch_1_20s));
        SONGS.add(new Song("Die Alte Waldhexe_2", R.raw.otto_lebtnoch_2_20s));
        SONGS.add(new Song("Im Wagen vor mir 1", R.raw.otto_imwagen_1_20s));
        SONGS.add(new Song("Hexen 2", R.raw.otto_hexen_2_20s));
        SONGS.add(new Song("Hexen 1", R.raw.otto_hexen_1_20s));
        SONGS.add(new Song("Aber bitte mit Sahne", R.raw.otto_mitsahne_25s));
    }

    public static OttoContent getInstance(){
        return INSTANCE;
    }

    public List<Song> getSongs(){
        return SONGS;
    }

    public class Song{
        private String title;
        private int resId;

        private Song(String title, int resId){
            this.title = title;
            this.resId = resId;
        }

        public String getTitle(){
            return title;
        }

        public int getResId(){
            return resId;
        }
    }

}
