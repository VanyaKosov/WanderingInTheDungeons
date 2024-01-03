import java.util.*;

public class Enemy {
    private final Dungeon dungeon;
    private final Player player;
    private final int viewRadius;
    private final Pos pos;
    private ArrayList<Pos> movePath;

    public Enemy(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        this.dungeon = dungeon;
        this.player = player;
        this.viewRadius = viewRadius;
        this.pos = pos;
    }

    public void move() {

    }

    // private boolean isPlayerAround() {

    // }
}
