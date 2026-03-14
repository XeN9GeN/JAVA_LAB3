package View;

import Model.BoardClass;
import Model.GameModel;

import javax.swing.*;
import java.awt.*;

public class PlayerEntryPanel extends JPanel {
    private final JTextField name_field;//JTextField is a lightweight component that allows the editing of a single line of text

    public PlayerEntryPanel(GameModel m) {
        setLayout(new GridBagLayout());//сетчатая сумка
        name_field = new JTextField(15);
        JLabel label = new JLabel("Enter your name:");//A display area for a short text string or an image, or both.

        this.add(label);
        this.add(name_field);
    }
    public String getPlayerName(){
        return name_field.getText().trim();//Returns the text contained in this TextComponent.
    }
}
