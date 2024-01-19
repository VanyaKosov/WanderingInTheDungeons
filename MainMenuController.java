import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * @author Ivan Kosov
 */
public class MainMenuController {
    /**
     * Controls main menu
     * @param input is an instance of class Input
     * @param display is an instance of class Display
     * @return Path is a file which has the information about selected dungeon
     * @throws IOException
     */
    public Path showMainMenu(Input input, Display display) throws IOException {
        String extension = ".dungeon";

        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get("./dungeons"), "*" + extension)) {
            var paths = new ArrayList<Path>();
            for (Path path : dir) {
                paths.add(path);
            }
            display.drawMainMenu(paths, extension);

            int answer = input.readNumber(0, paths.size());
            if (answer == 0) {
                return null;
            }

            return paths.get(answer - 1);
        }
    }
}
