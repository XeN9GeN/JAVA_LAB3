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
        setBackground(new Color(30, 30, 30));
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 0, getWidth(), getHeight());

        drawSection(g2, "CURRENT SCORE", 20, 70, Color.CYAN);
        drawValue(g2, String.valueOf(mod.getScore()), 20, 95, Color.WHITE);

        drawSeparator(g2, 20, 110, 240);

        drawSection(g2, "YOUR BEST", 20, 135, new Color(100, 255, 100));
        drawValue(g2, String.valueOf(mod.getHighScorePlayer()), 20, 160, Color.WHITE);

        drawSection(g2, "GLOBAL BEST", 20, 185, new Color(255, 100, 100));
        drawValue(g2, String.valueOf(mod.getHighScoreGlobal()), 20, 210, Color.WHITE);


        drawSeparator(g2, 20, 225, 240);

        drawSection(g2, "NEXT PIECE", 20, 250,new Color(255, 165, 0));
        drawNextPiece(g2, 50, 280);

        drawSeparator(g2, 13, 350, 260);

        drawSection(g2, "CONTROLS", 20, 375, new Color(200, 200, 255));

        int y = 400;
        drawControl(g2, "← →", "Move", 20, y);
        drawControl(g2, "↓", "Soft drop", 20, y + 25);
        drawControl(g2, "↑", "Rotate", 20, y + 50);
        drawControl(g2, "ESC", "Pause", 20, y + 75);
        drawControl(g2, "F1", "New game", 20, y + 100);

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

    private void drawSection(Graphics2D g2, String text, int x, int y, Color color) {
        g2.setColor(color);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(text, x, y);
    }
    private void drawValue(Graphics2D g2, String value, int x, int y, Color color) {
        g2.setColor(color);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString(value, x, y);
    }
    private void drawSeparator(Graphics2D g2, int x, int y, int width) {
        g2.setColor(new Color(100, 100, 100));
        g2.drawLine(x, y, x + width, y);
    }
    private void drawControl(Graphics2D g2, String key, String action, int x, int y) {
        g2.setColor(new Color(255, 255, 100));
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString(key, x, y);

        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Arial", Font.PLAIN, 15));
        g2.drawString("- " + action, x + 50, y);
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
