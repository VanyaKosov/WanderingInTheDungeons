import java.util.*;

/**
 * Controls the game
 * 
 * @author Ivan Kosov
 */
public class LevelController {
    private final Random random = new Random();
    private final Input input;
    private final Player player;
    private final Dungeon dungeon;
    private final Display display;
    private final BattleController battleController;
    private final InventoryController invController;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();;

    /**
     * Initializes the fields and spawns enemies
     * 
     * @param input is the instance of the Input class
     * @param player is the instance of the Player class
     * @param dungeon is the instance of the Dungeon class
     * @param display is the instance of the Display class
     * @param battleController is the instance of the BattleController class
     * @param invController is the instance of the InventoryController class
     */
    public LevelController(Input input, Player player, Dungeon dungeon, Display display,
            BattleController battleController, InventoryController invController) {
        this.input = input;
        this.player = player;
        this.dungeon = dungeon;
        this.display = display;
        this.battleController = battleController;
        this.invController = invController;

        addEnemies();

        displayField();
    }

    /**
     * Updates the game every turn
     */
    public void run() {
        while (!checkForVictory()) {
            var allKeys = input.readKey();

            boolean toExit = false;
            for (Input.Keys key : allKeys) {
                if (key == Input.Keys.MENU) {
                    toExit = true;
                    break;
                }

                if (key == Input.Keys.INVENTORY) {
                    invController.accessInventory();
                    displayField();
                    break;
                }

                if (key != Input.Keys.SKIP) {
                    player.movePlayer(key);
                }

                for (int i = 0; i < enemies.size(); i++) {
                    var enemy = enemies.get(i);

                    enemy.move();

                    if (!enemy.getPos().equals(player.getPos())) {
                        continue;
                    }

                    int battleResult = battleController.fight(enemy);
                    if (battleResult == 1) {
                        enemies.remove(i);
                    }
                    if (battleResult == -1) {
                        display.drawWaitForEnter();
                        input.waitForEnter();

                        return;
                    }
                }

                displayField();
                var item = player.checkForItem();
                if (item != null) {
                    display.drawItemPickUp(item);
                }
            }

            if (toExit) {
                break;
            }
        }
    }

    /**
     * Checks if the player has escaped through the portal
     * 
     * @return true if won, false if didn't
     */
    private boolean checkForVictory() {
        var playerPos = player.getPos();
        if (dungeon.getCell(playerPos.row, playerPos.col) == Cells.EXIT) {
            display.drawVictory();

            display.drawWaitForEnter();
            input.waitForEnter();

            return true;
        }

        return false;
    }

    /**
     * Adds random enemies
     */
    private void addEnemies() {
        for (int i = 0; i < dungeon.getAmountOfEnemies(); i++) {
            enemies.add(getRandomEnemy());
        }
    }

    /**
     * Creates a random enemy
     * 
     * @return a new instance of one of the classes that inherit Enemy class with random position
     */
    private Enemy getRandomEnemy() {
        Pos enemyPos;
        while (true) {
            int row = random.nextInt(dungeon.getHeight());
            int col = random.nextInt(dungeon.getWidth());
            if (dungeon.getCell(row, col) != Cells.EMPTY) {
                continue;
            }
            enemyPos = new Pos(row, col);
            break;
        }

        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return new MonsterOgre(dungeon, player, 3, enemyPos);
            case 1:
                return new MonsterGoblin(dungeon, player, 4, enemyPos);
            case 2:
                return new MonsterSkeleton(dungeon, player, 3, enemyPos);
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Processes a piece of map around the player, and displays it
     */
    private void displayField() {
        var viewRadius = player.getViewRadius();
        var fogOfWarRadius = player.getFogOfWarRadius();
        Cells[][] visibleMap = new Cells[(fogOfWarRadius + viewRadius) * 2 + 1][(fogOfWarRadius + viewRadius) * 2 + 1];

        int totalViewArea = viewRadius + fogOfWarRadius;
        int leftCol = player.getPos().col - totalViewArea;
        int rightCol = player.getPos().col + totalViewArea;
        int topRow = player.getPos().row - totalViewArea;
        int bottomRow = player.getPos().row + totalViewArea;
        Pos visibleMapPlayerPos = new Pos(visibleMap.length / 2, visibleMap.length / 2);

        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {
                if (row < 0 || col < 0 || row >= dungeon.getHeight() || col >= dungeon.getWidth()) {
                    visibleMap[row - topRow][col - leftCol] = Cells.UNDISCOVERED;
                    continue;
                }

                visibleMap[row - topRow][col - leftCol] = dungeon.getCell(row, col);

                dungeon.setVisitedCell(player.getPos().row, player.getPos().col);
            }
        }

        if (!player.hasXrayGlasses()) {
            for (int col = 0; col < visibleMap[0].length; col++) {
                int firstRow = 0;
                int lastRow = visibleMap.length - 1;

                hideInvisibleCells(visibleMap, new Pos(firstRow, col), visibleMapPlayerPos);
                hideInvisibleCells(visibleMap, new Pos(lastRow, col), visibleMapPlayerPos);
            }

            for (int row = 0; row < visibleMap.length; row++) {
                int firstCol = 0;
                int lastCol = visibleMap[0].length - 1;

                hideInvisibleCells(visibleMap, new Pos(row, firstCol), visibleMapPlayerPos);
                hideInvisibleCells(visibleMap, new Pos(row, lastCol), visibleMapPlayerPos);
            }

            for (int row = 0; row < visibleMap.length; row++) {
                for (int col = 0; col < visibleMap[0].length; col++) {
                    if (topRow + row < 0 || leftCol + col < 0 || topRow + row >= dungeon.getHeight()
                            || leftCol + col >= dungeon.getWidth()) {
                        continue;
                    }
                    if (!dungeon.getVisitedCell(topRow + row, leftCol + col)) {
                        visibleMap[row][col] = Cells.UNDISCOVERED;
                    }
                }
            }
        }

        drawEnemies(visibleMap, visibleMapPlayerPos);
        visibleMap[visibleMapPlayerPos.row][visibleMapPlayerPos.col] = Cells.PLAYER;

        display.draw(visibleMap);
    }

    /**
     * Draws enemies on a given map
     * 
     * @param visibleMap is a map to draw enemies on to
     * @param visibleMapPlayerPos is the position of the player on a given map
     */
    private void drawEnemies(Cells[][] visibleMap, Pos visibleMapPlayerPos) {
        Pos offset = player.getPos().sub(visibleMapPlayerPos);

        for (Enemy enemy : enemies) {
            Pos enemyPos = enemy.getPos().sub(offset);
            if (enemyPos.row > 0 && enemyPos.col > 0
                    && enemyPos.row < visibleMap.length && enemyPos.col < visibleMap.length
                    && visibleMap[enemyPos.row][enemyPos.col] != Cells.INVISIBLE
                    && visibleMap[enemyPos.row][enemyPos.col] != Cells.UNDISCOVERED) {
                visibleMap[enemyPos.row][enemyPos.col] = Cells.ENEMY;
            }
        }
    }

    /**
     * Uses DDA (Digital Differential Analyzer) algorithm to hide the cells which player can't see through walls and doors
     * 
     * @param visibleMap is the piece of map to process
     * @param edgePos is the position of a cell on the edge of the map
     * @param playerPos is the position of the player
     */
    private void hideInvisibleCells(Cells[][] visibleMap, Pos edgePos, Pos playerPos) {
        int deltaCol = edgePos.col - playerPos.col;
        int deltaRow = edgePos.row - playerPos.row;
        int stepsAmount;

        if (Math.abs(deltaCol) > Math.abs(deltaRow)) {
            stepsAmount = Math.abs(deltaCol);
        } else {
            stepsAmount = Math.abs(deltaRow);
        }

        float colIncrement = deltaCol / (float) stepsAmount;
        float rowIncrement = deltaRow / (float) stepsAmount;

        boolean visible = true;
        float col = playerPos.col;
        float row = playerPos.row;
        Pos offset = player.getPos().sub(playerPos);
        for (int i = 0; i < stepsAmount; i++) {
            col += colIncrement;
            row += rowIncrement;

            int roundedCol = Math.round(col);
            int roundedRow = Math.round(row);
            var currentCell = visibleMap[roundedRow][roundedCol];
            if (visible) {
                if (currentCell == Cells.WALL || currentCell == Cells.DOOR) {
                    visible = false;
                }
                int viewRadius = player.getViewRadius();
                if (roundedRow < playerPos.row - viewRadius + 1 || roundedCol < playerPos.col - viewRadius + 1
                        || roundedRow > playerPos.row + viewRadius - 1 || roundedCol > playerPos.col + viewRadius - 1) {
                    visible = false;
                }

                if (offset.row + roundedRow < 0 || offset.col + roundedCol < 0
                        || offset.row + roundedRow >= dungeon.getHeight()
                        || offset.col + roundedCol >= dungeon.getWidth()) {
                    continue;
                }
                dungeon.setVisitedCell(offset.row + roundedRow, offset.col + roundedCol);

                continue;
            }

            if (currentCell != Cells.UNDISCOVERED && currentCell != Cells.WALL && currentCell != Cells.DOOR) {
                visibleMap[roundedRow][roundedCol] = Cells.INVISIBLE;
            }
        }
    }
}
