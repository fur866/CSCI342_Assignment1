package com.example.fahad.csci342_assignment1;

/**
 * Name: Fahad Ur Rehman
 * Sols: fur866
 * ID: 4651960
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameModel model;
    private ArrayList<TileView> tileViews;
    private final int totalTiles = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the content view
        setContentView(R.layout.activity_main);

        //create array list of images to be used in the game
        final ArrayList<Drawable> pictures = new ArrayList<Drawable>();
        pictures.add(ContextCompat.getDrawable(this, R.drawable.baldhill));
        pictures.add(ContextCompat.getDrawable(this, R.drawable.cathedral));
        pictures.add(ContextCompat.getDrawable(this,R.drawable.lake));

        //initialize the tileViews
        this.tileViews = new ArrayList<TileView>();

        //create the GameMolde Object
        this.model = new GameModel(this.totalTiles,pictures);
        //set the interface with their implemenantations
        this.model.setGameModelInterface(new GameModel.gameInterface() {
            @Override
            public void gameDidComplete(final GameModel gameModel) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game Completed");
                alert.setMessage("Your score: " + String.valueOf(gameModel.getScore()));
                alert.setIcon(android.R.drawable.ic_dialog_info);
                alert.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish(); //finish the activity
                        startActivity(getIntent()); //run the game again
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish(); //close the app
                    }
                });
                alert.show();
            }

            @Override
            public void didMatchTile(final GameModel gameModel, final int tileIndex, final int previousTileIndex) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //after 1 second
                        tileViews.get(tileIndex).hideImage();
                        tileViews.get(previousTileIndex).hideImage();
                    }
                }, 1000);
            }

            @Override
            public void didFailToMatchTile(GameModel gameModel, final int tileIndex, final int previousTileIndex) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //after 1 second
                        tileViews.get(tileIndex).coverImage();
                        tileViews.get(previousTileIndex).coverImage();
                    }
                }, 1000);
            }

            @Override
            public void scoreDidUpdate(GameModel gameModel, int newScore) {
                int id = getResources().getIdentifier("score","id","com.example.fahad.csci342_assignment1");
                TextView label = (TextView)findViewById(id);
                label.setText("Score: "+ String.valueOf(newScore));
            }
        });

        //for the total number of tiles
        for(int i = 1; i <= totalTiles; i++)
        {
            //get the corresponding id
            int id = getResources().getIdentifier("tile"+String.valueOf(i),"id","com.example.fahad.csci342_assignment1");
            //find the tile view by id
            TileView tileViewInstance = (TileView)findViewById(id);
            //get the corresponding tile data
            TileData tileDataInstance = this.model.getTileData(i-1);

            if (tileViewInstance != null) {
                //implement the tile view listener
                tileViewInstance.setTileViewListener(new TileView.TileViewListener() {
                    @Override
                    public void didSelectTile(TileView tileView) {
                        if (!tileView.isTileTapped()) {
                            tileView.revealImage();
                            model.pushTileIndex(tileView.getTileIndex());
                        }
                    }
                });
            }

            //set the id
            tileViewInstance.setID(i-1);
            //set image
            tileViewInstance.setImage(tileDataInstance.getImage());
            //call the cover image method to set the image to default
            tileViewInstance.coverImage();

            //add the tile to the list of tile views
            this.tileViews.add(tileViewInstance);
        }
    }
}
