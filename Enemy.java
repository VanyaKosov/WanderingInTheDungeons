import java.util.*;

public class Enemy {
    private final Random random = new Random();
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private final ArrayList<Pos> accessibleCells;
    private Queue<Pos> movePath = new ArrayDeque<Pos>();
    public final ArrayList<Attack> attacks = new ArrayList<>();
    public final Stats stats;
    public final String name;
    public final String description;

    public Enemy(Dungeon dungeon, Player player, int viewRadius, Pos pos, Stats stats, String name,
            String description) {
        this.dungeon = dungeon;
        this.player = player;
        this.viewRadius = viewRadius;
        this.pos = pos;
        this.stats = stats;
        this.name = name;
        this.description = description;

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

    public static class Attack {
        public final String description;
        public final Defense[] defenses;

        public Attack(String description, Defense[] defenses) {
            this.description = description;
            this.defenses = defenses;
        }
    }

    public static class Defense {
        public final String description;
        public final int damage;
        public final String resultDescription;

        public Defense(String description, int damage, String resultDescription) {
            this.description = description;
            this.damage = damage;
            this.resultDescription = resultDescription;
        }
    }
}
