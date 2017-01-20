package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


public class Square {
    Array<Tile> mTileArray;
    protected int mRows;
    protected int mCols;
    protected int mNumTiles;

    public Square(int size) {
        mRows = mCols = size;
        mNumTiles = size*size;
        mTileArray = new Array<Tile>(mNumTiles);
        for(int i=0;i<mNumTiles;i++){
            mTileArray.add(new Tile());
        }
    }

    /* Convert the array position  into the corresponding row and col */
    private GridPoint2 pos2RowCol(int pos){
        int col = pos % mCols;
        int row = pos/mCols;
        return new GridPoint2(row,col);
    }

    /* Convert the row and col coordinates into the corresponding array position */
    private int RowCol2Pos(int row, int col){
        return col+row*mCols;
    }

    /* Tells if a (row,col) position is a valid one */
    public boolean isValid(int row, int col){
        if(row<0 || col<0 || row>=mRows || col>= mCols){ return false;}
        return true;
    }

    /* Single Tile operations */

    /* Tells if a position is Checked */
    public boolean isChecked(int row, int col){
        int pos = RowCol2Pos(row,col);
        return mTileArray.get(pos).isChecked();
    }

    /* Tells if a position is Empty */
    public boolean isEmpty(int row, int col){
        int pos = RowCol2Pos(row,col);
        return mTileArray.get(pos).isEmpty();
    }

    /* Sets a Tile as checked */
    public void markChecked(int row, int col){
        int pos = RowCol2Pos(row,col);
        mTileArray.get(pos).markChecked();
    }

    /* Sets a Tile as empty */
    public void markEmpty(int row, int col){
        int pos = RowCol2Pos(row,col);
        mTileArray.get(pos).markEmpty();
    }

    /* Multiple Tiles Operations */

    /* Extract the list of all the Checked Positions*/
    public ArrayList<GridPoint2> getTiles(){
        ArrayList<GridPoint2> allTiles = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            allTiles.add(pos2RowCol(i));
        }
        return allTiles;
    }

    /* Extract the list of all the Checked Positions*/
    public ArrayList<GridPoint2> getCheckedTiles(){
        ArrayList<GridPoint2> checkedSquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if(tempTile.isChecked()){
                checkedSquares.add(pos2RowCol(i));
            }
        }
        return checkedSquares;
    }

    /* Extract the list of all the Empyy Positions*/
    public ArrayList<GridPoint2> getEmptyTiles(){
        ArrayList<GridPoint2> emptySquares = new ArrayList<GridPoint2>();

        for(int i = 0; i < mTileArray.size; i ++) {
            Tile tempTile = mTileArray.get(i);
            if(tempTile.isEmpty()){
                emptySquares.add(pos2RowCol(i));
            }
        }
        return emptySquares;
    }
}
