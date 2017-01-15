package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;

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
                Gdx.app.debug("FillTheSquare", "Current Point:" + mCurrentPoint.x + " - " + mCurrentPoint.y);

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

}
