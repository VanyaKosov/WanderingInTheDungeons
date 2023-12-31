import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The main class of the program.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class WanderingInTheDungeons {
    public static void main(String[] args) throws IOException {
        var arrayListInputMap = readInputMap("dungeons\\test dungeon.dungeon");
        var inputMap = new String[arrayListInputMap.size()];
        for (int i = 0; i < arrayListInputMap.size(); i++) {
            inputMap[i] = arrayListInputMap.get(i);
        }

        try (var input = new Input()) {
            var dungeon = new Dungeon(inputMap);
            var levelController = new LevelController(input, new Player(dungeon), dungeon, new Display());

            levelController.run();
        }
    }

    private static List<String> readInputMap(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
