import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class MainMenuController {
    public Path showMainMenu(Input input) throws IOException {
        String extension = ".dungeon";

        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get("./dungeons"), "*" + extension)) {
            int counter = 1;
            var paths = new ArrayList<Path>();
            System.out.println("Please type:");
            System.out.println("0: To exit the program");
            for (Path path : dir) {
                paths.add(path);
                String name = path.toFile().getName();
                System.out.print(counter + ": To open ");
                System.out.println(name.substring(0, name.length() - extension.length()));
                counter++;
            }

            int answer = input.readNumber(0, paths.size());
            if (answer == 0) {
                return null;
            }

            return paths.get(answer - 1);
        }
    }
}
