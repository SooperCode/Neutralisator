package com.soopercode.neutralisator;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by ria on 5/14/15.
 */
public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(Context context) {
        super(context, R.style.NewDialog);

    }

    public static CustomProgressDialog createCustomProgressDialog(Context context, ProgressBar progressBar, String text){
        CustomProgressDialog dialog = new CustomProgressDialog(context);
        //dialog.setTitle(text);
        dialog.setCancelable(false);

        //this will add the ProgressBar to the dialog:
//        dialog.addContentView(progressBar, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return dialog;
    }



//    public static CustomProgressDialog show(Context context, CharSequence title, CharSequence message){
//        return show(context, title, message, false);
//    }
//
//    public static CustomProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate){
//        return show(context, title, message, indeterminate, false, null);
//    }
//
//    public static CustomProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable){
//        return show(context, title, message, indeterminate, cancelable, null);
//    }
//
//    public static CustomProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable, OnCancelListener cancelListener){
//        CustomProgressDialog dialog = new CustomProgressDialog(context);
//        dialog.setTitle(title);
//        dialog.setCancelable(cancelable);
//        dialog.setOnCancelListener(cancelListener);
//
//
//
//        //this will add the ProgressBar to the dialog:
//        dialog.addContentView(progressBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//
//        return dialog;
//    }
}
