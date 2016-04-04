package com.example.fahad.csci342_assignment1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Fahad on 3/04/2016.
 */
public class TileView extends LinearLayout{

    private Drawable image;
    private ImageView imageView;
    private GameModel.gameInterface tileInterface;
    private int tileIndex;


    public TileView(Context context, AttributeSet attr)
    {
        super(context,attr);
    }
}
