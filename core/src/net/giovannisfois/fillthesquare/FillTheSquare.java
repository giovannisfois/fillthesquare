package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector3;

import java.awt.Point;
import java.util.ArrayList;

public class FillTheSquare extends ApplicationAdapter {

	final int WORLD_WIDTH  = 22;
	final int WORLD_HEIGHT = 16;

	final int SQUARE_SIZE = 5;

	//Position of the left bottom corner of the game square
	final int SQUARE_BASE_X = 10;
	final int SQUARE_BASE_Y = 14;

	SpriteBatch batch;
	Texture spritesheet;

	//Various tiles
	private TextureRegion titleSquareRegion;
	private TextureRegion checkedTileRegion;
	private TextureRegion emptyTileRegion;
	private TextureRegion activeTileRegion;
	private TextureRegion currentTileRegion;

	private Sprite titleSquare;

	private Sprite checkedTile;
	private Sprite emptyTile;
	private Sprite activeTile;
	private Sprite currentTile;

	private Square mSquare;
	private Game mGame;

    Vector3 touchPoint;

    OrthographicCamera camera;


	@Override
	public void create () {
		batch = new SpriteBatch();
		spritesheet = new Texture("bluesheet.png");

		mSquare = new Square(SQUARE_SIZE);
		mGame   = new Game(mSquare, SQUARE_SIZE);

		titleSquareRegion = new TextureRegion(spritesheet, 0, 143, 190, 49);

		emptyTileRegion = new TextureRegion(spritesheet,288, 194,49,49);
		checkedTileRegion  = new TextureRegion(spritesheet,288, 140, 38, 36);
		activeTileRegion  = new TextureRegion(spritesheet,384, 36,34,34);
		currentTileRegion  = new TextureRegion(spritesheet,385, 210,36,36);

		titleSquare = new Sprite(titleSquareRegion);
		titleSquare.setSize(6,2);

		//Tiles Initialization
		checkedTile = new Sprite(checkedTileRegion);
		checkedTile.setSize(2,2);

		emptyTile = new Sprite(emptyTileRegion);
		emptyTile.setSize(2,2);

		activeTile = new Sprite(activeTileRegion);
		activeTile.setSize(2,2);

		currentTile = new Sprite(currentTileRegion);
		currentTile.setSize(2,2);

		//Set the camera viewpoint
		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		camera = new OrthographicCamera(WORLD_WIDTH, 4+WORLD_WIDTH*aspectRatio);
		camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);


        touchPoint = new Vector3();

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Intercept the touch events and forward them to the mGame, when appropriate...
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                // your touch down code here
                camera.unproject(touchPoint.set(x, y, 0));

                int col = (int)((touchPoint.x - SQUARE_BASE_X)/2);
                int row = -(int)((touchPoint.y - SQUARE_BASE_Y)/2);
				mGame.handleTouch(row,col);

                return true; // return true to indicate the event was handled
            }



        });
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(250, 250, 250, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.disableBlending();

		titleSquare.setPosition(2,12);
		titleSquare.draw(batch);
		titleSquare.setPosition(2,8);
		titleSquare.draw(batch);

		ArrayList<GridPoint2> Tiles = mSquare.getTiles();
		ArrayList<GridPoint2> checkedTiles = mSquare.getCheckedTiles();
		ArrayList<GridPoint2> activeTiles =  mGame.getActiveTiles();

		GridPoint2 currentTile = mGame.getCurrentTile();

		drawTiles(Tiles, emptyTile);
		drawTiles(checkedTiles, checkedTile);
		drawTiles(activeTiles, activeTile);


		// Angoli del mondo
		checkedTile.setPosition(0,0);
		checkedTile.draw(batch);

		checkedTile.setPosition(0,WORLD_HEIGHT-2);
		checkedTile.draw(batch);

		checkedTile.setPosition(WORLD_WIDTH-2,WORLD_HEIGHT-2);
		checkedTile.draw(batch);

		checkedTile.setPosition(WORLD_WIDTH-2,0);
		checkedTile.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		spritesheet.dispose();
	}

	private void  drawTiles(ArrayList<GridPoint2> points, Sprite sprite ){

		int TilePosX;
		int TilePosY;
		int row;
		int col;

		for (GridPoint2 point : points) {
			row = point.x;
			col = point.y;
			TilePosX = SQUARE_BASE_X + 2 * col;
			TilePosY = SQUARE_BASE_Y - 2*row - 2 ;

			//Gdx.app.debug("FillTheSquare", "Empty Pos " + TilePosX + "- " + TilePosY);
			sprite.setPosition(TilePosX, TilePosY);
			sprite.draw(batch);
		}

	}

}
