package com.example.android.musicplayerelements;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import java.util.ArrayList;

/*
Apps main activity displaying list of albums found in device memory in list or grid format
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding player controller fragment
        getSupportFragmentManager().beginTransaction().add(R.id.controller_container, new MusicControllFragment(), "controller").commit();

        //Adding custom toolbar
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //Adding default fragment with grid view of albums
        getSupportFragmentManager().beginTransaction().add(R.id.albums_container, new AlbumGridFragment(), "grid").commit();

        ArrayList<Album> albums = new ArrayList<>();

    }

    /*
    Method removing grid or list album view if exists
     */
    private void removeAlbums(){
        //Removing grid view fragment if exists
        Fragment albumGridFragment = getSupportFragmentManager().findFragmentByTag("grid");
        if (albumGridFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(albumGridFragment).commit();
        }

        //Removing list view fragment if exists
        Fragment albumListFragment = getSupportFragmentManager().findFragmentByTag("list");
        if (albumListFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(albumListFragment).commit();
        }

        //Removing list of songs in album fragmnt if exists
        Fragment songsListFragment = getSupportFragmentManager().findFragmentByTag("songs");
        if (songsListFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(songsListFragment).commit();
        }

    }

    /*
    Method adding menu items icons to the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_bar, menu);
        return  true;
    }

    /*
    Handling menu item clicks, allowing user to choose grid or list view
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_button_grid:
                /*
                Displaying albums in grid view
                 */
                removeAlbums();
                getSupportFragmentManager().beginTransaction().add(R.id.albums_container, new AlbumGridFragment(), "grid").commit();
                return true;
            case R.id.menu_button_list:
                /*
                Displaying albums in list view
                 */
                removeAlbums();
                getSupportFragmentManager().beginTransaction().add(R.id.albums_container, new AlbumsListFragment(), "list").commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
