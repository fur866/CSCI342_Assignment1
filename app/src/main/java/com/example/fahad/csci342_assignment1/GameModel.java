package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Name: Fahad Ur Rehman
 * Sols: fur866
 * ID: 4651960
 */
public class GameModel {
    private int lastTappedTile; //contains id for the last TappedTile
    private ArrayList<TileData> tiles; //contains the tiles
    private Boolean isFirst; //checks if the tapped tile is the first of the two tiles
    private int totalMatchedTiles; //keeps count of the total number of matched tiles
    private int totalTiles; //keeps count of total tiles
    public gameInterface interfaceReference; //reference to the interface
    private int gameScore; //contains the game score

    //constructor
    GameModel(int numberOfTiles, ArrayList<Drawable> images){

        //check if the number of tiles are even or not. If not we simply make it even
        if(numberOfTiles % 2 != 0){
            numberOfTiles += 1;
        }
        //initalize the arraylist only in constructer and then just clear it off in reset for efficiency purposes.
        this.tiles = new ArrayList<TileData>();
        this.totalTiles = numberOfTiles;
        reset(numberOfTiles, images);
    }

    public void setGameModelInterface(gameInterface interfaceReference)
    {
        this.interfaceReference = interfaceReference;
    }

    public void reset(int numberOfTiles, ArrayList<Drawable> images)
    {
        //reset all other members to default
        this.lastTappedTile = 0; //identifier varies from 1 to total images. 0 means not tapped yet
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

    public String description()
    {
        String tileDescription = "";
        for(TileData tile : this.tiles)
        {
            tileDescription += " " + tile.description();
        }
        return tileDescription;
    }

    public void pushTileIndex(int index)
    {
        if(this.isFirst) {
            this.lastTappedTile = index;
            this.isFirst = false;
        }
        else
        {
            if(this.tiles.get(this.lastTappedTile).getIdentifier() == this.tiles.get(index).getIdentifier())
            {
                this.gameScore += 200;
                this.interfaceReference.didMatchTile(this,this.lastTappedTile,index);
                this.totalMatchedTiles += 2;
            }
            else
            {
                this.gameScore -= 200;
                this.interfaceReference.didFailToMatchTile(this,this.lastTappedTile,index);
            }
            this.interfaceReference.scoreDidUpdate(this,this.gameScore);

            this.isFirst = true;
            this.lastTappedTile = 0;
        }

        if(this.totalMatchedTiles == this.totalTiles)
        {
            this.interfaceReference.gameDidComplete(this);
        }
    }

    public TileData getTileData(int index)
    {
        if(index <= this.tiles.size()) {
            return this.tiles.get(index);
        }
        return null;
    }

    public int getScore()
    {
        return this.gameScore;
    }
    public interface gameInterface
    {
        void gameDidComplete(GameModel gameModel);
        void didMatchTile(GameModel gameModel,int tileIndex, int previousTileIndex);
        void didFailToMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex);
        void scoreDidUpdate(GameModel gameModel, int newScore);
    }
}
