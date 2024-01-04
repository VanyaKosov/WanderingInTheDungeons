import java.util.*;

public class Enemy {
    private final Random random = new Random();
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private final ArrayList<Pos> accessibleCells;
    private ArrayList<Pos> movePath;
    private Pos destination;

    public Enemy(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        this.dungeon = dungeon;
        this.player = player;
        this.viewRadius = viewRadius;
        this.pos = pos;
        accessibleCells = dungeon.findAccessibleCells(pos);
    }

    public void move() {
        if (isPlayerAround()) {
            destination = new Pos(player.getPos().row, player.getPos().col);
            movePath = dungeon.findPath(pos, destination);
        }

        if (destination == null) {
            destination = accessibleCells.get(random.nextInt(accessibleCells.size()));
            movePath = dungeon.findPath(pos, destination);
        }

        pos.row = movePath.get(0).row;
        pos.col = movePath.get(0).col;
        movePath.remove(0);
        if (movePath.size() == 0) {
            movePath = null;
        }
    }

    private boolean isPlayerAround() {
        return Math.abs(pos.row - player.getPos().row) <= viewRadius
                && Math.abs(pos.col - player.getPos().col) <= viewRadius;
    }
}
