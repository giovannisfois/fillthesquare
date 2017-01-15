package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;


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
        for(int i=0;i<mNumTiles;i++){
            mTileArray.add(new Tile());
        }
    }

    private GridPoint2 pos2RowCol(int pos){
        int x = pos % mCols;
        int y = pos/mCols;

        return new GridPoint2(x,y);

    }

    public boolean isChecked(int row, int col){
        Gdx.app.debug("FillTheSquare", "isChecked Pos " + row + "- " + col);
        return mTileArray.get(col+row*mCols).isChecked();

    }

    public boolean isEmpty(int row, int col){
        Gdx.app.debug("FillTheSquare", "Is Empty " + row + "- " + col + ": "+ col+row*mCols);
        return mTileArray.get(col+row*mCols).isEmpty();
    }

    public void markChecked(int row, int col){
        Gdx.app.debug("FillTheSquare", "Mark Checked " + row + "- " + col + ": "+ col+row*mCols);
        mTileArray.get(col+row*mCols).markChecked();
    }

    public void markEmpty(int row, int col){
        mTileArray.get(row+mRows*col).markEmpty();
    }

    public ArrayList<GridPoint2> getCheckedSquares(){
        ArrayList<GridPoint2>EmptySquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if(tempTile.isChecked()){
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }


    public ArrayList<GridPoint2> getEmptySquares(){
        ArrayList<GridPoint2>EmptySquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if (tempTile.isEmpty()) {
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }
}
