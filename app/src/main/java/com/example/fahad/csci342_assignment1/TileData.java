package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;

/**
 * Created by Fahad on 3/04/2016.
 */
public class TileData {
    private Drawable Image;
    private int identifier;


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

    public String description()
    {
        return String.valueOf(this.identifier);
    }
}
