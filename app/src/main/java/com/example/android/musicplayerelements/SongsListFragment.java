package com.example.android.musicplayerelements;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsListFragment extends Fragment {

    public SongsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_albums_list, container, false);

        ArrayList<Song> songs = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if (bundle!=null){
            String author = bundle.getString("author");
            String album = bundle.getString("album");

            if (!author.isEmpty()&&!album.isEmpty()){
                songs = MusicDataProvider.getSongsFromAlbum(getActivity(), author, album);
                SongListAdapter songListAdapter = new SongListAdapter(getActivity(), songs);

                ListView listView = rootView.findViewById(R.id.albums_list_view);

                listView.setAdapter(songListAdapter);
            }
        }

        return rootView;
    }

}
