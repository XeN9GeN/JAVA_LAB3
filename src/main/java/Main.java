import Controller.GameController;
import View.GamePanel;
import Model.*;
import View.InfoPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            GameModel gm = new GameModel();
            GamePanel gp= new GamePanel(gm);
            InfoPanel ip = new InfoPanel(gm);
            GameController gc = new GameController(gm,gp,ip);
        });
    }
}