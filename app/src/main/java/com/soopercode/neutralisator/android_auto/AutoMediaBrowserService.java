package com.soopercode.neutralisator.android_auto;

import android.graphics.BitmapFactory;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.service.media.MediaBrowserService;

import com.soopercode.neutralisator.OttoContent;
import com.soopercode.neutralisator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ria on 6/11/15.
 */
public class AutoMediaBrowserService extends MediaBrowserService {

    private static final String TAG = AutoMediaBrowserService.class.getSimpleName();

    private static final String MEDIA_ID_ROOT = "__ROOT__";
    //private final String ROOT_ITEM_TEXT = getString(R.string.button_neutralize); //<-NPE!
    private final String ROOT_ITEM_TEXT = "Neutralisieren!";

    private MediaSession mediaSession;
    private List<OttoContent.Song> songs = OttoContent.getInstance().getSongs();



    @Override
    public void onCreate() {
        super.onCreate();

        mediaSession = new MediaSession(this, "Auto Neutralizer");
        mediaSession.setActive(true);
        //mediaSession.setCallback(/* ... */);
        setSessionToken(mediaSession.getSessionToken());
    }

    @Override
    public BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {

        // optional: check if caller is allowed access

        return new BrowserRoot(MEDIA_ID_ROOT, null);
    }

    /**
     * The Auto device client builds the top-level menu by calling onLoadChildren()
     * with the root node object and getting it's children. The client builds submenus
     * by calling the same method with other child nodes.
     */
    @Override
    public void onLoadChildren(String parentId, Result<List<MediaBrowser.MediaItem>> result) {
        List<MediaBrowser.MediaItem> items = new ArrayList<>();
        // Check if this is the root menu:
        if (MEDIA_ID_ROOT.equals(parentId)) {
            // we need our one root item which simulates the neutralize button
            items.add(generateBrowsableMediaItem(ROOT_ITEM_TEXT));

        } else if(!parentId.isEmpty()){
            // if it's not the root, it's the songs -> add them.
            int songCount = songs.size();
            for(int i=0; i<songCount; i++){
                items.add(generatePlayableMediaItem(i));
            }

        }
        result.sendResult(items);

    }

    private MediaBrowser.MediaItem generateBrowsableMediaItem(String label){
        MediaDescription.Builder mediaDescriptionBuilder = new MediaDescription.Builder();
        mediaDescriptionBuilder.setMediaId(label);
        mediaDescriptionBuilder.setTitle(label);
        mediaDescriptionBuilder.setIconBitmap(
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        return new MediaBrowser.MediaItem(mediaDescriptionBuilder.build(),
                MediaBrowser.MediaItem.FLAG_BROWSABLE );
    }

    private MediaBrowser.MediaItem generatePlayableMediaItem(int songIndex){
        MediaDescription.Builder mediaDescriptionBuilder = new MediaDescription.Builder();
        String title = songs.get(songIndex).getTitle();
        mediaDescriptionBuilder.setMediaId(title); //using title for now
        mediaDescriptionBuilder.setTitle(title);
        mediaDescriptionBuilder.setIconBitmap(
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        return new MediaBrowser.MediaItem(mediaDescriptionBuilder.build(),
                MediaBrowser.MediaItem.FLAG_PLAYABLE );
    }



}
