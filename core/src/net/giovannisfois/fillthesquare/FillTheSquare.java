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

	final int SQUARE_BASE_X = 10;
	final int SQUARE_BASE_Y = 14;

	SpriteBatch batch;
	Texture spritesheet;

	private TextureRegion titleSquareRegion;
	private TextureRegion checkedSquareRegion;
	private TextureRegion emptySquareRegion;
	private TextureRegion nextSquareRegion;

	private Sprite titleSquare;

	private Sprite checkedSquare;
	private Sprite emptySquare;
	private Sprite nextSquare;

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

		//checkedSquareRegion  = new TextureRegion(spritesheet,190, 194,49,45);
		checkedSquareRegion  = new TextureRegion(spritesheet,380, 36, 38, 36);

		emptySquareRegion = new TextureRegion(spritesheet,288, 194,49,49);
		nextSquareRegion  = new TextureRegion(spritesheet,288, 194,49,49);

		titleSquare = new Sprite(titleSquareRegion);
		titleSquare.setSize(6,2);

		checkedSquare = new Sprite(checkedSquareRegion);
		checkedSquare.setSize(2,2);

		emptySquare = new Sprite(emptySquareRegion);
		emptySquare.setSize(2,2);

		nextSquare = new Sprite(nextSquareRegion);
		nextSquare.setSize(2,2);

		nextSquare = new Sprite(nextSquareRegion);
		nextSquare.setSize(2,2);

		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

		camera = new OrthographicCamera(WORLD_WIDTH, 4+WORLD_WIDTH*aspectRatio);
		camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);

        touchPoint = new Vector3();

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                // your touch down code here
                Gdx.app.debug("FillTheSquare", "touchDown: " + x + " - " + y);
                camera.unproject(touchPoint.set(x, y, 0));
                Gdx.app.debug("FillTheSquare", "touchDown Local: " + touchPoint.x + " - " + touchPoint.y);

                int col = (int)((touchPoint.x - SQUARE_BASE_X)/2);
                int row = -(int)((touchPoint.y - SQUARE_BASE_Y)/2);
                Gdx.app.debug("FillTheSquare", "touchDown Row Col: " + row + " - " + col);

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
		//batch.disableBlending();
		titleSquare.setPosition(2,12);
		titleSquare.draw(batch);
		titleSquare.setPosition(2,8);
		titleSquare.draw(batch);

		ArrayList<GridPoint2> Squares = mSquare.getSquares();
		ArrayList<GridPoint2> checkedSquares = mSquare.getCheckedSquares();

		drawTiles(Squares, emptySquare);
		drawTiles(checkedSquares, checkedSquare);

		checkedSquare.setPosition(0,0);
		checkedSquare.draw(batch);

		checkedSquare.setPosition(0,WORLD_HEIGHT-2);
		checkedSquare.draw(batch);

		checkedSquare.setPosition(WORLD_WIDTH-2,WORLD_HEIGHT-2);
		checkedSquare.draw(batch);

		checkedSquare.setPosition(WORLD_WIDTH-2,0);
		checkedSquare.draw(batch);





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
		for (GridPoint2 point : points) {
			TilePosX = SQUARE_BASE_X + 2 * point.x ;
			TilePosY = SQUARE_BASE_Y - 2*point.y - 2 ;

			//Gdx.app.debug("FillTheSquare", "Empty Pos " + TilePosX + "- " + TilePosY);
			sprite.setPosition(TilePosX, TilePosY);
			sprite.draw(batch);
		}

	}

}
