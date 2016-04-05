package com.example.fahad.csci342_assignment1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Fahad on 3/04/2016.
 */
public class TileView extends LinearLayout{

    private Drawable image;
    private ImageView imageView;
    private TileViewListener tileListener;
    private int tileIndex;
    private Boolean isFlipped;


    public TileView(Context context, AttributeSet attr)
    {
        super(context,attr);
        this.isFlipped = false;

        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.image = ContextCompat.getDrawable(context,R.drawable.question);

        this.imageView.setImageDrawable(this.image);
        this.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!isFlipped) {
                    revealImage();
                }
                else {
                    coverImage();
                }
                //tileListener.didSelectTile(TileView.this);
            }
        });

        this.addView(this.imageView);
    }


    public void revealImage()
    {
        if(!isFlipped) {
            this.image = ContextCompat.getDrawable(getContext(),R.drawable.lake);
            this.imageView.setImageDrawable(this.image);
            this.isFlipped = true;
        }
    }

    public void coverImage()
    {
        if(isFlipped) {
            this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.question));
            this.isFlipped = false;
        }
    }

    public void hideImage()
    {
        this.imageView.setImageResource(android.R.color.transparent);
    }

    public interface TileViewListener {
        void didSelectTile(TileView tileView);
    }
}
