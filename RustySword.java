public class RustySword extends ItemWeapon {
    private static final String NAME = "Rusty Sword";
    private static final String DESCRIPTION = "Rusty sword description.";
    private static final int DAMAGE = 5;
    
    public RustySword() {
        super(NAME, DESCRIPTION, DAMAGE);
    }
    
    @Override
    public void apply(Player player) {
        // Nothing to do here
    }
}
