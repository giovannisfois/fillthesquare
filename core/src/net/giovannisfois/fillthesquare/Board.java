package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

public class Board implements IPlayable,IRegionUI {
    playSquare mSquare;
    int mBoardSize;
    int mEmptyTilesCount;
    int mCheckedTilesCount;
    int mActiveTilesCount;

    private Sprite mCheckedTileSprite;
    private Sprite mEmptyTileSprite;
    private Sprite mActiveTileSprite;
    private Sprite mCurrentTileSprite;

    Board(int boardSize){
        mBoardSize = boardSize;
        mSquare = new playSquare(mBoardSize);
        updateCounters();
    }

    /* Board Play Options */
    @Override
    public void reset() {
        mSquare = new playSquare(mBoardSize);
        updateCounters();
    }

    @Override
    public boolean isGameEnded() {
        if(mActiveTilesCount == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPlayerWon() {
        if(mEmptyTilesCount == 0){
            return true;
        }
        return false;
    }

    /* Board Graphic Options */
    @Override
    public void handleTouch(int row, int col) {
        mSquare.handleTouch(row, col);
        updateCounters();
    }

    @Override
    public void draw(SpriteBatch batch, int originX, int originY) {

        ArrayList<GridPoint2> Tiles = mSquare.getTiles();
        ArrayList<GridPoint2> checkedTiles = mSquare.getCheckedTiles();
        ArrayList<GridPoint2> activeTiles =  mSquare.getActiveTiles();

        ArrayList<GridPoint2> currentTiles = mSquare.getCurrentTiles();

        drawTiles(Tiles,        batch, originX, originY, mEmptyTileSprite);
        drawTiles(checkedTiles, batch, originX, originY, mCheckedTileSprite);
        drawTiles(activeTiles,  batch, originX, originY, mActiveTileSprite);
        drawTiles(currentTiles, batch, originX, originY, mCurrentTileSprite);
    }

    private void updateCounters(){
        mCheckedTilesCount = mSquare.getCheckedTiles().size();
        mEmptyTilesCount   = mSquare.getEmptyTiles().size();
        mActiveTilesCount   = mSquare.getActiveTiles().size();
    }

    public void setSprites(TextureRegion emptyTileRegion,
                            TextureRegion checkedTileRegion,
                            TextureRegion activeTileRegion,
                            TextureRegion currentTileRegion){
        //Tiles Initialization
        mCheckedTileSprite = new Sprite(checkedTileRegion);
        mCheckedTileSprite.setSize(2,2);

        mEmptyTileSprite = new Sprite(emptyTileRegion);
        mEmptyTileSprite.setSize(2,2);

        mActiveTileSprite = new Sprite(activeTileRegion);
        mActiveTileSprite.setSize(2,2);

        mCurrentTileSprite = new Sprite(currentTileRegion);
        mCurrentTileSprite.setSize(2,2);
    }



    private void  drawTiles(ArrayList<GridPoint2> points, SpriteBatch batch , int originX, int originY,  Sprite sprite){

        int TilePosX;
        int TilePosY;
        int row;
        int col;

        for (GridPoint2 point : points) {
            row = point.x;
            col = point.y;
            TilePosX = originX + 2 * col;
            TilePosY = originY - 2*row - 2 ;

            sprite.setPosition(TilePosX, TilePosY);
            sprite.draw(batch);
        }

    }

}
