import java.util.*;

/**
 * Write a description of class Input here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Input {
    private final InputEvents inputHandler;

    public Input(InputEvents inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void run() {
        var keyMapping = new HashMap<Character, Keys>() {
            {
                put('w', Keys.UP);
                put('s', Keys.DOWN);
                put('a', Keys.LEFT);
                put('d', Keys.RIGHT);
            }
        };
        try (var scanner = new Scanner(System.in)) {
            while (true) {
                var input = scanner.nextLine();

                for (int i = 0; i < input.length(); i++) {
                    var letter = Character.toLowerCase(input.charAt(i));
                    var key = keyMapping.get(letter);
                    if (key != null) {
                        inputHandler.onKeyPress(key);
                    }
                }
            }
        }
    }

    public enum Keys {
        UP, DOWN, LEFT, RIGHT
    }

    public interface InputEvents {
        void onKeyPress(Keys key);
    }
}
