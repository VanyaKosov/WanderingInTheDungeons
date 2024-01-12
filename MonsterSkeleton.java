
/**
 * Write a description of class MonsterSkeleton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MonsterSkeleton extends Enemy {
    public static final String name = "Skeleton";
    public static final String description = "This is a skeleton description";

    public MonsterSkeleton(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(15, 15, 2), name, description);

        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 10, "Defense 1 result description"),
                new Defense("duck down", 10, "Defense 2 result description"),
                new Defense("block with the shield", 0, "Defense 3 result description")
        };
        Attack attack1 = new Attack("PLACEHOLDER The skeleton aims at you with it's bow.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 7, "Defense 1 result description"),
                new Defense("duck down", 0, "Defense 2 result description"),
                new Defense("block with the shield", 10, "Defense 3 result description")
        };
        Attack attack2 = new Attack("PLACEHOLDER The skeleton aims at you with it's bow. The arrow glows suspiciously", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 7, "Defense 1 result description"),
                new Defense("duck down", 10, "Defense 2 result description"),
                new Defense("block with the shield", 0, "Defense 3 result description")
        };
        Attack attack3 = new Attack("The skeleton throws one of it's bones at you.", attack3Defenses);
        attacks.add(attack3);
    }
}
