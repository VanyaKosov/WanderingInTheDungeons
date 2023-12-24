public class LevelController implements Input.InputEvents {
    public final Player player;
    public final Dungeon dungeon;
    public final Display display;

    public LevelController(Player player, Dungeon dungeon, Display display) {
        this.player = player;
        this.dungeon = dungeon;
        this.display = display;

        displayField();
    }

    @Override
    public void onKeyPress(Input.Keys key) {
        if (!canMovePlayer(key)) {
            return;
        }

        dungeon.movePlayer(key);
        displayField();
    }

    private void displayField() {
        display.draw(dungeon.getMap());
    }

    public boolean canMovePlayer(Input.Keys key) {
        int row = dungeon.getPlayerPos().row;
        int col = dungeon.getPlayerPos().col;
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
                break;
        }

        if (dungeon.getMap()[row][col] == Cells.WALL) {
            return false;
        }

        return true;
    }

}
