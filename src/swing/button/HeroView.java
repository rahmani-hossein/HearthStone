package swing.button;

import logic.Constans;
import model.GamePlayer;
import model.card;
import swing.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HeroView {

    private Constans constans = Controller.getInstance().getConstants();
    private BufferedImage heroImage;
    private String name;
    private int x, y;
    private int sizeX = constans.getSizeX(), sizeY = constans.getSizeY();//sizeX=100, sizeY=135;
    private int hp;
    private int mana;

    public HeroView(int x, int y, GamePlayer gamePlayer) {
        this.name = gamePlayer.getHero().getName();
        this.hp = gamePlayer.getHero().getHP();
        this.mana = gamePlayer.getMana();
        this.x = x;
        this.y = y;
        setImage();

    }

    private void setImage() {
        heroImage = Controller.getInstance().getConverter().getImage(this.name);
        System.out.println("finish loading picture");
    }
    public void paintHero(Graphics g){
        g.drawImage(heroImage,this.x,this.y,sizeX,sizeY,null);
        paintHealth(g);
    }
    private void paintHealth(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x+(sizeY/9),y+(8*(sizeY/9)),sizeX/10,sizeX/10);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, sizeY/9);
        g.setFont(font);
        g.drawString(this.hp+"",this.x+(sizeY/9),this.y+(5*(sizeX/4)));
    }

}
