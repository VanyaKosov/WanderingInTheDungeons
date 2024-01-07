public class HealthPotion extends ItemConsumable {
    private static final String NAME = "Health Potion";
    private static final String DESCRIPTION = "Use this potion to restore 20 health";

    public HealthPotion() {
        super(NAME, DESCRIPTION);
    }

    @Override
    public void apply(Player player) {

    }
}
