
/**
 * Write a description of class Dungeon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dungeon {
    private String[] testInputMap = {
            "############",
            "#         ##",
            "##### # #  F",
            "#   # # ####",
            "# # ###   ##",
            "#S#     #  #",
            "############"
    };
    private Cells[][] map;
    private boolean[][] visitedCells;
    private Pos playerPos;

    public Dungeon() {
        map = new Cells[testInputMap.length][testInputMap[0].length()];
        visitedCells = new boolean[map.length][map[0].length];
        for (int row = 0; row < visitedCells.length; row++) {
            for (int col = 0; col < visitedCells[0].length; col++) {
                visitedCells[row][col] = false;
            }
        }

        for (int row = 0; row < testInputMap.length; row++) {
            for (int col = 0; col < testInputMap[0].length(); col++) {
                char currentCell = testInputMap[row].charAt(col);
                switch (currentCell) {
                    case '#':
                        map[row][col] = Cells.WALL;
                        break;
                    case ' ':
                        map[row][col] = Cells.EMPTY;
                        break;
                    case 'S':
                        map[row][col] = Cells.PLAYER;
                        playerPos = new Pos(row, col);
                        break;
                    case 'F':
                        map[row][col] = Cells.EXIT;
                        break;
                }
            }
        }
    }

    // TODO: remove player from dungeon
    public void movePlayer(Input.Keys direction) {
        int row = playerPos.row;
        int col = playerPos.col;
        switch (direction) {
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

        map[playerPos.row][playerPos.col] = Cells.EMPTY;
        playerPos.row = row;
        playerPos.col = col;
        map[playerPos.row][playerPos.col] = Cells.PLAYER;
    }

    public Cells getCell(int row, int col) {
        return map[row][col];
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

    public Pos getPlayerPos() {
        return playerPos;
    }
}
