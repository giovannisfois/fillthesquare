package net.giovannisfois.fillthesquare;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/* IRegionUI
    defines the interaction between the world and the playable/touchable areas of the game
 */
public interface IRegionUI {
    public void handleTouch(int row, int col);
    public void draw(SpriteBatch batch, int originX, int originY);
}
