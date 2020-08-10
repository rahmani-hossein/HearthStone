package swing.button;

import client.Controller;
import logic.ShopManager;
import model.Request;
import swing.panel.CardPanelCollection;
import swing.panel.FilterPanelCollection;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ButtonC implements MouseListener {
    private BufferedImage image;
    private String name;
    private int width;
    private int height;
    private int sizeW = 200;
    private int sizeH = 275;
    CardPanelCollection cardPanelCollection;


    public int getSizeW() {
        return sizeW;
    }

    public void setSizeW(int sizeW) {
        this.sizeW = sizeW;
    }

    public int getSizeH() {
        return sizeH;
    }

    public void setSizeH(int sizeH) {
        this.sizeH = sizeH;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ButtonC(BufferedImage image, String name, int width, int height) {
        this.image = image;
        this.name = name;
        this.width = width;
        this.height = height;

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void paint(Graphics g) {
        g.drawImage(this.image, this.width, this.height, this.sizeW, this.sizeH, null);
    }

    public void hidePanel() {
        cardPanelCollection.setVisible(false);
    }

ArrayList<String> parameters=null;
    public void makePanel(FilterPanelCollection filterPanelCollection) {
        cardPanelCollection = new CardPanelCollection(this.getName());
        int cardWidth = this.width;
        int cardHeight = this.height;
        cardPanelCollection.setSize(cardWidth, cardHeight);
        cardPanelCollection.setVisible(true);
        filterPanelCollection.add(cardPanelCollection);
        cardPanelCollection.setBounds(width, height, sizeW, sizeH);
        filterPanelCollection.getCardPanelCollections().add(cardPanelCollection);
        Request request= new Request(Controller.getInstance().getClient().getToken(),"infoCollection",parameters,this.name);
        Controller.getInstance().getClient().getSender().send(request);
    }

    public CardPanelCollection getCardPanelCollection() {
        return cardPanelCollection;
    }

    public void setCardPanelCollection(CardPanelCollection cardPanelCollection) {
        this.cardPanelCollection = cardPanelCollection;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
