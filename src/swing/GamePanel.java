package swing;

import CLI.GameState;
import CLI.LogicMapper;
import CLI.utilities;
import logic.Constans;
import model.*;
import swing.button.Picture;
import swing.panel.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private GameState gameState;
    private Constans constans = Controller.getInstance().getConstants();
    private Controller controller = Controller.getInstance();
    private LogicMapper logicMapper = controller.getLogicMapper();
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
    private Picture freind;
    private Picture enemy;
    private Picture selected = null;
    private Picture target = null;


    public GamePanel(int x, int y, GameState gameState) {
        this.gameState = gameState;
        this.setPreferredSize(new Dimension(x, y));
        setLayout(null);
        addMouseListener(this);
        setTurnButton();
        setBackButton();
        setExitButton();
        setBackground(Color.pink);
        init = new PlayPanel(constans.getGroundX(), constans.getMinYGame(), constans.getPanelWidth() - constans.getGroundX(), constans.getMaxYGame(), gameState.getFreind(), this);

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
        exit.setBounds(1450, 300, 80, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exitGame();
            }
        });
        add(exit);
    }

    private void setBackButton() {
        back = new JButton("back");
        back.setBounds(1550, 300, 80, 50);
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
                logicMapper.responce(LogicMapper.LogicRequest.TURN);
                repaint();
                revalidate();
            }
        });
        add(nextTurn);
    }

    private void paintMana(Graphics g, GamePlayer gamePlayer, boolean freind) {
        int y = 0;
        if (freind) {
            y = constans.getFriendManaY();
        } else {
            y = constans.getEnemyManaY();
        }
        g.setColor(Color.BLUE);
        g.drawString("Mana: " + gamePlayer.getMana(), constans.getManaX(), y);
        g.setColor(Color.BLACK);
    }

    private void paintLog(Graphics g) {
        g.setColor(Color.BLUE);
        if (gameState.getNote().size() < 10) {
            for (int i = 0; i < gameState.getNote().size(); i++) {
                g.drawString(gameState.getNote().get(i), 0, constans.getMinYGame() + i * constans.getNoteSize());
            }
        } else {
            int j = 0;
            for (int i = gameState.getNote().size() - 10; i < gameState.getNote().size(); i++) {
                g.drawString(gameState.getNote().get(i), 0, constans.getMinYGame() + j * constans.getNoteSize());
                j++;
            }
        }
        g.setColor(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateGameStateForPainting();
        initPaint(g);
        paintLog(g);
        // if its turn of friend
        paintHands(g);
        // for ground
        paintGrounds(g);
        freind.paintHero(g);
        enemy.paintHero(g);


    }

    private void initPaint(Graphics g) {
        g.drawLine(0, constans.getEnemyHeroY(), constans.getPanelWidth(), constans.getEnemyHeroY());
        g.drawLine(0, constans.getMaxYGame(), constans.getPanelWidth(), constans.getMaxYGame());
        g.drawLine(constans.getGroundX(), constans.getEnemyHeroY(), constans.getGroundX(), constans.getMaxYGame());
        g.drawLine(constans.getPanelWidth() - constans.getGroundX(), constans.getEnemyHeroY(), constans.getPanelWidth() - constans.getGroundX(), constans.getMaxYGame());


        paintMana(g, gameState.getFreind(), true);
        paintMana(g, gameState.getEnemy(), false);
    }

    private void paintHands(Graphics g) {
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
    }

    private void paintGrounds(Graphics g) {
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
    }

    private void exclusiveRepaint() {
        selected = null;
        target = null;
        repaint();
        revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameState.isTurn()) {// nobat friend
            freindClicked(e);

        } else {//nobat enemy
            enemyClicked(e);
        }

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void enemyClicked(MouseEvent e) {
        //hand
        for (int i = 0; i < enemyHand.size(); i++) {
            if ((enemyHand.get(i).getX() <= e.getX()) && (enemyHand.get(i).getX() + enemyHand.get(i).getSizeX() >= e.getX()) && (enemyHand.get(i).getY() <= e.getY()) && (enemyHand.get(i).getSizeY() + enemyHand.get(i).getY() >= e.getY())) {
                selected = enemyHand.get(i);
                if (constans.getTarget().get(selected.getName()) == 0) {
                    logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy().getHand()), null);
                    exclusiveRepaint();
                }
            }
        }
        //ground
        for (int i = 0; i < enemyGround.size(); i++) {
            if ((enemyGround.get(i).getX() <= e.getX()) && (enemyGround.get(i).getX() + enemyGround.get(i).getSizeX() >= e.getX()) && (enemyGround.get(i).getY() <= e.getY()) && (enemyGround.get(i).getSizeY() + enemyGround.get(i).getY() >= e.getY())) {
                if (selected != null) {
                    if (constans.getTarget().get(selected.getName()) == 1) {
                        target = enemyGround.get(i);
                        logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy().getHand()), returnCard(target, gameState.getEnemy().getGround()));
                        exclusiveRepaint();
                    }
                } else {
                    selected = enemyGround.get(i);
                }
            }
        }
        //weapen
        if (enemyWeapen != null && (enemyWeapen.getX() <= e.getX()) && (enemyWeapen.getX() + enemyWeapen.getSizeX() >= e.getX()) && (enemyWeapen.getY() <= e.getY()) && (enemyWeapen.getSizeY() + enemyWeapen.getY() >= e.getY())) {
            if (enemyWeapen != null) {
                selected = enemyWeapen;
            }
        }

        //target
        for (int i = 0; i < freindGround.size(); i++) {
            if ((freindGround.get(i).getX() <= e.getX()) && (freindGround.get(i).getX() + freindGround.get(i).getSizeX() >= e.getX()) && (freindGround.get(i).getY() <= e.getY()) && (freindGround.get(i).getSizeY() + freindGround.get(i).getY() >= e.getY())) {
                if (target != null) {
                    //nothing to do never
                } else {
                    if (selected != null) {
                        target = freindGround.get(i);
                        if (enemyHand.contains(selected)) {
                            if (constans.getTarget().get(selected.getName()) == 2) {
                                logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy().getHand()), returnCard(target, gameState.getFreind().getGround()));
                                exclusiveRepaint();
                            }
                        } else if (enemyGround.contains(selected)) {
                            Minion minion = returnCard(selected, gameState.getEnemy().getGround());
                            if (minion.getAttackInRound() == 0 && minion.getLiveInRound() >= 1) {
                                logicMapper.responce(LogicMapper.LogicRequest.ATTACKWITHMINION, gameState.getEnemy(), gameState.getFreind(), minion, returnCard(target, gameState.getFreind().getGround()));
                                minion.setAttackInRound(1);
                                exclusiveRepaint();
                            } else {
                                JOptionPane.showMessageDialog(controller.getMyFrame(), "you use this minion in this turn or it isnt available in this turn", "attackError", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (gameState.getEnemy().getMyWeapen() != null && gameState.getEnemy().getMyWeapen().getName().equalsIgnoreCase(selected.getName())) {
                            logicMapper.responce(LogicMapper.LogicRequest.ATTACKWEAPEN, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy()), returnCard(target, gameState.getFreind().getGround()));
                            exclusiveRepaint();
                        }
                    }
                }
            }
        }
        if ((freind.getX() <= e.getX()) && (freind.getX() + freind.getSizeX() >= e.getX()) && (freind.getY() <= e.getY()) && (freind.getSizeY() + freind.getY() >= e.getY())) {
            if (target != null) {
                //nothing to do never
            } else {
                if (selected != null) {
                    target = freind;
                    if (enemyHand.contains(selected)) {
                        if (constans.getTarget().get(selected.getName()) == 2) {
                            logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy().getHand()), returnHero(target, gameState.getFreind()));
                            exclusiveRepaint();
                        }
                    } else if (enemyGround.contains(selected)) {
                        Minion minion = returnCard(selected, gameState.getEnemy().getGround());
                        if (minion.getAttackInRound() == 0 && minion.getLiveInRound() >= 1) {
                            logicMapper.responce(LogicMapper.LogicRequest.ATTACKWITHMINION, gameState.getEnemy(), gameState.getFreind(), minion, returnHero(target, gameState.getFreind()));
                            minion.setAttackInRound(1);
                            exclusiveRepaint();
                        } else {
                            JOptionPane.showMessageDialog(controller.getMyFrame(), "you use this minion in this turn or it isnt available in this turn", "attackError", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (gameState.getEnemy().getMyWeapen() != null && gameState.getEnemy().getMyWeapen().getName().equalsIgnoreCase(selected.getName())) {
                        logicMapper.responce(LogicMapper.LogicRequest.ATTACKWEAPEN, gameState.getEnemy(), gameState.getFreind(), returnCard(selected, gameState.getEnemy()), returnHero(target, gameState.getFreind()));
                        exclusiveRepaint();
                    }
                }
            }
        }
    }

    private void freindClicked(MouseEvent e) {
        //hand
        for (int i = 0; i < freindHand.size(); i++) {
            if ((freindHand.get(i).getX() <= e.getX()) && (freindHand.get(i).getX() + freindHand.get(i).getSizeX() >= e.getX()) && (freindHand.get(i).getY() <= e.getY()) && (freindHand.get(i).getSizeY() + freindHand.get(i).getY() >= e.getY())) {
                selected = freindHand.get(i);
                if (constans.getTarget().get(selected.getName()) == 0) {
                    logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind().getHand()), null);
                    exclusiveRepaint();
                }
            }
        }
        //ground
        for (int i = 0; i < freindGround.size(); i++) {
            if ((freindGround.get(i).getX() <= e.getX()) && (freindGround.get(i).getX() + freindGround.get(i).getSizeX() >= e.getX()) && (freindGround.get(i).getY() <= e.getY()) && (freindGround.get(i).getSizeY() + freindGround.get(i).getY() >= e.getY())) {
                if (selected != null) {
                    if (constans.getTarget().get(selected.getName()) == 1) {
                        target = freindGround.get(i);
                        logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind().getHand()), returnCard(target, gameState.getFreind().getGround()));
                        exclusiveRepaint();
                    }
                } else {
                    selected = freindGround.get(i);
                }
            }
        }
        //weapen
        if (freindWeapen != null && (freindWeapen.getX() <= e.getX()) && (freindWeapen.getX() + freindWeapen.getSizeX() >= e.getX()) && (freindWeapen.getY() <= e.getY()) && (freindWeapen.getSizeY() + freindWeapen.getY() >= e.getY())) {
            if (freindWeapen != null) {
                selected = freindWeapen;
            }
        }


        //target
        for (int i = 0; i < enemyGround.size(); i++) {
            if ((enemyGround.get(i).getX() <= e.getX()) && (enemyGround.get(i).getX() + enemyGround.get(i).getSizeX() >= e.getX()) && (enemyGround.get(i).getY() <= e.getY()) && (enemyGround.get(i).getSizeY() + enemyGround.get(i).getY() >= e.getY())) {
                if (target != null) {
                    //nothing to do never
                } else {
                    if (selected != null) {
                        target = enemyGround.get(i);
                        if (freindHand.contains(selected)) {
                            if (constans.getTarget().get(selected.getName()) == 2) {
                                logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind().getHand()), returnCard(target, gameState.getEnemy().getGround()));
                                exclusiveRepaint();
                            }
                        } else if (freindGround.contains(selected)) {
                            Minion minion = returnCard(selected, gameState.getFreind().getGround());
                            if (minion.getLiveInRound() >= 1 && minion.getAttackInRound() == 0) {
                                logicMapper.responce(LogicMapper.LogicRequest.ATTACKWITHMINION, gameState.getFreind(), gameState.getEnemy(), minion, returnCard(target, gameState.getEnemy().getGround()));
                                minion.setAttackInRound(1);
                                exclusiveRepaint();
                            } else {
                                JOptionPane.showMessageDialog(controller.getMyFrame(), "you use this minion in this turn or it isnt available in this turn", "attackError", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (gameState.getFreind().getMyWeapen() != null && gameState.getFreind().getMyWeapen().getName().equalsIgnoreCase(selected.getName())) {
                            logicMapper.responce(LogicMapper.LogicRequest.ATTACKWEAPEN, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind()), returnCard(target, gameState.getEnemy().getGround()));
                            exclusiveRepaint();
                        }
                    }
                }
            }
        }
        if ((enemy.getX() <= e.getX()) && (enemy.getX() + enemy.getSizeX() >= e.getX()) && (enemy.getY() <= e.getY()) && (enemy.getSizeY() + enemy.getY() >= e.getY())) {
            if (target != null) {
                //nothing to do never
            } else {
                if (selected != null) {
                    target = enemy;
                    if (freindHand.contains(selected)) {
                        if (constans.getTarget().get(selected.getName()) == 2) {
                            logicMapper.responce(LogicMapper.LogicRequest.DRAWHAND, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind().getHand()), returnHero(target, gameState.getEnemy()));
                            exclusiveRepaint();
                        }
                    } else if (freindGround.contains(selected)) {
                        Minion minion = returnCard(selected, gameState.getFreind().getGround());
                        if (minion.getLiveInRound() >= 1 && minion.getAttackInRound() == 0) {
                            logicMapper.responce(LogicMapper.LogicRequest.ATTACKWITHMINION, gameState.getFreind(), gameState.getEnemy(), minion, returnHero(target, gameState.getEnemy()));
                            minion.setAttackInRound(1);
                            exclusiveRepaint();
                        } else {
                            JOptionPane.showMessageDialog(controller.getMyFrame(), "you use this minion in this turn or it isnt available in this turn", "attackError", JOptionPane.ERROR_MESSAGE);
                        }

                    } else if (gameState.getFreind().getMyWeapen() != null && gameState.getFreind().getMyWeapen().getName().equalsIgnoreCase(selected.getName())) {
                        logicMapper.responce(LogicMapper.LogicRequest.ATTACKWEAPEN, gameState.getFreind(), gameState.getEnemy(), returnCard(selected, gameState.getFreind()), returnHero(target, gameState.getEnemy()));
                        exclusiveRepaint();
                    }
                }
            }
        }
    }

    private card returnCard(Picture picture, ArrayList<card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            if (picture.getName().equalsIgnoreCase(hand.get(i).getName())) {
                return hand.get(i);
            }
        }
        return null;
    }

    private Minion returnCard(Picture picture, LinkedList<Minion> ground) {
        for (int i = 0; i < ground.size(); i++) {
            if ((picture.getName().equalsIgnoreCase(ground.get(i).getName())) &&
                    picture.getHp() == ground.get(i).getHealth() &&
                    picture.getLiveInRound() == ground.get(i).getLiveInRound() &&
                    picture.isTaunt() == ground.get(i).isTaunt() &&
                    picture.isDivineSheild() == ground.get(i).isDivineSheild()) {
                return ground.get(i);
            }

        }
        return null;
    }

    private Hero returnHero(Picture picture, GamePlayer gamePlayer) {
        if (picture.getName().equalsIgnoreCase(gamePlayer.getHero().getName())) {
            return gamePlayer.getHero();
        }
        return null;
    }

    private weapen returnCard(Picture picture, GamePlayer gamePlayer) {
        if (picture.getName().equalsIgnoreCase(gamePlayer.getMyWeapen().getName())) {
            return gamePlayer.getMyWeapen();
        }
        return null;
    }

    private void doLog(String log) {
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() + Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1, log + " " + utilities.time() + "\n", true);

    }

    private void doLog(String log, GamePlayer gamePlayer) {
        String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() + Controller.getInstance().getGameState().getPlayer().getPassword());
        Controller.myLogger(st1, gamePlayer.getNameOfPlayer() + " " + log + " " + utilities.time() + "\n", true);
    }
    //getter& setter

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Constans getConstans() {
        return constans;
    }

    public void setConstans(Constans constans) {
        this.constans = constans;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public LogicMapper getLogicMapper() {
        return logicMapper;
    }

    public void setLogicMapper(LogicMapper logicMapper) {
        this.logicMapper = logicMapper;
    }

    public PlayPanel getInit() {
        return init;
    }

    public void setInit(PlayPanel init) {
        this.init = init;
    }

    public JButton getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(JButton nextTurn) {
        this.nextTurn = nextTurn;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }

    public ArrayList<Picture> getFreindHand() {
        return freindHand;
    }

    public void setFreindHand(ArrayList<Picture> freindHand) {
        this.freindHand = freindHand;
    }

    public ArrayList<Picture> getEnemyHand() {
        return enemyHand;
    }

    public void setEnemyHand(ArrayList<Picture> enemyHand) {
        this.enemyHand = enemyHand;
    }

    public LinkedList<Picture> getFreindGround() {
        return freindGround;
    }

    public void setFreindGround(LinkedList<Picture> freindGround) {
        this.freindGround = freindGround;
    }

    public LinkedList<Picture> getEnemyGround() {
        return enemyGround;
    }

    public void setEnemyGround(LinkedList<Picture> enemyGround) {
        this.enemyGround = enemyGround;
    }

    public Picture getFreindWeapen() {
        return freindWeapen;
    }

    public void setFreindWeapen(Picture freindWeapen) {
        this.freindWeapen = freindWeapen;
    }

    public Picture getEnemyWeapen() {
        return enemyWeapen;
    }

    public void setEnemyWeapen(Picture enemyWeapen) {
        this.enemyWeapen = enemyWeapen;
    }

    public Picture getFreind() {
        return freind;
    }

    public void setFreind(Picture freind) {
        this.freind = freind;
    }

    public Picture getEnemy() {
        return enemy;
    }

    public void setEnemy(Picture enemy) {
        this.enemy = enemy;
    }

    public Picture getSelected() {
        return selected;
    }

    public void setSelected(Picture selected) {
        this.selected = selected;
    }

    public Picture getTarget() {
        return target;
    }

    public void setTarget(Picture target) {
        this.target = target;
    }
}
