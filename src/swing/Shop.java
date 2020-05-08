package swing;

import CLI.utilities;
import logic.Constans;
import logic.ShopManager;
import swing.button.Button;
import swing.panel.FilterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Shop extends JPanel implements MouseListener {
    private ShopManager shopManager;
    private JPanel north;
    private FilterPanel center;
    private JButton sellable;
    private JButton buyable;
    private JLabel wallet;
    private JButton exit;
    private JButton back;
    private int space = Constans.space;
    private int sizeW = 200;
    private int sizeH = 275;

    Converter converter = new Converter();
    private ArrayList<BufferedImage> showCards = null;
    private ArrayList<swing.button.Button> showButton = null;
    private Controller controller;


    public Shop(ShopManager shopManager) {
        controller = Controller.getInstance();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.shopManager = shopManager;
        north = new JPanel(new FlowLayout());
        add(north, BorderLayout.NORTH);
        initNorthButtons();
        showCards = converter.convert(shopManager.showBuyable());
        showButton = initButton(showCards, shopManager.showBuyable());
        center = new FilterPanel(showButton);
        JScrollPane scrollPane = new JScrollPane();
       scrollPane.setViewportView(center);
        Controller.getInstance().setShope(this);
       // controller.getShop().add(scrollPane);
        add( BorderLayout.CENTER,scrollPane);

        center.setBackground(Color.YELLOW);

    }
    public void init(){

    }

    private void initNorthButtons() {
        wallet = new JLabel("Wallet");
        sellable = new JButton("sellable");
        buyable = new JButton("buyable");
        exit = new JButton("exit");
        back = new JButton("back");
        north.add(buyable);
        north.add(sellable);
        north.add(wallet);
        north.add(exit);
        north.add(back);
        buyable.addMouseListener(this);
        sellable.addMouseListener(this);
        wallet.addMouseListener(this);
        exit.addMouseListener(this);
        back.addMouseListener(this);
    }


    private ArrayList<swing.button.Button> initButton(ArrayList<BufferedImage> showCards, ArrayList<String> buyable) {
        ArrayList<swing.button.Button> showButton = new ArrayList<>();
        for (int i = 0; i < showCards.size(); i++) {
            swing.button.Button button = new Button(showCards.get(i), buyable.get(i), ((i % 5) + 1) * space + ((i%5) * sizeW), ((i / 5) + 1) * space + ((i/5) * sizeH));
            addMouseListener(button);
            showButton.add(button);
        }
        //makePanel(showButton);
        return showButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < showButton.size(); i++) {
            showButton.get(i).paint(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {


        if (e.getSource() == sellable) {
            for (int i = 0; i < center.getCardPanels().size(); i++) {
               // center.getCardPanels().get(i).remove();
                center.remove( center.getCardPanels().get(i));
            }
            showCards = converter.convert(shopManager.showSellable());
            showButton = initButton(showCards, shopManager.showSellable());
            center.setShowButton(showButton);
            String st1 = String.format("%s.txt", controller.getGameState().getPlayer().getUsername() +  controller.getGameState().getPlayer().getPassword());
            Controller.myLogger(st1," click for see sellable cards  "+ utilities.time(),true);

            repaint();
            revalidate();
        } else if (e.getSource() == buyable) {
            showCards = converter.convert(shopManager.showBuyable());
            showButton = initButton(showCards, shopManager.showBuyable());
            center.setShowButton(showButton);
            String st1 = String.format("%s.txt", controller.getGameState().getPlayer().getUsername() +  controller.getGameState().getPlayer().getPassword());
            Controller.myLogger(st1," click for see buyables cards  "+ utilities.time()+"\n",true);

            repaint();
            revalidate();
        } else if (e.getSource() == exit) {
            controller.exitGame();
        } else if (e.getSource() == back) {
            controller.getMyFrame().setPanel("menu");
            String st1 = String.format("%s.txt", controller.getGameState().getPlayer().getUsername() +  controller.getGameState().getPlayer().getPassword());
            Controller.myLogger(st1," back to menu   "+ utilities.time()+"\n",true);
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
        if (e.getSource() == wallet) {
            wallet.setText(shopManager.seeWallet() + "");
            repaint();
            revalidate();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == wallet) {
            wallet.setText("Wallet");
            String st1 = String.format("%s.txt", controller.getGameState().getPlayer().getUsername() +  controller.getGameState().getPlayer().getPassword());
            Controller.myLogger(st1," player see wallet  "+ utilities.time()+"\n",true);
            repaint();
            revalidate();
        }
    }
}
