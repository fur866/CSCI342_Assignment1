package com.example.fahad.csci342_assignment1;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameModel model;
    private ArrayList<TileView> tileViews;
    private final int totalTiles = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Drawable> pictures = new ArrayList<Drawable>();
        pictures.add(ContextCompat.getDrawable(this, R.drawable.baldhill));
        pictures.add(ContextCompat.getDrawable(this, R.drawable.cathedral));
        pictures.add(ContextCompat.getDrawable(this,R.drawable.question));

        this.model = new GameModel(this.totalTiles,pictures);
        this.model.setGameModelInterface(new GameModel.gameInterface() {
            @Override
            public void gameDidComplete(GameModel gameModel) {

            }

            @Override
            public void didMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex) {

            }

            @Override
            public void didFailToMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex) {

            }

            @Override
            public void scoreDidUpdate(GameModel gameModel, int newScore) {

            }
        });

        for(int i = 0; i < totalTiles; i++)
        {
            int id = getResources().getIdentifier("tile"+String.valueOf(i),"id","com.example.fahad.csci342_assignment1");
            TileView tileViewInstance = (TileView)findViewById(id);
            TileData tileDataInstance = this.model.getTileData(i);

            if (tileViewInstance != null) {
                tileViewInstance.setTileViewListener(new TileView.TileViewListener() {
                    @Override
                    public void didSelectTile(TileView tileView) {
                        tileView.revealImage();
                        model.pushTileIndex(tileView.getTileIndex());
                    }
                });
            }

            tileViewInstance.setID(i + 1);
            tileViewInstance.setImage(tileDataInstance.getImage());
            //tileViewInstance.setTileViewListener(this);
            tileViewInstance.coverImage();

            tileViews.add(tileViewInstance);
        }
        //Log.d("Here: ",model.description());
    }
}
