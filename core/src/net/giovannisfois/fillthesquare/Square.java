package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.utils.Array;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by giovans on 13/01/17.
 */

public class Square {
    Array<Tile> mTileArray;
    int mRows;
    int mCols;
    int mNumTiles;

    public Square(int size) {
        mRows = mCols = size;
        mNumTiles = size*size;
        mTileArray = new Array<Tile>(mNumTiles);
    }

    private Point pos2RowCol(int pos){
        int x = pos % mCols;
        int y = pos/mCols;

        return new Point(x,y);

    }

    public boolean isChecked(int row, int col){
        return mTileArray.get(row+mRows*col).isChecked();
    }

    public boolean isEmpty(int row, int col){
        return mTileArray.get(row+mRows*col).isEmpty();
    }

    public void markChecked(int row, int col){
        mTileArray.get(row+mRows*col).markChecked();
    }

    public void markEmpty(int row, int col){
        mTileArray.get(row+mRows*col).markEmpty();
    }

    public ArrayList<Point> getCheckedSquares(){
        ArrayList<Point>EmptySquares = new ArrayList<Point>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if(tempTile.isEmpty()){
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }


    public ArrayList<Point> getEmptySquares(){
        ArrayList<Point>EmptySquares = new ArrayList<Point>();
        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if (tempTile.isChecked()) {
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }
}
