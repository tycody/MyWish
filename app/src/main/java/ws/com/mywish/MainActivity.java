package ws.com.mywish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Helpers.JSONParser;


public class MainActivity extends Activity {

    private ItemAdapter adapter;
    private List<WishlistItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<WishlistItem>();
        makeExamples(items);
        adapter = new ItemAdapter(this, items);
        GridView grid = (GridView) findViewById(R.id.gridview);
        grid.setAdapter(adapter);



        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent i = new Intent(view.getContext(), WishItemsView.class);
                i.putExtra("ItemId", position);
                i.putExtra("itemName", items.get(position).getName());

                startActivity(i);


            }
        });


        conect();

    }


    private void conect() {
        //TODO - implement asynctask rather than overriding thread policies
        //also, rewrite php code to return a test json right now the parser breaks because  of an
        //unexpected http response
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONParser parser = new JSONParser();

        String theURL = "http://192.168.1.115:1337/Wishlist/getWishlists.php";
        try {
            JSONObject jobj = parser.makeHttpRequest(theURL, "GET", params);
            Log.d("jobj: ", "" + jobj);
        }catch(java.io.IOException e) {
            Log.e("io",""+e);
        }
    }
    private void makeExamples(List<WishlistItem> list) {
        for(int i = 0; i < 10; i++) {
            WishlistItem item = new WishlistItem(i, "Wishlist  no " + i, "url: " + i + "RAND");
            list.add(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
