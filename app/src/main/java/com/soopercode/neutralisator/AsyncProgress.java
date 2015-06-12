package com.soopercode.neutralisator;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class AsyncProgress extends AsyncTask<Integer, Void, Void> {

    private static final String TAG = AsyncProgress.class.getSimpleName();

    private ProgressBar progressBar;
//    private int pStatus = 0;
    private Context context;

    private Button neutralize;
    private Dialog progressDialog;
    private Dialog doneDialog;

    private GifAnimationDrawable gif;

    public AsyncProgress(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        neutralize = (Button)((Activity)context).findViewById(R.id.button_neutralize);
        neutralize.setVisibility(View.INVISIBLE);

        progressDialog = createNeutralizeDialog();
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Integer... params) {
        int songDurationMillis = params[0] - 200; //make up for thread's delay
        long start = SystemClock.uptimeMillis();

        int pStatus = 0;
        while(pStatus < 100){
            double position = ((double)SystemClock.uptimeMillis()-start) / songDurationMillis*100;
            pStatus = (int)position;
            progressBar.setProgress(pStatus);
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){ Log.e(TAG, e.toString());}
            if(isCancelled()){
                break;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();

        doneDialog = createDoneDialog();
        doneDialog.show();
    }

    @Override
    protected void onCancelled() {
        progressDialog.dismiss();
        neutralize.setVisibility(View.VISIBLE);
    }

    private Dialog createNeutralizeDialog(){

        Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_neutralize);
        dialog.setCancelable(false);

        //show random text
        TextView text2 = (TextView)dialog.findViewById(R.id.textview_progress_changeable);
        text2.setText(chooseText());

        progressBar = (ProgressBar)dialog.findViewById(R.id.progressbar);

        try {
            gif = new GifAnimationDrawable(context.getResources().openRawResource(R.raw.dancing_bat));
            gif.setOneShot(false);
            ImageView img = (ImageView)dialog.findViewById(R.id.imageview_progress);
            img.setImageDrawable(gif);
            gif.start();
        } catch (Resources.NotFoundException | IOException e) {
            Log.e(TAG, "Animation went wrong", e);
        }

        return dialog;
    }

    private Dialog createDoneDialog(){
        Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_done);
        dialog.setCancelable(false);
        Button btn = (Button)dialog.findViewById(R.id.button_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neutralize.setVisibility(View.VISIBLE);
                doneDialog.dismiss();
            }
        });
        return dialog;
    }

    private String chooseText(){
        String text = "";
        Random randy = new Random();

        switch (randy.nextInt(9)){
            case 0: text = context.getString(R.string.message_01); break;
            case 1: text = context.getString(R.string.message_02); break;
            case 2: text = context.getString(R.string.message_03); break;
            case 3: text = context.getString(R.string.message_04); break;
            case 4: text = context.getString(R.string.message_05); break;
            case 5: text = context.getString(R.string.message_06); break;
            case 6: text = context.getString(R.string.message_07); break;
            case 7: text = context.getString(R.string.message_08); break;
            case 8: text = context.getString(R.string.message_09); break;
            case 9: text = context.getString(R.string.message_10); break;
        }
        return text;
    }

}