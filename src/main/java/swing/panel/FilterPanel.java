package swing.panel;

import CLI.utilities;
import client.ClientConstants;
import client.Controller;
import logic.Constans;
import model.Request;
import swing.button.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FilterPanel extends JPanel implements MouseListener {
    private ClientConstants constans=Controller.getInstance().getClientConstants();
    ArrayList<Button> showButton = new ArrayList<>();
    private Button onclick;
    private int width = constans.getCardWidth();
    private int height = constans.getCardHeigth();
    private int space = constans.getSpace();
    private int sizeW = width + space;
    private int sizeH = height + space;
    private ArrayList<CardPanel>cardPanels=new ArrayList<>();
    private ArrayList<CardPanelCollection> cardPanelCollections=new ArrayList<>();


    public ArrayList<CardPanel> getCardPanels() {
        return cardPanels;
    }

    public void setCardPanels(ArrayList<CardPanel> cardPanels) {
        this.cardPanels = cardPanels;
    }

    public FilterPanel(ArrayList<Button> showCards, int width, int height) {
        this.showButton = showCards;
        this.setSize(width, height);
        this.add(new JScrollBar());
        this.setBackground(Color.YELLOW);
    }

    public ArrayList<Button> getShowButton() {
        return showButton;
    }

    public void setShowButton(ArrayList<Button> showButton) {
        this.showButton = showButton;
        repaint();
        revalidate();
    }

    public FilterPanel(ArrayList<Button> showButton) {
        this.showButton = showButton;
        setPreferredSize(new Dimension(constans.getPanelWidth(),2*constans.getPanelHeight()+constans.getPanelHeight()));
        addMouseListener(this);
        repaint();
        revalidate();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < showButton.size(); i++) {
            showButton.get(i).paint(g);
        }

    }
    ArrayList<String> parameters;
    private void create(int i){
        onclick = showButton.get(i);
        onclick.makePanel(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < showButton.size(); i++) {
            if ((e.getX()>=showButton.get(i).getWidth())&&(e.getX()<=showButton.get(i).getWidth()+width)&&(e.getY()>=showButton.get(i).getHeight())&&(e.getY()<=showButton.get(i).getHeight()+height)){
               // System.out.println("button "+i+"clicked");
              create(i);
                Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress()," card  "+showButton.get(i).getName()+" in the shop clicked"+ utilities.time()+"\n",true);
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

    public Button getOnclick() {
        return onclick;
    }

    public void setOnclick(Button onclick) {
        this.onclick = onclick;
    }
}
