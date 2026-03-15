import Controller.GameController;
import Extra.HighScore;
import View.*;
import Model.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String h_score_file = args[0];
            // You could set this as a system property or pass to HighScore
            System.setProperty("high_score.txt", h_score_file);
        }

        SwingUtilities.invokeLater(()->{

            GameModel gm = new GameModel();
            GamePanel gp= new GamePanel(gm);
            InfoPanel ip = new InfoPanel(gm);
            PausePanel pp = new PausePanel(gm);
            MenuPanel mp = new MenuPanel(gm);
            PlayerEntryPanel pep = new PlayerEntryPanel(gm);
            ScorePanel sp = new ScorePanel(gm);
            GameController gc = new GameController(gm,gp,ip,pp,mp,pep,sp);
        });
    }
}