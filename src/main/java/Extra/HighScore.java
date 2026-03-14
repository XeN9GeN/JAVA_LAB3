package Extra;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

public class HighScore {
    private int high_score;
    private static final String FILENAME = "high_score.txt";
    private Map<String,Integer> scores;

    public HighScore() throws Exception {
        loadHighScore();
    }
    public int getHigh_score() {
        return high_score;
    }
    public void setHigh_score(int high_score) {
        this.high_score = high_score;
        saveHighScore();
    }


    public void loadHighScore() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME), "Windows-1251"));
        String line;

        while((line = reader.readLine())!=null){
            line=line.trim();
            String[] args = line.split("=");
            String name = args[0];
            String sc=args[1];
            scores.put(name,Integer.getInteger(sc));
        }

    }




        public void saveHighScore(){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                (new FileOutputStream(FILENAME),Charset.forName("Windows-1251")))){

            writer.write(String.valueOf(high_score));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
