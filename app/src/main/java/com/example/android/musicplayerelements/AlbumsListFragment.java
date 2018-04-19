package com.example.android.musicplayerelements;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsListFragment extends Fragment {
    private ArrayList<Album> albums;

    public AlbumsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_albums_list, container, false);

        albums = new ArrayList<>();
        albums = MusicDataProvider.getAlbums(getActivity());

        ListView listView = rootView.findViewById(R.id.albums_list_view);

        AlbumListAdapter albumListAdapter = new AlbumListAdapter(getActivity(), albums);

        listView.setAdapter(albumListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeAlbums();

                //Opens list of songs from clicked album
                SongsListFragment songsListFragment = new SongsListFragment();
                Bundle bundle = new Bundle();
                String album = ((TextView)view.findViewById(R.id.list_title)).getText().toString();
                String author = ((TextView)view.findViewById(R.id.list_title)).getText().toString();
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
