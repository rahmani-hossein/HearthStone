package swing;

import CLI.GameMaker;
import client.ClientConstants;
import model.Player;
import client.Controller;
import logic.Constans;
import model.Deck;
import swing.Listener.MenuListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel  {

    public static final String GAME_PANEL = "play";
    private MenuListener menuListener;
    private Player player;
    private ClientConstants constans;
    private GameMaker gameMaker;
    private JButton collection;
    private JButton shop;
    private JButton online;
    private JButton offline;
    private JButton rank;
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
        constans = Controller.getInstance().getClientConstants();
        this.setLayout(null);
        menuListener= new MenuListener(this);
        collection = new JButton("COLLECTION");
        shop = new JButton("SHOP");
        online = new JButton("Online");
        offline= new JButton("Offline");
        rank =new JButton("rank");
        reader = new JTextField(20);
        exit = new JButton("exit");
        delete = new JButton("delete");
        decks = new JComboBox(deckNames());
        passives = new JComboBox(constans.getPassives());
        init();
        setLayout();

    }

    private void setLayout() {
        rank.setBounds(800,220,150,50);
        collection.setBounds(800, 300, 150, 50);
        shop.setBounds(800, 380, 150, 50);
        offline.setBounds(800, 460, 150, 50);
        reader.setBounds(800, 540, 150, 50);
        decks.setBounds(800, 620, 150, 50);
        passives.setBounds(800, 700, 150, 50);
        online.setBounds(800,780,150,50);
        exit.setBounds(1200, 100, 150, 50);
        exit.addMouseListener(menuListener);
        delete.addMouseListener(menuListener);
        delete.setBounds(1400, 100, 150, 50);
        add(collection);
        add(shop);
        add(reader);
        add(decks);
        add(passives);
        add(exit);
        add(delete);
        add(online);
        add(offline);
        add(rank);
        shop.addMouseListener(menuListener);
        collection.addMouseListener(menuListener);
        // status.addMouseListener(this);
        offline.addMouseListener(menuListener);
        online.addMouseListener(menuListener);
        rank.addMouseListener(menuListener);

    }



    private void init() {
        try {
            String url = String.format("src/main/resources/image/MenuBackground.jpg");
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

    public void playClickAction(){
        String cur = (String) decks.getItemAt(decks.getSelectedIndex());
        String passive=(String) passives.getItemAt(passives.getSelectedIndex());
        String deckReader = null;
        deckReader = reader.getText();

        gameMaker = new GameMaker(player, deckReader, cur,Controller.getInstance().getGameState(),passive);
        //set Game state gamePlayers
        gameMaker.buildGameState();
        gameMaker.buildPassive();
        for (int i = 0; i < Controller.getInstance().getGameState().getFreind().getInitCard().size(); i++) {
            System.out.println(Controller.getInstance().getGameState().getFreind().getInitCard().get(i).toString());
        }
        GamePanel gamePanel=new GamePanel(constans.getPanelWidth(),constans.getPanelHeight(),Controller.getInstance().getGameState());
        controller.getMyFrame().getMainpanel().add(gamePanel,GAME_PANEL);
        controller.setGamePanel(gamePanel);
        controller.getMyFrame().setPanel("play");
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ClientConstants getConstans() {
        return constans;
    }

    public void setConstans(ClientConstants constans) {
        this.constans = constans;
    }

    public GameMaker getGameMaker() {
        return gameMaker;
    }

    public void setGameMaker(GameMaker gameMaker) {
        this.gameMaker = gameMaker;
    }

    public JButton getCollection() {
        return collection;
    }

    public void setCollection(JButton collection) {
        this.collection = collection;
    }

    public JButton getShop() {
        return shop;
    }

    public void setShop(JButton shop) {
        this.shop = shop;
    }

    public JButton getOnline() {
        return online;
    }

    public void setOnline(JButton online) {
        this.online = online;
    }

    public JButton getOffline() {
        return offline;
    }

    public void setOffline(JButton offline) {
        this.offline = offline;
    }

    public JButton getRank() {
        return rank;
    }

    public void setRank(JButton rank) {
        this.rank = rank;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public BufferedImage getBackGround() {
        return backGround;
    }

    public void setBackGround(BufferedImage backGround) {
        this.backGround = backGround;
    }

    public JTextField getReader() {
        return reader;
    }

    public void setReader(JTextField reader) {
        this.reader = reader;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public JButton getDelete() {
        return delete;
    }

    public void setDelete(JButton delete) {
        this.delete = delete;
    }

    public JComboBox getDecks() {
        return decks;
    }

    public void setDecks(JComboBox decks) {
        this.decks = decks;
    }

    public JComboBox getPassives() {
        return passives;
    }

    public void setPassives(JComboBox passives) {
        this.passives = passives;
    }

}

//

