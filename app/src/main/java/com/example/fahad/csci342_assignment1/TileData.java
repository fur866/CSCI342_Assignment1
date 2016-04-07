package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;

/**
 * Name: Fahad Ur Rehman
 * Sols: fur866
 * ID: 4651960
 */
public class TileData {
    private Drawable Image;
    private int identifier;

    //constructor
    TileData(Drawable image, int id)
    {
        //check if image is an instance of drawable
        if(image != null) {
            this.Image = image;
        }

        //check if id is an int or not
        if(id == (int)id) {
            this.identifier = id;
        }
    }

    //get function for Image
    public Drawable getImage()
    {
        return this.Image;
    }

    //get function for drawable
    public int getIdentifier()
    {
        return this.identifier;
    }

    //for debugging purposes. Outputs the id data
    public String description()
    {
        return String.valueOf(this.identifier);
    }
}
