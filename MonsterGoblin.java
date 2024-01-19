
/**
 * Write a description of class MonsterGoblin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MonsterGoblin extends Enemy {
    public static final String name = "Goblin";
    public static final String description = "A short, fast, and evil creature with green skin. It has several daggers tied to it's belt.";

    public MonsterGoblin(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(20, 3, 0), name, description);

        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 0, "You jumped, the goblin is too short to reach you. Damage: 0"),
                new Defense("duck down", 5, "The goblin left a slashed both of your legs. Damage: 5"),
                new Defense("block with the shield", 3, "The goblin is too fast. He left a deep cut in one of your legs. Damage: 3")
        };
        Attack attack1 = new Attack("The goblin tries to slash your legs with a dagger.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 5, "The goblin caught one of your legs and stabbed it. Damage: 5"),
                new Defense("duck down", 0, "The goblin jumped over you. Damage: 0"),
                new Defense("block with the shield", 3, "You weren't fast enough. Goblin cut one of your arms. Damage: 3")
        };
        Attack attack2 = new Attack("The goblin jumps on your back.", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 3, "The dagger slashed your leg. Damage: 2"),
                new Defense("duck down", 5, "The dagger left a deep wound on your shoulder. Damage: 5"),
                new Defense("block with the shield", 0, "You successfuly blocked the dagger with the shield. Damage: 0")
        };
        Attack attack3 = new Attack("The goblin trows it's dagger at you.", attack3Defenses);
        attacks.add(attack3);
    }
}