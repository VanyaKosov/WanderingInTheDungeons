public abstract class Item {
    public final String name;
    public final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void apply(Player player);
}
