package net.giovannisfois.fillthesquare;


/* IPlayable
     defines the most common options for a Playable Game
 */
public interface IPlayable {
    public void reset();
    public boolean isGameEnded();
    public boolean hasPlayerWon();
}
