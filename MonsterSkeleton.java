/**
 * Inherits the Enemy class
 *
 * @author Ivan Kosov
 */
public class MonsterSkeleton extends Enemy {
    public static final String name = "Skeleton";
    public static final String description = "A skeleton that once was a human. It's eyes glow with terrifying purple glow. It has a bow on it's back.";

    public MonsterSkeleton(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(15, 15, 2), name, description);

        addAttacks();
    }

    /**
     * Creates attacks and defenses for the skeleton
     */
    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 10, "The skeleton's arrow hit you in the leg. Damage: 10"),
                new Defense("duck down", 10, "You weren't fast enough. The arrow pierced your shoulder. Damage: 10"),
                new Defense("block with the shield", 0, "You successfully blocked the arrow. Damage: 0")
        };
        Attack attack1 = new Attack("The skeleton aims at you with it's bow.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 0, "You barely evaded the arrow, it just scratched one of your legs. Damage: 1"),
                new Defense("duck down", 0,
                        "The arrow cut a few hairs from your head, but otherwise you are intact. Damage: 0"),
                new Defense("block with the shield", 10,
                        "The arrow was enchanted because it penetrated through you shield like it wasn't even there and hit you. Damage: 10")
        };
        Attack attack2 = new Attack("The skeleton aims at you with it's bow. The arrow glows suspiciously",
                attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 10, "The bone pierced your belly. Damage: 10"),
                new Defense("duck down", 5,
                        "You almost evaded the hit but the bone still left a deep cut in your shoulder. Damage: 5"),
                new Defense("block with the shield", 0, "The bone bounced off your shield. Damage: 0")
        };
        Attack attack3 = new Attack("The skeleton throws one of it's bones at you.", attack3Defenses);
        attacks.add(attack3);
    }
}
