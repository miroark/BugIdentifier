package com.example.ryan.bugidentifier;

/**
 * Created by Randula.
 * Website: https://acadgild.com/blog/save-retrieve-image-sqlite-android/
 * Github: https://github.com/hiteshbpatel/Android_Blog_Projects/tree/master/Android-SQLite-master
 *
 * Modified by: Michael R. Roark
 */

public class ImageHelper {

    private String imageId;
    private String insect;
    private byte[] imageByteArray;

    public String getInsect(){
        return insect;
    }
    public String getImageId() {
        return imageId;
    }
    public void setInsect(String insect){
        this.insect = insect;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public byte[] getImageByteArray() {
        return imageByteArray;
    }
    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

}
