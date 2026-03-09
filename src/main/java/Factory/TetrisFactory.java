package Factory;
import Model.*;
public class TetrisFactory {
    public static TetrominoClass create(String type){
        return switch (type){
            case "I" -> new TetrominoClass(new int[][] {
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0}
            }, 1,"I");
            case "O" -> new TetrominoClass(new int [][]{
                    {1,1},
                    {1,1}
            },2,"O");
            case "L" -> new TetrominoClass(new int [][]{
                    {0,1,0},
                    {0,1,0},
                    {0,1,1}
            },3,"L");
            case "S" -> new TetrominoClass(new int[][]{
                    {0,1,1},
                    {1,1,0},
                    {0,0,0}
            },4,"S");
            case "Z"-> new TetrominoClass(new int[][]{
                    {1,1,0},
                    {0,1,1},
                    {0,0,0}
            },5,"Z");
            case "J" -> new TetrominoClass(new int [][]{
                    {0,1,0},
                    {0,1,0},
                    {1,1,0}
            },6,"J");
            case "T" -> new TetrominoClass(new int[][]{
                    {0,1,0},
                    {1,1,1},
                    {0,0,0}
            },7,"T");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
