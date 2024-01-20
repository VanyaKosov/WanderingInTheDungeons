import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * The main class of the program
 *
 * @author Ivan Kosov
 */
public class WanderingInTheDungeons {
    public static void main(String[] args) throws IOException {
        try (var input = new Input()) {
            showTutorial();
            input.waitForEnter();
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
                var player = new Player(dungeon);
                var invController = new InventoryController(player, display, input);
                var battleController = new BattleController(display, player, input, invController);
                var levelController = new LevelController(input, player, dungeon, display, battleController,
                        invController);
                levelController.run();
                ;
            }
        }
    }

    /**
     * Reads dungeon map from a file
     * 
     * @param mapPath is the path of the file with the map
     * @return List of lines of the file
     * @throws IOException
     */
    private static List<String> readInputMap(Path mapPath) throws IOException {
        return Files.readAllLines(mapPath);
    }
    
    private static void showTutorial() throws IOException {
        var tutorial = Files.readAllLines(Paths.get("README.txt"));
        for (String line : tutorial) {
            System.out.println(line);
        }
        
        System.out.print("\nPress enter to continue");
    }
}
