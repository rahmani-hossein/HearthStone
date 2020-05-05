package swing;

import logic.CollectionManager;
import logic.Constans;
import model.Deck;
import swing.button.Button;
import swing.button.ButtonC;
import swing.panel.FilterPanel;
import swing.panel.FilterPanelCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Collection extends JPanel implements MouseListener {
    private int space = Constans.space;
    private int sizeW = 200;
    private int sizeH = 275;

    Converter converter = new Converter();
    private CollectionManager collectionManager;
    private ArrayList<BufferedImage> showCards = null;
    private ArrayList<swing.button.ButtonC> showButton = null;
    private Controller controller;
    private JPanel north;
    private JPanel south;
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
    private  JButton changeHero;
    private JTextField name;
    private JButton makeDeck;

    private JTextField text;
    private JButton search;

    public Collection(CollectionManager collectionManager) {
        controller = Controller.getInstance();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.collectionManager = collectionManager;
        south = new JPanel(new FlowLayout());
        north = new JPanel(new FlowLayout());
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
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
        decks=new JComboBox(collectionManager.getdeck());
        add=new JButton("add to deck");
        remove=new JButton("remove");
        deck=new JTextField(10);
        changeName=new JButton("change the name of this deck");
        changeHero=new JButton("change hero of this deck");
        name= new JTextField(10);
        south.add(decks);
        south.add(add);
        south.add(deck);
        south.add(remove);
        south.add(changeName);
        south.add(name);
        south.add(changeHero);
        remove.addMouseListener(this);
        add.addMouseListener(this);
        changeName.addMouseListener(this);
        changeHero.addMouseListener(this);

    }

    private void initNorthButtons() {
        comboBox = new JComboBox(Constans.HeroClass);
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
        showClass.addMouseListener(this);
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

    private ArrayList<swing.button.ButtonC> initButton(ArrayList<BufferedImage> showCards, ArrayList<String> buyable) {
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
            String value = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
            center.hidePanel();
            fillShowButton(value);
            repaint();
            revalidate();
        } else if (e.getSource() == allCard) {
            center.hidePanel();
            showCards = converter.convert(collectionManager.allCards());
            showButton = initButton(showCards, collectionManager.allCards());
            center.setShowButton(showButton);
            repaint();
            revalidate();
        } else if (e.getSource() == knocked) {
            center.hidePanel();
            showCards = converter.convert(collectionManager.knocked());
            showButton = initButton(showCards, collectionManager.knocked());
            center.setShowButton(showButton);
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
        } else if (e.getSource() == search) {
            String value = text.getText();
            center.hidePanel();
            showCards = converter.convert(collectionManager.searchEngine(value));
            showButton = initButton(showCards, collectionManager.searchEngine(value));
            center.setShowButton(showButton);
            repaint();
            revalidate();
        } else if (e.getSource() == exit) {
            controller.exitGame();
        } else if (e.getSource() == back) {
            controller.getMyFrame().setPanel("menu");
        }
        else if (e.getSource()==add){
            String deckName= (String) decks.getItemAt(decks.getSelectedIndex());
            String card= deck.getText();
            Deck deck=collectionManager.getDeck(deckName);
            if (collectionManager.allowAdd(card,deck)){
                collectionManager.addToDeck(card,deck);
            }
            else {
                JOptionPane.showMessageDialog(getParent(),"you can not add it to this deck","add to deck error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource()==remove){
            String deckName= (String) decks.getItemAt(decks.getSelectedIndex());
            String card= deck.getText();
            Deck deck=collectionManager.getDeck(deckName);
            collectionManager.removeFromDeck(card,deck);
            JOptionPane.showMessageDialog(getParent(),"you remove "+card,"remove",JOptionPane.INFORMATION_MESSAGE);
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
