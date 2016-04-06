package com.example.fahad.csci342_assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameModel model;
    private ArrayList<TileView> tileViews;
    private final int totalTiles = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Drawable> pictures = new ArrayList<Drawable>();
        pictures.add(ContextCompat.getDrawable(this, R.drawable.baldhill));
        pictures.add(ContextCompat.getDrawable(this, R.drawable.cathedral));
        pictures.add(ContextCompat.getDrawable(this,R.drawable.question));

        this.model = new GameModel(this.totalTiles,pictures);
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
                        gameModel.reset(totalTiles, pictures);
                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
