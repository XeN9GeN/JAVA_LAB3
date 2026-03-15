package Extra;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HighScore {
    private static final String FILENAME = "high_score.txt";
    private final Map<String,Integer> scores_table;

    private int high_score_global;


    public HighScore() throws Exception {
        scores_table = new java.util.HashMap<>();
        loadHighScore();
    }


    public int getHighScoreGLobal() {
        return high_score_global;
    }
    public int getPlayerHighScore(String name) {
        return scores_table.getOrDefault(name, 0);
    }
    public Map<String, Integer> getScoresTable() {
        return scores_table;
    }

    public void setHighScore(String n, int s) {
        if (n == null || n.trim().isEmpty()) return;
        if (s < 0) return;

        int current_high = scores_table.getOrDefault(n, 0);

        if(s > current_high){
            scores_table.put(n, s);
            if(s > high_score_global) high_score_global = s;
            saveHighScore();
        }
    }

    public void loadHighScore() throws Exception{
        File file = new File(FILENAME);
        if(!file.exists()) return;

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while ((line=reader.readLine())!=null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] args = line.split("=");
                if (args.length == 2) {

                    String name = args[0].trim();
                    String s = args[1].trim();

                    if(Integer.parseInt(s)<0) {
                        int j=Integer.parseInt(s);
                        j=0;
                        s=String.valueOf(j);
                    }

                    try {
                        scores_table.put(name, Integer.parseInt(s));
                        if (Integer.parseInt(s) > high_score_global) {
                            high_score_global = Integer.parseInt(s);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score format: " + s);
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        }finally {
            if(reader!=null) reader.close();
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
