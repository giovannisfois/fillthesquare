package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.utils.Array;

/**
 * Created by giovans on 13/01/17.
 */

public class Square {
    Array<Tile> mTileArray;
    int mSize;
    int mNumTiles;

    public Square(int size) {
        mSize = size;
        mNumTiles = size*size;
        mTileArray = new Array<Tile>(mNumTiles);
    }

    public boolean isChecked(int row, int col){
        return mTileArray.get(row+mSize*col).isChecked();
    }

    public boolean isEmpty(int row, int col){
        return mTileArray.get(row+mSize*col).isEmpty();
    }

    public boolean markChecked(int row, int col){
        mTileArray.get(row+mSize*col).markChecked();
    }

    public boolean markEmpty(int row, int col){
        mTileArray.get(row+mSize*col).markEmpty();
    }
    
}
