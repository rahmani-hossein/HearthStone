package swing.panel;

import CLI.utilities;
import client.ClientConstants;
import client.Controller;
import logic.CardManager;
import logic.Constans;
import logic.ShopManager;
import model.Request;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CardPanelCollection extends JPanel implements MouseListener {
    private ClientConstants constans = Controller.getInstance().getClientConstants();

    private CardManager cardManager;
    private String name;
    private int cost = 0;
    private String type = "";
    private String rarity = "";
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;
    private JButton buy;
    private JButton exit;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public JButton getBuy() {
        return buy;
    }

    public void setBuy(JButton buy) {
        this.buy = buy;
    }

    public CardPanelCollection(String name) {
        cardManager = new CardManager();
        this.name = name;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        buy = new JButton("buy");
        exit = new JButton("exit");
        exit.addMouseListener(this);
        buy.addMouseListener(this);
        add(buy);
        add(exit);

    }

    public void createLabels(String name, String rarity, String type, int cost) {
        info1 = new JLabel("name:" + name);
        info2 = new JLabel("cost:" + cost);
        info3 = new JLabel("type:" + type);
        info4 = new JLabel("rarity:" + rarity);
        add(info1);
        add(info2);
        add(info3);
        add(info4);
    }

    ArrayList<String> parameters = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == buy) {
            Controller.getInstance().setBuy(this);
            int action = JOptionPane.showConfirmDialog(Controller.getInstance().getMyFrame(), "do you want to buy?", "buy Title", JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                Request request = new Request(Controller.getInstance().getClient().getToken(), "buyCollection", parameters, name);
                Controller.getInstance().getClient().getSender().send(request);
            }

        } else if (e.getSource() == exit) {
            this.setVisible(false);
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
