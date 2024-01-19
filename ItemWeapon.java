import java.util.*;

public abstract class ItemWeapon extends Item {
    private static final Random RANDOM = new Random();
    //public final int damage;
    public final int minDamage;
    public final int maxDamage;

    public ItemWeapon(String name, String description, int minDamage, int maxDamage) {
        super(name, description);

        //this.damage = damage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    
    public int getDamage() {
        return RANDOM.nextInt(minDamage, maxDamage + 1);
    }
}
