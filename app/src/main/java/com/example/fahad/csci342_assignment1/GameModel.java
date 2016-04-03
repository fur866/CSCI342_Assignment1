package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

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

        //get some data
        int totalImages = images.size();
        int imageCounter = 0;
        this.tiles = new ArrayList<TileData>();

        for(int i = 0; i < numberOfTiles; i++){
            //add a TileData : the identifier varies from 1 to total images
            this.tiles.add(new TileData(images.get(imageCounter),imageCounter+1));

            //if we have run at the end of images, set the imageCounter to 1 again
            imageCounter = imageCounter+1 >= totalImages ? 1 : imageCounter+1;
        }

        //set all other members to default
        this.firstTappedTile = 0;
        this.secondTappedTile = 0;
        this.isFirst = true;
        this.totalMatchedTiles = 0;
        this.gameScore = 0;
    }
    public interface gameInterface
    {

    }
}
