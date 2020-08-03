package swing.panel;

import CLI.utilities;
import client.Controller;
import logic.CollectionManager;
import logic.Constans;
import model.Deck;
import swing.Collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class DeckPanel extends JPanel {
    private CollectionManager collectionManager;
    private Collection parent;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Constans constans =Controller.getInstance().getConstants();
    private JLabel deckName;
    private JLabel winAverage;
    private JLabel wins;
    private JLabel numberOfUse;
    private JLabel averageCost;
    private JLabel averageMana;
    private JLabel heroName;
    private JLabel rarest;
    private JPanel panel;
    private Deck clickedDeck=new Deck();


    public DeckPanel(Collection collection) {
        parent=collection;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(constans.getPanelWidth()/4, 2*constans.getPanelHeight()));
        this.collectionManager = parent.getCollectionManager();
        setLabel();
        Collections.sort(this.collectionManager.getPlayer().getAvailableDecks());
        int n =this. collectionManager.getPlayer().getAvailableDecks().size();
        for (int i = 0; i < this.collectionManager.getPlayer().getAvailableDecks().size(); i++) {
            addButton(this.collectionManager.getPlayer().getAvailableDecks().get(n-i-1).getName());
            //Deck deck=this.collectionManager.getPlayer().getAvailableDecks().get(n - i - 1);
//            JButton button = new JButton(this.collectionManager.getPlayer().getAvailableDecks().get(n - i - 1).getName());
//            button.setName(this.collectionManager.getPlayer().getAvailableDecks().get(n - i - 1).getName());
//            buttons.add(button);
//            add(button);
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                     clickedDeck=collectionManager.getDeck(button.getName());
//                    parent.setShowCards(parent.getConverter().convert(collectionManager.DeckCards(clickedDeck.getName())));
//                   parent.setShowButton(parent.initButton(parent.getShowCards(),collectionManager.DeckCards(clickedDeck.getName())));
//                    parent.getCenter().setShowButton(parent.getShowButton());
//                    deckName.setText("name: "+clickedDeck.getName());
//                    winAverage.setText("winAverage: "+clickedDeck.getWinAverage());
//                    wins.setText("wins: "+clickedDeck.getWins());
//                    numberOfUse.setText("numberOfUse: "+clickedDeck.getNumbreOfUse());
//                    averageCost.setText("averageCost: "+clickedDeck.getCostAverage());
//                    averageMana.setText("averageMana: "+clickedDeck.getManaAverage());
//                    heroName.setText("heroName: "+clickedDeck.getDeckHero().getName());
//                    rarest.setText("rarestCard: "+clickedDeck.getRarest());
//                }
//            });

        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
    }
    public void addButton(String name){
        JButton button = new JButton(name);
        button.setName(name);
        buttons.add(button);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedDeck=collectionManager.getDeck(button.getName());
                parent.setShowCards(parent.getConverter().convert(collectionManager.DeckCards(clickedDeck.getName())));
                parent.setShowButton(parent.initButton(parent.getShowCards(),collectionManager.DeckCards(clickedDeck.getName())));
                parent.getCenter().setShowButton(parent.getShowButton());
                deckName.setText("name: "+clickedDeck.getName());
                winAverage.setText("winAverage: "+clickedDeck.getWinAverage());
                wins.setText("wins: "+clickedDeck.getWins());
                numberOfUse.setText("numberOfUse: "+clickedDeck.getNumbreOfUse());
                averageCost.setText("averageCost: "+clickedDeck.getCostAverage());
                averageMana.setText("averageMana: "+clickedDeck.getManaAverage());
                heroName.setText("heroName: "+clickedDeck.getDeckHero().getName());
                rarest.setText("rarestCard: "+clickedDeck.getRarest());
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"button "+clickedDeck.getName()+" "+ utilities.time()+"\n",true);
            }
        });
    }
    public void removeButton(String name){
        int k=findButton(name);
        if (k!=-1){
            remove(buttons.get(k));
            buttons.remove(buttons.get(k));
        }
       repaint();
       revalidate();
    }
    private int findButton(String name){
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }


    private void setLabel() {
        deckName = new JLabel("name: ");
        winAverage = new JLabel("winAverage: ");
        wins = new JLabel("wins: ");
        numberOfUse = new JLabel("numberOfUse: ");
        averageCost = new JLabel("averageCost: ");
        averageMana = new JLabel("averageMana: ");
        heroName = new JLabel("heroName: ");
        rarest = new JLabel("rarestCard: ");
        panel = new JPanel();
        panel.setVisible(true);
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setSize(400,400);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(deckName);
        panel.add(winAverage);
        panel.add(wins);
        panel.add(numberOfUse);
        panel.add(averageCost);
        panel.add(averageMana);
        panel.add(heroName);
        panel.add(rarest);
    }
}
