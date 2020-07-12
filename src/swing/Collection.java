package swing;

import CLI.utilities;
import logic.CollectionManager;
import logic.Constans;
import model.Deck;
import swing.button.Button;
import swing.button.ButtonC;
import swing.panel.DeckPanel;
import swing.panel.FilterPanel;
import swing.panel.FilterPanelCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Collection extends JPanel implements MouseListener {
    private Constans constans=Controller.getInstance().getConstants();
    private int space = constans.getSpace();
    private int sizeW = constans.getCardWidth();//200
    private int sizeH =constans.getCardHeigth();//285

    Converter converter = new Converter();
    private CollectionManager collectionManager;
    private ArrayList<BufferedImage> showCards = null;
    private ArrayList<swing.button.ButtonC> showButton = null;
    private Controller controller;
    private JPanel north;
    private JPanel south;
    private DeckPanel east;
    private FilterPanelCollection center;
    private JButton exit;
    private JButton back;
    private JButton showClass;
    private JButton allCard;
    private JButton knocked;
    private JComboBox comboBox;
    private JComboBox cost;
    private JButton showCost;
    private JComboBox decks;
    private JButton add;
    private JTextField deck;
    private JButton remove;
    private JButton changeName;
    private JButton changeHero;
    private JTextField name;
    private JButton makeDeck;
    private JLabel hero;
    private JTextField heroName;
    private  JLabel deckName;
    private JTextField nameText;
    private JButton removeDeck;

    private JTextField text;
    private JButton search;

    public Collection(CollectionManager collectionManager) {
        controller = Controller.getInstance();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.collectionManager = collectionManager;
        south = new JPanel(new FlowLayout());
        north = new JPanel(new FlowLayout());
        east=new DeckPanel(this);
        east.setBackground(Color.pink);
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(east,BorderLayout.EAST);
        initNorthButtons();
        initSouthButtons();
        showCards = converter.convert(collectionManager.findHeroClass("neutral"));
        showButton = initButton(showCards, collectionManager.findHeroClass("neutral"));
        center = new FilterPanelCollection(showButton, collectionManager);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(center);
        Controller.getInstance().setCollection(this);
        // controller.getShop().add(scrollPane);
        add(BorderLayout.CENTER, scrollPane);

        center.setBackground(Color.YELLOW);
//
//        showCards = converter.convert(shopManager.showSellable());
//        showButton = initButton(showCards, shopManager.showSellable());
//        center = new FilterPanel(showButton);
    }

    private void initSouthButtons() {
        decks = new JComboBox(collectionManager.getdeck());
        add = new JButton("add to deck");
        remove = new JButton("remove");
        deck = new JTextField(10);
        changeName = new JButton("change the name of this deck");
        changeHero = new JButton("change hero of this deck");
        name = new JTextField(10);
        makeDeck=new JButton("makeDeck");
        hero=new JLabel("hero");
        heroName=new JTextField(10);
        deckName=new JLabel("name:");
        nameText=new JTextField(10);
        removeDeck=new JButton("remove current deck");
        south.add(decks);
        south.add(add);
        south.add(deck);
        south.add(remove);
        south.add(changeName);
        south.add(name);
        south.add(changeHero);
        south.add(makeDeck);
        south.add(hero);
        south.add(heroName);
        south.add(deckName);
        south.add(nameText);
        south.add(removeDeck);
        remove.addMouseListener(this);
        add.addMouseListener(this);
        changeName.addMouseListener(this);
        changeHero.addMouseListener(this);
        makeDeck.addMouseListener(this);
        removeDeck.addMouseListener(this);

    }

    private void initNorthButtons() {
        comboBox = new JComboBox(constans.getHeroClass());
        showClass = new JButton("showHero");
        allCard = new JButton("all");
        knocked = new JButton("knockedCards");
        cost = new JComboBox(new String[]{1 + "", 2 + "", String.valueOf(3), 4 + "", 5 + "", 6 + "", 7 + "", 8 + "", 9 + "", 10 + ""});
        showCost = new JButton("cost");
        text = new JTextField(15);
        search = new JButton("search");
        exit = new JButton("exit");
        back = new JButton("back");
        north.add(comboBox);
        north.add(showClass);
        north.add(allCard);
        north.add(knocked);
        north.add(cost);
        north.add(showCost);
        north.add(text);
        north.add(search);
        north.add(exit);
        north.add(back);
        showClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
                center.hidePanel();
                fillShowButton(value);
                repaint();
                revalidate();
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"you clicked to see "+value+"s cards  "+ utilities.time()+"\n",true);
            }
        });
        allCard.addMouseListener(this);
        knocked.addMouseListener(this);
        showCost.addMouseListener(this);
        search.addMouseListener(this);
        exit.addMouseListener(this);
        back.addMouseListener(this);
    }

    private void fillShowButton(String value) {

        showCards = converter.convert(collectionManager.findHeroClass(value));
        showButton = initButton(showCards, collectionManager.findHeroClass(value));
        center.setShowButton(showButton);

    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ArrayList<BufferedImage> getShowCards() {
        return showCards;
    }

    public void setShowCards(ArrayList<BufferedImage> showCards) {
        this.showCards = showCards;
    }

    public ArrayList<ButtonC> getShowButton() {
        return showButton;
    }

    public void setShowButton(ArrayList<ButtonC> showButton) {
        this.showButton = showButton;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public JPanel getNorth() {
        return north;
    }

    public void setNorth(JPanel north) {
        this.north = north;
    }

    public JPanel getSouth() {
        return south;
    }

    public void setSouth(JPanel south) {
        this.south = south;
    }

    public FilterPanelCollection getCenter() {
        return center;
    }

    public void setCenter(FilterPanelCollection center) {
        this.center = center;
    }

    public ArrayList<swing.button.ButtonC> initButton(ArrayList<BufferedImage> showCards, ArrayList<String> buyable) {
        ArrayList<swing.button.ButtonC> showButton = new ArrayList<>();
        for (int i = 0; i < showCards.size(); i++) {
            swing.button.ButtonC button = new ButtonC(showCards.get(i), buyable.get(i), ((i % 5) + 1) * space + ((i % 5) * sizeW), ((i / 5) + 1) * space + ((i / 5) * sizeH));
            addMouseListener(button);
            showButton.add(button);
        }
        //makePanel(showButton);
        return showButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == showClass) {

        } else if (e.getSource() == allCard) {
            center.hidePanel();
            showCards = converter.convert(collectionManager.allCards());
            showButton = initButton(showCards, collectionManager.allCards());
            center.setShowButton(showButton);
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you clicked to see all cards  "+ utilities.time()+"\n",true);
            repaint();
            revalidate();
        } else if (e.getSource() == knocked) {
            center.hidePanel();
            showCards = converter.convert(collectionManager.knocked());
            showButton = initButton(showCards, collectionManager.knocked());
            center.setShowButton(showButton);
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you clicked to see knocked cards  "+ utilities.time()+"\n",true);
            repaint();
            revalidate();
        } else if (e.getSource() == showCost) {
            String value = (String) cost.getItemAt(cost.getSelectedIndex());
            center.hidePanel();
            showCards = converter.convert(collectionManager.findCost(Integer.valueOf(value)));
            showButton = initButton(showCards, collectionManager.findCost(Integer.valueOf(value)));
            center.setShowButton(showButton);
            repaint();
            revalidate();
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you clicked to show cost cards with cost "+value+" "+ utilities.time()+"\n",true);
        } else if (e.getSource() == search) {
            String value = text.getText();
            center.hidePanel();
            showCards = converter.convert(collectionManager.searchEngine(value));
            showButton = initButton(showCards, collectionManager.searchEngine(value));
            center.setShowButton(showButton);
            repaint();
            revalidate();
//            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
//            Controller.myLogger(st1,"you search "+value+" "+ utilities.time()+"\n",true);
        } else if (e.getSource() == exit) {
            controller.exitGame();
        } else if (e.getSource() == back) {
            controller.getMenu().update();
            controller.getMyFrame().setPanel("menu");
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"back to menu"+" "+ utilities.time()+"\n",true);
        } else if (e.getSource() == add) {
            String deckName = (String) decks.getItemAt(decks.getSelectedIndex());
            String card = deck.getText();
            Deck deck = collectionManager.getDeck(deckName);
            if (collectionManager.allowAdd(card, deck)) {
                collectionManager.addToDeck(card, deck);
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"you add  "+card+"to "+deckName+" "+ utilities.time()+"\n",true);
            } else {
                JOptionPane.showMessageDialog(getParent(), "you can not add it to this deck", "add to deck error", JOptionPane.ERROR_MESSAGE);
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"you cant add  "+card+"to  "+deckName+" "+ utilities.time()+"\n",true);
            }
        } else if (e.getSource() == remove) {
            String deckName = (String) decks.getItemAt(decks.getSelectedIndex());
            String card = deck.getText();
            Deck deck = collectionManager.getDeck(deckName);
            collectionManager.removeFromDeck(card, deck);
            JOptionPane.showMessageDialog(getParent(), "you remove " + card, "remove", JOptionPane.INFORMATION_MESSAGE);
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you remove card  "+card+" from deck "+deckName+" "+ utilities.time()+"\n",true);
        } else if (e.getSource() == changeName) {
            String deckOldName = (String) decks.getItemAt(decks.getSelectedIndex());
            String newName = name.getText();
            collectionManager.changeDeckName(collectionManager.getDeck(deckOldName), newName);
            decks.removeItem(deckOldName);
            decks.insertItemAt(newName,0);
            east.removeButton(deckOldName);
            east.addButton(newName);
          //  decks = new JComboBox(collectionManager.getdeck());
            System.out.println("renaming is done");
            repaint();
            revalidate();
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you change the name of deck "+deckOldName+"to  "+newName+" "+ utilities.time()+"\n",true);
        } else if (e.getSource() == changeHero) {
            String deckOldName = (String) decks.getItemAt(decks.getSelectedIndex());
            String heroName = name.getText();
            Deck deck1 = collectionManager.getDeck(deckOldName);
            if (collectionManager.allowChangeHero(deck1)) {
                collectionManager.changeHero(deck1, heroName);
                decks = new JComboBox(collectionManager.getdeck());
                repaint();
                revalidate();
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"you change hero of deck "+deckOldName+" to "+heroName+" "+ utilities.time()+"\n",true);
            }

        }
        else if(e.getSource()==makeDeck){
            Deck deck1=null;
            String h= heroName.getText();
            String dN= nameText.getText();
            deck1=collectionManager.makeDeck(dN,h);
            //decks.addItem();
            decks.insertItemAt(deck1.getName(),0);
            east.addButton(deck1.getName());
            repaint();
            revalidate();
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you make a deck with name "+nameText+" "+ utilities.time()+"\n",true);
        }
        else if (e.getSource()==removeDeck){
            String delete = (String) decks.getItemAt(decks.getSelectedIndex());
            east.removeButton(delete);
            decks.removeItem(delete);
            collectionManager.deleteDeck(collectionManager.getDeck(delete));
           repaint();
           revalidate();
            String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
            Controller.myLogger(st1,"you removed "+delete+" "+ utilities.time()+"\n",true);
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
