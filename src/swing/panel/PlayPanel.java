package swing.panel;

import model.GamePlayer;
import model.card;
import swing.Controller;
import swing.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class PlayPanel extends JPanel {

    private GamePlayer friend;
    private JLabel card1;
    private JLabel card2;
    private JLabel card3;
    private int clicked1 = 0;
    private int clicked2 = 0;
    private int clicked3 = 0;
    private JButton exit;
    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private GamePanel parent;

    public PlayPanel(int x, int y, int lastX, int lastY, GamePlayer friend, GamePanel parent) {
        this.friend = friend;
        this.x = x;
        this.y = y;
        this.sizeX = lastX;
        this.sizeY = lastY;
        setLayout(null);
        this.parent=parent;
        this.parent.add(this);
        this.setBounds(this.x, this.y, this.sizeX-x, this.sizeY-y);
        setExit();
        setLabel();
    }

    private void setExit() {
        exit = new JButton("exit");
        exit.setBounds(8 * (x / 10), 8 * (y / 10), 4*x / 10, 4*y / 10);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < friend.getInitCard().size(); i++) {
                    friend.getHand().add(friend.getInitCard().remove(i));
                }
                setVisible(false);
                parent.repaint();
                parent.revalidate();
            }
        });
        add(exit);
    }

    private void setLabel(){
        card1=new JLabel("card1");
        card2=new JLabel("card2");
        card3= new JLabel("card3");
      setImage(card1,friend.getInitCard().get(0));
      setImage(card2,friend.getInitCard().get(1));
        setImage(card3,friend.getInitCard().get(2));
        card1.setBounds(Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY(),Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY());
        add(card1);
        setMouseLisener(card1, friend.getInitCard().get(0));
        card2.setBounds(3*Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY(),Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY());
        card3.setBounds(5*Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY(),Controller.getInstance().getConstants().getSizeX(),Controller.getInstance().getConstants().getSizeY());
        add(card2);
        add(card3);
        setMouseLisener(card2,friend.getInitCard().get(1));
        setMouseLisener(card3,friend.getInitCard().get(2));
    }
     void setImage(JLabel jLabel , card card){
         BufferedImage image = Controller.getInstance().getConverter().getImage(card.getName());
         Image scaled = image.getScaledInstance(image.getWidth()/2,image.getHeight()/2,Image.SCALE_SMOOTH);
         jLabel.setIcon(new ImageIcon(scaled));
     }

    private void setMouseLisener(JLabel jLabel, card card) {
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (findClickedNumber(jLabel) == 0) {
                    card card5 = friend.getDeck().remove(0);
                    card card4 = friend.getInitCard().remove(findCard(card.getName()));
                    friend.getDeck().add(card4);
                    friend.getHand().add(card5);
                    findClickedAndIncrease(jLabel);
                   setImage(jLabel,card5);
                    jLabel.setText(card5.getName());

                } else {
                    //nothing to do
                }
            }
        });

    }


    private int findCard(String name) {
        int i = -1;
        for (int j = 0; j < friend.getInitCard().size(); j++) {
            if (friend.getInitCard().get(j).getName().equalsIgnoreCase(name)) {
                i = j;
                break;
            }
        }
        return i;
    }

    private void findClickedAndIncrease(JLabel jLabel) {
        switch (jLabel.getText()) {
            case "card1":
                clicked1++;
                break;
            case "card2":
                clicked2++;
                break;
            case "card3":
                clicked3++;
                break;

        }
    }

    private int findClickedNumber(JLabel label) {
        int result2 = -1;
        switch (label.getText()) {
            case "card1":
                result2 = clicked1;
                break;
            case "card2":
                result2 = clicked2;
                break;
            case "card3":
                result2 = clicked3;
                break;

        }
        return result2;
    }

}
