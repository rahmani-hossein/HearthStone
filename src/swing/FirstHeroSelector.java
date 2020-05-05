package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import static swing.Constants.*;

public class FirstHeroSelector extends JPanel {

    private JButton mage;
    private JButton rogue;
    private JButton warlock;
    private JButton src;
    private JButton choose;
    private String hero;

    private int size=350;
    private int spacing=400;
    private int startX=340;
    private int startY=10;
    private int width=210;
    private int height=300;


//    public FirstHeroSelector(){
//        repaint();
////        setSize(new Dimension(1600,1000));
//        setLayout(null);
//        JButton mage=new JButton();
//        mage.setIcon(mageIcon);
//        mage.setContentAreaFilled(false);
//        mage.setBorderPainted(false);
//        mage.setBounds(startX,startY,size,size);
//        mage.setFont(f2);
//        add(mage);
//
//        JButton rogue =new JButton();
//        rogue.setIcon(rogueIcon);
//        rogue.setContentAreaFilled(false);
//        rogue.setBorderPainted(false);
//        rogue.setBounds(startX+spacing,startY,size,size);
//        rogue.setFont(f2);
//        add(rogue);
//
//        JButton warlock = new JButton();
//        warlock.setIcon(warlockIcon);
//        warlock.setContentAreaFilled(false);
//        warlock.setBorderPainted(false);
//        warlock.setBounds(startX+2*spacing,startY,size,size);
//        warlock.setFont(f2);
//        add(warlock);
//
//        JButton choose=new JButton("Choose");
//        choose.setFocusable(false);
//        choose.setBounds(120 , gameHight-120,120,30);
//        choose.setFont(f2);
//        add(choose);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        g.drawImage(heroBackgroung,0,0,1600,1000,null);
//
//        Graphics2D g2d=(Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setFont(f2.deriveFont(35.0f));
//        FontMetrics fontMetrics=g2d.getFontMetrics(f2);
//        g2d.setColor(Color.BLUE);
//        g2d.drawString("Mage" , (startX+120),startY+size+15);
//        g2d.drawString("Rouge" , (startX+120)+spacing,startY+size+15);
//        g2d.drawString("warlock" , (startX+120)+2*spacing,startY+size+15);
//
//        g2d.setFont(f2.deriveFont(40.0f));
//        g2d.setColor(Color.YELLOW);
//        g2d.drawString("     Hero    ",20,startY+size+15);
//        g2d.drawString("  Hero Power ",20,startY+500);
//        g2d.drawString("Special Cards" , 20 ,startY+750);
//
//
//        g2d.drawImage(magePower , startX+70 , startY+370,180,250,null);
//        g2d.drawImage(magePower , (startX+70)+spacing+10 , startY+370,180,250,null);
//        g2d.drawImage(magePower , (startX+70)+2*spacing+10 , startY+370,180,250,null);
//
//
//        g.drawImage(cardPics.get("polymorph") ,startX-50,startY+610,width,height,null);
//        g.drawImage(cardPics.get("flamestrike"),startX+150,startY+610,width,height,null);
//        g2d.drawImage(cardPics.get("friendlysmith") , (startX-50)+spacing +10, startY+610,width,height,null);
//        g2d.drawImage(cardPics.get("umbralskulker") , (startX+150)+spacing +10, startY+610,width,height,null);
//        g2d.drawImage(cardPics.get("dreadscale") , (startX-50)+2*spacing +20, startY+610,width,height,null);
//        g2d.drawImage(cardPics.get("darkskies") , (startX+150)+2*spacing +20, startY+610,width,height,null);
//
//
//        g2d.setColor(Color.RED);
//        g2d.setFont(designer);
//        g2d.drawString("Designed by rahmani" , 5,940);
//
//
//    }


}
