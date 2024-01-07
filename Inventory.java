import java.util.*;

public class Inventory {
    private final ArrayList<InventoryItem> inventory = new ArrayList<>();

    public void addItem(Item item, int amount, Player player) {
        if (!(item instanceof ItemConsumable)) {
            item.apply(player);
        }

        for (InventoryItem inventoryItem : inventory) {
            if (item == inventoryItem.item) {
                inventoryItem.amount += amount;

                return;
            }
        }
        inventory.add(new InventoryItem(item, amount));
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public static class InventoryItem {
        public final Item item;
        private int amount;

        private InventoryItem(Item item, int amount) {
            this.item = item;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }
}
