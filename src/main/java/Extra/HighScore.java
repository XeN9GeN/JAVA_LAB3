package Extra;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HighScore {
    private static final String FILENAME = "high_score.txt";
    private final Map<String,Integer> scores_table;

    private int high_score_global;
    private int high_score_cur_player;
    private String current_player_name;


    public HighScore() throws Exception {
        scores_table = new java.util.HashMap<>();
        loadHighScore();
    }


    public int getHighScoreGLobal() {
        return high_score_global;
    }
    public int getCurPlayerHighScore(){return high_score_cur_player;}


    public void setHighScore(String n, int s) {
        //if cur=null -> cur=0
        int current_high = scores_table.getOrDefault(n, 0);//только от игрока

        if(s>current_high){
            scores_table.put(n,s);

            if(s> high_score_global)
                high_score_global = s;
            if (n.equals(current_player_name)) {
                high_score_cur_player = s;
            }
            saveHighScore();
        }
    }


    public void loadHighScore() throws Exception{
        File file = new File(FILENAME);

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
