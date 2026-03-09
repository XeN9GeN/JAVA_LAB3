package Controller;
import Model.*;
import View.GamePanel;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController{
    private GameModel model;
    private GamePanel view;
    private Timer timer;//javax.swing

    public GameController(GameModel m, GamePanel v){
        this.model=m;
        this.view=v;

        JFrame frame = new JFrame("Tetris");//окно, фрейм под swing

        //configuration
        frame.add(view);//добавляет компоненту в конец Container(класс с компонентами)List в ContentPane посредством перегруженного
        //в JFrame метода addImpl и перенаправления компоненты туда
        frame.pack();//както автоматически задаёт размер окна?
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//show window(true)
        frame.revalidate();//пересчитать размеры и позиции после .add
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//иначе просто скроет, не закроет
        setup(frame);

        timer=new Timer(500,e -> {
            model.gameTick();
            view.repaint();
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
