
/**
 * Write a description of class Dungeon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dungeon
{
    private String[] testInputMap = {"############",
                                     "#         ##",
                                     "##### # #   ",
                                     "#   # # ####",
                                     "# # ###   ##",
                                     "#@#     #  #",
                                     "############"};
    public Cells[][] map;
    public Player player;
    
    public Dungeon() {
        map = new Cells[testInputMap.length][testInputMap[0].length()];
        player = new Player();
        
        for (int row = 0; row < testInputMap.length; row++) {
            for (int col = 0; col < testInputMap[0].length(); col++) {
                char currentCell = testInputMap[row].charAt(col);
                switch (currentCell) {
                    case '#': map[row][col] = Cells.WALL;
                              break;
                    case ' ': map[row][col] = Cells.EMPTY;
                              break;
                    case '@': map[row][col] = Cells.PLAYER;
                              break;
                }
            }
        }
    }
}
