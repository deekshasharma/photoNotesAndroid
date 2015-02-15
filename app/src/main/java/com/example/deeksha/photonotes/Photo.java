package com.example.deeksha.photonotes;

import android.net.Uri;

public class Photo {

    private int PHOTO_ID;
    private String PHOTO_CAPTION;

    private Uri PHOTO_LOCATION;


    public Photo(int id, String caption, Uri location) {
        this.PHOTO_ID = id;
        this.PHOTO_CAPTION = caption;
        this.PHOTO_LOCATION = location;
    }


    public Photo() {

    }

    public int getPHOTO_ID() {
        return PHOTO_ID;
    }

    public String getPHOTO_CAPTION() {
        return PHOTO_CAPTION;
    }

    public Uri getPHOTO_LOCATION() {
        return PHOTO_LOCATION;
    }

    public void setPHOTO_ID(int PHOTO_ID) {
        this.PHOTO_ID = PHOTO_ID;
    }

    public void setPHOTO_CAPTION(String PHOTO_CAPTION) {
        this.PHOTO_CAPTION = PHOTO_CAPTION;
    }


    public void setPHOTO_LOCATION(Uri PHOTO_LOCATION) {
        this.PHOTO_LOCATION = PHOTO_LOCATION;
    }

}
