public class LevelController {
    public final Input input;
    public final Player player;
    public final Dungeon dungeon;
    public final Display display;

    public LevelController(Input input, Player player, Dungeon dungeon, Display display) {
        this.input = input;
        this.player = player;
        this.dungeon = dungeon;
        this.display = display;

        displayField();
    }

    public void run() {
        while (!checkForVictory()) {
            var allKeys = input.readKey();

            boolean toExit = false;
            for (Input.Keys key : allKeys) {
                if (key == Input.Keys.MENU) {
                    toExit = true;
                    break;
                }

                player.movePlayer(key);
                displayField();
            }

            if (toExit) {
                break;
            }
        }
    }

    private boolean checkForVictory() {
        var playerPos = player.getPos();
        if (dungeon.getCell(playerPos.row, playerPos.col) == Cells.EXIT) {
            display.drawVictory();
            return true;
        }

        return false;
    }

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

        visibleMap[visibleMapPlayerPos.row][visibleMapPlayerPos.col] = Cells.PLAYER;

        display.draw(visibleMap);
    }

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
