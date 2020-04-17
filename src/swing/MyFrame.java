package swing;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public static final String MAIN_PANEL = "main";
    public static final String LOGIN_PANEL = "login";
    private Login login;
    private JPanel store;
    private JPanel collection;
    private JPanel mainpanel;


    private void setCardLayout() {
        try {
            mainpanel = new JPanel(new CardLayout());
            setContentPane(mainpanel);
            store = new JPanel();
            mainpanel.add(login, LOGIN_PANEL);
            mainpanel.add(store, MAIN_PANEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public MyFrame() {
        super("HearthStone");
        setSize(1000,562);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        login = new Login();
        setCardLayout();
        setVisible(true);
    }
//    CardLayout cl = (CardLayout) (mainpanel.getLayout());
//                cl.show(, MAIN_PANEL);

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }

}
