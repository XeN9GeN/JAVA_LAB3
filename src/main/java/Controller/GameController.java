package Controller;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController{
    private GameModel game_model;
    private GamePanel view_panel;
    private InfoPanel info_panel;
    private PausePanel pause_panel;
    private MenuPanel menu_panel;
    private Timer timer;//javax.swing
    private JFrame frame;
    private JLayeredPane layeredPane;


    public GameController(GameModel gm, GamePanel gp, InfoPanel ip, PausePanel pp,MenuPanel mp) {
        this.game_model = gm;
        this.view_panel = gp;
        this.info_panel = ip;
        this.pause_panel = pp;
        this.menu_panel = mp;

        setupFrame();
        setupKey();
        startGame();
    }
    private void setupFrame(){
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int cellSize = 30;
        int boardWidth = BoardClass.WIDTH * cellSize;
        int boardHeight = BoardClass.HEIGHT * cellSize;
        int infoWidth = 250;
        int totalWidth = boardWidth + infoWidth;
        int totalHeight = boardHeight;


        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(BoardClass.WIDTH * 50 + 100, BoardClass.HEIGHT * 30));

        //игра
        JPanel main_game_panel = new JPanel(new BorderLayout());
        main_game_panel.setBounds(0,0,totalWidth,totalHeight);
        main_game_panel.add(view_panel,BorderLayout.CENTER);//читать опиcание, поле в центре
        main_game_panel.add(info_panel,BorderLayout.EAST);

        //меню поверх
        pause_panel.setBounds(0, 0, totalWidth,totalHeight);
        pause_panel.setVisible(false);

        menu_panel.setBounds(0, 0, totalWidth,totalHeight);
        menu_panel.setVisible(true);

        //все слои для frame
        layeredPane.add(main_game_panel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pause_panel,JLayeredPane.POPUP_LAYER);
        layeredPane.add(menu_panel, JLayeredPane.MODAL_LAYER);


        frame.add(layeredPane);//добавляет компоненту в конец Container(класс с компонентами)List в ContentPane посредством перегруженного
        //в JFrame метода addImpl и перенаправления компоненты туда
        frame.pack();//както автоматически задаёт размер окна?
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//show window(true)
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.revalidate();//пересчитать размеры и позиции после .add
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame=frame;
    }
    private synchronized void setupKey() {
        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent k) {
                if(k.getKeyCode()==KeyEvent.VK_F1) {
                    game_model.reset();
                    menu_panel.setVisible(false);
                    timer.start();
                    return;
                }

                if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (game_model.getState() == GameState.PLAY) {
                        game_model.pause();
                        timer.stop();
                        pause_panel.setVisible(true);
                    }
                } else if (game_model.getState() == GameState.PAUSE) {
                    game_model.pause();
                    timer.start();
                    pause_panel.setVisible(false);
                }

                if (game_model.getState() == GameState.PLAY) {
                    switch (k.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> game_model.moveLeft();
                        case KeyEvent.VK_RIGHT -> game_model.moveRight();
                        case KeyEvent.VK_DOWN -> game_model.moveDown();
                        case KeyEvent.VK_UP -> game_model.rotateInGame();
                    }
                }
                frame.repaint();
            }
        });
    }
    private synchronized void startGame(){
        if(game_model.getState()!=GameState.PLAY){
            game_model.pause();
        }
        timer = new Timer(600, e->{
            if(game_model.getState()==GameState.PLAY){
                game_model.gameTick();
                view_panel.repaint();
                info_panel.repaint();
            }
        });
        timer.start();
    }
}
