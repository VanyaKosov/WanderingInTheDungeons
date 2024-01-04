import java.util.*;

public class Enemy {
    private final Random random = new Random();
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private final ArrayList<Pos> accessibleCells;
    //private ArrayList<Pos> movePath;
    private Queue<Pos> movePath = new ArrayDeque<Pos>();
    //private Pos destination;

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
            if (pathToPlayer != null /*&& pathToPlayer.size() != 0*/) {
                movePath.clear();
                movePath.addAll(dungeon.findPath(pos, player.getPos()));
            }
            //destination = new Pos(player.getPos().row, player.getPos().col);
            // movePath = dungeon.findPath(pos, destination);
            //movePath = dungeon.findPath(pos, new Pos(player.getPos().row, player.getPos().col));
        }

        // while (movePath == null) {
        //     // destination = accessibleCells.get(random.nextInt(accessibleCells.size()));
        //     // movePath = dungeon.findPath(pos, destination);
        //     movePath = dungeon.findPath(pos, accessibleCells.get(random.nextInt(accessibleCells.size())));
        // }
        while (movePath.size() == 0) {
            movePath.addAll(dungeon.findPath(pos, accessibleCells.get(random.nextInt(accessibleCells.size()))));
        }

        Pos futurePos = movePath.poll();
        pos.row = futurePos.row;
        pos.col = futurePos.col;

        // pos.row = movePath.get(0).row;
        // pos.col = movePath.get(0).col;
        // movePath.remove(0);
        // if (movePath.size() == 0) {
        //     movePath = null;
        // }
    }

    private boolean isPlayerAround() {
        return Math.abs(pos.row - player.getPos().row) <= viewRadius
                && Math.abs(pos.col - player.getPos().col) <= viewRadius;
    }

    public Pos getPos() {
        return new Pos(pos.row, pos.col);
    }
}
