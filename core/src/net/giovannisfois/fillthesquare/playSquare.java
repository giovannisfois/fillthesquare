package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

/* Extends the basic Square Class adding the concept of the current point,
    which is the last clicked point
 */

public class playSquare extends Square {
    // Keeps the reference to the last point clicked
    private GridPoint2 mCurrentPoint;

    /* constructor, instantiates the parent square object and marks the 0,0 point as checked */
    public playSquare(int squareSize) {
        super(squareSize);
        mCurrentPoint = new GridPoint2(-1,-1);
        //markChecked(mCurrentPoint.x, mCurrentPoint.y);
    }

    /* The user has clicked on the row, col position of the current play square,
        We apply the game rules in order to change the Game configuration
     */
    public void handleTouch(int row, int col){
        if(isValid(row,col)){

            if(mCurrentPoint.x<0 || mCurrentPoint.y<0){
                markChecked(row, col);
                mCurrentPoint.set(row,col);
                return;
            }


            if(isEmpty(row, col)) {

                if(mCurrentPoint.x == row && mCurrentPoint.y == col-3){markChecked(row, col);mCurrentPoint.set(row,col); return;}
                if(mCurrentPoint.x == row && mCurrentPoint.y == col+3){markChecked(row, col);mCurrentPoint.set(row,col); return; }
                if(mCurrentPoint.x == row-3 && mCurrentPoint.y == col){markChecked(row, col);mCurrentPoint.set(row,col); return;}
                if(mCurrentPoint.x == row+3 && mCurrentPoint.y == col){markChecked(row, col);mCurrentPoint.set(row,col); return;}

                if(mCurrentPoint.x == row-2 && mCurrentPoint.y == col-2){markChecked(row, col);mCurrentPoint.set(row,col); return;}
                if(mCurrentPoint.x == row+2 && mCurrentPoint.y == col-2){markChecked(row, col);mCurrentPoint.set(row,col); return;}
                if(mCurrentPoint.x == row-2 && mCurrentPoint.y == col+2){markChecked(row, col);mCurrentPoint.set(row,col); return;}
                if(mCurrentPoint.x == row+2 && mCurrentPoint.y == col+2){markChecked(row, col);mCurrentPoint.set(row,col); return;}
            }
        }
    }

    /*
        Get the list of the current tiles, which is only one.
        We use an array for uniformity with the other calls
     */
    public ArrayList<GridPoint2> getCurrentTiles(){
        ArrayList<GridPoint2>CurrentTiles = new ArrayList<GridPoint2>();
        CurrentTiles.add(mCurrentPoint);
        return CurrentTiles;
    }

    /* Get the list of the active tiles, i.e.: valid clickable tiles for the current point */
    public ArrayList<GridPoint2> getActiveTiles(){
        // This is our definition of x and y
        int row = mCurrentPoint.x;
        int col = mCurrentPoint.y;

        if(row<0 || col<0){
            return getTiles();
        }

        ArrayList<GridPoint2>ActiveSquares = new ArrayList<GridPoint2>();

        if(isValid(row+3,col) &&  isEmpty(row+3, col)){ ActiveSquares.add(new GridPoint2(row+3,col)); }
        if(isValid(row-3,col) &&  isEmpty(row-3, col)){ ActiveSquares.add(new GridPoint2(row-3,col)); }
        if(isValid(row,col+3) &&  isEmpty(row, col+3)){ ActiveSquares.add(new GridPoint2(row,col+3)); }
        if(isValid(row,col-3) &&  isEmpty(row, col-3)){ ActiveSquares.add(new GridPoint2(row,col-3)); }

        if(isValid(row-2,col-2) &&  isEmpty(row-2, col-2)){ ActiveSquares.add(new GridPoint2(row-2,col-2)); }
        if(isValid(row+2,col-2) &&  isEmpty(row+2, col-2)){ ActiveSquares.add(new GridPoint2(row+2,col-2)); }
        if(isValid(row-2,col+2) &&  isEmpty(row-2, col+2)){ ActiveSquares.add(new GridPoint2(row-2,col+2)); }
        if(isValid(row+2,col+2) &&  isEmpty(row+2, col+2)){ ActiveSquares.add(new GridPoint2(row+2,col+2)); }
       return ActiveSquares;
    }

}
