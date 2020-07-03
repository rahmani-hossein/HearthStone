package swing;

import logic.Constans;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public static final String LOGIN_PANEL = "login";
    public static final String MENU_PANEL = "menu";

    private CardLayout cardLayout;
    private Controller controller;
    private Login login;
    private Menu menu;

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public void setMainpanel(JPanel mainpanel) {
        this.mainpanel = mainpanel;
    }

    private JPanel mainpanel;//package level for using cardLayout


    private void createPanel() {
        try {
            Controller.getInstance().setMyFrame(this);
            cardLayout = new CardLayout();
            mainpanel = new JPanel(cardLayout);
            setContentPane(mainpanel);
            login = new Login(this);
            mainpanel.add(login, LOGIN_PANEL);

//            mainpanel.add(collection,COLLECTION_PANEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPanel(String panelName) {
        cardLayout.show(mainpanel, panelName);
    }


    public MyFrame() {
        super("HearthStone");
        Constans.getInstance().fill();
        setSize(1778, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        createPanel();
        setVisible(true);


    }
}
