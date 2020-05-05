package swing.panel;

import logic.CollectionManager;
import logic.Constans;
import swing.button.Button;
import swing.button.ButtonC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FilterPanelCollection extends JPanel implements MouseListener {

    ArrayList<ButtonC> showButton;
    private int width = Constans.cardWidth;
    private int height = Constans.cardHeigth;
    private int space = Constans.space;
    private int sizeW = width + space;
    private int sizeH = height + space;
    private ArrayList<CardPanelCollection> cardPanelCollections=new ArrayList<>();
    private CollectionManager collectionManager;

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
    public void hidePanel(){
        for (int i=0;i<cardPanelCollections.size();i++){
            cardPanelCollections.get(i).setVisible(false);
        }
    }

    public FilterPanelCollection(ArrayList<ButtonC> showButton,CollectionManager collectionManager) {
        this.showButton = showButton;
        this.collectionManager=collectionManager;
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
            if (collectionManager.isKnocked(showButton.get(i).getName())){
                g.setColor(Color.DARK_GRAY);
                g.fillRect(showButton.get(i).getWidth(),showButton.get(i).getHeight(),showButton.get(i).getSizeW(), showButton.get(i).getSizeH());
                g.setColor(Color.WHITE);
            }
            showButton.get(i).paint(g);

            //  System.out.println(showButton.get(i).getName());

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
