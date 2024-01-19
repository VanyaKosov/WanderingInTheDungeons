import java.util.*;

/**
 * Controls all of the battles
 * 
 * @author Ivan Kosov
 */
public class BattleController {
    private final Random random = new Random();
    private final Display display;
    private final Player player;
    private final Input input;
    private final InventoryController invController;

    /**
     * Initializes the fields
     * 
     * @param display is the instance of the Display class
     * @param player is the instance of the Player class
     * @param input is the instance of the Input class
     * @param invController is the instance of the InventoryController class
     */
    public BattleController(Display display, Player player, Input input, InventoryController invController) {
        this.display = display;
        this.player = player;
        this.input = input;
        this.invController = invController;
    }

    /**
     * Controls the fight between player and an enemy
     * 
     * @param enemy is the enemy player fights
     * @return the result of the battle (1 if player won, -1 if lost, and 0 if player ran away)
     */
    public int fight(Enemy enemy) {
        display.drawBattleIntroduction(enemy);
        input.waitForEnter();

        while (true) {
            display.drawBattleMenu(player, enemy);
            int answer = input.readNumber(1, 3);
            switch (answer) {
                case 1:
                    if (playerAttack(enemy) == 1) {
                        display.drawWaitForEnter();
                        input.waitForEnter();
                        return 1;
                    }
                    display.drawWaitForEnter();
                    input.waitForEnter();
                    if (enemyAttack(enemy) == -1) {
                        return -1;
                    }
                    break;
                case 2:
                    invController.accessInventory();
                    if (enemyAttack(enemy) == -1) {
                        return -1;
                    }
                    break;
                case 3:
                    if (runAway(enemy)) {
                        display.drawWaitForEnter();
                        input.waitForEnter();
                        return 0;
                    }
                    if (enemyAttack(enemy) == -1) {
                        return -1;
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    /**
     * Asks the player to choose the weapon
     * 
     * @return damage of the chosen weapon
     */
    private int chooseWeaponDamage() {
        var allWeapons = player.inventory.weaponItems;
        display.drawChooseWeapon(allWeapons);
        int answer = input.readNumber(1, allWeapons.items.size());
        return allWeapons.items.get(answer - 1).item.getDamage();
    }

    /**
     * Player attacks the enemy
     * 
     * @param enemy is the enemy player fights
     * @return the result of the attack (1 if player won, and 2 if battle is still going)
     */
    private int playerAttack(Enemy enemy) {
        int weaponDamage = chooseWeaponDamage();
        int damage = enemy.stats.damage(player.stats.getStrength() + weaponDamage);
        display.drawPlayerAttack(damage, enemy.stats.getHealth(), enemy.name);
        if (!enemy.stats.alive()) {
            display.drawWonBattle(enemy.name);
            return 1;
        }

        return 2;
    }

    /**
     * Enemy attacks player
     * 
     * @param enemy is the enemy player fights
     * @return the result of the attack (-1 if player lost, and 2 if battle is still going)
     */
    private int enemyAttack(Enemy enemy) {
        var attack = enemy.attacks.get(random.nextInt(enemy.attacks.size()));
        display.drawEnemyAttackPreparation(attack.description, attack.defenses);
        int answer = input.readNumber(1, attack.defenses.length);
        var defense = attack.defenses[answer - 1];
        display.drawEnemyAttack(defense.resultDescription);
        display.drawWaitForEnter();
        input.waitForEnter();

        player.stats.damage(defense.damage);
        if (!player.stats.alive()) {
            display.drawDefeat();
            return -1;
        }

        return 2;
    }

    /**
     * Decides if the player ran away or not (2 in 3 chance)
     * 
     * @param enemy is the enemy player fights
     * @return true if successfully ran away, and false if didn't
     */
    private boolean runAway(Enemy enemy) {
        if (random.nextInt(3) != 0) {
            display.drawRunAwaySuccessful();
            enemy.skipTurns(1);
            return true;
        }

        display.drawRunAwayFailed();
        return false;
    }
}