package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Fahad on 3/04/2016.
 */
public class GameModel {
    private int firstTappedTile;
    private int secondTappedTile;
    private ArrayList<TileData> tiles;
    private Boolean isFirst;
    private int totalMatchedTiles;
    public gameInterface interfaceReference;
    private int gameScore;

    //constructor
    GameModel(int numberOfTiles, ArrayList<Drawable> images){

        //check if the number of tiles are even or not. If not we simply make it even
        if(numberOfTiles % 2 != 0){
            numberOfTiles += 1;
        }
        //initalize the arraylist only in constructer and then just clear it off in reset for efficiency purposes.
        this.tiles = new ArrayList<TileData>();
        reset(numberOfTiles,images);
    }

    public void reset(int numberOfTiles, ArrayList<Drawable> images)
    {
        //reset all other members to default
        this.firstTappedTile = 0; //identifier varies from 1 to total images. 0 means not tapped yet
        this.secondTappedTile = 0; //identifier varies from 1 to total images. 0 means not tapped yet
        this.isFirst = true; //set to true means the first tile is tapped, it will be true
        this.totalMatchedTiles = 0; //0 initially
        this.gameScore = 0; //0 initially
        this.tiles.clear(); // clear the list


        int totalImages = images.size(); //get total number of images
        int imageCounter = 0; //set imageCounter

        for(int i = 0; i < numberOfTiles;){
            //add two same TileDatas : the identifier varies from 1 to total number of images
            this.tiles.add(new TileData(images.get(imageCounter),imageCounter+1));
            this.tiles.add(new TileData(images.get(imageCounter),imageCounter+1));

            //if we have run at the end of images, set the imageCounter to 1 again, else increment it
            imageCounter = imageCounter+1 >= totalImages ? 1 : imageCounter+1;

            i += 2; //increase i by 2 after each loop
        }

        shuffle();
    }

    // shuffle should only be down once, when the game is being reset. Hence, this method is private
    private void shuffle()
    {
        Collections.shuffle(this.tiles);
    }

    public interface gameInterface
    {

    }
}
