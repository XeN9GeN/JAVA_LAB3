package Model;
import Extra.*;
import Factory.TetrisFactory;
import java.util.Random;
import javax.swing.*;

public class GameModel {
    private TetrominoClass next_tetromino;
    private TetrominoClass cur_tetromino;
    private HighScore high_score;
    private ScoreCalc score_calc;
    private BoardClass board;
    private GameState state;
    private Random random;

    private static final String[] TETROMINO_TYPES = {"I", "O", "L", "S", "Z", "J", "T"};
    private int score;

    //Constructors
    public GameModel() {
        board = new BoardClass();
        state = GameState.PLAY;
        score = 0;
        high_score = new HighScore();
        score_calc = new ScoreCalc();
        random = new Random();
        spawnNewTet();
    }
    public TetrominoClass createRandomPiece() {
        String r_type = TETROMINO_TYPES[random.nextInt(TETROMINO_TYPES.length)];
        return TetrisFactory.create(r_type);
    }


    //Tetromino Actions
    public synchronized void moveCurTet(int dx, int dy) {
        if (state == GameState.PLAY && cur_tetromino.canMove(board, dx, dy)) {
            cur_tetromino.move(dx, dy);
        }
    }
    public synchronized void rotateCurTet() {
        if (state == GameState.PLAY && cur_tetromino.canRotate(board)) {
            cur_tetromino.rotateTet(board);
        }
    }

    public void moveLeft() { moveCurTet(-1, 0); }
    public void moveRight() { moveCurTet(1, 0); }
    public void moveDown() { moveCurTet(0, 1); }
    public void rotateInGame() {
        rotateCurTet();
    }


    //Game Actions
    public synchronized void spawnNewTet() {
        if (next_tetromino == null) {
            next_tetromino = createRandomPiece();
            cur_tetromino = createRandomPiece();
        } else {
            cur_tetromino = next_tetromino;
            next_tetromino = createRandomPiece();
        }
        if (!cur_tetromino.canPlaceRightHere(board)) {
            state = GameState.END;
        }
    }
    private void gameOver(){
        if(state==GameState.END){
            if(score>high_score.getHigh_score()){
                high_score.setHigh_score(score);
            }
            JOptionPane.showMessageDialog(null, "GAME OVER\nYOUR SCORE: "
                    + high_score.getHigh_score(),"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
            reset();
        }
    }
    public synchronized void gameTick() {
        if(state!=GameState.PLAY) return;

        if(cur_tetromino==null){
            spawnNewTet();
            return;
        }

        if(cur_tetromino.canMove(board,0,1)){
            cur_tetromino.move(0,1);
        }
        else{
            board.placeTet(cur_tetromino);
            int l = board.clearFullLines();

            if(l>0){
                score+=score_calc.calcScore(l);
                if(score>high_score.getHigh_score()){
                    high_score.setHigh_score(score);
                }
            }
            spawnNewTet();
            if(state==GameState.END) gameOver();
        }
    }
    private synchronized void reset(){
        board= new BoardClass();
        state=GameState.PLAY;
        score=0;
        next_tetromino=null;
        cur_tetromino=null;
        spawnNewTet();
    }


    //Get
    public TetrominoClass getCurTetromino(){
        return cur_tetromino;
    }
    public TetrominoClass getNextTetromino() {
        return next_tetromino;
    }
    public BoardClass getBoard(){
        return board;
    }
    public int getScore() {
        return score;
    }
}
