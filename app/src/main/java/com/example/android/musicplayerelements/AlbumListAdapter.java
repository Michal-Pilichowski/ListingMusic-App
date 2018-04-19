package com.example.android.musicplayerelements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michal Pichowski on 2018-03-15.
 */

/*
Custom adapter displaying found albums in list format
 */
public class AlbumListAdapter extends ArrayAdapter<Album> {

    public AlbumListAdapter(Activity context, ArrayList<Album> albums){
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.albums_list_element, parent, false);
        }

        final Album currentAlbum = getItem(position);

        ImageView albumCover = listItemView.findViewById(R.id.list_cover);
        TextView albumTitle = listItemView.findViewById(R.id.list_title);
        TextView albumAuthor = listItemView.findViewById(R.id.list_author);

        //Setting image bitmap from file path for cover
        if (currentAlbum.getCover()==null){
            albumCover.setImageResource(currentAlbum.getImageID());
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(currentAlbum.getCover());
            albumCover.setImageBitmap(bitmap);
        }

        albumAuthor.setText(currentAlbum.getAlbumAuthor());
        albumTitle.setText(currentAlbum.getAlbumTitle());

        return listItemView;
    }

}
