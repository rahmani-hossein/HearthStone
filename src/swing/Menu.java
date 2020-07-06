package swing;

import CLI.GameMaker;
import CLI.Player;
import logic.Constans;
import model.Deck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements MouseListener {

    public static final String GAME_PANEL = "play";
    private Player player;
    private Constans constans;
    private GameMaker gameMaker;
    private JButton collection;
    private JButton shop;
    private JButton play;
    private Controller controller;
    private BufferedImage backGround;
    private JTextField reader;
    private JButton exit;
    private JButton delete;
    private JComboBox decks;
    private JComboBox passives;// 3 ta random midim


    public Menu(Player player) {
        this.controller = Controller.getInstance();
        this.player = player;
        constans = Constans.getInstance();
        this.setLayout(null);
        collection = new JButton("COLLECTION");
        shop = new JButton("SHOP");
        play = new JButton("PLAY");
        reader = new JTextField();
        exit = new JButton("exit");
        delete = new JButton("delete");
        decks = new JComboBox(deckNames());
        passives = new JComboBox(constans.getPassives());
        init();
        setLayout();

    }

    private void setLayout() {
        collection.setBounds(800, 300, 150, 50);
        shop.setBounds(800, 380, 150, 50);
        play.setBounds(800, 460, 150, 50);
        reader.setBounds(800, 540, 150, 50);
        decks.setBounds(800, 620, 150, 50);
        passives.setBounds(800, 700, 150, 50);
        exit.setBounds(1200, 100, 150, 50);
        exit.addMouseListener(this);
        delete.addMouseListener(this);
        delete.setBounds(1400, 100, 150, 50);
        add(collection);
        add(shop);
        add(reader);
        add(decks);
        add(play);
        add(passives);
        add(exit);
        add(delete);
        shop.addMouseListener(this);
        collection.addMouseListener(this);
        // status.addMouseListener(this);
        play.addMouseListener(this);

    }

    private void init() {
        try {
            String url = String.format("resources\\Image\\MenuBackground.jpg");
            File file = new File(url);
            backGround = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        decks.removeAllItems();
        for (Deck deck : player.getAvailableDecks()) {
            decks.addItem(deck.getName());
        }
        repaint();
        revalidate();
    }

    private String[] deckNames() {
        String[] names = new String[player.getAvailableDecks().size()];
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            names[i] = player.getAvailableDecks().get(i).getName();
        }
        return names;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private void playClickAction(){
        String cur = (String) decks.getItemAt(decks.getSelectedIndex());
        String passive=(String) passives.getItemAt(passives.getSelectedIndex());
        String deckReader = null;
        deckReader = reader.getText();

        gameMaker = new GameMaker(player, deckReader, cur,Controller.getInstance().getGameState(),passive);
        //set Game state gamePlayers
        gameMaker.buildGameState();
        gameMaker.buildPassive();
        GamePanel gamePanel=new GamePanel(Controller.getInstance().getGameState());
        controller.getMyFrame().getMainpanel().add(gamePanel,GAME_PANEL);
        controller.getMyFrame().setPanel("play");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller = Controller.getInstance();
        if (e.getSource() == shop) {
            System.out.println("before go to shop");
            controller.getMyFrame().setPanel("shop");
            System.out.println("welcome to shop");
        } else if (collection == e.getSource()) {
            controller.getMyFrame().setPanel("collection");
        } else if (play == e.getSource()) {
           playClickAction();
        } else if (exit == e.getSource()) {
            controller.exitGame();
        } else if (delete == e.getSource()) {
            String text = JOptionPane.showInputDialog(getParent(), "please type your password", "Delete", JOptionPane.OK_CANCEL_OPTION);
            if ((text != null) && text.equals(controller.getGameState().getPlayer().getPassword())) {
                controller.delete(text);
            }
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
}
//

