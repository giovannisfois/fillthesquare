package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;


/* IRegionUI
    defines the interaction between the world and the playable/touchable areas of the game
 */
public interface IRegionUI {
    public boolean isTouched(float base_x, float base_y, float x, float y);
    public void handleTouch(float base_x, float base_y, float x, float y);
    public void draw(SpriteBatch batch, int originX, int originY);

}
