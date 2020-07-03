package swing.panel;

import CLI.utilities;
import logic.Constans;
import swing.Controller;
import swing.button.Button;
import swing.panel.CardPanel;
import swing.panel.CardPanelCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FilterPanel extends JPanel implements MouseListener {
    private Constans constans=Constans.getInstance();
    ArrayList<swing.button.Button> showButton;
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

    public FilterPanel(ArrayList<swing.button.Button> showCards, int width, int height) {
        this.showButton = showCards;
        this.setSize(width, height);
        this.add(new JScrollBar());
        this.setBackground(Color.YELLOW);
    }

    public ArrayList<swing.button.Button> getShowButton() {
        return showButton;
    }

    public void setShowButton(ArrayList<swing.button.Button> showButton) {
        this.showButton = showButton;
        repaint();
        revalidate();
    }

    public FilterPanel(ArrayList<Button> showButton) {
        this.showButton = showButton;
        setPreferredSize(new Dimension(constans.getPanelWidth(),2*constans.getPanelHeight()+constans.getPanelHeight()));
        addMouseListener(this);
//        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));


      //
        repaint();
        revalidate();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < showButton.size(); i++) {
          //  System.out.println(showButton.get(i).getName());
            showButton.get(i).paint(g);
           // g.drawImage(showButton.get(i).getImage(), space*(i%5+1) + (i%5)*width , (i/5 + 1)*space + (i/5)*height, null);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < showButton.size(); i++) {
            if ((e.getX()>=showButton.get(i).getWidth())&&(e.getX()<=showButton.get(i).getWidth()+width)&&(e.getY()>=showButton.get(i).getHeight())&&(e.getY()<=showButton.get(i).getHeight()+height)){
               // System.out.println("button "+i+"clicked");
                showButton.get(i).makePanel(this);
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1," card  "+showButton.get(i).getName()+" in the shop clicked"+ utilities.time()+"\n",true);
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
