import Controller.GameController;
import View.GamePanel;
import Model.*;
import View.InfoPanel;
import View.PausePanel;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        String h_score_file = args[0];
        try(Reader reader = new InputStreamReader(new FileInputStream(h_score_file), Charset.forName("Windows-1251"))) {
            int h_score=reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(()->{
            GameModel gm = new GameModel();
            GamePanel gp= new GamePanel(gm);
            InfoPanel ip = new InfoPanel(gm);
            PausePanel mp = new PausePanel(gm);
            GameController gc = new GameController(gm,gp,ip,mp);
        });
    }
}