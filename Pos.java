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
}
