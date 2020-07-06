package swing;

import CLI.GameState;
import CLI.Player;
import logic.Constans;
import model.Deck;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

   private GameState gameState;
   private Constans constans=Constans.getInstance();
    //graphic items



    public GamePanel(GameState gameState){
        this.gameState=gameState;



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


    }
}
