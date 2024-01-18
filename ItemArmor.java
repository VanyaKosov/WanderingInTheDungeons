public abstract class ItemArmor extends Item {
    public final int armor;

    public ItemArmor(String name, String description, int armor) {
        super(name, description);

        this.armor = armor;
    }

    public void apply(Player player) {
        player.stats.increaseArmor(armor);
    }
}
