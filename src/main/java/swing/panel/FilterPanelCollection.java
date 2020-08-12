package swing.panel;

import client.ClientConstants;
import client.Controller;
import client.LogicManagerClient;
import logic.CollectionManager;
import logic.Constans;
import swing.button.ButtonC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FilterPanelCollection extends JPanel implements MouseListener {
    private ClientConstants constans = Controller.getInstance().getClientConstants();
    ArrayList<ButtonC> showButton;
    private ButtonC onclick;
    private int width = constans.getCardWidth();
    private int height = constans.getCardHeigth();
    private int space = constans.getSpace();
    private int sizeW = width + space;
    private int sizeH = height + space;
    private ArrayList<CardPanelCollection> cardPanelCollections = new ArrayList<>();
    private LogicManagerClient logicManagerClient=new LogicManagerClient(Controller.getInstance().getGameState().getPlayer());


    public ArrayList<CardPanelCollection> getCardPanelCollections() {
        return cardPanelCollections;
    }

    public void setCardPanelCollections(ArrayList<CardPanelCollection> cardPanelCollections) {
        this.cardPanelCollections = cardPanelCollections;
    }

    public FilterPanelCollection(ArrayList<swing.button.ButtonC> showCards, int width, int height) {
        this.showButton = showCards;
        this.setSize(width, height);
        this.add(new JScrollBar());
        this.setBackground(Color.YELLOW);
    }

    public ArrayList<swing.button.ButtonC> getShowButton() {
        return showButton;
    }

    public void setShowButton(ArrayList<swing.button.ButtonC> showButton) {
        this.showButton = showButton;
        repaint();
        revalidate();
    }

    public void hidePanel() {
        for (int i = 0; i < cardPanelCollections.size(); i++) {
            cardPanelCollections.get(i).setVisible(false);
        }
    }

    public FilterPanelCollection(ArrayList<ButtonC> showButton) {
        this.showButton = showButton;
        setPreferredSize(new Dimension(constans.getPanelWidth(), 2 * constans.getPanelHeight() + constans.getPanelHeight()));
        addMouseListener(this);
        repaint();
        revalidate();
        setLayout(null);
    }

    public ButtonC getOnclick() {
        return onclick;
    }

    public void setOnclick(ButtonC onclick) {
        this.onclick = onclick;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < showButton.size(); i++) {
            if (logicManagerClient.isKnocked(showButton.get(i).getName())) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(showButton.get(i).getWidth(), showButton.get(i).getHeight(), showButton.get(i).getSizeW(), showButton.get(i).getSizeH());
                g.setColor(Color.WHITE);
            }
            showButton.get(i).paint(g);
        }

    }

    private void create(int i) {
        onclick = showButton.get(i);
        onclick.makePanel(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < showButton.size(); i++) {
            if ((e.getX() >= showButton.get(i).getWidth()) && (e.getX() <= showButton.get(i).getWidth() + width) && (e.getY() >= showButton.get(i).getHeight()) && (e.getY() <= showButton.get(i).getHeight() + height)) {
                create(i);
                repaint();
                revalidate();
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
