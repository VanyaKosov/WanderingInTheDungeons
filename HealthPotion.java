public class HealthPotion extends ItemConsumable {
    private static final String NAME = "Health Potion";
    private static final String DESCRIPTION = "Use this potion to restore 20 health";
    private static final int HEAL_AMOUNT = 20;

    public HealthPotion() {
        super(NAME, DESCRIPTION);
    }

    @Override
    public void apply(Player player) {
        player.stats.heal(HEAL_AMOUNT);
    }
}
