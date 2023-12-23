
/**
 * The main class of the  program.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class WanderingInTheDungeons
{
    public static void main(String[] args) {
        var display = new Display();
        var dungeon = new Dungeon();
        var input = new Input();
        
        display.display(dungeon.map, dungeon.player);
    }
}
