package com.example.android.musicplayerelements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michal Pichowski on 2018-03-15.
 */

/*
Custom adapter populating list or grid view with found music albums
 */
public class AlbumGridAdapter extends BaseAdapter {
   private Context mContext;
   private ArrayList<Album> mAlbums;

   public AlbumGridAdapter(Context context, ArrayList<Album> albums){
       mContext = context;
       mAlbums = albums;
   }

    @Override
    public int getCount() {
        return mAlbums.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Album currentAlbum = mAlbums.get(position);

        if (convertView == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.albums_grid_element, null);
        }

        ImageView cover = convertView.findViewById(R.id.album_cover_grid);
        TextView title = convertView.findViewById(R.id.album_title_grid);
        TextView author = convertView.findViewById(R.id.album_artist_grid);

        //Setting image bitmap from file path for cover
        if (currentAlbum.getCover()==null){
            cover.setImageResource(currentAlbum.getImageID());
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(currentAlbum.getCover());
            cover.setImageBitmap(bitmap);
        }

        title.setText(currentAlbum.getAlbumTitle());
        author.setText(currentAlbum.getAlbumAuthor());

        return convertView;
    }

}
