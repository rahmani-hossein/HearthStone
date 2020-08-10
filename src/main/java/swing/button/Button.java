package swing.button;

import client.Controller;
import logic.ShopManager;
import model.Request;
import swing.panel.CardPanel;
import swing.panel.FilterPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Button implements MouseListener {

    private BufferedImage image;
    private String name;
    private int width;
    private int height;
    private int sizeW=200;
    private int sizeH=275;
    CardPanel cardPanel;

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

    public Button(BufferedImage image, String name, int width, int height) {
        this.image = image;
        this.name = name;
        this.width=width;
        this.height=height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void paint(Graphics g){
        g.drawImage(this.image,this.width,this.height,this.sizeW,this.sizeH,null);
    }

    public void hidePanel(){
        cardPanel.setVisible(false);
    }

    ArrayList<String> parameters=new ArrayList<>();
    public void makePanel(FilterPanel filterPanel) {

         cardPanel = new CardPanel(this.getName());
        int cardWidth = this.width;
        int cardHeight =this.height ;
        cardPanel.setSize(cardWidth, cardHeight);
        cardPanel.setVisible(true);
        filterPanel.add(cardPanel);
        cardPanel.setBounds(width,height,sizeW,sizeH);
        filterPanel.getCardPanels().add(cardPanel);
        Request request= new Request(Controller.getInstance().getClient().getToken(),"information",parameters,this.name);
        Controller.getInstance().getClient().getSender().send(request);
    }

    public CardPanel getCardPanel() {
        return cardPanel;
    }

    public void setCardPanel(CardPanel cardPanel) {
        this.cardPanel = cardPanel;
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
