package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

/**
 * Created by giovans on 15/01/17.
 */

public class Game {
    private GridPoint2 mCurrentPoint;
    private Square mGameSquare;

    private int mSquareSize;

    public Game(Square gameSquare, int squareSize) {
        mGameSquare = gameSquare;
        mCurrentPoint = new GridPoint2(0,0);
        mSquareSize = squareSize;
        mGameSquare.markChecked(mCurrentPoint.x, mCurrentPoint.y);
    }


    public void handleTouch(int row, int col){
        if(row >= 0 && row < mSquareSize && col >= 0 && col < mSquareSize){
            if(mGameSquare.isEmpty(row, col)) {
                Gdx.app.debug("FillTheSquare", "Touch Current Point : " + mCurrentPoint.x + "- " + mCurrentPoint.y);
                if(mCurrentPoint.x == row && mCurrentPoint.y == col-3){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row && mCurrentPoint.y == col+3){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row-3 && mCurrentPoint.y == col){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row+3 && mCurrentPoint.y == col){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}

                if(mCurrentPoint.x == row-2 && mCurrentPoint.y == col-2){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row+2 && mCurrentPoint.y == col-2){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row-2 && mCurrentPoint.y == col+2){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}
                if(mCurrentPoint.x == row+2 && mCurrentPoint.y == col+2){mGameSquare.markChecked(row, col);mCurrentPoint.set(row,col);}


            }
        }
    }

    public ArrayList<GridPoint2> getCurrentTiles(){
        ArrayList<GridPoint2>CurrentTile = new ArrayList<GridPoint2>();
        CurrentTile.add(mCurrentPoint);
        return CurrentTile;
    }

    public ArrayList<GridPoint2> getActiveTiles(){
        int row = mCurrentPoint.x;
        int col = mCurrentPoint.y;

        ArrayList<GridPoint2>ActiveSquares = new ArrayList<GridPoint2>();
        if(mGameSquare.isValid(row+3,col) &&  mGameSquare.isEmpty(row+3, col)){ ActiveSquares.add(new GridPoint2(row+3,col));}
        if(mGameSquare.isValid(row-3,col) &&  mGameSquare.isEmpty(row-3, col)){ ActiveSquares.add(new GridPoint2(row-3,col));}
        if(mGameSquare.isValid(row,col+3) &&  mGameSquare.isEmpty(row, col+3)){ ActiveSquares.add(new GridPoint2(row,col+3));}
        if(mGameSquare.isValid(row,col-3) &&  mGameSquare.isEmpty(row, col-3)){ ActiveSquares.add(new GridPoint2(row,col-3));}

        if(mGameSquare.isValid(row-2,col-2) &&  mGameSquare.isEmpty(row-2, col-2)){ ActiveSquares.add(new GridPoint2(row-2,col-2));}
        if(mGameSquare.isValid(row+2,col-2) &&  mGameSquare.isEmpty(row+2, col-2)){ ActiveSquares.add(new GridPoint2(row+2,col-2));}
        if(mGameSquare.isValid(row-2,col+2) &&  mGameSquare.isEmpty(row-2, col+2)){ ActiveSquares.add(new GridPoint2(row-2,col+2));}
        if(mGameSquare.isValid(row+2,col+2) &&  mGameSquare.isEmpty(row+2, col+2)){ ActiveSquares.add(new GridPoint2(row+2,col+2));}

       return ActiveSquares;
    }

}
