package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

public class Board implements IPlayable,IRegionUI {
    playSquare mSquare;
    int mBoardSize;
    float mTileSize;
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
    public boolean isTouched(float base_x, float base_y, float x, float y){
        float last_x = base_x + mBoardSize*mTileSize;
        float last_y = base_y - mBoardSize*mTileSize;

        if(x > base_x && x < last_x && y < base_y && y>last_y){
            return true;
        }
        return false;
    }

    @Override
    public void handleTouch(float base_x, float base_y, float x, float y){
        int col = (int) ((x - base_x) / mTileSize);
        int row = -(int) ((y - base_y) / mTileSize);
        Gdx.app.debug("FillTheSquare", "TouchCol: row:" + row + " col:" + col);
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

    public void setSprites(float tileSize,
                            TextureRegion emptyTileRegion,
                            TextureRegion checkedTileRegion,
                            TextureRegion activeTileRegion,
                            TextureRegion currentTileRegion){
        mTileSize = tileSize;
        //Tiles Initialization
        mCheckedTileSprite = new Sprite(checkedTileRegion);
        mCheckedTileSprite.setSize(tileSize,tileSize);

        mEmptyTileSprite = new Sprite(emptyTileRegion);
        mEmptyTileSprite.setSize(tileSize,tileSize);

        mActiveTileSprite = new Sprite(activeTileRegion);
        mActiveTileSprite.setSize(tileSize,tileSize);

        mCurrentTileSprite = new Sprite(currentTileRegion);
        mCurrentTileSprite.setSize(tileSize,tileSize);
    }



    private void  drawTiles(ArrayList<GridPoint2> points, SpriteBatch batch , int originX, int originY,  Sprite sprite){

        int TilePosX;
        int TilePosY;
        int row;
        int col;

        for (GridPoint2 point : points) {
            row = point.x;
            col = point.y;
            TilePosX = originX + (int) mTileSize * col;
            TilePosY = originY - (int)( mTileSize*(row+1)) ;

            sprite.setPosition(TilePosX, TilePosY);
            sprite.draw(batch);
        }

    }

}
