package com.example.fahad.csci342_assignment1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Name: Fahad Ur Rehman
 * Sols: fur866
 * ID: 4651960
 */
public class TileView extends LinearLayout{

    private Drawable image;
    private ImageView imageView;
    private TileViewListener tileListener;
    private int tileIndex;
    private Boolean isFlipped;
    private Boolean isTapped; //checks whether the tile is tapped or not.

    //constructor
    public TileView(Context context, AttributeSet attr)
    {
        super(context,attr);
        this.isFlipped = false;
        this.isTapped = false;

        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.image = ContextCompat.getDrawable(context,R.drawable.question);

        this.imageView.setImageDrawable(this.image);
        this.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tileListener.didSelectTile(TileView.this);
            }
        });

        this.addView(this.imageView);
    }

    //sets the image to the passed image
    public void setImage(Drawable picture)
    {
        this.image = picture;
    }

    //sets the ID
    public void setID(int id)
    {
        this.tileIndex = id;
    }

    //set the tile view listener
    public void setTileViewListener(TileViewListener listener)
    {
        this.tileListener = listener;
    }

    public Boolean isTileTapped()
    {
        return this.isTapped;
    }

    //returns the tile index
    public int getTileIndex()
    {
        return this.tileIndex;
    }

    //reveals the image associated with the tile
    public void revealImage()
    {
        if(!isFlipped && !isTapped) {
            this.imageView.setImageDrawable(this.image);
            this.isFlipped = true;
        }
    }

    //covers the image asscociated with the tile with the default question mark image
    public void coverImage()
    {
        if(isFlipped && !isTapped) {
            this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.question));
            this.isFlipped = false;
        }
    }

    //hides the image
    public void hideImage()
    {
        this.imageView.setImageResource(android.R.color.transparent);
        this.isTapped = true;
    }

    //interface TileviewListener
    public interface TileViewListener {
        void didSelectTile(TileView tileView);
    }
}
