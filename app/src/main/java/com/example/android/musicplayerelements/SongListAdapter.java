package com.example.android.musicplayerelements;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Michal Pichowski on 2018-03-16.
 */

/*
Custom adapter for populating list view with information about found songs.
 */
public class SongListAdapter extends ArrayAdapter<Song> {

    public SongListAdapter(Activity context, ArrayList<Song> songs){
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_element, parent, false);
        }

        final Song currentSong = getItem(position);

        TextView number = listItemView.findViewById(R.id.song_element_number);
        TextView title = listItemView.findViewById(R.id.song_element_title);
        TextView lenght = listItemView.findViewById(R.id.song_list_lenght);

        number.setText("# " + currentSong.getNumberOnAlbum());
        title.setText(currentSong.getSongTitle());

        Date date = new Date(currentSong.getSongLenght());
        String formattedDate = formatTime(date);

        lenght.setText(formattedDate);

        return listItemView;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat;
        timeFormat = new SimpleDateFormat("mm:ss");
        return timeFormat.format(dateObject);
    }

}
