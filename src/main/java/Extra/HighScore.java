package Extra;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HighScore {
    private int high_score;
    private static final String FILENAME = "high_score.txt";
    private final Map<String,Integer> scores_table;

    public HighScore() throws Exception {
        scores_table = new java.util.HashMap<>();
        loadHighScore();
    }
    public int getHighScore() {
        return high_score;
    }
    public void setHighScore(String n, int s) {
        int current_high = scores_table.getOrDefault(n, 0);
        if(s>current_high){
            scores_table.put(n,current_high);
            saveHighScore();
        }
    }


    public void loadHighScore() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)));
        String line;
        while((line=reader.readLine())!=null){
            line.trim();
            String[] args = line.split(("="));
            String name = args[0];
            String score = args[1];
            scores_table.put(name,Integer.parseInt(score));
        }
    }
    public void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(FILENAME, StandardCharsets.UTF_8)) {
            for (Map.Entry<String, Integer> w : scores_table.entrySet()) {
                String name = w.getKey();
                Integer score = w.getValue();
                writer.println(name + "=" + score);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
