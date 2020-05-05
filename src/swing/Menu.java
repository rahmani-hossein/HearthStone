package swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements MouseListener {
    private JButton collection;
    private JButton shop;
    private JButton play;
    private JButton status;
    private Controller controller;
    private BufferedImage backGround;
    private JButton exit;
    private JButton delete;

    public Menu() {
        this.controller = Controller.getInstance();
        this.setLayout(null);
        collection = new JButton("COLLECTION");
        shop = new JButton("SHOP");
        play = new JButton("PLAY");
        status = new JButton("STATUS");
        exit = new JButton("exit");
        delete = new JButton("delete");
        init();
        setLayout();

    }

    private void setLayout() {
        collection.setBounds(800, 300, 150, 50);
        shop.setBounds(800, 380, 150, 50);
        play.setBounds(800, 460, 150, 50);
        status.setBounds(800, 540, 150, 50);
        exit.setBounds(1200, 100, 150, 50);
        exit.addMouseListener(this);
        delete.addMouseListener(this);
        delete.setBounds(1400, 100, 150, 50);
        add(collection);
        add(shop);
        add(status);
        add(play);
        add(exit);
        add(delete);
        shop.addMouseListener(this);
        collection.addMouseListener(this);
        status.addMouseListener(this);
        play.addMouseListener(this);

    }

    private void init() {
        try {
            String url = String.format("resources\\Image\\MenuBackground.jpg");
            File file = new File(url);
            backGround = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == shop) {
            System.out.println("before go to shop");
            controller.getMyFrame().setPanel("shop");
            System.out.println("welcome to shop");
        } else if (collection == e.getSource()) {
            controller.getMyFrame().setPanel("collection");
        } else if (status == e.getSource()) {
            controller.getMyFrame().setPanel("status");
        } else if (play == e.getSource()) {
            controller.getMyFrame().setPanel("play");
        } else if (exit == e.getSource()) {
            controller.exitGame();
        } else if (delete == e.getSource()) {
            String text = JOptionPane.showInputDialog(getParent(), "please type your password", "Delete", JOptionPane.OK_CANCEL_OPTION);
            if (text.equals(controller.getGameState().getPlayer().getPassword())) {
                controller.delete(text);
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
//

