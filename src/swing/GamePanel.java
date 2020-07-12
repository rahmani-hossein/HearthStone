package swing;

import CLI.GameState;
import CLI.utilities;
import logic.Constans;
import model.GamePlayer;
import swing.button.Picture;
import swing.panel.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePanel extends JPanel implements MouseListener {

    private GameState gameState;
    private Constans constans = Controller.getInstance().getConstants();
    private Controller controller = Controller.getInstance();
    //graphic items
    private PlayPanel init;
    private JButton nextTurn;
    private JButton exit;
    private JButton back;
    private ArrayList<Picture> freindHand = new ArrayList<>();
    private ArrayList<Picture> enemyHand = new ArrayList<>();
    private LinkedList<Picture> freindGround = new LinkedList<>();
    private LinkedList<Picture> enemyGround = new LinkedList<>();
    private Picture freindWeapen = null;
    private Picture enemyWeapen = null;
    private Picture freind ;
    private Picture enemy ;


    public GamePanel(int x , int y , GameState gameState) {
        this.gameState = gameState;
        this.setPreferredSize(new Dimension(x,y));
        setLayout(null);
        addMouseListener(this);
        setTurnButton();
        setBackButton();
        setExitButton();
        setBackground(Color.pink);
        init =new PlayPanel(constans.getGroundX(),constans.getMinYGame(),constans.getPanelWidth()-constans.getGroundX(),constans.getMaxYGame(),gameState.getFreind(),this);

    }

    private void updateGameStateForPainting() {
        setFriendHand();
        setEnemyHand();
        setFriendGround();
        setEnemyGround();
        setEnemyHero();
        setFreindHero();
    }

    private void setFriendHand() {
        ArrayList<Picture> list = new ArrayList<>();
        for (int i = 0; i < gameState.getFreind().getHand().size(); i++) {
            Picture picture = new Picture(((i % 12) + 1) * constans.getHandSpace() + ((i % 12) * constans.getSizeX()), constans.getMaxYGame(), gameState.getFreind().getHand().get(i));
            list.add(picture);
        }
        freindHand = list;
    }

    private void setFreindHero() {
        Picture heroView = new Picture(constans.getFreindHeroX(), constans.getFriendHeroY(), gameState.getFreind());
        freind = heroView;
    }

    private void setEnemyHero() {
        Picture heroView1 = new Picture(constans.getEnemyHeroX(), constans.getEnemyHeroY(), gameState.getEnemy());
        enemy = heroView1;
    }

    private void setEnemyHand() {
        ArrayList<Picture> list = new ArrayList<>();
        for (int i = 0; i < gameState.getEnemy().getHand().size(); i++) {
            Picture picture = new Picture(((i % 12) + 1) * constans.getHandSpace() + ((i % 12) * constans.getSizeX()), 0, gameState.getEnemy().getHand().get(i));
            list.add(picture);
        }
        enemyHand = list;
    }

    private void setFriendGround() {
        LinkedList<Picture> list = new LinkedList<>();
        for (int i = 0; i < gameState.getFreind().getGround().size(); i++) {
            Picture picture = new Picture(constans.getGroundX() + ((i % 7) + 1) * constans.getGroundSpace() + ((i % 7) * constans.getSizeX()), constans.getFriendGroundY(), gameState.getFreind().getGround().get(i));
            list.add(picture);
        }
        freindGround = list;
    }

    private void setEnemyGround() {
        LinkedList<Picture> pictureLinkedList = new LinkedList<>();
        for (int i = 0; i < gameState.getEnemy().getGround().size(); i++) {
            Picture picture = new Picture(constans.getGroundX() + ((i % 7) + 1) * constans.getGroundSpace() + ((i % 7) * constans.getSizeX()), constans.getEnenmyGroundY(), gameState.getEnemy().getGround().get(i));
            pictureLinkedList.add(picture);
        }
        enemyGround = pictureLinkedList;
    }

    private void setExitButton() {
        exit = new JButton("exit");
        exit.setBounds(1500, 50, 80, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().exitGame();
            }
        });
        add(exit);
    }

    private void setBackButton() {
        back = new JButton("back");
        back.setBounds(1600, 50, 80, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getMyFrame().setPanel("menu");
                doLog("back to menu");
            }
        });
        add(back);
    }

    private void setTurnButton() {
        nextTurn = new JButton("turn");
        nextTurn.setBounds(1500, 500, 100, 75);
        nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nextTurn method in gameManager
            }
        });
        add(nextTurn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateGameStateForPainting();
        g.drawLine(0,constans.getEnemyHeroY(),constans.getPanelWidth(),constans.getEnemyHeroY());
        g.drawLine(0,constans.getMaxYGame(),constans.getPanelWidth(),constans.getMaxYGame());
        g.drawLine(constans.getGroundX(),constans.getEnemyHeroY(),constans.getGroundX(),constans.getMaxYGame());
        g.drawLine(constans.getPanelWidth()-constans.getGroundX(),constans.getEnemyHeroY(),constans.getPanelWidth()-constans.getGroundX(),constans.getMaxYGame());



        // if its turn of friend
        if (!gameState.isTurn()) {
            for (int i = 0; i < freindHand.size(); i++) {
                freindHand.get(i).paint(g);
            }
            for (int i = 0; i < enemyHand.size(); i++) {
                enemyHand.get(i).paintSkin(g);
            }
        } else {
            for (int i = 0; i < enemyHand.size(); i++) {
                enemyHand.get(i).paint(g);
            }
            for (int i = 0; i < freindHand.size(); i++) {
                freindHand.get(i).paintSkin(g);
            }
        }
        // for ground
        for (int i = 0; i < freindGround.size(); i++) {
            freindGround.get(i).paint(g);
        }
        for (int i = 0; i < enemyGround.size(); i++) {
            enemyGround.get(i).paint(g);
        }
        if (freindWeapen != null) {
            freindWeapen.paint(g);
        }
        if (enemyWeapen != null) {
            enemyWeapen.paint(g);
        }
        freind.paintHero(g);
        enemy.paintHero(g);


    }


    private void doLog(String log) {
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() + Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1, log + " " + utilities.time() + "\n", true);

    }

    private void doLog(String log, GamePlayer gamePlayer) {
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() + Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1, gamePlayer.getNameOfPlayer() + " " + log + " " + utilities.time() + "\n", true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() +"   "+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
