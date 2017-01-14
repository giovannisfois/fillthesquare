package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FillTheSquare extends ApplicationAdapter {

	final float WORLD_WIDTH  = 22;
	final float WORLD_HEIGHT = 16;

	SpriteBatch batch;
	Texture spritesheet;

	private TextureRegion titleSquareRegion;
	private TextureRegion usedSquareRegion;
	private TextureRegion emptySquareRegion;
	private TextureRegion nextSquareRegion;

	private Sprite titleSquare;

	private Sprite usedSquare;
	private Sprite emptySquare;
	private Sprite nextSquare;

	private Square mSquare;

	OrthographicCamera camera;


	@Override
	public void create () {
		batch = new SpriteBatch();
		spritesheet = new Texture("bluesheet.png");

		titleSquareRegion = new TextureRegion(spritesheet, 0, 143, 190, 49);

		usedSquareRegion  = new TextureRegion(spritesheet,190, 194,49,45);
		emptySquareRegion = new TextureRegion(spritesheet,288, 194,49,49);
		nextSquareRegion  = new TextureRegion(spritesheet,288, 194,49,49);

		titleSquare = new Sprite(titleSquareRegion);
		titleSquare.setSize(6,2);

		usedSquare = new Sprite(usedSquareRegion);
		usedSquare.setSize(2,2);

		emptySquare = new Sprite(emptySquareRegion);
		emptySquare.setSize(2,2);

		usedSquare = new Sprite(usedSquareRegion);
		usedSquare.setSize(2,2);

		nextSquare = new Sprite(nextSquareRegion);
		nextSquare.setSize(2,2);

		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_WIDTH*aspectRatio);
		camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(250, 250, 250, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//batch.disableBlending();
		titleSquare.setPosition(2,10);
		titleSquare.draw(batch);
		titleSquare.setPosition(2,6);
		titleSquare.draw(batch);

		emptySquare.setPosition(10,10);
		emptySquare.draw(batch);
		emptySquare.setPosition(12,10);
		emptySquare.draw(batch);
		emptySquare.setPosition(14,10);
		emptySquare.draw(batch);
		usedSquare.setPosition(16,10);
		usedSquare.draw(batch);
		usedSquare.setPosition(18,10);
		usedSquare.draw(batch);
		usedSquare.setPosition(10,8);
		usedSquare.draw(batch);
		usedSquare.setPosition(12,8);
		usedSquare.draw(batch);
		usedSquare.setPosition(14,8);
		usedSquare.draw(batch);





		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		spritesheet.dispose();
	}
}
