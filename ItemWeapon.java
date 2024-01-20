import java.util.*;

/**
 * Inherits Item class.
 * Inherited by all weapon items
 * 
 * @author Ivan Kosov
 */
public abstract class ItemWeapon extends Item {
    private static final Random RANDOM = new Random();
    public final int minDamage;
    public final int maxDamage;

    public ItemWeapon(String name, String description, int minDamage, int maxDamage) {
        super(name, description);

        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * @return a random damage between minimum and maximum
     */
    public int getDamage() {
        return RANDOM.nextInt(minDamage, maxDamage + 1);
    }
}
