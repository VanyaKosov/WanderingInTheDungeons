
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player {
    private Dungeon dungeon;
    private Pos pos = new Pos();
    private int viewRadius;
    private int fogOfWarRadius;

    public Player(Dungeon dungeon, int viewRadius, int fogOfWarRadius) {
        this.dungeon = dungeon;
        this.viewRadius = viewRadius;
        this.fogOfWarRadius = fogOfWarRadius;

        pos.row = dungeon.getPlayerPos().row;
        pos.col = dungeon.getPlayerPos().col;
    }

    public void movePlayer(Input.Keys direction) {
        if (!canMovePlayer(direction)) {
            return;
        }

        int row = pos.row;
        int col = pos.col;
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                throw new IllegalStateException();
        }

        pos.row = row;
        pos.col = col;
        dungeon.movePlayerPos(row, col);
    }

    public boolean canMovePlayer(Input.Keys key) {
        int row = pos.row;
        int col = pos.col;
        switch (key) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                throw new IllegalStateException();
        }

        if (dungeon.getCell(row, col) == Cells.WALL) {
            return false;
        }

        return true;
    }

    public Pos getPos() {
        return new Pos(pos.row, pos.col);
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