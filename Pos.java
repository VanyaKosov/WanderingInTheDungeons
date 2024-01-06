public class Pos {
    public int row;
    public int col;

    public Pos() {

    }

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Pos sub(Pos p) {
        return new Pos(row - p.row, col - p.col);
    }

    public Pos add(Pos p) {
        return new Pos(row + p.row, col + p.col);
    }

    public int manhattanDistance(Pos p) {
        return Math.abs(p.row - row) + Math.abs(p.col - col);
    }

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
