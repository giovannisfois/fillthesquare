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

import java.util.ArrayList;

public class FillTheSquare extends ApplicationAdapter {

	final int WORLD_WIDTH  = 24;
	final int WORLD_HEIGHT = 18;

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

	private Board mBoard;

    Vector3 touchPoint;

    OrthographicCamera camera;


	@Override
	public void create () {
		batch = new SpriteBatch();
		spritesheet = new Texture("bluesheet.png");

		titleSquareRegion  = new TextureRegion(spritesheet, 0, 143, 190, 49);
		emptyTileRegion    = new TextureRegion(spritesheet,288, 194,49,49);
		checkedTileRegion  = new TextureRegion(spritesheet,288, 140, 38, 36);
		activeTileRegion   = new TextureRegion(spritesheet,384, 36,34,34);
		currentTileRegion  = new TextureRegion(spritesheet,385, 210,36,36);

		mBoard = new Board(SQUARE_SIZE);
		mBoard.setSprites(emptyTileRegion, checkedTileRegion, activeTileRegion, currentTileRegion);

		titleSquare = new Sprite(titleSquareRegion);
		titleSquare.setSize(6,2);

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
				mBoard.handleTouch(row,col);
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

		mBoard.draw(batch, SQUARE_BASE_X, SQUARE_BASE_Y);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		spritesheet.dispose();
	}


}
