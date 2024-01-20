/**
 * The basic class inherited by all items
 * 
 * @author Ivan Kosov
 */
public abstract class Item {
    public final String name;
    public final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Applies the effect of the item to the player
     * 
     * @param player is the instance of the Player class
     */
    public abstract void apply(Player player);
}
