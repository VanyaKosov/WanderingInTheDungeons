
/**
 * Increases player's strength by 7-12
 *
 * @author Ivan Kosov
 */
public class SteelSword extends ItemWeapon {
    private static final String NAME = "Steel Sword";
    private static final String DESCRIPTION = "A shiny steel sword.";
    private static final int MIN_DAMAGE = 7;
    private static final int MAX_DAMAGE = 12;

    public SteelSword() {
        super(NAME, DESCRIPTION, MIN_DAMAGE, MAX_DAMAGE);
    }

    @Override
    public void apply(Player player) {
        // Nothing to do here
    }
}
