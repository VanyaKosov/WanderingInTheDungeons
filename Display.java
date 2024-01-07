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
        System.out.println("You escaped!");
    }

    public void drawDefeat() {
        System.out.println("You died!");
    }

    public void drawInventory(Player player) {
        System.out.println("Your stats:");
        System.out.println("Health: " + player.stats.getHealth() + "/" + player.stats.getMaxHealth());
        System.out.println("Armour: " + player.stats.getArmour());
        System.out.println("Strength: " + player.stats.getArmour());
        System.out.println();

        System.out.println("Your items:");
        System.out.println("  Weapons: ");
        for (Item item : player.getInventory()) { // To Do sort by categories first
            if (item instanceof ItemWeapon weapon) {
                System.out.println("  >" + weapon.name);
                System.out.println("   " + weapon.description);
                System.out.println("   Damage: " + weapon.damage);
            }
            // if (item instanceof ItemLight)
        }

    }

    public void drawBattleMenu(Player player, Enemy enemy) {
        System.out.println("Your stats:\t\t\t\t" + enemy.name + "'s stats:");
        System.out
                .println("Health: " + player.stats.getHealth() + "/" + player.stats.getMaxHealth() + "\t\t\t\tHealth: "
                        + enemy.stats.getHealth() + "/" + enemy.stats.getMaxHealth());
        System.out.println("Armour: " + player.stats.getArmour() + "\t\t\t\tArmour: " + enemy.stats.getArmour());
        System.out
                .println("Strength: " + player.stats.getStrength() + "\t\t\t\tStrength: " + enemy.stats.getStrength());
        System.out.print("\n\n\n\n\n");
        System.out.println("Please type:");
        System.out.println("1: To attack\t 2: To use an item\t 3: To run away");
    }

    public void drawRunAwaySuccessful() {
        System.out.println("You successfully ran away.");
    }

    public void drawRunAwayFailed() {
        System.out.println("You couldn't run away");
    }

    public void drawEnemyAttackPreparation(String attackDescription, Enemy.Defense[] defenses) {
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
        System.out.println(resultDescription);
        System.out.println();
    }

    public void drawPlayerAttack(int damage, int remainingHealth, String enemyName) {
        System.out.println(
                "You dealt " + damage + " damage. The " + enemyName + "'s remaining health is: " + remainingHealth);
        System.out.println();
    }

    public void drawWonBattle(String name) {
        System.out.println("You've defeated the " + name);
    }

    public void drawMainMenu(ArrayList<Path> paths, String extension) {
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
                    case XRAY_GLASSES:
                        System.out.print("X ");
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            System.out.println();
        }
    }
}
