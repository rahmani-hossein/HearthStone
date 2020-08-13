package swing.Listener;

import client.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        } else if (menu.getCollection() == e.getSource()) {
            controller.getMyFrame().setPanel("collection");
            System.out.println("welcome to collection");
        } else if (menu.getOffline() == e.getSource()) {
            menu.playClickAction();
        } else if (menu.getExit() == e.getSource()) {
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
}
