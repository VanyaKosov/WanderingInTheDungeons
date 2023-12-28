public class LevelController implements Input.InputEvents {
    public final Player player;
    public final Dungeon dungeon;
    public final Display display;

    public LevelController(Player player, Dungeon dungeon, Display display) {
        this.player = player;
        this.dungeon = dungeon;
        this.display = display;

        displayField();
    }

    @Override
    public void onKeyPress(Input.Keys key) {
        if (!canMovePlayer(key)) {
            return;
        }

        dungeon.movePlayer(key);
        displayField();
    }

    private void displayField() {
        var viewRadius = player.getViewRadius();
        var fogOfWarRadius = player.getFogOfWarRadius();
        Cells[][] visibleMap = new Cells[(fogOfWarRadius + viewRadius) * 2 + 1][(fogOfWarRadius + viewRadius) * 2 + 1];

        int totalViewArea = viewRadius + fogOfWarRadius;
        int leftCol = dungeon.getPlayerPos().col - totalViewArea;
        int rightCol = dungeon.getPlayerPos().col + totalViewArea;
        int topRow = dungeon.getPlayerPos().row - totalViewArea;
        int bottomRow = dungeon.getPlayerPos().row + totalViewArea;
        Pos visibleMapPlayerPos = new Pos(visibleMap.length - totalViewArea - 1,
                visibleMap.length - totalViewArea - 1);

        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {
                if (row < 0 || col < 0 || row >= dungeon.getHeight() || col >= dungeon.getWidth()) {
                    visibleMap[row - topRow][col - leftCol] = Cells.UNDISCOVERED;
                    continue;
                }

                visibleMap[row - topRow][col - leftCol] = dungeon.getCell(row, col);

                dungeon.setVisitedCell(dungeon.getPlayerPos().row, dungeon.getPlayerPos().col);
                /*if (row >= topRow + fogOfWarRadius && row <= bottomRow - fogOfWarRadius
                        && col >= leftCol + fogOfWarRadius && col <= rightCol - fogOfWarRadius) {
                    dungeon.setVisitedCell(row, col);
                }*/
            }
        }

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
        Pos offset = dungeon.getPlayerPos().sub(playerPos);
        for (int i = 0; i < stepsAmount; i++) {
            col += colIncrement;
            row += rowIncrement;

            int roundedCol = Math.round(col);
            int roundedRow = Math.round(row);
            var currentCell = visibleMap[roundedRow][roundedCol];
            if (visible) {
                if (currentCell == Cells.WALL) {
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

            if (currentCell != Cells.UNDISCOVERED && currentCell != Cells.WALL) {
                visibleMap[roundedRow][roundedCol] = Cells.INVISIBLE;
            }
        }
    }

    public boolean canMovePlayer(Input.Keys key) {
        int row = dungeon.getPlayerPos().row;
        int col = dungeon.getPlayerPos().col;
        switch (key) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                break;
        }

        if (dungeon.getCell(row, col) == Cells.WALL) {
            return false;
        }

        return true;
    }

}
