package swing;

import CLI.Administer;
import CLI.GameState;
import logic.Constans;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login extends JPanel {
    Constans constans=Controller.getInstance().getConstants();
    private BufferedImage backGround;
    private JLabel subject;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userNameText;
    private JTextField passwordText;
    private JButton login;
    private JButton create;
    private JButton exit;
    private MyFrame myFrame;
    private int similiar=constans.getPanelHeight();

    public Login(MyFrame frame) {
        this.myFrame = frame;
        Administer administer = new Administer();
        setSize(constans.getPanelWidth(), constans.getPanelHeight());
        subject = new JLabel("LoginForm");
        passwordLabel = new JLabel("password:");
        userNameLabel = new JLabel("userName:");
        userNameText = new JTextField(similiar/50);
        passwordText = new JTextField(similiar/50);
        login = new JButton("login");
        create = new JButton("new Account");
        exit = new JButton("exit");
        setLayout(null);
        exit.setBounds(similiar-(similiar/100), similiar/10, 7*(similiar/100), 3*(similiar/100));
        subject.setBounds(275, 150, 70, 30);
        userNameLabel.setBounds(250, 200, 90, 20);
        userNameText.setBounds(316, 200, 50, 20);
        passwordLabel.setBounds(250, 250, 90, 20);
        passwordText.setBounds(316, 250, 50, 20);
        login.setBounds(280, 300, 70, 30);
        create.setBounds(200, 350, 150, 30);
        //ActionListener
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (administer.login(userNameText.getText(), passwordText.getText(), true)) {
                    System.out.println("we are in menu");
                    myFrame.setPanel("menu");
                } else {
                    JOptionPane.showConfirmDialog(login, "your username or password is not valid", "Error login", JOptionPane.OK_CANCEL_OPTION);
                }
            }
        });
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (administer.login(userNameText.getText(), passwordText.getText(), false)) {

                    myFrame.setPanel("menu");
                }
            }
        });
        //fonts and color of Jlabels
        //login form
        subject.setForeground(Color.WHITE);

        //userName
        userNameLabel.setForeground(Color.WHITE);

        //password
        passwordLabel.setForeground(Color.WHITE);

        add(subject);
        add(userNameLabel);
        add(userNameText);
        add(passwordLabel);
        add(passwordText);
        add(login);
        add(create);
        add(exit);
        init();
    }


    private void init() {
        try {
            String url = String.format("resources\\Image\\pics\\login1.jpg");
            File file = new File(url);
            backGround = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, null);
    }
}
