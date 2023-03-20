package io.mcumiskey.petrescueapp;

public class SimplePet {
    int mID;
    String mName;
    String mImageID;
    boolean mIsFavorite;

    SimplePet(){
        mID = 0;
        mName = "Pet";
        mImageID = "R.drawable.a";
        mIsFavorite = false;
    }

    SimplePet(String name){
        mName = name;
        mImageID = "a";
        mIsFavorite = false;
    }

    SimplePet(String name, String imageID){
        mName = name;
        mImageID = imageID;
        mIsFavorite = false;
    }

    SimplePet(int ID, String name, String imageID, boolean isFavorite){
        mID = ID;
        mName = name;
        mImageID = imageID;
        mIsFavorite = isFavorite;
    }

    public String getName() {return mName;}
    public void setName(String name) { mName = name;}
    public int getID() {return mID;}
    public void setID(int ID) { mID = ID;}
    public String getImageID() {return mImageID;}
    public void setImageID(String ID) { mImageID = ID;}
    public boolean getIsFavorite() {return mIsFavorite;}
    public void setIsFavorite(boolean isFavorite) { mIsFavorite = isFavorite;}

}
