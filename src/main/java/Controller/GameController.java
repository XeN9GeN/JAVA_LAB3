package Controller;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController{
    private final GameModel game_model;
    private final GamePanel view_panel;
    private final InfoPanel info_panel;
    private final PausePanel pause_panel;
    private final MenuPanel menu_panel;
    private final PlayerEntryPanel player_panel;
    private Timer timer;//javax.swing
    private JFrame frame;


    public GameController(GameModel gm, GamePanel gp, InfoPanel ip, PausePanel pp, MenuPanel mp, PlayerEntryPanel pep) {
        this.game_model = gm;
        this.view_panel = gp;
        this.info_panel = ip;
        this.pause_panel = pp;
        this.menu_panel = mp;
        player_panel = pep;

        setupFrame();
        setupKey();
        startGame();
    }
    private void setupFrame(){
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int cellSize = 30;
        int boardWidth = BoardClass.WIDTH * cellSize;
        int totalHeight = BoardClass.HEIGHT * cellSize;
        int infoWidth = 250;
        int totalWidth = boardWidth + infoWidth;


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(BoardClass.WIDTH * 45 + 100, BoardClass.HEIGHT * 30));

        //игра
        JPanel main_game_panel = new JPanel(new BorderLayout());
        main_game_panel.setBounds(0,0,totalWidth,totalHeight);
        main_game_panel.add(view_panel,BorderLayout.CENTER);//читать опиcание, поле в центре
        main_game_panel.add(info_panel,BorderLayout.EAST);

        //меню поверх
        pause_panel.setBounds(0, 0, totalWidth,totalHeight);
        pause_panel.setVisible(false);
        menu_panel.setBounds(0, 0, totalWidth,totalHeight);
        menu_panel.setVisible(false);
        player_panel.setBounds(0,0,totalWidth,totalHeight);
        player_panel.setVisible(true);


        //все слои для frame(main_game_panel + pause/menu
        layeredPane.add(main_game_panel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pause_panel,JLayeredPane.POPUP_LAYER);
        layeredPane.add(menu_panel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(player_panel,JLayeredPane.DRAG_LAYER);


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
        player_panel.getNameField().addActionListener(e -> {//атоматом подразумевает нажатие enter как авто генерация
            //и ActionEvent и вызывает все зарегистрированные ActionListener
            String name = player_panel.getPlayerName();
            if(!name.isEmpty()){
                game_model.setCurrentPlayerName(name);
                player_panel.setVisible(false);
                menu_panel.setVisible(true);
                frame.repaint();
            }
        });
    }
    private synchronized void setupKey() {
        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent k) {
//                if (player_panel.isVisible()) {
                    //KeyListener не получает события клавиш, когда фокус находится на JTextField во время ввода имени игрока
                    //поэтому нажатие enter физически не работает в данных if'ах
//                    if (k.getKeyCode() == KeyEvent.VK_ENTER) {
//                        String name = player_panel.getPlayerName();
//                        if (!name.isEmpty()) {
//                            game_model.setCurrentPlayerName(name);
//                            player_panel.setVisible(false);
//                            menu_panel.setVisible(true);
//                        }
//                    }
//                    frame.repaint();
//                }

                if(k.getKeyCode()==KeyEvent.VK_F1) {
                    game_model.reset();
                    menu_panel.setVisible(false);
                    timer.start();
                    frame.repaint();
                    return;
                }

                if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (game_model.getState() == GameState.PLAY) {
                        game_model.pause();
                        timer.stop();
                        pause_panel.setVisible(true);
                    } else if (game_model.getState() == GameState.PAUSE) {
                        game_model.pause();
                        timer.start();
                        pause_panel.setVisible(false);
                    }
                    frame.repaint();
                    return;
                }

                if (game_model.getState() == GameState.PLAY) {
                    switch (k.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> game_model.moveLeft();
                        case KeyEvent.VK_RIGHT -> game_model.moveRight();
                        case KeyEvent.VK_DOWN -> game_model.moveDown();
                        case KeyEvent.VK_UP -> game_model.rotateInGame();
                    }
                    frame.repaint();
                }
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
