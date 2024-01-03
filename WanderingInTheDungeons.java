import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * The main class of the program.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class WanderingInTheDungeons {
    public static void main(String[] args) throws IOException {

        try (var input = new Input()) {
            while (true) {
                var display = new Display();
                var mainMenuController = new MainMenuController();
                Path mapPath = mainMenuController.showMainMenu(input, display);
                if (mapPath == null) {
                    return;
                }

                var arrayListInputMap = readInputMap(mapPath);
                var inputMap = new String[arrayListInputMap.size()];
                for (int i = 0; i < arrayListInputMap.size(); i++) {
                    inputMap[i] = arrayListInputMap.get(i);
                }

                var dungeon = new Dungeon(inputMap);
                // System.out.println(dungeon.findPath(new Pos(1, 1), new Pos(3, 1)));
                // System.out.println(dungeon.findAccessibleCells(new Pos(3, 3)));
                var levelController = new LevelController(input, new Player(dungeon), dungeon, display);
                levelController.run();
                return;
            }
        }
    }

    private static List<String> readInputMap(Path mapPath) throws IOException {
        return Files.readAllLines(mapPath);
    }
}
