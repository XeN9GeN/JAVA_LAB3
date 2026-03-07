package Model;

public class BoardClass {
    public static final int WIDTH =10;
    public static final int HEIGHT =20;
    private int[][] board;
    private int filledlines;


    public BoardClass(){
        board= new int[WIDTH][HEIGHT];
    }

    public int[][] getBoard() {
        return board;
    }
    public void set_board(int[][] b) {
        this.board = b;
    }
    public boolean boardCanPlaceTet(int[][] tetromino, int corner_X, int corner_Y) {
    //corner_X имеется ввиду x коорда левого вверхнего угла
        for (int i = 0; i < tetromino.length; i++) {
            for (int j = 0; j < tetromino[i].length; j++) {
                if (tetromino[i][j] != 0) {
                    int boardX = j+corner_X;//boardX - коорда ячейки относительно всей доски
                    int boardY = i+corner_Y;

                    if (boardX < 0 || boardX >= WIDTH || boardY < 0 || boardY >= HEIGHT) {
                        return false;
                    }
                    if (board[boardX][boardY] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
