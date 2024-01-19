public class TestSword extends ItemWeapon {
    private static final String NAME = "Test Sword";
    private static final String DESCRIPTION = "This is a test sword. Remove it later.";
    //private static final int DAMAGE = 20;
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 25;

    public TestSword() {
        super(NAME, DESCRIPTION, MIN_DAMAGE, MAX_DAMAGE);
    }

    @Override
    public void apply(Player player) {
        // Nothing to do here
    }
}
