import java.util.*;

/**
 * Write a description of class Dungeon here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Dungeon {
    private static final int EMPTY_CELLS_PER_ENEMY = 20;
    private final Cells[][] map;
    private final boolean[][] visitedCells;
    private final Pos startPlayerPos;
    private int amountOfEnemies;

    public Dungeon(String[] inputMap) {
        map = new Cells[inputMap.length][inputMap[0].length()];
        visitedCells = new boolean[map.length][map[0].length];
        for (int row = 0; row < visitedCells.length; row++) {
            for (int col = 0; col < visitedCells[0].length; col++) {
                visitedCells[row][col] = false;
            }
        }

        int freeSpaces = 0;
        Pos playerPos = null;
        for (int row = 0; row < inputMap.length; row++) {
            for (int col = 0; col < inputMap[0].length(); col++) {
                char currentCell = inputMap[row].charAt(col);
                switch (currentCell) {
                    case '#':
                        map[row][col] = Cells.WALL;
                        break;
                    case ' ':
                        map[row][col] = Cells.EMPTY;
                        freeSpaces++;
                        break;
                    case '.':
                        map[row][col] = Cells.EMPTY_DO_NOT_TAKE;
                        break;
                    case '/':
                        map[row][col] = Cells.DOOR;
                        break;
                    case 'S':
                        map[row][col] = Cells.EMPTY_DO_NOT_TAKE;
                        if (playerPos != null) {
                            throw new IllegalStateException();
                        }
                        playerPos = new Pos(row, col);
                        break;
                    case 'F':
                        map[row][col] = Cells.EXIT;
                        break;
                    case 'i':
                        map[row][col] = Cells.CANDLE;
                        break;
                    case 'r':
                        map[row][col] = Cells.RUSTY_SWORD;
                        break;
                    case 'x':
                        map[row][col] = Cells.XRAY_GLASSES;
                        break;
                    case 'h':
                        map[row][col] = Cells.HEALTH_POTION;
                        break;
                    case 'l':
                        map[row][col] = Cells.LEATHER_ARMOR;
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }

        amountOfEnemies = freeSpaces / EMPTY_CELLS_PER_ENEMY;

        if (playerPos == null) {
            throw new IllegalStateException();
        }
        startPlayerPos = playerPos;
    }

    public ArrayList<Pos> findAccessibleCells(Pos startingPos) {
        var visitedCells = new boolean[map.length][map[0].length];
        var accessibleCells = new ArrayList<Pos>();
        findConnectedCells(startingPos, visitedCells, accessibleCells);

        return accessibleCells;
    }

    private void findConnectedCells(Pos startingPos, boolean[][] visitedCells, ArrayList<Pos> accessibleCells) {
        Pos[] cellsAround = getCellsAround(startingPos);
        for (Pos pos : cellsAround) {
            if (map[pos.row][pos.col] == Cells.WALL || map[pos.row][pos.col] == Cells.DOOR
                    || map[pos.row][pos.col] == Cells.EXIT || visitedCells[pos.row][pos.col]) {
                continue;
            }
            visitedCells[pos.row][pos.col] = true;
            findConnectedCells(pos, visitedCells, accessibleCells);
            accessibleCells.add(pos);
        }
    }

    public ArrayList<Pos> findPath(Pos start, Pos finish) {
        var shortestPath = new ArrayList<Pos>();
        Pos currentCell = new Pos(finish.row, finish.col);
        var field = getDistanceField(start, finish);
        if (field == null) {
            return null;
        }

        shortestPath.add(finish);
        for (int i = field[finish.row][finish.col]; i >= 0; i--) {
            Pos[] cellsAround = getCellsAround(currentCell);

            for (Pos pos : cellsAround) {

                if (field[pos.row][pos.col] == null) {
                    continue;
                }

                if (field[pos.row][pos.col] < i) {
                    currentCell.row = pos.row;
                    currentCell.col = pos.col;
                    shortestPath.add(0, pos);
                    break;
                }
            }
        }
        //shortestPath.add(0, new Pos(start.row, start.col));
        if (shortestPath.size() > 1) {
            shortestPath.remove(0);
        }

        return shortestPath;

    }

    private Pos[] getCellsAround(Pos pos) {
        Pos[] cellsAround = {
                new Pos(pos.row - 1, pos.col),
                new Pos(pos.row + 1, pos.col),
                new Pos(pos.row, pos.col - 1),
                new Pos(pos.row, pos.col + 1),
        };

        return cellsAround;
    }

    private Integer[][] getDistanceField(Pos start, Pos finish) {
        var field = new Integer[map.length][map[0].length];

        var priorityQueue = new PriorityQueue<Cell>(new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                return (cell1.stepsAmount + cell1.pos.manhattanDistance(finish))
                        - (cell2.stepsAmount + cell2.pos.manhattanDistance(finish));
            }
        });

        priorityQueue.add(new Cell(start, 0));
        while (true) {
            if (priorityQueue.size() == 0) {
                return null;
            }

            Cell currentCell = priorityQueue.poll();

            Integer currentFieldCell = field[currentCell.pos.row][currentCell.pos.col];
            if (currentFieldCell != null && currentFieldCell >= currentCell.stepsAmount) {
                continue;
            }

            currentFieldCell = currentCell.stepsAmount;
            field[currentCell.pos.row][currentCell.pos.col] = currentFieldCell;

            Cell[] cellsAround = {
                    new Cell(new Pos(currentCell.pos.row - 1, currentCell.pos.col), currentFieldCell + 1),
                    new Cell(new Pos(currentCell.pos.row + 1, currentCell.pos.col), currentFieldCell + 1),
                    new Cell(new Pos(currentCell.pos.row, currentCell.pos.col - 1), currentFieldCell + 1),
                    new Cell(new Pos(currentCell.pos.row, currentCell.pos.col + 1), currentFieldCell + 1)
            };

            for (Cell cell : cellsAround) {
                int row = cell.pos.row;
                int col = cell.pos.col;
                if ((field[row][col] != null && field[row][col] <= cell.stepsAmount) || map[row][col] == Cells.WALL
                        || map[row][col] == Cells.DOOR) {
                    continue;
                }

                priorityQueue.add(cell);

            }

            if (currentCell.pos.row == finish.row && currentCell.pos.col == finish.col) {
                return field;
            }
        }
    }

    public int getAmountOfEnemies() {
        return amountOfEnemies;
    }

    public Cells getCell(int row, int col) {
        return map[row][col];
    }

    public void setCell(Cells cell, int row, int col) {
        map[row][col] = cell;
    }

    public boolean getVisitedCell(int row, int col) {
        return visitedCells[row][col];
    }

    public void setVisitedCell(int row, int col) {
        visitedCells[row][col] = true;
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public Pos getStartPlayerPos() {
        return startPlayerPos;
    }

    private static class Cell {
        public final Pos pos;
        public final int stepsAmount;

        public Cell(Pos pos, int stepsAmount) {
            this.pos = pos;
            this.stepsAmount = stepsAmount;
        }
    }
}