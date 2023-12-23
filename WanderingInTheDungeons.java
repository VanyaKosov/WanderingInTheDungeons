
/**
 * The main class of the  program.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class WanderingInTheDungeons {
    public static void main(String[] args) {
        var levelController = new LevelController(new Player(), new Dungeon());
        var display = new Display();
        var input = new Input();

        //display.display(levelController.dungeon.getMap(), levelController.player.getViewArea(),
        //        levelController.player.getFogOfWarArea());
    }
}
