public abstract class ItemWeapon extends Item {
    public final int damage;

    public ItemWeapon(String name, String description, int damage) {
        super(name, description);

        this.damage = damage;
    }
}
