public class MonsterOgre extends Enemy {
    public final String description = "This is an ogre description";

    public MonsterOgre(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(50, 10, 3));

        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("Jump", 0, "Defense 1 result description"),
                new Defense("Duck down", 5, "Defense 2 result description"),
                new Defense("Block with shield", 10, "Defense 3 result description")
        };
        Attack attack1 = new Attack("Attack 1 description", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("Jump", 5, "Defense 1 result description"),
                new Defense("Duck down", 10, "Defense 2 result description"),
                new Defense("Block with shield", 0, "Defense 3 result description")
        };
        Attack attack2 = new Attack("Attack 2 description", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("Jump", 10, "Defense 1 result description"),
                new Defense("Duck down", 0, "Defense 2 result description"),
                new Defense("Block with shield", 5, "Defense 3 result description")
        };
        Attack attack3 = new Attack("Attack 3 description", attack3Defenses);
        attacks.add(attack3);
    }
}
