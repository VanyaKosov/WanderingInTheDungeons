
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
    private Pos playerPos;

    public Dungeon() {
        map = new Cells[testInputMap.length][testInputMap[0].length()];

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

    public Cells[][] getMap() {
        return map;
    }

    public Pos getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(Pos playerPos) {
        this.playerPos = playerPos;
    }
}
