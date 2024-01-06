public class MonsterOgre extends Enemy {
    public static final String name = "Ogre";
    public static final String description = "This is an ogre description";

    public MonsterOgre(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(60, 10, 7), name, description);

        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 0, "Defense 1 result description"),
                new Defense("duck down", 5, "Defense 2 result description"),
                new Defense("block with the shield", 10, "Defense 3 result description")
        };
        Attack attack1 = new Attack("PLACEHOLDER The ogre aims at your legs with it's giant club.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 5, "Defense 1 result description"),
                new Defense("duck down", 10, "Defense 2 result description"),
                new Defense("block with the shield", 0, "Defense 3 result description")
        };
        Attack attack2 = new Attack("PLACEHOLDER The ogre is about to punch you with a fist.", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 10, "Defense 1 result description"),
                new Defense("duck down", 0, "Defense 2 result description"),
                new Defense("block with the shield", 5, "Defense 3 result description")
        };
        Attack attack3 = new Attack("PLACEHOLDER The ogre aims at your head with it's giant club.", attack3Defenses);
        attacks.add(attack3);
    }
}
