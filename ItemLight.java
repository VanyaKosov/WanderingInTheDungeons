/**
 * Inherits Item class.
 * Inherited by all light items
 * 
 * @author Ivan Kosov
 */
public abstract class ItemLight extends Item {
    public final int viewRadiusIncrease;
    public final int fogOfWarRadiusIncrease;

    public ItemLight(String name, String description, int viewRadiusIncrease, int fogOfWarRadiusIncrease) {
        super(name, description);

        this.viewRadiusIncrease = viewRadiusIncrease;
        this.fogOfWarRadiusIncrease = fogOfWarRadiusIncrease;
    }

    /**
     * Increases player's view area
     */
    @Override
    public void apply(Player player) {
        player.addViewRadius(viewRadiusIncrease);
        player.addFogOfWarRadius(fogOfWarRadiusIncrease);
    }
}
