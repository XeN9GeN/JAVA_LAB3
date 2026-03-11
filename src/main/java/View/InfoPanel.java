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
        gr2.setFont(new Font("Arial", Font.BOLD, 14));

        gr2.drawString("SCORE", 20, 50);
        gr2.drawString(String.valueOf(mod.getScore()), 20, 70);

        gr2.drawString("NEXT", 20, 150);
        drawNextPiece(gr2, 20, 170);
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
