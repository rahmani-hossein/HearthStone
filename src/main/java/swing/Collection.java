package swing;

import CLI.utilities;
import client.ClientConstants;
import client.Controller;
import client.Converter;
import logic.CollectionManager;
import logic.Constans;
import model.Deck;
import model.Request;
import server.ConstantsLoader;
import swing.button.ButtonC;
import swing.panel.DeckPanel;
import swing.panel.FilterPanelCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Collection extends JPanel  {
    private ClientConstants constans = Controller.getInstance().getClientConstants();
    private int space = constans.getSpace();
    private int sizeW = constans.getCardWidth();//200
    private int sizeH = constans.getCardHeigth();//285

    Converter converter = new Converter();
    private ArrayList<BufferedImage> showCards = new ArrayList<>();
    private ArrayList<swing.button.ButtonC> showButton =new ArrayList<>() ;
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
    private JLabel deckName;
    private JTextField nameText;
    private JButton removeDeck;

    private JTextField text;
    private JButton search;

    public Collection() {
        controller = Controller.getInstance();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        south = new JPanel(new FlowLayout());
        north = new JPanel(new FlowLayout());
        east = new DeckPanel(this);
        east.setBackground(Color.pink);
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(east, BorderLayout.EAST);
        initNorthButtons();
        initSouthButtons();
        center = new FilterPanelCollection(showButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(center);
        add(BorderLayout.CENTER, scrollPane);
        center.setBackground(Color.YELLOW);
    }

    private void initSouthButtons() {
        initDecks();
        initAdd();
        initDeck();
        initRemove();
        initChangeName();
        initName();
        initChangeHero();
        initMakeDeck();
        initHero();
        initHeroName();
        initDeckName();
        initNameText();
        initRemoveDeck();
    }

    public DeckPanel getEast() {
        return east;
    }

    public void setEast(DeckPanel east) {
        this.east = east;
    }

    private void initDecks() {
        Request request =new Request(Controller.getInstance().getClient().getToken(),"deckNames",null,"");
        Controller.getInstance().getClient().getSender().send(request);

    }
    public void innerInitDecks(String[] parameters){
        decks = new JComboBox( parameters);
        south.add(decks);
    }


    private void initAdd() {
        add = new JButton("add to deck");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list = new ArrayList<>();
                String deckName = (String) decks.getItemAt(decks.getSelectedIndex());
                String card = deck.getText();
                list.add(deckName);
                list.add(card);
                Request request = new Request(Controller.getInstance().getClient().getToken(), "add", list, "");
                Controller.getInstance().getClient().getSender().send(request);

            }
        });
        south.add(add);
    }

    private void initDeck() {
        deck = new JTextField(10);
        south.add(deck);
    }

    private void initRemove() {
        remove = new JButton("remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list = new ArrayList<>();
                String deckName = (String) decks.getItemAt(decks.getSelectedIndex());
                String card = deck.getText();
                list.add(deckName);
                list.add(card);
                Request request = new Request(Controller.getInstance().getClient().getToken(), "remove", list, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        south.add(remove);
    }

    private void initChangeName() {
        changeName = new JButton("change the name of this deck");
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list = new ArrayList<>();
                String deckOldName = (String) decks.getItemAt(decks.getSelectedIndex());
                String newName = name.getText();
                list.add(deckOldName);
                list.add(newName);
                Request request = new Request(Controller.getInstance().getClient().getToken(), "changeName", list, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        south.add(changeName);

    }

    private void initName() {
        name = new JTextField(10);
        south.add(name);
    }

    private void initChangeHero() {
        changeHero = new JButton("change hero of this deck");
        changeHero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deckOldName = (String) decks.getItemAt(decks.getSelectedIndex());
                String heroName = name.getText();
                ArrayList<String> list = new ArrayList<>();
                list.add(deckOldName);
                list.add(heroName);
                Request request = new Request(Controller.getInstance().getClient().getToken(), "changeHero", list, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        south.add(changeHero);

    }

    private void initMakeDeck() {
        makeDeck = new JButton("makeDeck");
        makeDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String h = heroName.getText();
                String dN = nameText.getText();
                ArrayList<String> list = new ArrayList<>();
                list.add(dN);
                list.add(h);
                Request request = new Request(Controller.getInstance().getClient().getToken(), "makeDeck", list, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        south.add(makeDeck);

    }

    private void initHero() {
        hero = new JLabel("hero");
        south.add(hero);

    }

    private void initHeroName() {
        heroName = new JTextField(10);
        south.add(heroName);

    }

    private void initDeckName() {
        deckName = new JLabel("name:");
        south.add(deckName);

    }

    private void initNameText() {
        nameText = new JTextField(10);
        south.add(nameText);
    }

    private void initRemoveDeck() {
        removeDeck = new JButton("remove current deck");
        removeDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String delete = (String) decks.getItemAt(decks.getSelectedIndex());
                Request request = new Request(Controller.getInstance().getClient().getToken(), "removeDeck", null, delete);
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        south.add(removeDeck);

    }

    private void initNorthButtons() {
        initComboBox();
        initShowClass();
        initAllCard();
        initKnocked();
        initCost();
        initShowCost();
        initText();
        initSearch();
        initExit();
        initBack();
    }

    private void initKnocked() {
        knocked = new JButton("knockedCards");
        knocked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                center.hidePanel();
                Request request = new Request(Controller.getInstance().getClient().getToken(), "knocked", null, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        north.add(knocked);
    }

    private void initAllCard() {
        allCard = new JButton("all");
        allCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                center.hidePanel();
                Request request = new Request(Controller.getInstance().getClient().getToken(), "allCard", null, "");
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        north.add(allCard);
    }

    private void initCost() {
        cost = new JComboBox(new String[]{1 + "", 2 + "", String.valueOf(3), 4 + "", 5 + "", 6 + "", 7 + "", 8 + "", 9 + "", 10 + ""});
        north.add(cost);

    }

    private void initShowCost() {
        showCost = new JButton("cost");
        showCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) cost.getItemAt(cost.getSelectedIndex());
                center.hidePanel();
                Request request = new Request(Controller.getInstance().getClient().getToken(), "showCost", null, value);
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        north.add(showCost);
    }

    private void initShowClass() {
        showClass = new JButton("showHero");
        showClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
                center.hidePanel();
                Request request = new Request(Controller.getInstance().getClient().getToken(), "showClass", null, value);
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        north.add(showClass);

    }

    private void initText() {
        text = new JTextField(15);
        north.add(text);
    }

    private void initComboBox() {
        comboBox = new JComboBox(ConstantsLoader.getInstance().getServerConstants().getHeroClass());
        north.add(comboBox);

    }

    private void initSearch() {
        search = new JButton("search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = text.getText();
                center.hidePanel();
                Request request = new Request(Controller.getInstance().getClient().getToken(), "search", null, value);
                Controller.getInstance().getClient().getSender().send(request);
            }
        });
        north.add(search);

    }

    private void initExit() {
        exit = new JButton("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exitGame();
            }
        });
        north.add(exit);
    }

    private void initBack() {
        back = new JButton("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getMenu().update();
                controller.getMyFrame().setPanel("menu");
                Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress(), "back to menu" + " " + utilities.time() + "\n", true);
            }
        });
        north.add(back);
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
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

    public ArrayList<swing.button.ButtonC> initButton(ArrayList<String> madenazar) {
        ArrayList<swing.button.ButtonC> showButton = new ArrayList<>();
        showCards = converter.convert(madenazar);
        for (int i = 0; i < showCards.size(); i++) {
            swing.button.ButtonC button = new ButtonC(showCards.get(i), madenazar.get(i), ((i % 5) + 1) * space + ((i % 5) * sizeW), ((i / 5) + 1) * space + ((i / 5) * sizeH));
            addMouseListener(button);
            showButton.add(button);
        }
        return showButton;
    }

    public void setGraphicOfDeck(String deckOldName, String newName) {
        decks.removeItem(deckOldName);
        decks.insertItemAt(newName, 0);
        east.removeButton(deckOldName);
        east.addButton(newName);
        //  decks = new JComboBox(collectionManager.getdeck());
        System.out.println("renaming is done");
        repaint();
        revalidate();
    }
    public void setGraphicOfMakeDeck(String name) {
        decks.insertItemAt(name, 0);
        east.addButton(name);
        repaint();
        revalidate();
    }
    public void setGraphicRemoveDeck(String delete){
        east.removeButton(delete);
        decks.removeItem(delete);
        repaint();
        revalidate();
    }




    public JComboBox getDecks() {
        return decks;
    }

    public void setDecks(JComboBox decks) {
        this.decks = decks;
    }
}
