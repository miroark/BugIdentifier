package com.example.ryan.bugidentifier;

import android.graphics.Bitmap;

/**
 * Created by Ryan on 11/25/2017.
 */

public class Item {
    private int colId;
    private String insectType;
    private Bitmap image;
    private String imageId;

    public Item(int colId, String insectType, Bitmap image, String imageId) {
        this.colId = colId;
        this.insectType = insectType;
        this.image = image;
        this.imageId = imageId;
    }

    // Get/Set functions --------------------------
    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public String getInsectType() {
        return insectType;
    }

    public void setInsectType(String insectType) {
        this.insectType = insectType;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    // End of Get/Set methods.-----------------------
}
