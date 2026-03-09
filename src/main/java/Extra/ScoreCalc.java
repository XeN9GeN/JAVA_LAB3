package Extra;

public class ScoreCalc {
    public int calcScore(int cleared_lines) {
        return switch (cleared_lines) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
    }
}
