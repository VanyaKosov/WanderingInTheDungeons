public class XrayGlasses extends Item {
    private static final String DESCRIPTION = "These glasses allow you to see through walls";

    public XrayGlasses() {
        super(DESCRIPTION);
    }

    @Override
    public void apply(Player player) {
        player.setXrayGlasses(true);
    }
}
