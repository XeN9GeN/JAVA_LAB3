package Model;
import Extra.*;
import Factory.TetrisFactory;
import java.util.Random;

public class GameModel {
    private BoardClass board;
    private TetrominoClass cur_tetromino;
    private TetrominoClass next_tetromino;
    private int score;
    private GameState state;
    private HighScore high_score;
    private Random random;
    private static final String[] TETROMINO_TYPES = {"I", "O", "L", "S", "Z", "J", "T"};

    public GameModel() {
        board = new BoardClass();
        state = GameState.PLAY;
        score = 0;
        random = new Random();
    }

    public TetrominoClass createRandomPiece() {
        String r_type = TETROMINO_TYPES[random.nextInt(TETROMINO_TYPES.length)];
        return TetrisFactory.create(r_type);
    }

    public void spawnNewTet() {
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


    public void moveCurTet(int dx, int dy) {
        if (state == GameState.PLAY && cur_tetromino.canMove(board, dx, dy)) {
            cur_tetromino.move(dx, dy);
        }
    }
    public void rotateCurTet() {
        if (state == GameState.PLAY && cur_tetromino.canRotate(board)) {
            cur_tetromino.rotateTet(board);
        }
    }

    public void moveLeft() { moveCurTet(-1, 0); }
    public void moveRight() { moveCurTet(1, 0); }
    public void moveDown() { moveCurTet(0, 1); }
    public void moveUp() { moveCurTet(0, -1); }
    public void rotateInGame() {
        rotateCurTet();
    }













    public int[][] getCurTetromino(){
        return cur_tetromino.getTetromino();
    }
    public int[][] getNextTetromino() {
        return next_tetromino.getTetromino();
    }
    public GameState getState() {
        return state;
    }
    public BoardClass getBoard() {
        return board;
    }
    public int getScore() {
        return score;
    }
    public HighScore getHighScore() {
        return high_score;
    }
}
