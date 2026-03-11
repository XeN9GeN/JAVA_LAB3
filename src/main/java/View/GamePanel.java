package View;
import Model.*;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel{
    private GameModel model;
    private static final int CELL=30;

    public GamePanel(GameModel model){
        this.model = model;
        setPreferredSize(new Dimension(BoardClass.WIDTH*CELL,BoardClass.HEIGHT*CELL));
        setBackground(Color.BLACK);
    }

    //Graphics
    //paintComponent(Gr)
    @Override
    protected void paintComponent(Graphics gr){//все упавшие
        super.paintComponent(gr);
        int[][] b = model.getBoard().getBoard();

        for(int x=0;x<BoardClass.WIDTH;x++) {
            for (int y = 0; y < BoardClass.HEIGHT; y++) {

                int v = b[x][y];//лежит цвет из BoardClass.placeTet
                if (v != 0) {
                    gr.setColor(getColor(v));//цвет кисти
                    gr.fillRect(x * CELL, y * CELL, CELL, CELL);//квадрат
                }
                gr.setColor(Color.DARK_GRAY);
                gr.drawRect(x * CELL, y * CELL, CELL, CELL);
            }
        }
        drawCurTet(gr);//падающая поверх доски
    }
    private void drawCurTet(Graphics gr){//отрисовка падающей
        TetrominoClass t = model.getCurTetromino();
        int[][] sh=t.getTetromino();

        for(int i=0;i<sh.length;i++){
            for(int j=0;j<sh[i].length;j++){
                if(sh[i][j]!=0){
                    int x =(t.getX()+j)*CELL;//смещение под фигуру
                    int y =(t.getY()+i)*CELL;
                    gr.setColor(getColor(t.getColor()));
                    gr.fillRect(x,y,CELL,CELL);
                }
            }
        }
    }
    private Color getColor(int c) {
        return switch (c) {
            case 1 -> Color.CYAN;
            case 2 -> Color.YELLOW;
            case 3 -> Color.ORANGE;
            case 4 -> Color.GREEN;
            case 5 -> Color.RED;
            case 6 -> Color.BLUE;
            case 7 -> Color.MAGENTA;
            default -> Color.GRAY;
        };
    }
}
