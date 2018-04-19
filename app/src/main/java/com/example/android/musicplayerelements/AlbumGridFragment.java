package com.example.android.musicplayerelements;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

/*
Fragment displaying list of music albums in grid format
 */
public class AlbumGridFragment extends Fragment {
    private ArrayList<Album> albums;

    public AlbumGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_album_gridr, container, false);

        albums = new ArrayList<>();

        albums = MusicDataProvider.getAlbums(getActivity().getApplicationContext());

        GridView gridView = rootView.findViewById(R.id.grid_view);

        AlbumGridAdapter albumGridAdapter = new AlbumGridAdapter(getActivity(), albums);

        gridView.setAdapter(albumGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeAlbums();

                //Opens list of songs from clicked album
                SongsListFragment songsListFragment = new SongsListFragment();
                Bundle bundle = new Bundle();
                String album = ((TextView)view.findViewById(R.id.album_title_grid)).getText().toString();
                String author = ((TextView)view.findViewById(R.id.album_artist_grid)).getText().toString();
                bundle.putString("album", album);
                bundle.putString("author", author);
                songsListFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.albums_container, songsListFragment, "songs").commit();
            }
        });

        return rootView;
    }

    /*
    Method removing grid or list album view if exists
    */
    private void removeAlbums(){
        //Removing grid view fragment if exists
        Fragment albumGridFragment = getActivity().getSupportFragmentManager().findFragmentByTag("grid");
        if (albumGridFragment!=null){
            getActivity().getSupportFragmentManager().beginTransaction().remove(albumGridFragment).commit();
        }

        //Removing list view fragment if exists
        Fragment albumListFragment = getActivity().getSupportFragmentManager().findFragmentByTag("list");
        if (albumListFragment!=null){
            getActivity().getSupportFragmentManager().beginTransaction().remove(albumListFragment).commit();
        }

        //Removing list of songs in album fragmnt if exists
        Fragment songsListFragment = getActivity().getSupportFragmentManager().findFragmentByTag("songs");
        if (songsListFragment!=null){
            getActivity().getSupportFragmentManager().beginTransaction().remove(songsListFragment).commit();
        }

    }




}
