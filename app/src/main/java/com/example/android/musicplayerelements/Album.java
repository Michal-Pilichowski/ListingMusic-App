package com.example.android.musicplayerelements;

import android.graphics.Bitmap;

/**
 * Created by Michal Pichowski on 2018-03-14.
 */

/*
class storing information about particular music album
 */
public class Album {
    private String albumTitle;
    private String albumAuthor;
    private int imageID;
    private String coverPath;

    /*
    Constructors
     */
    public Album(String alb, String aut, int img){
        albumTitle = alb;
        albumAuthor = aut;
        imageID = img;
    }

    public Album(String alb, String aut, int img, String cov){
        albumTitle = alb;
        albumAuthor = aut;
        imageID = img;
        coverPath = cov;
    }

    /*
    Getters
     */
    public String getCover(){return coverPath;}

    public String getAlbumTitle(){
        return albumTitle;
    }

    public String getAlbumAuthor(){
        return albumAuthor;
    }

    public int getImageID(){
        return imageID;
    }

}
