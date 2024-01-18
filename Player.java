import java.util.*;

/**
 * Write a description of class Player here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Player {
    private final Dungeon dungeon;
    private final Pos pos = new Pos();
    public final Inventory inventory = new Inventory();
    private final HashMap<Cells, Item> stuff = new HashMap<>();
    private int viewRadius;
    private int fogOfWarRadius;
    private boolean hasXrayGlasses = false;
    public Stats stats = new Stats(50, 5, 3);

    public Player(Dungeon dungeon) {
        this.dungeon = dungeon;

        pos.row = dungeon.getStartPlayerPos().row;
        pos.col = dungeon.getStartPlayerPos().col;

        stuff.put(Cells.CANDLE, new Candle());
        stuff.put(Cells.TEST_SWORD, new TestSword());
        stuff.put(Cells.RUSTY_SWORD, new RustySword());
        stuff.put(Cells.XRAY_GLASSES, new XrayGlasses());
        stuff.put(Cells.HEALTH_POTION, new HealthPotion());
        stuff.put(Cells.LEATHER_ARMOR, new LeatherArmor());

        inventory.addItem(stuff.get(Cells.CANDLE), 1, this);
        inventory.addItem(stuff.get(Cells.TEST_SWORD), 1, this);
        inventory.addItem(stuff.get(Cells.HEALTH_POTION), 3, this);
    }

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

        //checkForItem();
    }

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

    public Item checkForItem() {
        var item = stuff.get(dungeon.getCell(pos.row, pos.col));
        if (item == null) {
            return null;
        }
        dungeon.setCell(Cells.EMPTY, pos.row, pos.col);
        inventory.addItem(item, 1, this);

        //display.drawItemPickUp(item);
        return item;
    }

    public boolean hasXrayGlasses() {
        return hasXrayGlasses;
    }

    public void setXrayGlasses(boolean state) {
        hasXrayGlasses = state;
    }

    public Pos getPos() {
        return new Pos(pos.row, pos.col);
    }

    public void addViewRadius(int viewArea) {
        this.viewRadius += viewArea;
    }

    public int getViewRadius() {
        return viewRadius;
    }

    public void addFogOfWarRadius(int fogOfWarArea) {
        this.fogOfWarRadius += fogOfWarArea;
    }

    public int getFogOfWarRadius() {
        return fogOfWarRadius;
    }
}