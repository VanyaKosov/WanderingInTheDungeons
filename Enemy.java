import java.util.*;

public class Enemy {
    private final Random random = new Random();
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private final ArrayList<Pos> accessibleCells;
    private Queue<Pos> movePath = new ArrayDeque<Pos>();

    public Enemy(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        this.dungeon = dungeon;
        this.player = player;
        this.viewRadius = viewRadius;
        this.pos = pos;
        accessibleCells = dungeon.findAccessibleCells(pos);
    }

    public void move() {
        if (accessibleCells.size() == 0) {
            return;
        }

        if (isPlayerAround()) {
            var pathToPlayer = dungeon.findPath(pos, player.getPos());
            if (pathToPlayer != null) {
                movePath.clear();
                movePath.addAll(dungeon.findPath(pos, player.getPos()));
            }
        }

        while (movePath.size() == 0) {
            movePath.addAll(dungeon.findPath(pos, accessibleCells.get(random.nextInt(accessibleCells.size()))));
        }

        Pos futurePos = movePath.poll();
        pos.row = futurePos.row;
        pos.col = futurePos.col;
    }

    private boolean isPlayerAround() {
        return Math.abs(pos.row - player.getPos().row) <= viewRadius
                && Math.abs(pos.col - player.getPos().col) <= viewRadius;
    }

    public Pos getPos() {
        return new Pos(pos.row, pos.col);
    }
}
