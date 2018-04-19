package com.example.android.musicplayerelements;

/**
 * Created by Michal Pichowski on 2018-03-16.
 */

/*
Custom class holding information about single song
 */
public class Song {
    private int numberOnAlbum;
    private String songTitle;
    private String songAuthor;
    private long songLenght;
    private String songAlbum;

    public Song(int number, String title, String author, long lenght, String album){
        numberOnAlbum = number;
        songTitle = title;
        songAuthor = author;
        songLenght = lenght;
        songAlbum = album;
    }

    /*
    Getters
     */
    public int getNumberOnAlbum(){return numberOnAlbum;}

    public String getSongTitle(){return songTitle;}

    public String getSongAuthor(){return songAuthor;}

    public long getSongLenght(){return songLenght;}

    public String getSongAlbum(){return songAlbum;}
}
