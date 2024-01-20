/**
 * Increases player's armor by 2
 * 
 * @author Ivan Kosov
 */
public class LeatherArmor extends ItemArmor {
    private static final String NAME = "Leather armor";
    private static final String DESCRIPTION = "Simple armor made out of leather. It barely provides any protection";
    private static final int ARMOR = 2;

    public LeatherArmor() {
        super(NAME, DESCRIPTION, ARMOR);
    }
}
