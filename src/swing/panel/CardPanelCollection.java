package swing.panel;

import CLI.utilities;
import logic.CardManager;
import logic.Constans;
import logic.ShopManager;
import swing.Controller;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardPanelCollection  extends JPanel implements MouseListener {

    ShopManager shopManager;
    private CardManager cardManager;
    private String name;
    private int cost=0;
    private String type="";
    private String rarity="";
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;
    private JButton buy;
    private JButton exit;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public JButton getBuy() {
        return buy;
    }

    public void setBuy(JButton buy) {
        this.buy = buy;
    }

    public CardPanelCollection(String name, ShopManager shopManager ){
        cardManager=new CardManager();
        this.shopManager=shopManager;
        this.name=name;
//        System.out.println(Constans.costsMap);
        this.cost= Constans.costsMap.get(name);
        this.type=Constans.types.get(name);
        this.rarity=cardManager.tellRarity(name);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        info1=new JLabel("name:"+name);
        info2=new JLabel("cost:"+cost);
        info3=new JLabel("type:"+type);
        info4=new JLabel("rarity:"+rarity);
        buy=new JButton("buy");
        exit=new JButton("exit");
        exit.addMouseListener(this);
        buy.addMouseListener(this);
        add(info1);
        add(info2);
        add(info3);
        add(info4);
        add(buy);
        add(exit);

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==buy) {
            if (shopManager.canBuy(name)) {
                int action = JOptionPane.showConfirmDialog(getParent(), "do you want to buy?", "buy Title", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    this.setVisible(false);
                    String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                    Controller.myLogger(st1,"you clicked to "+name+"  and you go to shop "+ utilities.time()+"\n",true);
                    Controller.getInstance().getMyFrame().setPanel("shop");
                }
            } else {
                JOptionPane.showMessageDialog(getParent(), "you can not buy this card ", "Error", JOptionPane.ERROR);
                String st1 = String.format("%s.txt", Controller.getInstance().getGameState().getPlayer().getUsername() +  Controller.getInstance().getGameState().getPlayer().getPassword());
                Controller.myLogger(st1,"you clicked to "+name+"  but you cant buy"+ utilities.time()+"\n",true);
            }
        }
         else if (e.getSource()==exit){
            this.setVisible(false);
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
