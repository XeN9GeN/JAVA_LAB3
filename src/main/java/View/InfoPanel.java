package View;

import Model.GameModel;
import Model.*;
import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.getColor;

public class InfoPanel extends JPanel{
    private GameModel mod;

    public InfoPanel(GameModel m){
        this.mod=m;
        setPreferredSize(new Dimension(250, BoardClass.HEIGHT * 30));
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D gr2 = (Graphics2D) gr;
        gr2.setColor(Color.WHITE);
        gr2.setFont(new Font("Arial", Font.BOLD, 16));

        gr2.drawString("SCORE", 20, 50);
        gr2.drawString(String.valueOf(mod.getScore()), 20, 70);

        gr2.drawString("NEXT", 25, 150);
        drawNextPiece(gr2, 20, 170);

        gr2.drawString("HIGH SCORE", 110, 50);
        gr2.drawString(String.valueOf(mod.getHighScore()),110,70);

        int x = 20;      // Отступ слева
        int yStart = 350; // Начальная высота для списка управления
        int step = 25;   // Расстояние между строками

        gr2.drawString("CONTROLS:", 20, 350);
        gr2.drawString("Left:  <-", 20, 375);
        gr2.drawString("Right: ->", 20, 400);
        gr2.drawString("Down:   ↓", 20, 425);
        gr2.drawString("Rotate: ↑", 20, 450);

        // Добавляем ваши клавиши
        gr2.drawString("Pause: ESC", 20, 475);
        gr2.drawString("New Game: F1", 20, 500);

    }
    private void drawNextPiece(Graphics2D g2,int x, int y){
        TetrominoClass next = mod.getNextTetromino();
        if(next!=null){
            int[][] s = next.getTetromino();
            for(int i=0;i<s.length;i++){
                for(int j=0;j<s[i].length;j++){
                    if(s[i][j]!=0){
                        g2.setColor(getColor(next.getColor()));
                        g2.fillRect(x + j * 20, y + i * 20, 18, 18);
                    }
                }
            }
        }
    }
}
