import java.util.*;

public class BattleController {
    private final Random random = new Random();
    private final Display display;
    private final Player player;
    private final Input input;
    private final InventoryController invController;

    public BattleController(Display display, Player player, Input input, InventoryController invController) {
        this.display = display;
        this.player = player;
        this.input = input;
        this.invController = invController;
    }

    public int fight(Enemy enemy) { // returns 1 if player won, -1 if lost, and 0 if player ran away.
        while (true) {
            display.drawBattleMenu(player, enemy);
            int answer = input.readNumber(1, 3);
            switch (answer) {
                case 1:
                    if (playerAttack(enemy) == 1) {
                        return 1;
                    }
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

    private int playerAttack(Enemy enemy) { // returns 1 if player won, and 2 if battle is still going.
        int damage = enemy.stats.damage(player.stats.getStrength());
        display.drawPlayerAttack(damage, enemy.stats.getHealth(), enemy.name);
        if (!enemy.stats.alive()) {
            display.drawWonBattle(enemy.name);
            return 1;
        }

        return 2;
    }

    private int enemyAttack(Enemy enemy) { // -1 if player lost, and 2 if battle is still going.
        var attack = enemy.attacks.get(random.nextInt(enemy.attacks.size()));
        display.drawEnemyAttackPreparation(attack.description, attack.defenses);
        int answer = input.readNumber(1, attack.defenses.length);
        var defense = attack.defenses[answer - 1];
        display.drawEnemyAttack(defense.resultDescription);

        player.stats.damage(defense.damage);
        if (!player.stats.alive()) {
            display.drawDefeat();
            return -1;
        }

        return 2;
    }

    private boolean runAway(Enemy enemy) { // returns true if ran away, and false if didn't
        if (random.nextInt(3) != 0) {
            display.drawRunAwaySuccessful();
            enemy.skipTurns(1);
            return true;
        }

        display.drawRunAwayFailed();
        return false;
    }
}