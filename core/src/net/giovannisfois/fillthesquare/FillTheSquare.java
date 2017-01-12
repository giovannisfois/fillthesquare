package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FillTheSquare extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private TextureRegion region;
	private Sprite sprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 0, 0, img.getWidth()/2, img.getHeight()/2);
		sprite = new Sprite(region);
		sprite.setPosition(Gdx.graphics.getWidth()*2/3, Gdx.graphics.getWidth()*2 );
		sprite.setRotation(275);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 120, 120, 0, 0, img.getWidth(), img.getHeight(), false, false);
		batch.draw(img, Gdx.graphics.getWidth()/2, 0, 120, 120, 0, img.getWidth()/2, img.getWidth()/2, img.getHeight()/2, false, false);

		batch.draw(region, 10, 10);
		batch.draw(region, Gdx.graphics.getWidth()*2/3, 0,region.getRegionWidth()/2, region.getRegionHeight());
		sprite.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
