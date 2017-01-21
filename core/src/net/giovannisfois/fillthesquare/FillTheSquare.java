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
import com.badlogic.gdx.math.Vector3;

public class FillTheSquare extends ApplicationAdapter {

	float mWorldWidth  ;
	float mWorldHeight ;

	int mBoardSize;

	int mBoardBaseX;
	int mBoardBaseY;
	float mBoardTileSize;

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

		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Gdx.app.debug("FillTheSquare","Screen Height:" +  Gdx.graphics.getHeight());
		Gdx.app.debug("FillTheSquare","Screen Width:" +   Gdx.graphics.getWidth());
		Gdx.app.debug("FillTheSquare","Aspect Ratio:" +  aspectRatio);

		mBoardSize  = 5;

		mWorldWidth  = 32;
		mWorldHeight = (int) (mWorldWidth * aspectRatio);
        mBoardTileSize = (mWorldWidth - 2)/mBoardSize;

		Gdx.app.debug("FillTheSquare","World Height:" +  mWorldHeight);

		mBoardBaseX = 1;
		mBoardBaseY = (int) (1+(mBoardSize)*mBoardTileSize);

		Gdx.app.debug("FillTheSquare","Base Y:" +  mBoardBaseY);

		mBoard = new Board(mBoardSize);
		mBoard.setSprites(mBoardTileSize, emptyTileRegion, checkedTileRegion, activeTileRegion, currentTileRegion);

		titleSquare = new Sprite(titleSquareRegion);
		titleSquare.setSize(6,2);

		//Set the camera viewpoint
		camera = new OrthographicCamera(mWorldWidth, mWorldHeight);
		camera.position.set(mWorldWidth/2, mWorldHeight/2,0);

        touchPoint = new Vector3();


		// Intercept the touch events and forward them to the mGame, when appropriate...
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
				Gdx.app.debug("FillTheSquare","TouchCamera: x:" + x + " y:" + y);

                // your touch down code here
                camera.unproject(touchPoint.set(x, y, 0));
				Gdx.app.debug("FillTheSquare","TouchPoint: x:" + touchPoint.x + " y:" + touchPoint.y);
                if(mBoard.isTouched(mBoardBaseX,mBoardBaseY, touchPoint.x, touchPoint.y)) {
                    mBoard.handleTouch(mBoardBaseX,mBoardBaseY, touchPoint.x, touchPoint.y);
                    return true; // return true to indicate the event was handled
                }
                return false;
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

		mBoard.draw(batch, mBoardBaseX, mBoardBaseY);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		spritesheet.dispose();
	}


}
