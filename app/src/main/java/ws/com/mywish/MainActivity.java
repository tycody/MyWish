package ws.com.mywish;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;


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
                Intent i = new Intent(view.getContext(), WishItems.class);
                i.putExtra("ItemId", position);
                i.putExtra("itemName", items.get(position).getName());
                startActivity(i);

            }
        });
    }

    private void makeExamples(List<WishlistItem> list) {
        for(int i = 0; i < 10; i++) {
            WishlistItem item = new WishlistItem(i, "Item no " + i, "url: " + i + "RAND");
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
