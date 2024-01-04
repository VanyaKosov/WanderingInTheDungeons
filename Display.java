import java.nio.file.*;
import java.util.*;

/**
 * Write a description of class Display here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Display {
    public Display() {

    }

    public void drawVictory() {
        System.out.println("You escaped!");
    }

    public void drawMainMenu(ArrayList<Path> paths, String extension) {
        int counter = 1;
        System.out.println("Please type:");
        System.out.println("0: To exit the program");
        for (Path path : paths) {
            String name = path.toFile().getName();
            System.out.print(counter + ": To open ");
            System.out.println(name.substring(0, name.length() - extension.length()));
            counter++;
        }
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
                    case EMPTY_DO_NOT_TAKE:
                        System.out.print("  ");
                        break;
                    case DOOR:
                        System.out.print("/ ");
                        break;
                    case PLAYER:
                        System.out.print("@ ");
                        break;
                    case ENEMY:
                        System.out.print("& ");
                        break;
                    case EXIT:
                        System.out.print("O ");
                        break;
                    case CANDLE:
                        System.out.print("i ");
                        break;
                    case XRAY_GLASSES:
                        System.out.print("X ");
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            System.out.println();
        }
    }
}
