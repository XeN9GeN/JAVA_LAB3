import Controller.GameController;
import View.GamePanel;
import Model.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int a =5;
        SwingUtilities.invokeLater(()->{
            GameModel gm = new GameModel();
            GamePanel gp= new GamePanel(gm);
            //random commit com
            GameController gc = new GameController(gm,gp);
        });
    }
}