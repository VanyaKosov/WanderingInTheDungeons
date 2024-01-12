public abstract class ItemArmour extends Item{
    public final int armour;
    
    public ItemArmour(String name, String description, int armour) {
        super(name, description);
        
        this.armour = armour;
    }
    
    public void apply(Player player) {
        player.stats.increaseArmour(armour);
    }
}
