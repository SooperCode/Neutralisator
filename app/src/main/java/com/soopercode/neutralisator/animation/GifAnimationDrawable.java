package com.soopercode.neutralisator.animation;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** https://www.dropbox.com/s/rlyamlkqw02n0y9/GifAnimationDrawable.java?dl=0
 * <p>Creates an AnimationDrawable from a GIF image.</p>
 *
 * @author Femi Omojola <femi@hipmob.com>
 */
public class GifAnimationDrawable extends AnimationDrawable {

    private static final String TAG = GifAnimationDrawable.class.getSimpleName();

    private boolean decoded;
    private GifDecoder mGifDecoder;
    private Bitmap mTmpBitmap;
    private int height, width;

    public GifAnimationDrawable(File f) throws IOException
    {
        this(f, false);
    }

    public GifAnimationDrawable(InputStream is) throws IOException
    {
        this(is, false);
    }

    public GifAnimationDrawable(File f, boolean inline) throws IOException
    {
        this(new BufferedInputStream(new FileInputStream(f), 32768), inline);
    }

    public GifAnimationDrawable(InputStream is, boolean inline) throws IOException
    {
        super();
        InputStream bis = is;
        if(!BufferedInputStream.class.isInstance(bis)) bis = new BufferedInputStream(is, 32768);
        decoded = false;
        mGifDecoder = new GifDecoder();
        mGifDecoder.read(bis);
        mTmpBitmap = mGifDecoder.getFrame(0);
        Log.d("GifAnimationDrawable", "===>Lead frame: ["+width+"x"+height+"; "+mGifDecoder.getDelay(0)+";"+mGifDecoder.getLoopCount()+"]");
        height = mTmpBitmap.getHeight();
        width = mTmpBitmap.getWidth();
        addFrame(new BitmapDrawable(mTmpBitmap), mGifDecoder.getDelay(0));
        setOneShot(mGifDecoder.getLoopCount() != 0);
        setVisible(true, true);
        if(inline){
            loader.run();
        }else{
            new Thread(loader).start();
        }
    }

    public boolean isDecoded(){ return decoded; }

    private Runnable loader = new Runnable(){
        public void run()
        {
            mGifDecoder.complete();
            int frameCount = mGifDecoder.getFrameCount();
            for(int i=1; i<frameCount; i++){
                mTmpBitmap = mGifDecoder.getFrame(i);
                int delay = mGifDecoder.getDelay(i);
                addFrame(new BitmapDrawable(mTmpBitmap), delay);
                Log.d(TAG, "addFrame " + i + " | delay: " + delay);
            }
            decoded = true;
            mGifDecoder = null;
        }
    };

    public int getMinimumHeight(){ return height; }
    public int getMinimumWidth(){ return width; }
    public int getIntrinsicHeight(){ return height; }
    public int getIntrinsicWidth(){ return width; }
}
