package swing.panel;

import CLI.utilities;
import client.ClientConstants;
import client.Controller;
import logic.CollectionManager;
import logic.Constans;
import model.Deck;
import model.Player;
import model.Request;
import swing.Collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class DeckPanel extends JPanel {
    private Collection parent;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ClientConstants constans =Controller.getInstance().getClientConstants();
    private JLabel deckName;
    private JLabel cup;
    private JLabel winAverage;
    private JLabel wins;
    private JLabel numberOfUse;
    private JLabel averageCost;
    private JLabel averageMana;
    private JLabel heroName;
    private JLabel rarest;
    private JPanel panel;
    private Deck clickedDeck;


    public DeckPanel(Collection collection) {
        parent=collection;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(constans.getPanelWidth()/4, 2*constans.getPanelHeight()));
        setLabel();
       update();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
    }

    public void update(){
        Player player = Controller.getInstance().getGameState().getPlayer();
        Collections.sort(player.getAvailableDecks());
        int n =player.getAvailableDecks().size();
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            addButton(player.getAvailableDecks().get(n-i-1).getName());

        }
    }

    public void addButton(String name){
        JButton button = new JButton(name);
        button.setName(name);
        buttons.add(button);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request(Controller.getInstance().getClient().getToken(),"deckButton",null,name);
                Controller.getInstance().getClient().getSender().send(request);


            }
        });
    }

    public void setThings(Deck deck){
        clickedDeck=deck;
        deckName.setText("name: "+clickedDeck.getName());
        cup.setText("cup: "+clickedDeck.getCup());
        winAverage.setText("winAverage: "+clickedDeck.getWinAverage());
        wins.setText("wins: "+clickedDeck.getWins());
        numberOfUse.setText("numberOfUse: "+clickedDeck.getNumbreOfUse());
        averageCost.setText("averageCost: "+clickedDeck.getCostAverage());
        averageMana.setText("averageMana: "+clickedDeck.getManaAverage());
        heroName.setText("heroName: "+clickedDeck.getDeckHero().getName());
        rarest.setText("rarestCard: "+clickedDeck.getRarest());
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
        cup =new JLabel("cup: ");
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
        panel.add(cup);
        panel.add(winAverage);
        panel.add(wins);
        panel.add(numberOfUse);
        panel.add(averageCost);
        panel.add(averageMana);
        panel.add(heroName);
        panel.add(rarest);
    }
}

