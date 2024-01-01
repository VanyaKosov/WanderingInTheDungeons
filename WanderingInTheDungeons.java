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
            var mainMenuController = new MainMenuController();
            Path mapPath = mainMenuController.showMainMenu(input);
            if (mapPath == null) {
                return;
            }

            var arrayListInputMap = readInputMap(mapPath);
            var inputMap = new String[arrayListInputMap.size()];
            for (int i = 0; i < arrayListInputMap.size(); i++) {
                inputMap[i] = arrayListInputMap.get(i);
            }

            var dungeon = new Dungeon(inputMap);
            var levelController = new LevelController(input, new Player(dungeon), dungeon, new Display());
            levelController.run();
        }

    }

    private static List<String> readInputMap(Path mapPath) throws IOException {
        return Files.readAllLines(mapPath);
    }
}
