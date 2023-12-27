
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player {
    private int viewRadius;
    private int fogOfWarRadius;

    public Player() {
        viewRadius = 2;
        fogOfWarRadius = 1;
    }

    public void setViewRadius(int viewArea) {
        this.viewRadius = viewArea;
    }

    public int getViewRadius() {
        return viewRadius;
    }

    public void setFogOfWarRadius(int fogOfWarArea) {
        this.fogOfWarRadius = fogOfWarArea;
    }

    public int getFogOfWarRadius() {
        return fogOfWarRadius;
    }
}
