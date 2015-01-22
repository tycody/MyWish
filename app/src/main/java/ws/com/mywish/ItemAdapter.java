package ws.com.mywish;

/**
 * Created by ziggyzaggy on 22/01/2015.
 */


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<WishlistItem> itemArr;

    public ItemAdapter(Context c, List<WishlistItem> list) {
        mContext = c;
        this.itemArr = list;
    }

    @Override
    public int getCount() {
        return itemArr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(v == null){
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_layout, null);

        }

        TextView tv = (TextView) v.findViewById(R.id.maintext);
        tv.setText(itemArr.get(position).getName());


        return v;
    }
}
