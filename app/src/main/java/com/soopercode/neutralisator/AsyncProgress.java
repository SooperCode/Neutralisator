package com.soopercode.neutralisator;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
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
        int songDuration = params[0];
        long sleepTime = (songDuration- 850)/100;
        int pStatus = 0;

        while(pStatus < 100){
            pStatus++;
            progressBar.setProgress(pStatus);
            try{
                Thread.sleep(sleepTime);
            }catch(InterruptedException e){ Log.e(TAG, e.toString());}
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();

        doneDialog = createDoneDialog();
        doneDialog.show();
    }

    private Dialog createNeutralizeDialog(){
/*        progressBar = new ProgressDialog(context);

        progressBar.setMessage(context.getString(R.string.progress_message));
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        ImageView img = new ImageView(context);
//        img.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));
//        progressBar.setView(img);
        progressBar.setCancelable(false);
        progressBar.setMax(100);
        progressBar.setProgressNumberFormat(null);
        progressBar.setProgressPercentFormat(null);

        Drawable customDrawable = context.getResources().getDrawable(R.drawable.custom_progressbar);
        progressBar.setProgressDrawable(customDrawable);
*/


//        Dialog progressDialog = new Dialog(context, R.style.DialogStyle);
        Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_neutralize);
        dialog.setCancelable(false);

        //make custom text
        TextView text2 = (TextView)dialog.findViewById(R.id.textview_progress_changeable);
        text2.setText(chooseText());

        progressBar = (ProgressBar)dialog.findViewById(R.id.progressbar);
//        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.custom_progressbar));

        try {
            gif = new
                    GifAnimationDrawable(context.getResources().openRawResource(R.raw.dancing_bat));
            gif.setOneShot(false);
        } catch (Resources.NotFoundException | IOException e) {
            e.printStackTrace();
        }
        gif.setVisible(true, true);
        ImageView img = (ImageView)dialog.findViewById(R.id.imageview_progress);
        img.setImageDrawable(gif);

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
            case 0: text = context.getString(R.string.message_01);
                break;
            case 1: text = context.getString(R.string.message_02);
                break;
            case 2: text = context.getString(R.string.message_03);
                break;
            case 3: text = context.getString(R.string.message_04);
                break;
            case 4: text = context.getString(R.string.message_05);
                break;
            case 5: text = context.getString(R.string.message_06);
                break;
            case 6: text = context.getString(R.string.message_07);
                break;
            case 7: text = context.getString(R.string.message_08);
                break;
            case 8: text = context.getString(R.string.message_09);
                break;
            case 9: text = context.getString(R.string.message_10); break;
        }

        return text;
    }

}