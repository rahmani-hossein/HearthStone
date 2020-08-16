package swing.Listener;

import client.Controller;
import model.Deck;
import model.Player;
import model.Request;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuListener implements MouseListener {

    private swing.Menu menu;
    private Controller controller;

    public MenuListener(swing.Menu menu) {
        this.menu = menu;
        controller = Controller.getInstance();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == menu.getShop()) {
            controller.getMyFrame().setPanel("shop");
            System.out.println("welcome to shop");
        }
        else if (menu.getCollection() == e.getSource()) {
            controller.getMyFrame().setPanel("collection");
            System.out.println("welcome to collection");
        }
        else if (menu.getTraining() == e.getSource()) {
            String cur = (String) menu.getDecks().getItemAt(menu.getDecks().getSelectedIndex());
            String passive=(String) menu.getPassives().getItemAt(menu.getPassives().getSelectedIndex());
           String  deckReader = menu.getReader().getText();
            ArrayList<String> parameters= new ArrayList<>();
            parameters.add(deckReader);
            parameters.add(cur);
            parameters.add(passive);
            Request request = new Request(Controller.getInstance().getClient().getToken(),"training",parameters,Controller.getInstance().getStringValueOfGameState(Controller.getInstance().getGameState()));
            Controller.getInstance().getClient().getSender().send(request);
          //  menu.playClickAction();
        }
        else if (menu.getOnline()==e.getSource()){
            String cur = (String) menu.getDecks().getItemAt(menu.getDecks().getSelectedIndex());
            String passive=(String) menu.getPassives().getItemAt(menu.getPassives().getSelectedIndex());
            String  deckReader = menu.getReader().getText();
            Controller.getInstance().getGameState().getPlayer().setCurrentDeck(findDeck(Controller.getInstance().getGameState().getPlayer(),cur));
            ArrayList<String> parameters= new ArrayList<>();
            if (deckReader!=null&&deckReader.length()>=2){
                parameters.add(deckReader);
            }else {
                parameters.add(" ");
            }
            parameters.add(cur);
            parameters.add(passive);
            parameters.add(Controller.getInstance().getGameState().getPlayer().getCurrentDeck().getCup()+"");
            Request request = new Request(Controller.getInstance().getClient().getToken(),"online",parameters,Controller.getInstance().getStringValueOfGameState(Controller.getInstance().getGameState()));
            Controller.getInstance().getClient().getSender().send(request);
        }
        else if (menu.getExit() == e.getSource()) {
            controller.exitGame();
        } else if (menu.getDelete() == e.getSource()) {
            String text = JOptionPane.showInputDialog(controller.getMyFrame(), "please type your password", "Delete", JOptionPane.OK_CANCEL_OPTION);
            if ((text != null) && text.equals(controller.getGameState().getPlayer().getPassword())) {
                controller.delete(text);
            }
        } else if (menu.getRank() == e.getSource()) {
            Controller.getInstance().getMyFrame().setPanel("rank");
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

    private Deck findDeck(Player player, String deck) {
        for (int i = 0; i < player.getAvailableDecks().size(); i++) {
            Deck Mydeck = player.getAvailableDecks().get(i);
            if (Mydeck.getName().equalsIgnoreCase(deck)) {
                return Mydeck;
            }
        }
        return null;
    }
}
