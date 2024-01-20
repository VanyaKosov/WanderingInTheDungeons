import java.util.*;

/**
 * Stores all of the information about an enemy
 * 
 * @author Ivan Kosov
 */
public class Enemy {
    private final Random random = new Random();
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private final ArrayList<Pos> accessibleCells;
    private Queue<Pos> movePath = new ArrayDeque<Pos>();
    private int turnsToSkip;
    public final ArrayList<Attack> attacks = new ArrayList<>();
    public final Stats stats;
    public final String name;
    public final String description;

    /**
     * 
     * @param dungeon is an instance of the Dungeon class
     * @param player is an instance of the Player class
     * @param viewRadius is the radius in which it will sense the player and follow them
     * @param pos is the starting position of the enemy
     * @param stats is an instance of the Stats class
     * @param name is the name of the enemy
     * @param description is the description of the enemy
     */
    public Enemy(Dungeon dungeon, Player player, int viewRadius, Pos pos, Stats stats, String name,
            String description) {
        this.dungeon = dungeon;
        this.player = player;
        this.viewRadius = viewRadius;
        this.pos = pos;
        this.stats = stats;
        this.name = name;
        this.description = description;
        turnsToSkip = 0;

        accessibleCells = dungeon.findAccessibleCells(pos);
    }

    /**
     * When the player is not in reach, the enemy randomly chooses a cell and goes to it. 
     * When the enemy senses the player nearby, it will follow them instead
     */
    public void move() {
        if (turnsToSkip > 0) {
            turnsToSkip--;

            return;
        }

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

    /**
     * Skips a given amount of turns
     * 
     * @param amount is the amount of turns to skip
     */
    public void skipTurns(int amount) {
        turnsToSkip += amount;
    }

    /**
     * Checks if the player is within reach
     * 
     * @return true if the player is nearby, and false if not
     */
    private boolean isPlayerAround() {
        return Math.abs(pos.row - player.getPos().row) <= viewRadius
                && Math.abs(pos.col - player.getPos().col) <= viewRadius;
    }

    /**
     * @return current position of the enemy
     */
    public Pos getPos() {
        return new Pos(pos.row, pos.col);
    }

    /**
     * Stores the description of an attack, and all of the possible defenses
     */
    public static class Attack {
        public final String description;
        public final Defense[] defenses;

        public Attack(String description, Defense[] defenses) {
            this.description = description;
            this.defenses = defenses;
        }
    }

    /**
     * Stores the description of the defense, the amount of damage received, and the description of the result of this defense
     */
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
