package swing.button;

import CLI.utilities;
import logic.Constans;
import model.*;
import swing.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture implements Comparable<Picture> {


    private Constans constans = Controller.getInstance().getConstants();
    private BufferedImage image;
    private BufferedImage skin;
    private String name;
    private String type;
    private int x, y;
    private int sizeX = constans.getSizeX(), sizeY = constans.getSizeY();//sizeX=100, sizeY=135;
    private int hp;
    private int mana;
    private int damage;
    private int durability;
    private int liveInRound;
    private boolean taunt;
    private boolean divineSheild;
    private int attackInRound;


    public Picture(int x, int y, card card) {
        this.name = card.getName();
        this.x = x;
        this.y = y;
        setImage();
        handleType(card);
    }

    public Picture(int x, int y, GamePlayer gamePlayer) {
        this.name = gamePlayer.getHero().getName();
        this.hp = gamePlayer.getHero().getHP();
        this.mana = gamePlayer.getMana();
        this.x=x;
        this.y=y;
        setImage();
    }

    private void setImage() {
        image = Controller.getInstance().getConverter().getImage(this.name);
        skin = Controller.getInstance().getConverter().getImage("skin");
    }

    public void paintHero(Graphics g) {
        g.drawImage(image, this.x, this.y, sizeX, sizeY, null);
        paintHeroHp(g);
    }

    public void paint(Graphics g) {
        g.drawImage(image, this.x, this.y, sizeX, sizeY, null);
        paintMana(g);
        if (type.equals("minion")) {
            paintHp(g);
            paintDamage(g);
        } else if (type.equals("weapen")) {
            paintDamage(g);
            paintDurability(g);
        }

    }

    private void paintDamage(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x + (sizeY / 9), y + (8 * (sizeY / 9)), sizeX / 10, sizeX / 10);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, sizeY / 9);
        g.setFont(font);
        g.drawString(this.damage + "", this.x + (sizeY / 9), this.y + (5 * (sizeX / 4)));
    }

    private void paintHp(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x + (8 * (sizeX / 10)), y + (11 * (sizeX / 10)), sizeY / 9, sizeY / 9);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, sizeY / 9);
        g.setFont(font);
        g.drawString(this.hp + "", this.x + (17 * (sizeX / 20)), this.y + (5 * sizeX / 4));
      //  System.out.println("we draw " + hp + " in the ");
    }

    private void paintDurability(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x + (8 * (sizeX / 10)), y + (11 * (sizeX / 10)), sizeY / 9, sizeY / 9);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, sizeY / 9);
        g.setFont(font);
        g.drawString(this.durability + "", this.x + (17 * (sizeX / 20)), this.y + (5 * (sizeX / 4)));
       // System.out.println("we draw " + durability + " in the ");
    }

    private void paintMana(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x + (sizeX / 10), y + (sizeX / 10), sizeY / 9, sizeY / 9);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, 15);
        g.setFont(font);
        g.drawString(this.mana + "", this.x + (sizeY / 9), this.y + (sizeX / 5));
     //   System.out.println("we draw " + mana + " in the ");
    }

    public void paintSkin(Graphics g) {
        g.drawImage(skin, this.x, this.y, sizeX, sizeY, null);
    }

    private void paintHeroHp(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x + (8 * (sizeX / 10)), y + (11 * (sizeX / 10)), sizeY / 9, sizeY / 9);
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", Font.BOLD, sizeY / 9);
        g.setFont(font);
        g.drawString(this.hp + "", this.x + (17 * (sizeX / 20)), this.y + (5 * sizeX / 4));
    }

    //for handle if &else in constructor
    private void handleType(card card) {
        this.mana = card.getManaCost();
        if (card instanceof Minion) {
            type = "minion";
            loadFieldsMinion(((Minion) card).getHealth(), ((Minion) card).getDamage(), ((Minion) card).getLiveInRound(), card.isTaunt(), ((Minion) card).isDivineSheild(), ((Minion) card).getAttackInRound());
        } else if (card instanceof weapen) {
            type = "weapen";
            loadFieldsWeapen(((weapen) card).getDamage(), ((weapen) card).getDurability());
        } else if (card instanceof spell) {
            type = "spell";

        }
    }

    private void loadFieldsMinion(int hp, int damage, int liveInRound, boolean taunt, boolean divineSheild, int attackInRound) {
        this.hp = hp;
        this.damage = damage;
        this.liveInRound = liveInRound;
        this.taunt = taunt;
        this.divineSheild = divineSheild;
        this.attackInRound=attackInRound;
    }

    private void loadFieldsWeapen(int damage, int durability) {
        this.damage = damage;
        this.durability = durability;
    }


    //getter & setter


    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isDivineSheild() {
        return divineSheild;
    }

    public void setDivineSheild(boolean divineSheild) {
        this.divineSheild = divineSheild;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public BufferedImage getSkin() {
        return skin;
    }

    public void setSkin(BufferedImage skin) {
        this.skin = skin;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getLiveInRound() {
        return liveInRound;
    }

    public void setLiveInRound(int liveInRound) {
        this.liveInRound = liveInRound;
    }

    @Override
    public int compareTo(Picture o) {
        if (this.x < o.getX()) {
            return -1;
        } else {
            if (this.x == o.getX()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
