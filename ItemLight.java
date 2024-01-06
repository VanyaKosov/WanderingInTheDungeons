public abstract class ItemLight extends Item {
    public final int viewRadiusIncrease;
    public final int fogOfWarRadiusIncrease;

    public ItemLight(String description, int viewRadiusIncrease, int fogOfWarRadiusIncrease) {
        super(description);

        this.viewRadiusIncrease = viewRadiusIncrease;
        this.fogOfWarRadiusIncrease = fogOfWarRadiusIncrease;
    }

    @Override
    public void apply(Player player) {
        player.addViewRadius(viewRadiusIncrease);
        player.addFogOfWarRadius(fogOfWarRadiusIncrease);
    }
}
