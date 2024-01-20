import java.util.*;

/**
 * Stores the inventory
 * 
 * @author Ivan Kosov
 */
public class Inventory {
    public final Category<ItemWeapon> weaponItems = new Category<>();
    public final Category<ItemConsumable> consumableItems = new Category<>();
    public final Category<ItemArmor> armorItems = new Category<>();
    public final Category<Item> otherItems = new Category<>();

    /**
     * Removes a given amount of a certain item from the inventory
     * 
     * @param item is the item you want to remove
     * @param amount is the amount to remove
     * @param player is an instance of the Player class
     */
    public void removeItem(Item item, int amount, Player player) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        if (item instanceof ItemWeapon weapon) {
            weaponItems.removeItem(weapon, amount);
        } else if (item instanceof ItemConsumable consumable) {
            consumableItems.removeItem(consumable, amount);
        } else if (item instanceof ItemArmor armor) {
            armorItems.removeItem(armor, amount);
        } else {
            otherItems.removeItem(item, amount);
        }
    }

    /**
     * Adds a given amount of a certain item to the inventory
     * 
     * @param item is the item you want to add
     * @param amount is the amount to add
     * @param player is an instance of the Player class
     */
    public void addItem(Item item, int amount, Player player) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        if (!(item instanceof ItemConsumable)) {
            item.apply(player);
        }

        if (item instanceof ItemWeapon weapon) {
            weaponItems.addItem(weapon, amount);
        } else if (item instanceof ItemConsumable consumable) {
            consumableItems.addItem(consumable, amount);
        } else if (item instanceof ItemArmor armor) {
            armorItems.addItem(armor, amount);
        } else {
            otherItems.addItem(item, amount);
        }
    }

    /**
     * A category of items.
     * Has an ArrayList of the items of items in it
     */
    public static class Category<T extends Item> {
        public final ArrayList<InventoryItem<T>> items = new ArrayList<>();

        /**
         * Adds a certain amount of an item to the ArrayList of items
         * 
         * @param item is the item you want to add
         * @param amount is the amount to add
         */
        private void addItem(T item, int amount) {
            for (InventoryItem<T> inventoryItem : items) {
                if (item == inventoryItem.item) {
                    inventoryItem.amount += amount;

                    return;
                }
            }
            items.add(new InventoryItem<T>(item, amount));
        }

        /**
         * Removes a certain amount of an item from the ArrayList of items
         * 
         * @param item is the item you want to remove
         * @param amount is the amount to remove
         */
        private void removeItem(T item, int amount) {
            for (int i = 0; i < items.size(); i++) {
                var inventoryItem = items.get(i);
                if (item == inventoryItem.item) {
                    if (inventoryItem.amount - amount < 0) {
                        throw new IllegalArgumentException();
                    }
                    inventoryItem.amount -= amount;

                    if (inventoryItem.amount == 0) {
                        items.remove(i);
                    }

                    return;
                }
            }
            throw new IllegalArgumentException("Item not found");
        }
    }

    /**
     * Stores the item and it's amount
     */
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
