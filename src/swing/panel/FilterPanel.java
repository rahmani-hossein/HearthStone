package swing.panel;

import logic.Constans;
import swing.button.Button;
import swing.panel.CardPanel;
import swing.panel.CardPanelCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FilterPanel extends JPanel implements MouseListener {
    ArrayList<swing.button.Button> showButton;
    private int width = Constans.cardWidth;
    private int height = Constans.cardHeigth;
    private int space = Constans.space;
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
        setPreferredSize(new Dimension(Constans.panelWidth,2*Constans.panelHeight+Constans.panelHeight));
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
