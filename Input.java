import java.io.Closeable;
import java.io.IOException;
import java.util.*;

/**
 * Write a description of class Input here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
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
        }
    };

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

    public enum Keys {
        UP, DOWN, LEFT, RIGHT, MENU, SKIP
    }

    @Override
    public void close() throws IOException {
        wordScanner.close();
        numberScanner.close();
    }
}
