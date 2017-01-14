package net.giovannisfois.fillthesquare;

/**
 * Created by giovans on 13/01/17.
 */

public class Tile {

    final int EMPTY   = 1;
    final int CHECKED = 2;

    private int mStatus;

    public Tile() {
        mStatus = EMPTY;
    }

    public void markEmpty() {
        mStatus = EMPTY;
    }

    public void markChecked(){
        mStatus = CHECKED;
    }

    public boolean isEmpty(){
        return mStatus == EMPTY;
    }

    public boolean isChecked(){
        return mStatus == CHECKED;
    }

}
