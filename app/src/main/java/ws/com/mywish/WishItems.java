package ws.com.mywish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import Helpers.AsyncImageFetcher;

/**
 * Created by ziggyzaggy on 23/01/2015.
 */
public class WishItems extends Activity{

    private int itemid;
    private String itemName;
    private TextView TVitemid;
    private ImageView bmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_items);

        getExtras(getIntent());

        TVitemid =(TextView) findViewById(R.id.itemid);
        TVitemid.setText("ID: " + itemid + "\n Name: " + itemName);

        bmView = (ImageView) findViewById(R.id.bmView);

        AsyncImageFetcher fetcher = new AsyncImageFetcher(bmView, this);
        fetcher.execute("http://images.visitcanberra.com.au/images/canberra_hero_image.jpg");
    }



    private void getExtras(Intent in){
        itemid = in.getIntExtra("ItemId", 0);
        itemName = in.getStringExtra("itemName");
    }
}
