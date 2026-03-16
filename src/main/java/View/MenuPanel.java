package View;

import Model.GameModel;
import Model.BoardClass;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final GameModel mod;

    private static final Color BG_TOP = new Color(44, 62, 80);
    private static final Color BG_BOTTOM = new Color(20, 30, 48);
    private static final Color ACCENT_COLOR = new Color(52, 152, 219);
    private static final Color TEXT_PRIMARY = Color.WHITE;
    private static final Color SCORE_COLOR = new Color(241, 196, 15);

    public MenuPanel(GameModel m){
        this.mod = m;
        setPreferredSize(new Dimension(250, BoardClass.HEIGHT * 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0, BG_TOP, 0, getHeight(), BG_BOTTOM);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        String title = "TETRIS MENU";
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 40));
        g2d.setColor(new Color(0, 0, 0, 150));
        drawCenteredString(g2d, title, 82, getWidth());
        g2d.setColor(ACCENT_COLOR);
        drawCenteredString(g2d, title, 80, getWidth());


        g2d.setColor(new Color(255, 255, 255, 50));
        g2d.drawLine(40, 110, getWidth() - 40, 110);

        g2d.setFont(new Font("Monospaced", Font.PLAIN, 25));
        g2d.setColor(TEXT_PRIMARY);
        drawCenteredString(g2d, "F1 - START GAME", 180, getWidth());
        drawCenteredString(g2d, "ESC - RESUME", 210, getWidth());

        g2d.setFont(new Font("Segoe UI", Font.BOLD, 25));
        g2d.setColor(new Color(255, 255, 255, 180));
        drawCenteredString(g2d, "GLOBAL BEST SCORE", 380, getWidth());

        g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
        g2d.setColor(SCORE_COLOR);
        drawCenteredString(g2d, String.valueOf(mod.getHighScoreGlobal()), 420, getWidth());

        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(getWidth()/2 - 60, 390, 120, 45, 15, 15);
    }
    private void drawCenteredString(Graphics2D g, String text, int y, int width) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (width - metrics.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }
}
