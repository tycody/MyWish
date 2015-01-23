package Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.InputStream;

import ws.com.mywish.R;

/**
 * Created by ziggyzaggy on 23/01/2015.
 */
public class AsyncImageFetcher extends AsyncTask<String, Void, Bitmap> {

    ImageView bmView;
    ProgressDialog dialog;
    Context context;
    public AsyncImageFetcher(ImageView iv, Activity activity){
        this.bmView = iv;
        context = activity;
        dialog = new ProgressDialog(context);
    }

    //impletement if needed
    //(show progress dialogs etc, do anything before executing the task)
    protected void onPreExecute(){
       /* dialog.setMessage("Please wait");
        dialog.show();*/
    }

    //task itself
    @Override
    protected Bitmap doInBackground(String... urls) {
        String from = urls[0];
        Bitmap bm = null;

        try {
            InputStream in = new java.net.URL(from).openStream();
            bm = BitmapFactory.decodeStream(in);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }


        return bm;
    }
    //put acquired bitmap as the source, animate the whole thing
    protected void onPostExecute(Bitmap result){
        bmView.setImageBitmap(result);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        bmView.startAnimation(anim);

        if(dialog.isShowing())
            dialog.hide();
    }
}
