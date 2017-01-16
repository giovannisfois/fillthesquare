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
        int col = pos % mCols;
        int row = pos/mCols;

        return new GridPoint2(row,col);

    }

    public boolean isChecked(int row, int col){
        Gdx.app.debug("FillTheSquare", "isChecked Pos " + row + "- " + col);
        return mTileArray.get(col+row*mCols).isChecked();

    }

    public boolean isValid(int row, int col){
        if(row<0 || col<0 || row>=mRows || col>= mCols){ return false;}
        return true;
    }

    public boolean isEmpty(int row, int col){
        return mTileArray.get(col+row*mCols).isEmpty();
    }

    public void markChecked(int row, int col){
        mTileArray.get(col+row*mCols).markChecked();
    }

    public void markEmpty(int row, int col){
        mTileArray.get(col+row*mCols).markEmpty();
    }

    public ArrayList<GridPoint2> getCheckedTiles(){
        ArrayList<GridPoint2>EmptySquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if(tempTile.isChecked()){
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }


    public ArrayList<GridPoint2> getEmptyTiles(){
        ArrayList<GridPoint2>EmptySquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if (tempTile.isEmpty()) {
                EmptySquares.add(pos2RowCol(i));
            }
        }
        return EmptySquares;
    }

    public ArrayList<GridPoint2> getTiles(){
        ArrayList<GridPoint2>Squares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            Squares.add(pos2RowCol(i));
        }
        return Squares;
    }
}
