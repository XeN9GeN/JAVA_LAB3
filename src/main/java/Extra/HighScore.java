package Extra;

import java.io.*;
import java.nio.charset.Charset;

public class HighScore {
    private int high_score;
    private static final String FILENAME = "high_score.txt";

    public HighScore(){
        loadHighScore();
    }
    public int getHigh_score() {
        return high_score;
    }
    public void setHigh_score(int high_score) {
        this.high_score = high_score;
        saveHighScore();
    }

    public void loadHighScore(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(FILENAME),Charset.forName("Windows-1251")))){

            String line = reader.readLine();
            if(line!=null){
                this.high_score=Integer.parseInt(line.trim());
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка при загрузке рекорда: " + e.getMessage());
            this.high_score = 0;
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
