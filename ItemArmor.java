/**
 * Inherits Item class.
 * Inherited by all armor items
 * 
 * @author Ivan kosov
 */
public abstract class ItemArmor extends Item {
    public final int armor;

    public ItemArmor(String name, String description, int armor) {
        super(name, description);

        this.armor = armor;
    }

    /**
     * Increases player's armor stat by a certain amount
     */
    public void apply(Player player) {
        player.stats.increaseArmor(armor);
    }
}
