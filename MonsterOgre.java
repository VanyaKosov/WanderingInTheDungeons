public class MonsterOgre extends Enemy {
    public static final String name = "Ogre";
    public static final String description = "A terrifying, green, stinking giant. As it's weapon, it wields a club made out of a whole tree.";

    public MonsterOgre(Dungeon dungeon, Player player, int viewRadius, Pos pos) {
        super(dungeon, player, viewRadius, pos, new Stats(60, 10, 4), name, description);
        
        addAttacks();
    }

    private void addAttacks() {
        Defense[] attack1Defenses = {
                new Defense("jump", 0, "You jump over the club. Damage: 0"),
                new Defense("duck down", 15, "You you ducked, the club hit you in the head. Damage: 15"),
                new Defense("block with the shield", 10, "You tried to block the hit, but the club is too heavy. Damage: 10")
        };
        Attack attack1 = new Attack("The ogre aims at your legs with it's giant club.", attack1Defenses);
        attacks.add(attack1);

        Defense[] attack2Defenses = {
                new Defense("jump", 10, "You couldn't jump high enough, the ogre still hit your legs. Damage: 10"),
                new Defense("duck down", 15, "You tried to duck down, but you weren't fast enough. Ogre's punch sends you flying across the room. Damage: 15"),
                new Defense("block with the shield", 0, "Even though the hit was strong, you successfuly blocked the hit. Damage: 0")
        };
        Attack attack2 = new Attack("The ogre is about to punch you with a fist.", attack2Defenses);
        attacks.add(attack2);

        Defense[] attack3Defenses = {
                new Defense("jump", 15, "The ogre hit you in the middle of the jump. Damage: 15"),
                new Defense("duck down", 0, "You successfuly evaded the hit by rolling under it. Damage: 0"),
                new Defense("block with the shield", 10, "You tried to block the hit, but the club is too heavy. Damage: 10")
        };
        Attack attack3 = new Attack("The ogre aims at your head with it's giant club.", attack3Defenses);
        attacks.add(attack3);
    }
}
