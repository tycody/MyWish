package Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Entities.WishItem;
import Helpers.AsyncImageFetcher;
import ws.com.mywish.R;

/**
 * Created by kris on 24/01/2015.
 */
public class WishAdapter extends BaseAdapter{

    private Context mContext;
    ViewHolder holder;
    private ArrayList<WishItem> wishItemList;
    private AsyncImageFetcher fetcher;
    public WishAdapter(Context c, ArrayList<WishItem> list){
        mContext = c;
        wishItemList = list;

    }


    @Override
    public int getCount() {
        return wishItemList.size();
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
            v = li.inflate(R.layout.wish_layout, parent, false);
            holder = new ViewHolder();
            holder.text =(TextView) v.findViewById(R.id.wish_item_desc);
            holder.img = (ImageView)v.findViewById(R.id.bm_item_View);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }

        holder.text.setText(wishItemList.get(position).getItem_name() + wishItemList.get(position).getImageUrl());


        fetcher = new AsyncImageFetcher(holder.img, (Activity)mContext);
        fetcher.execute(wishItemList.get(position).getImageUrl());
/*
        TextView descTV = (TextView) v.findViewById(R.id.wish_item_desc);
        descTV.setText(wishItemList.get(position).getItem_name());

        ImageView bmView = (ImageView) v.findViewById(R.id.bm_item_View);
        fetcher = new AsyncImageFetcher(bmView, (Activity)mContext);
        fetcher.execute(wishItemList.get(position).getImageUrl());


        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
        bmView.setColorFilter(filter);
*/
        return v;
    }



    static class ViewHolder{
        TextView text;
        ImageView img;
    }
}
