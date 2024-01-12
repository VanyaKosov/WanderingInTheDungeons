import java.nio.file.*;
import java.util.*;

/**
 * Write a description of class Display here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Display {
    public Display() {

    }

    public void drawVictory() {
        System.out.print('\u000C');
        
        System.out.println("You escaped!\n");
    }

    public void drawDefeat() {
        System.out.print('\u000C');
        
        System.out.println("You died!\n");
    }
    
    public void drawWaitForEnter() {
        System.out.println("Press enter to continue\n");
    }
    
    public void drawItemPickUp(Item item) { // TODO fix displaying this
        System.out.println("You've found " + item.name);
        System.out.println(item.description);
    }
    
    public void drawChooseWeapon(Inventory.Category<ItemWeapon> weapons) {
        System.out.println("Please choose a weapon:");
        int counter = 1;
        for (Inventory.InventoryItem<ItemWeapon> weapon : weapons.items) {
            System.out.println(counter + ": " + weapon.item.name);
            counter++;
            System.out.println("   Damage: " + weapon.item.damage);
        }
    }

    public void drawMainInventory(Player player) {
        System.out.print('\u000C');
        
        System.out.println("Your stats:");
        System.out.println("Health: " + player.stats.getHealth() + "/" + player.stats.getMaxHealth());
        System.out.println("Armour: " + player.stats.getArmour());
        System.out.println("Strength: " + player.stats.getArmour());
        System.out.println();

        System.out.println("Which category of items would you like to see?");
        System.out.println("1: consumables");
        System.out.println("2: weapons");
        System.out.println("3: other items");

        System.out.println("\n0: to exit");
    }

    public void drawConsumablesInventory(Inventory.Category<ItemConsumable> category) {
        System.out.print('\u000C');
        
        int counter = 1;
        System.out.println("  Consumables: ");
        for (Inventory.InventoryItem<ItemConsumable> consumable : category.items) {
            System.out.println("   " + counter + ":" + consumable.item.name + " x" + consumable.getAmount());
            System.out.println("    " + consumable.item.description);

            counter++;
        }
        System.out.println("   0: to exit");
        System.out.println();
    }

    public void drawWeaponsInventory(Inventory.Category<ItemWeapon> category) {
        System.out.print('\u000C');
        
        int counter = 1;
        System.out.println("  Weapons: ");
        for (Inventory.InventoryItem<ItemWeapon> weapon : category.items) {
            System.out.println("   " + counter + ":" + weapon.item.name + " x" + weapon.getAmount());
            System.out.println("    " + weapon.item.description);
            System.out.println("    Damage: " + weapon.item.damage);

            counter++;
        }

        System.out.println("   0: to exit");
        System.out.println();
    }

    public void drawOtherItemsInventory(Inventory.Category<Item> category) {
        System.out.print('\u000C');
        
        int counter = 1;
        System.out.println("  Other items: ");
        for (Inventory.InventoryItem<Item> other : category.items) {
            System.out.println("   " + counter + ":" + other.item.name + " x" + other.getAmount());
            System.out.println("    " + other.item.description);

            counter++;
        }

        System.out.println("   0: to exit");
        System.out.println();
    }

    public void drawBattleMenu(Player player, Enemy enemy) {
        System.out.print('\u000C');
        
        System.out.println("Your stats:\t\t\t\t" + enemy.name + "'s stats:");
        System.out
                .println("Health: " + player.stats.getHealth() + "/" + player.stats.getMaxHealth() + "\t\t\t\tHealth: "
                        + enemy.stats.getHealth() + "/" + enemy.stats.getMaxHealth());
        System.out.println("Armour: " + player.stats.getArmour() + "\t\t\t\tArmour: " + enemy.stats.getArmour());
        System.out
                .println("Strength: " + player.stats.getStrength() + "\t\t\t\tStrength: " + enemy.stats.getStrength());
        System.out.print("\n\n\n\n\n");
        System.out.println("Please type:");
        System.out.println("1: to attack\t 2: to open inventory\t 3: to run away");
    }

    public void drawRunAwaySuccessful() {
        System.out.print('\u000C');
        
        System.out.println("You successfully ran away.");
    }

    public void drawRunAwayFailed() {
        System.out.print('\u000C');
        
        System.out.println("You couldn't run away");
    }

    public void drawEnemyAttackPreparation(String attackDescription, Enemy.Defense[] defenses) {
        System.out.print('\u000C');
        
        System.out.println(attackDescription);
        System.out.println("\nPlease type: ");
        int counter = 1;
        for (int i = 0; i < defenses.length; i++) {
            System.out.println(counter + ": to " + defenses[i].description);
            counter++;
        }
        System.out.println();
    }

    public void drawEnemyAttack(String resultDescription) {
        System.out.print('\u000C');
        
        System.out.println(resultDescription);
        System.out.println();
    }

    public void drawPlayerAttack(int damage, int remainingHealth, String enemyName) {
        System.out.print('\u000C');
        
        System.out.println(
                "You dealt " + damage + " damage. The " + enemyName + "'s remaining health is: " + remainingHealth);
        System.out.println();
    }

    public void drawWonBattle(String name) {
        System.out.println("You've defeated the " + name);
        System.out.println();
    }

    public void drawMainMenu(ArrayList<Path> paths, String extension) {
        System.out.print('\u000C');
        
        System.out.println("Please type:");
        System.out.println("0: To exit the program");
        int counter = 1;
        for (Path path : paths) {
            String name = path.toFile().getName();
            System.out.print(counter + ": To open ");
            System.out.println(name.substring(0, name.length() - extension.length()));
            counter++;
        }
    }

    public void draw(Cells[][] map) {
        System.out.print('\u000C');
        
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                switch (map[row][col]) {
                    case UNDISCOVERED:
                        System.out.print(". ");
                        break;
                    case INVISIBLE:
                        System.out.print("? ");
                        break;
                    case WALL:
                        System.out.print("# ");
                        break;
                    case EMPTY:
                        System.out.print("  ");
                        break;
                    case EMPTY_DO_NOT_TAKE:
                        System.out.print("  ");
                        break;
                    case DOOR:
                        System.out.print("/ ");
                        break;
                    case PLAYER:
                        System.out.print("@ ");
                        break;
                    case ENEMY:
                        System.out.print("& ");
                        break;
                    case EXIT:
                        System.out.print("O ");
                        break;
                    case CANDLE:
                        System.out.print("i ");
                        break;
                    case TEST_SWORD:
                        System.out.print("! ");
                        break;
                    case RUSTY_SWORD:
                        System.out.print("! ");
                        break;
                    case XRAY_GLASSES:
                        System.out.print("X ");
                        break;
                    case HEALTH_POTION:
                        System.out.print("* ");
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            System.out.println();
        }
    }
}
