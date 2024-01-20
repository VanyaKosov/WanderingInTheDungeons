import java.io.Closeable;
import java.io.IOException;
import java.util.*;

/**
 * Gets all of the input
 *
 * @author Ivan Kosov
 */
public class Input implements Closeable {
    private static final Scanner wordScanner = new Scanner(System.in);
    private static final Scanner numberScanner = new Scanner(System.in);
    private static final HashMap<Character, Keys> keyMapping = new HashMap<Character, Keys>() {
        {
            put('w', Keys.UP);
            put('s', Keys.DOWN);
            put('a', Keys.LEFT);
            put('d', Keys.RIGHT);
            put('`', Keys.MENU);
            put(' ', Keys.SKIP);
            put('i', Keys.INVENTORY);
        }
    };

    /**
     * Waits until user presses enter
     */
    public void waitForEnter() {
        while (true) {
            var answer = wordScanner.nextLine();
            if (answer != "") {
                continue;
            }
            return;
        }
    }

    /**
     * Reads all of the entered keys
     * 
     * @return an ArrayList of all of the entered keys
     */
    public ArrayList<Keys> readKey() {
        var allKeys = new ArrayList<Keys>();
        var input = wordScanner.nextLine();

        for (int i = 0; i < input.length(); i++) {
            var letter = Character.toLowerCase(input.charAt(i));
            var key = keyMapping.get(letter);
            if (key != null) {
                allKeys.add(key);
            }
        }

        return allKeys;
    }

    /**
     * Reads a number
     * 
     * @param min is the minimum number
     * @param max is the maximum number
     * @return the number that was entered
     */
    public int readNumber(int min, int max) {
        while (true) {
            int number;
            try {
                number = numberScanner.nextInt();
            } catch (InputMismatchException e) {
                continue;
            }

            if (number < min || number > max) {
                continue;
            }

            return number;
        }
    }

    /**
     * All of the possible keys
     */
    public enum Keys {
        UP, DOWN, LEFT, RIGHT, MENU, SKIP, INVENTORY
    }

    @Override
    public void close() throws IOException {
        wordScanner.close();
        numberScanner.close();
    }
}
