import java.util.*;

/**
 * Write a description of class Dungeon here.
 *
 * @author Ivan Kosov
 * @version 0.0.1
 */
public class Dungeon {
    private final Cells[][] map;
    private final boolean[][] visitedCells;
    private final Pos startPlayerPos;

    public Dungeon(String[] inputMap) {
        map = new Cells[inputMap.length][inputMap[0].length()];
        visitedCells = new boolean[map.length][map[0].length];
        for (int row = 0; row < visitedCells.length; row++) {
            for (int col = 0; col < visitedCells[0].length; col++) {
                visitedCells[row][col] = false;
            }
        }

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
                        break;
                    case 'S':
                        map[row][col] = Cells.EMPTY;
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
                    default:
                        throw new IllegalStateException();
                }
            }
        }

        if (playerPos == null) {
            throw new IllegalStateException();
        }
        startPlayerPos = playerPos;
    }

    public ArrayList<Pos> findPath(Pos start, Pos finish) {
        var shortestPath = new ArrayList<Pos>();
        var field = getDistanceField(start, finish);
        Pos currentCell = new Pos(finish.row, finish.col);
        for (int i = field[finish.row][finish.col]; i >= 0; i--) {
            Pos[] cellsAround = {
                    new Pos(currentCell.row - 1, currentCell.col),
                    new Pos(currentCell.row + 1, currentCell.col),
                    new Pos(currentCell.row, currentCell.col - 1),
                    new Pos(currentCell.row, currentCell.col + 1),
            };

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
        shortestPath.add(0, new Pos(start.row, start.col));

        /*for (Integer[] row : field) {
            for (Integer integer : row) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
        
        System.out.println(shortestPath);*/

        return shortestPath;

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
                if ((field[row][col] != null && field[row][col] <= cell.stepsAmount) || map[row][col] == Cells.WALL) {
                    continue;
                }

                priorityQueue.add(cell);

            }

            if (currentCell.pos.row == finish.row && currentCell.pos.col == finish.col) {
                return field;
            }
        }
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