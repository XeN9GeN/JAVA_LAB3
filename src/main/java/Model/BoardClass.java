package Model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BoardClass {
    public static final int WIDTH =10;
    public static final int HEIGHT =20;
    private int[][] board;


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
    public void placeTet(TetrominoClass tet){
        int[][] t =tet.getTetromino();

        for(int i=0;i<t.length;i++){
            for(int j=0;j<t[i].length;j++){
                if(t[i][j]!=0){
                    int boardX = j+tet.getX();
                    int boardY = i+tet.getY();
                    board[boardX][boardY]=tet.getColor();
                }
            }
        }
    }
    public int clearFullLines(){
        int cleared_lines=0;

        for (int y=HEIGHT-1;y>=0;y--){
            boolean l_full=true;

            for(int x=0;x<WIDTH;x++){
                if(board[x][y]==0){
                    l_full=false;
                    break;
                }
            }

            if(l_full){
                cleared_lines++;

                for(int row=y;row>0;row--){
                    for(int x=0;x<WIDTH;x++){
                        board[x][row]=board[x][row-1];
                    }
                }

                for(int x=0;x<WIDTH;x++){
                    board[x][0]=0;
                }
                y++;
            }
        }
        return cleared_lines;
    }
}
