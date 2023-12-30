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
        /*String[] testInputMap = {
                "############",
                "#         ##",
                "##### # #  F",
                "#   # # ####",
                "# # ###   ##",
                "#S#     #  #",
                "############"
        };*/
        var arrayListInputMap = readInputMap("dungeonMap.txt");
        var inputMap = new String[arrayListInputMap.size()];
        for (int i = 0; i < arrayListInputMap.size(); i++) {
            inputMap[i] = arrayListInputMap.get(i);
        }

        var dungeon = new Dungeon(inputMap);
        var levelController = new LevelController(new Player(dungeon), dungeon, new Display());
        var input = new Input(levelController);

        input.run();
    }

    private static List<String> readInputMap(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
