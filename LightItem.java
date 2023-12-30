public abstract class LightItem extends Item {
    public final int viewRadiusIncrease;
    public final int fogOfWarRadiusIncrease;

    public LightItem(String description, int viewRadiusIncrease, int fogOfWarRadiusIncrease) {
        super(description);

        this.viewRadiusIncrease = viewRadiusIncrease;
        this.fogOfWarRadiusIncrease = fogOfWarRadiusIncrease;
    }
}
