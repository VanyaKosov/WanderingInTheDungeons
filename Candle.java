/**
 * Candle allow the player to see further
 * 
 * @author Ivan Kosov
 */
public class Candle extends ItemLight {
    private static final String NAME = "Candle";
    private static final String DESCRIPTION = "This is just a candle. It allows you to see further in the dark.";

    public Candle() {
        super(NAME, DESCRIPTION, 1, 1);
    }
}
