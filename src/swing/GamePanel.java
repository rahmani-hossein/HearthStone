package swing;

import CLI.GameState;
import CLI.Player;
import logic.Constans;
import model.Deck;
import model.card;
import swing.button.HeroView;
import swing.button.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GamePanel extends JPanel {

   private GameState gameState;
   private Constans constans=Constans.getInstance();
    //graphic items
    private JButton nextTurn;
    private ArrayList<Picture> freindHand=new ArrayList<>();
    private ArrayList<Picture> enemyHand =new ArrayList<>();
    private LinkedList<Picture> freindGround= new LinkedList<>();
    private LinkedList<Picture> enemyGround= new LinkedList<>();
    private Picture freindWeapen=null;
    private Picture enemyWeapen =null;
    private HeroView freind=null;
    private HeroView enemy=null;


    public GamePanel(GameState gameState){
        this.gameState=gameState;
        setButton();


    }

    private void setLists(){
        setFriendHand();
        setEnemyHand();
    }
    private void setFriendHand(){
        ArrayList<Picture> list =new ArrayList<>();
        for (int i = 0; i < gameState.getFreind().getHand().size(); i++) {
           Picture picture =new Picture(((i % 12) + 1) * constans.getHandSpace() + ((i% 12) * constans.getSizeX()),constans.getMaxYGame(),gameState.getFreind().getHand().get(i));
           list.add(picture);
        }
        freindHand=list;
    }

    private void setEnemyHand(){
        ArrayList<Picture> list =new ArrayList<>();
        for (int i = 0; i < gameState.getEnemy().getHand().size(); i++) {
            Picture picture =new Picture(((i % 12) + 1) * constans.getHandSpace() + ((i% 12) * constans.getSizeX()),constans.getMinYGame(),gameState.getEnemy().getHand().get(i));
            list.add(picture);
        }
        enemyHand=list;
    }

    private void setFriendGround(){
        LinkedList<Picture> list =new LinkedList<>();
        for (int i = 0; i < gameState.getFreind().getGround().size(); i++) {
            Picture picture =new Picture(((i % 7) + 1)*constans.getGroundSpace()  + ((i% 7) * constans.getSizeX()),constans.getMinYGame()+(((i / 7) + 1) * constans.getHandSpace() + ((i/12) * constans.getSizeY())))
        }
    }

    private void setButton(){
        nextTurn=new JButton("turn");
        nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nextTurn method in gameManager
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


    }
}
