
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player {
    private int viewArea;
    private int fogOfWarArea;

    public Player() {
        viewArea = 2;
        fogOfWarArea = 4;
    }

    public void setViewArea(int viewArea) {
        this.viewArea = viewArea;
    }

    public int getViewArea() {
        return viewArea;
    }

    public void setFogOfWarArea(int fogOfWarArea) {
        this.fogOfWarArea = fogOfWarArea;
    }

    public int getFogOfWarArea() {
        return fogOfWarArea;
    }
}
