public class TestSword extends ItemWeapon {
    private static final String NAME = "Test Sword";
    private static final String DESCRIPTION = "This is a test sword. Remove it later.";
    private static final int DAMAGE = 20;

    public TestSword() {
        super(NAME, DESCRIPTION, DAMAGE);
    }

    @Override
    public void apply(Player player) {
        // Weapons don't do anything right now
    }
}
