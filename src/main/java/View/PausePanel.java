package View;
import Model.GameModel;
import Model.*;
import javax.swing.*;
import java.awt.*;
import static javax.swing.UIManager.getColor;

public class PausePanel extends JPanel{
    private GameModel mod;

    public PausePanel(GameModel m){
        this.mod=m;
        setPreferredSize(new Dimension(200, BoardClass.HEIGHT *20));
        setBackground(new Color(0, 0, 0, 180));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mod.getState() == GameState.PAUSE) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(new Color(0, 0, 0, 200));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));

            FontMetrics fm = g2d.getFontMetrics();
            String pauseText = "PAUSE"; int x = (getWidth() - fm.stringWidth(pauseText)) / 2; int y = getHeight() / 2 - 30;
            g2d.drawString(pauseText, x, y);

            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            String scoreText = "Score: " + mod.getScore(); fm = g2d.getFontMetrics(); x = (getWidth() - fm.stringWidth(scoreText)) / 2;
            y = getHeight() / 2 + 20;
            g2d.drawString(scoreText, x, y);

            g2d.setFont(new Font("Arial", Font.PLAIN, 16));
            String hintText = "Press ESC to continue"; fm = g2d.getFontMetrics(); x = (getWidth() - fm.stringWidth(hintText)) / 2;
            y = getHeight() / 2 + 60;
            g2d.drawString(hintText, x, y);

            g2d.dispose();
        }
    }
}
