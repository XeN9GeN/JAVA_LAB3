package View;

import Model.GameModel;
import Model.*;
import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.getColor;

public class ScorePanel extends JPanel{
    private GameModel mod;

    public ScorePanel(GameModel m){
        this.mod=m;
        setPreferredSize(new Dimension(250, BoardClass.HEIGHT * 30));
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics gr){

    }

}
