package View;

import Model.GameModel;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map;

public class ScorePanel extends JPanel {
    private GameModel mod;

    public ScorePanel(GameModel m) {
        this.mod = m;
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

        drawSection(g2, " HIGH SCORES ", 50, new Color(255, 215, 0));
        drawSeparator(g2, 20, 70, 210);


        g2.setColor(new Color(200, 200, 200));
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("#", 25, 95);
        g2.drawString("PLAYER", 60, 95);
        g2.drawString("SCORE", 170, 95);

        drawSeparator(g2, 20, 105, 210);

        //В мапе нет сортировки, пришлось идти окольными путями

        Set<Map.Entry<String,Integer>> map = mod.getHighScore().getScoresTable().entrySet();
        List<Map.Entry<String,Integer>> sortedScores = new ArrayList<>(map);
        sortedScores.sort((e1,e2)-> e2.getValue().compareTo(e1.getValue()));

        int y = 125;
        for (int i = 0; i < Math.min(10, sortedScores.size()); i++) {
            Map.Entry<String, Integer> entry = sortedScores.get(i);

            if (i == 0) {
                g2.setColor(new Color(255, 215, 0));
                g2.setFont(new Font("Arial", Font.BOLD, 15));
                g2.drawString("🥇", 25, y);
            } else if (i == 1) {
                g2.setColor(new Color(192, 192, 192));
                g2.setFont(new Font("Arial", Font.BOLD, 15));
                g2.drawString("🥈", 25, y);
            } else if (i == 2) {
                g2.setColor(new Color(205, 127, 50));
                g2.setFont(new Font("Arial", Font.BOLD, 15));
                g2.drawString("🥉", 25, y);
            } else {
                g2.setColor(Color.LIGHT_GRAY);
                g2.setFont(new Font("Arial", Font.PLAIN, 15));
                g2.drawString(String.valueOf(i + 1), 30, y);
            }


            //имена в таблице
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 15));
            String name = entry.getKey();
            g2.drawString(name, 60, y);
            //счета в таблице
            if (entry.getKey().equals(mod.getCurrentPlayerName())) {
                g2.setColor(new Color(100, 255, 255));
            } else {
                g2.setColor(new Color(100, 255, 100));
            }
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            g2.drawString(String.valueOf(entry.getValue()), 170, y);

            y += 25;
        }
        drawSeparator(g2, 20, y + 5, 210);
    }
    private void drawSection(Graphics2D g2, String text, int y, Color color) {
        g2.setColor(color);
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString(text, 20, y);
    }
    private void drawSeparator(Graphics2D g2, int x, int y, int width) {
        g2.setColor(new Color(100, 100, 100));
        g2.drawLine(x, y, x + width, y);
    }
}