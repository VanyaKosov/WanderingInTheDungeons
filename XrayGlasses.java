/**
 * X-Ray Glasses allow the player to see through walls
 * 
 * @author Ivan Kosov
 */
public class XrayGlasses extends Item {
    private static final String NAME = "X-Ray Glasses";
    private static final String DESCRIPTION = "These glasses allow you to see through walls";

    public XrayGlasses() {
        super(NAME, DESCRIPTION);
    }

    @Override
    public void apply(Player player) {
        player.setXrayGlasses(true);
    }
}
