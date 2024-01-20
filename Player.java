import java.util.*;

/**
 * Stores all of the information about the player
 *
 * @author Ivan Kosov
 */
public class Player {
    private final Dungeon dungeon;
    private final Pos pos = new Pos();
    public final Inventory inventory = new Inventory();
    private final HashMap<Cells, Item> stuff = new HashMap<>();
    private int viewRadius;
    private int fogOfWarRadius;
    private boolean hasXrayGlasses = false;
    public Stats stats = new Stats(30, 5, 0);

    /**
     * Initializes the player
     * 
     * @param dungeon is the instance of the Dungeon class
     */
    public Player(Dungeon dungeon) {
        this.dungeon = dungeon;

        pos.row = dungeon.getStartPlayerPos().row;
        pos.col = dungeon.getStartPlayerPos().col;

        stuff.put(Cells.CANDLE, new Candle());
        stuff.put(Cells.TEST_SWORD, new TestSword());
        stuff.put(Cells.RUSTY_SWORD, new RustySword());
        stuff.put(Cells.STEEL_SWORD, new SteelSword());
        stuff.put(Cells.XRAY_GLASSES, new XrayGlasses());
        stuff.put(Cells.HEALTH_POTION, new HealthPotion());
        stuff.put(Cells.LEATHER_ARMOR, new LeatherArmor());

        inventory.addItem(stuff.get(Cells.CANDLE), 1, this);
        //inventory.addItem(stuff.get(Cells.TEST_SWORD), 1, this);
        inventory.addItem(stuff.get(Cells.HEALTH_POTION), 3, this);
    }

    /**
     * Moves the player in a given direction
     * 
     * @param direction is the direction to move to
     */
    public void movePlayer(Input.Keys direction) {
        if (!canMovePlayer(direction)) {
            return;
        }

        int row = pos.row;
        int col = pos.col;
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                throw new IllegalStateException();
        }

        pos.row = row;
        pos.col = col;
    }

    /**
     * Checks if the player can move in a given direction
     * 
     * @param key is the direction where to move
     * @return true if can move, false if can't
     */
    public boolean canMovePlayer(Input.Keys key) {
        int row = pos.row;
        int col = pos.col;
        switch (key) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            default:
                throw new IllegalStateException();
        }

        if (dungeon.getCell(row, col) == Cells.WALL) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the player is on the item and picks it up
     * 
     * @return item that was picked up
     */
    public Item checkForItem() {
        var item = stuff.get(dungeon.getCell(pos.row, pos.col));
        if (item == null) {
            return null;
        }
        dungeon.setCell(Cells.EMPTY, pos.row, pos.col);
        inventory.addItem(item, 1, this);

        return item;
    }

    /**
     * @return true if player has X-Ray Glasses, false if doesn't
     */
    public boolean hasXrayGlasses() {
        return hasXrayGlasses;
    }

    /**
     * Set the state of the X-Ray Glasses
     * 
     * @param state is true to add X-Ray Glasses, and false to remove them
     */
    public void setXrayGlasses(boolean state) {
        hasXrayGlasses = state;
    }

    /**
     * @return player's position
     */
    public Pos getPos() {
        return new Pos(pos.row, pos.col);
    }

    /**
     * Increase view radius by a given amount
     * 
     * @param viewRadius is the amount increase
     */
    public void addViewRadius(int viewRadius) {
        this.viewRadius += viewRadius;
    }

    /**
     * @return current player's view radius
     */
    public int getViewRadius() {
        return viewRadius;
    }

    /**
     * Increase fog of war radius by a given amount
     * 
     * @param fogOfWarArea is the amount increase
     */
    public void addFogOfWarRadius(int fogOfWarArea) {
        this.fogOfWarRadius += fogOfWarArea;
    }

    /**
     * @return current player's fog of war radius
     */
    public int getFogOfWarRadius() {
        return fogOfWarRadius;
    }
}