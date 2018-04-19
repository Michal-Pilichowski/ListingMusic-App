package com.example.android.musicplayerelements;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Michal Pichowski on 2018-03-16.
 */

/*
Method searching for songs and albums in devices internal memory.
 */
public class MusicDataProvider {

    /*
    Static method returning list array containing information about music albums from
    sd card. Information: Album title and Album Author
     */
    public static ArrayList<Album> getAlbums(Context context){
        ArrayList<Album> albums = new ArrayList<>();

        final String album_name = MediaStore.Audio.Albums.ALBUM;
        final String artist = MediaStore.Audio.Albums.ARTIST;
        final String cover = MediaStore.Audio.Albums.ALBUM_ART;

        String[] projection = new String[] { MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST, MediaStore.Audio.Albums.ALBUM_ART, MediaStore.Audio.Albums.NUMBER_OF_SONGS };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = MediaStore.Audio.Media.ALBUM + " ASC";
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder);

        do {
            try {
                albums.add(new Album(cursor.getString(cursor.getColumnIndex(album_name)), cursor.getString(cursor.getColumnIndex(artist)), R.drawable.default_cover, cursor.getString(cursor.getColumnIndex(cover))));
            } catch (Exception e){
                e.printStackTrace();
            }
        } while (cursor.moveToNext());

        cursor.close();
        return albums;
    }

    /*
    Returns array list containing all songs from album specified by album name
     */
    public static ArrayList<Song> getSongsFromAlbum(Context context, String author, String album){
        ArrayList<Song> songs = new ArrayList<>();

        final String songAlbum = MediaStore.Audio.Media.ALBUM;
        final String songLenght = MediaStore.Audio.Media.DURATION;
        final String songTitle = MediaStore.Audio.Media.TITLE;
        int currentSong = 1;

        String[] projection = new String[] {MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = MediaStore.Audio.Media.TRACK + " ASC";
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder);

        do{
            try {
                if (album.equals(cursor.getString(cursor.getColumnIndex(songAlbum)))){
                    songs.add(new Song(
                            currentSong,
                            cursor.getString(cursor.getColumnIndex(songTitle)),
                            author,
                            cursor.getLong(cursor.getColumnIndex(songLenght)),
                            album
                    ));
                    currentSong++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        } while (cursor.moveToNext());

        cursor.close();;
        return songs;
    }

    /*
    Provides color for album element in list or grid
     */
    public static int [] calculateSummaryRGB(Bitmap img){
        int [] RGB = new int [3];
        RGB[0] = 0;
        RGB[1] = 0;
        RGB[2] = 0;
        int [] redArray = new int [256];
        int [] greenArray = new int [256];
        int [] blueArray = new int [256];

        for (int i=0;i<256;i++){
            redArray[i] = 0;
            greenArray[i] = 0;
            blueArray[0] = 0;
        }

        //Zliczenie licznosci poszczegolnych skladowych koloru (r g b) okladki albumu
        for (int i=0;i<img.getHeight();i++)
            for (int j=0;j<img.getWidth();j++){

                int currentColor = img.getPixel(j, i);

                for (int k=0;k<256;k++){
                    if (Color.red(currentColor)==k){
                        redArray[k]++;
                    }

                    if (Color.green(currentColor)==k){
                        greenArray[k]++;
                    }

                    if (Color.blue(currentColor)==k){
                        blueArray[k]++;
                    }

                }

            }

        int R = 0;
        int G = 0;
        int B = 0;

        for (int i=0;i<256;i++){
            if (R<redArray[i]){
                R = redArray[i];
                RGB[0] = i;
            }

            if (G<greenArray[i]){
                G = greenArray[i];
                RGB[1] = i;
            }

            if (B<blueArray[i]){
                B = blueArray[i];
                RGB[2] = i;
            }
        }

        return RGB;
    }

}
