public class ItemWeapon extends Item {
    public final int damage;

    public ItemWeapon(String description, int damage) {
        super(description);

        this.damage = damage;
    }

    @Override
    public void apply(Player player) {
        // Weapons don't do anything right now
    }

}
