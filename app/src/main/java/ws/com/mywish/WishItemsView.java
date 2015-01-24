package ws.com.mywish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.WishAdapter;
import Entities.WishItem;
import Helpers.AsyncImageFetcher;

/**
 * Created by ziggyzaggy on 23/01/2015.
 */
public class WishItemsView extends Activity{

    private int itemid;
    private String itemName;
    private TextView TVitemid;
    private ImageView bmView;
    GridView grid;
    private HashMap<Integer, ArrayList<WishItem>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_items);

        map = new HashMap<>();
        makeExamples(map);
        getExtras(getIntent());

        if(!map.containsKey(itemid)){
            new AlertDialog.Builder(WishItemsView.this, R.style.Theme_AppCompat_Light_Dialog)
                    .setTitle("Confirm")
                    .setMessage(" щас работает только список номер 1, остальные всё крашнут нах")
                    .setPositiveButton("K", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }else{
            init();
        }




    }

    private void init(){
        grid = (GridView) findViewById(R.id.wishGridView);
        WishAdapter adapter = new WishAdapter(this, map.get(itemid));
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView bm =(ImageView) v.findViewById(R.id.bm_item_View);
               /* ColorMatrix cm = new ColorMatrix();
                cm.setSaturation(1);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
                bm.setColorFilter(filter);*/

                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                bm.startAnimation(anim); //set animation
            }
        });
    }

    private void getExtras(Intent in){
        itemid = in.getIntExtra("ItemId", 0);
        itemName = in.getStringExtra("itemName");
    }

    private void makeExamples(HashMap<Integer, ArrayList<WishItem>> list){
        ArrayList<WishItem> itemArr = new ArrayList<WishItem>();



        for(int i = 0; i < 5; i++){
            WishItem w = new WishItem(i, 1, "Wish item number: " + i, "http://upload.wikimedia.org/wikipedia/commons/0/08/Macchiato_as_being_served_at_Kaffebrenneriet_Torshov%2C_Oslo%2C_Norway_2_600x600_100KB.jpg");
            itemArr.add(i, w);
        }

        list.put(1, itemArr);
    }
}
