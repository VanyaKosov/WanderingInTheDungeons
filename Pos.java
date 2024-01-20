/**
 * Stores a position in two dimensions
 * 
 * @author Ivan Kosov
 */
public class Pos {
    public int row;
    public int col;

    /**
     * Default constructor
     */
    public Pos() {

    }

    /**
     * Constructor that sets row and column values
     * 
     * @param row is the row coordinate
     * @param col is the column coordinate
     */
    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Subtracts this Pos's row and col from another Pos's row and col
     * 
     * @param p is another Pos
     * @return new Pos that is the difference between the two pos
     */
    public Pos sub(Pos p) {
        return new Pos(row - p.row, col - p.col);
    }

    /**
     * Adds this Pos's row and col to another Pos's row and col
     * 
     * @param p is another Pos
     * @return new Pos that is the sum of the two pos
     */
    public Pos add(Pos p) {
        return new Pos(row + p.row, col + p.col);
    }

    /**
     * Finds the manhattan distance between this Pos and another Pos
     * @param p is another Pos
     * @return the manhattan distance
     */
    public int manhattanDistance(Pos p) {
        return Math.abs(p.row - row) + Math.abs(p.col - col);
    }

    /**
     * Finds if this Pos and another Pos have equal row and col
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pos p) {
            if (p.row == row && p.col == col) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "{" + row + ", " + col + "}";
    }
}
