import java.util.*;

/**
 * Write a description of class Input here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Input {
    private static final HashMap<Character, Keys> keyMapping = new HashMap<Character, Keys>() {
        {
            put('w', Keys.UP);
            put('s', Keys.DOWN);
            put('a', Keys.LEFT);
            put('d', Keys.RIGHT);
        }
    };

    public ArrayList<Keys> readKey() {
        var allKeys = new ArrayList<Keys>();

        //try (var scanner = new Scanner(System.in)) {
        var scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        for (int i = 0; i < input.length(); i++) {
            var letter = Character.toLowerCase(input.charAt(i));
            var key = keyMapping.get(letter);
            if (key != null) {
                allKeys.add(key);
            }
        }
        //}

        return allKeys;
    }

    public int readNumber(int min, int max) {
        //try (var scanner = new Scanner(System.in)) {
        var scanner = new Scanner(System.in);
        while (true) {
            int number;
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                continue;
            }

            if (number < min || number > max) {
                continue;
            }

            return number;
        }
        //}
    }

    /*public void run() {
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
                        //inputHandler.onKeyPress(key);
                    }
                }
            }
        }
    }*/

    public enum Keys {
        UP, DOWN, LEFT, RIGHT
    }
}
