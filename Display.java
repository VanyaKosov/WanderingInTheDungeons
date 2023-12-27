
/**
 * Write a description of class Display here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Display {
    public Display() {

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
                    case PLAYER:
                        System.out.print("@ ");
                        break;
                    case EXIT:
                        System.out.print("O ");
                        break;
                }
            }
            System.out.println();
        }
    }
}
