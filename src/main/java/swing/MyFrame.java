package swing;

import client.Controller;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public static final String CONNECT_PANEL = "connect";
    public static final String MENU_PANEL = "menu";

    private CardLayout cardLayout;
    private JPanel mainpanel;
    private Connect connect;


    private void createPanel() {
        try {
            Controller.getInstance().setMyFrame(this);
            cardLayout = new CardLayout();
            mainpanel = new JPanel(cardLayout);
            setContentPane(mainpanel);
             connect= new Connect();
            mainpanel.add(connect, CONNECT_PANEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPanel(String panelName) {
        cardLayout.show(mainpanel, panelName);
    }


    public MyFrame() {
        super("HearthStone");
        Controller.getInstance().getConstants().initFill();
        setSize(Controller.getInstance().getClientConstants().getPanelWidth(), Controller.getInstance().getClientConstants().getPanelHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        createPanel();
        setVisible(true);


    }

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public void setMainpanel(JPanel mainpanel) {
        this.mainpanel = mainpanel;
    }
}
