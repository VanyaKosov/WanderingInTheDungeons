/**
 * Increases player's strength by 3-7
 * 
 * @author Ivan Kosov
 */
public class RustySword extends ItemWeapon {
    private static final String NAME = "Rusty Sword";
    private static final String DESCRIPTION = "A sword covered in rust.";
    private static final int MIN_DAMAGE = 3;
    private static final int MAX_DAMAGE = 7;

    public RustySword() {
        super(NAME, DESCRIPTION, MIN_DAMAGE, MAX_DAMAGE);
    }

    @Override
    public void apply(Player player) {
        // Nothing to do here
    }
}
