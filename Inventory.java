import java.util.*;

public class Inventory {
    public final Category<ItemWeapon> weaponItems = new Category<>();
    public final Category<ItemConsumable> consumableItems = new Category<>();
    public final Category<Item> otherItems = new Category<>();

    public void addItem(Item item, int amount, Player player) {
        if (!(item instanceof ItemConsumable)) {
            item.apply(player);
        }

        if (item instanceof ItemWeapon weapon) {
            weaponItems.addItem(weapon, amount);
        } else if (item instanceof ItemConsumable consumable) {
            consumableItems.addItem(consumable, amount);
        } else {
            otherItems.addItem(item, amount);
        }
    }

    public static class Category<T extends Item> {
        public final ArrayList<InventoryItem<T>> items = new ArrayList<>();

        private void addItem(T item, int amount) {
            for (InventoryItem<T> inventoryItem : items) {
                if (item == inventoryItem.item) {
                    inventoryItem.amount += amount;

                    return;
                }
            }
            items.add(new InventoryItem<T>(item, amount));
        }
    }

    public static class InventoryItem<T extends Item> {
        public final T item;
        private int amount;

        private InventoryItem(T item, int amount) {
            this.item = item;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }
}
