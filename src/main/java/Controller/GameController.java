package Controller;
import Model.*;
import View.GamePanel;
import View.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController{
    private GameModel model;
    private GamePanel view;
    private InfoPanel panel;
    private Timer timer;//javax.swing

    public GameController(GameModel m, GamePanel v, InfoPanel i){
        this.model=m;
        this.view=v;
        this.panel=i;

        JFrame frame = new JFrame("Tetris");
        JPanel main_panel = new JPanel(new BorderLayout());
        main_panel.add(view,BorderLayout.CENTER);//читать описание, поле в центре
        main_panel.add(panel,BorderLayout.EAST);

        frame.add(main_panel);//добавляет компоненту в конец Container(класс с компонентами)List в ContentPane посредством перегруженного
        //в JFrame метода addImpl и перенаправления компоненты туда
        frame.pack();//както автоматически задаёт размер окна?
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//show window(true)
        frame.revalidate();//пересчитать размеры и позиции после .add
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//иначе просто скроет, не закроет
        setup(frame);

        timer=new Timer(600,e -> {
            model.gameTick();
            view.repaint();
            panel.repaint();
        });
        timer.start();
    }
    private void setup(JFrame frame){
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent k){
                switch (k.getKeyCode()){
                    case KeyEvent.VK_LEFT -> model.moveLeft();
                    case KeyEvent.VK_RIGHT -> model.moveRight();
                    case KeyEvent.VK_DOWN -> model.moveDown();
                    case KeyEvent.VK_UP -> model.rotateInGame();
                }
                view.repaint();
            }
        });
    }
}
