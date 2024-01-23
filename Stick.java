/**
 * This is a default weapon that player gets at the start of the game.
 * Increases player strength by 1
 * 
 * @author Ivan Kosov
 */
public class Stick extends ItemWeapon {
    private static final String NAME = "Stick";
    private static final String DESCRIPTION = "This is just a stick";
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 1;

    public Stick() {
        super(NAME, DESCRIPTION, MIN_DAMAGE, MAX_DAMAGE);
    }

    @Override
    public void apply(Player player) {
        // Nothing to do here
    }
}
