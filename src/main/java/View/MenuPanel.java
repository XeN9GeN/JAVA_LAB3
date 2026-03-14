package View;

import Model.GameModel;
import Model.*;
import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.getColor;
public class MenuPanel extends JPanel {
    private GameModel mod;

    public MenuPanel(GameModel m){
        this.mod=mod;
        setPreferredSize(new Dimension(250, BoardClass.HEIGHT * 30));
        setBackground(Color.GRAY);
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D gr2 = (Graphics2D) gr;

        //Сглаживание текста
        gr2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gr2.setColor(Color.WHITE);
        gr2.setFont(new Font("Arial", Font.BOLD, 24));
        gr2.drawString("TETRIS MENU", 40, 100);

        gr2.setFont(new Font("Arial", Font.PLAIN, 16));
        gr2.drawString("Press F1 to Start", 55, 200);
        gr2.drawString("Press ESC to Resume", 45, 230);

        gr2.setFont(new Font("Arial", Font.BOLD, 14));
        gr2.drawString("BEST SCORE:", 75, 400);
        gr2.setColor(Color.YELLOW);
        gr2.drawString(String.valueOf(mod.getHighScore()), 105, 425);
    }
}