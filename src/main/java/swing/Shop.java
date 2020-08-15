package swing;

import CLI.utilities;
import client.Controller;
import client.Converter;
import logic.ShopManager;
import model.Request;
import swing.button.Button;
import swing.panel.FilterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Shop extends JPanel implements MouseListener {

    private JPanel north;
    private FilterPanel center;
    private JButton sellable;
    private JButton buyable;
    private JLabel wallet;
    private JButton exit;
    private JButton back;
    private int space = Controller.getInstance().getClientConstants().getSpace();
    private int sizeW = 200;
    private int sizeH = 275;

    Converter converter = new Converter();
    private ArrayList<BufferedImage> showCards = new ArrayList<>();
    private ArrayList<Button> showButton = new ArrayList<>();
    private Controller controller;


    public Shop() {
        controller = Controller.getInstance();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        north = new JPanel(new FlowLayout());
        add(north, BorderLayout.NORTH);
        initNorthButtons();
        center = new FilterPanel(showButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(center);
        add(BorderLayout.CENTER, scrollPane);

        center.setBackground(Color.YELLOW);

    }

    private void initNorthButtons() {
        wallet = new JLabel("Wallet");
        north.add(wallet);
        wallet.addMouseListener(this);
        initBack();
        initBuyable();
        initExit();
        initSellable();
    }

    private void initBuyable() {
        buyable = new JButton("buyable");
        buyable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request(Controller.getInstance().getClient().getToken(), "buyable", null, "");
                Controller.getInstance().getClient().getSender().send(request);
                Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress(), " click for see buyables cards  " + utilities.time() + "\n", true);

            }
        });
        north.add(buyable);
    }

    private void initSellable() {
        sellable = new JButton("sellable");
        sellable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request(Controller.getInstance().getClient().getToken(), "sellable", null, "");
                Controller.getInstance().getClient().getSender().send(request);
                Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress(), " click for see sellable cards  " + utilities.time(), true);
            }
        });
        north.add(sellable);

    }

    private void initExit() {
        exit = new JButton("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exitGame();
            }
        });
        north.add(exit);

    }

    private void initBack() {
        back = new JButton("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getMyFrame().setPanel("menu");
                Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress(), " back to menu   " + utilities.time() + "\n", true);
            }
        });
        north.add(back);

    }

    public ArrayList<Button> initButton(ArrayList<String> madenazar) {
        ArrayList<Button> showButton = new ArrayList<>();
        showCards = converter.convert(madenazar);
        for (int i = 0; i < showCards.size(); i++) {
            swing.button.Button button = new Button(showCards.get(i), madenazar.get(i), ((i % 5) + 1) * space + ((i % 5) * sizeW), ((i / 5) + 1) * space + ((i / 5) * sizeH));
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
            Controller.getInstance().getShop().getWallet().setText(Controller.getInstance().getGameState().getPlayer().getDiamond() + "");
            repaint();
            revalidate();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == wallet) {
            wallet.setText("Wallet");
            Controller.getInstance().myLogger(Controller.getInstance().getTxtAddress(), " player see wallet  " + utilities.time() + "\n", true);
            repaint();
            revalidate();
        }
    }

    public JPanel getNorth() {
        return north;
    }

    public void setNorth(JPanel north) {
        this.north = north;
    }

    public FilterPanel getCenter() {
        return center;
    }

    public void setCenter(FilterPanel center) {
        this.center = center;
    }

    public JButton getSellable() {
        return sellable;
    }

    public void setSellable(JButton sellable) {
        this.sellable = sellable;
    }

    public JButton getBuyable() {
        return buyable;
    }

    public void setBuyable(JButton buyable) {
        this.buyable = buyable;
    }

    public JLabel getWallet() {
        return wallet;
    }

    public void setWallet(JLabel wallet) {
        this.wallet = wallet;
    }

    public ArrayList<BufferedImage> getShowCards() {
        return showCards;
    }

    public void setShowCards(ArrayList<BufferedImage> showCards) {
        this.showCards = showCards;
    }

    public ArrayList<Button> getShowButton() {
        return showButton;
    }

    public void setShowButton(ArrayList<Button> showButton) {
        this.showButton = showButton;
    }
}
