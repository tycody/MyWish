package Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

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
    protected void onPreExecute(){
       /* dialog.setMessage("Please wait");
        dialog.show();*/
    }


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

    protected void onPostExecute(Bitmap result){
        bmView.setImageBitmap(result);
        if(dialog.isShowing())
            dialog.hide();
    }
}
