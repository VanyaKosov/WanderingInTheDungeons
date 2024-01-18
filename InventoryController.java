public class InventoryController {
    private final Player player;
    private final Display display;
    private final Input input;

    public InventoryController(Player player, Display display, Input input) {
        this.player = player;
        this.display = display;
        this.input = input;
    }

    public void accessInventory() {
        while (true) {
            display.drawMainInventory(player);
            int answer = input.readNumber(0, 4);
            switch (answer) {
                case 0:
                    return;
                case 1:
                    accessConsumables();
                    break;
                case 2:
                    accessWeapons();
                    break;
                case 3:
                    accessArmor();
                    break;
                case 4:
                    accessOtherItems();
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private void accessConsumables() {
        while (true) {
            display.drawConsumablesInventory(player.inventory.consumableItems);
            int answer = input.readNumber(0, player.inventory.consumableItems.items.size());
            if (answer == 0) {
                return;
            }
            var item = player.inventory.consumableItems.items.get(answer - 1).item;
            item.apply(player);
            player.inventory.removeItem(item, 1, player);
        }
    }

    private void accessWeapons() {
        display.drawWeaponsInventory(player.inventory.weaponItems);
        int answer = input.readNumber(0, 0);
        if (answer == 0) {
            return;
        }
    }

    private void accessArmor() {
        display.drawArmorInventory(player.inventory.armorItems);
        int answer = input.readNumber(0, 0);
        if (answer == 0) {
            return;
        }
    }

    private void accessOtherItems() {
        while (true) {
            display.drawOtherItemsInventory(player.inventory.otherItems);
            int answer = input.readNumber(0, player.inventory.otherItems.items.size());
            if (answer == 0) {
                return;
            }
        }
    }
}
