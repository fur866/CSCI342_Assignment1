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
        pictures.add(ContextCompat.getDrawable(this,R.drawable.baldhill));
        pictures.add(ContextCompat.getDrawable(this,R.drawable.cathedral));
        pictures.add(ContextCompat.getDrawable(this,R.drawable.question));

        this.model = new GameModel(this.totalTiles,pictures);
        Log.d("Here: ",model.description());
    }
}
