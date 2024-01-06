public abstract class Item {
    public final String description;

    public Item(String description) {
        this.description = description;
    }

    public abstract void apply(Player player);
}
