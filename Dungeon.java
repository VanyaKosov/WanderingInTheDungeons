
/**
 * Write a description of class Dungeon here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Dungeon {
    private final Cells[][] map;
    private final boolean[][] visitedCells;
    private final Pos startPlayerPos;

    public Dungeon(String[] inputMap) {
        map = new Cells[inputMap.length][inputMap[0].length()];
        visitedCells = new boolean[map.length][map[0].length];
        for (int row = 0; row < visitedCells.length; row++) {
            for (int col = 0; col < visitedCells[0].length; col++) {
                visitedCells[row][col] = false;
            }
        }

        Pos playerPos = null;
        for (int row = 0; row < inputMap.length; row++) {
            for (int col = 0; col < inputMap[0].length(); col++) {
                char currentCell = inputMap[row].charAt(col);
                switch (currentCell) {
                    case '#':
                        map[row][col] = Cells.WALL;
                        break;
                    case ' ':
                        map[row][col] = Cells.EMPTY;
                        break;
                    case 'S':
                        map[row][col] = Cells.EMPTY;
                        if (playerPos != null) {
                            throw new IllegalStateException();
                        }
                        playerPos = new Pos(row, col);
                        break;
                    case 'F':
                        map[row][col] = Cells.EXIT;
                        break;
                    case 'i':
                        map[row][col] = Cells.CANDLE;
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }

        if (playerPos == null) {
            throw new IllegalStateException();
        }
        startPlayerPos = playerPos;
    }

    public Cells getCell(int row, int col) {
        return map[row][col];
    }

    public void setCell(Cells cell, int row, int col) {
        map[row][col] = cell;
    }

    public boolean getVisitedCell(int row, int col) {
        return visitedCells[row][col];
    }

    public void setVisitedCell(int row, int col) {
        visitedCells[row][col] = true;
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public Pos getStartPlayerPos() {
        return startPlayerPos;
    }
}
