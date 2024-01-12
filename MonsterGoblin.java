
/**
 * Write a description of class MonsterGoblin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MonsterGoblin extends Enemy {
    public static final String name = "Goblin";
    public static final String description = "This is a goblin description";

    public MonsterGoblin(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(20, 3, 0), name, description);

        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 2, "Defense 1 result description"),
                new Defense("duck down", 5, "Defense 2 result description"),
                new Defense("block with the shield", 0, "Defense 3 result description")
        };
        Attack attack1 = new Attack("PLACEHOLDER The goblin wants to slash your legs with a dagger.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 0, "Defense 1 result description"),
                new Defense("duck down", 5, "Defense 2 result description"),
                new Defense("block with the shield", 1, "Defense 3 result description")
        };
        Attack attack2 = new Attack("PLACEHOLDER The goblin tries to jump on your back.", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 2, "Defense 1 result description"),
                new Defense("duck down", 5, "Defense 2 result description"),
                new Defense("block with the shield", 0, "Defense 3 result description")
        };
        Attack attack3 = new Attack("PLACEHOLDER The goblin trows his dagger at you.", attack3Defenses);
        attacks.add(attack3);
    }
}