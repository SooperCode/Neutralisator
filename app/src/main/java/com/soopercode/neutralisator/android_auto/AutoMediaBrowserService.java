package com.soopercode.neutralisator.android_auto;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.service.media.MediaBrowserService;

import java.util.List;

/**
 * Created by ria on 6/11/15.
 */
public class AutoMediaBrowserService extends MediaBrowserService {

    private static final String TAG = AutoMediaBrowserService.class.getSimpleName();

    private static final String MEDIA_ID_ROOT = "__ROOT__";



    @Override
    public void onCreate() {
        super.onCreate();

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

        // Check if this is the root menu:
        if (MEDIA_ID_ROOT.equals(parentId)) {


        } else if(!parentId.isEmpty()){

        }

    }



}
