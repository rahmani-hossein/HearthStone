package swing;


import client.ClientConstants;
import client.Controller;
import model.Request;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JPanel {
    private ClientConstants constans= Controller.getInstance().getClientConstants();
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
    private int ten=similiar/100;

    public Login() {
        this.myFrame = Controller.getInstance().getMyFrame();
        setSize(constans.getPanelWidth(), constans.getPanelHeight());
        setLayout(null);
        init();
        initPasswordLabel();
        initPasswordText();
        initUsernameLabel();
        initUsernameText();
        initSubject();
        initExit();
        initCreate();
        initLogin();
    }


    private void init() {
        try {
            String url = String.format("src/main/resources/image/pics/login1.jpg");
            File file = new File(url);
            backGround = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPasswordLabel(){
        passwordLabel = new JLabel("password:");
        passwordLabel.setBounds(5*ten, 25*ten, 9*ten, 2*ten);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);
    }

    private void initPasswordText(){
        passwordText = new JTextField(similiar/(5*ten));
        passwordText.setBounds(31*ten+ten/2, 25*ten, 5*ten, 2*ten);
        add(passwordText);

    }

    private void initSubject(){
        subject = new JLabel("LoginForm");
        subject.setBounds(27*ten+ten/2, 15*ten, 7*ten, 3*ten);
        subject.setForeground(Color.WHITE);
        add(subject);
    }

    private void initUsernameLabel(){
        userNameLabel = new JLabel("userName:");
        userNameLabel.setBounds(25*ten, 20*ten, 9*ten, 2*ten);
        userNameLabel.setForeground(Color.WHITE);
        add(userNameLabel);
    }

    private void initUsernameText(){
        userNameText = new JTextField(similiar/(5*ten));
        userNameText.setBounds(31*ten+ten/2, 20*ten, 5*ten, 2*ten);
        add(userNameText);
    }

    private void initExit(){
        exit = new JButton("exit");
        exit.setBounds(similiar-(similiar/100), similiar/10, 7*(similiar/100), 3*(similiar/100));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);
    }

    private void initLogin(){
        login = new JButton("login");
        login.setBounds(28*ten, 30*ten, 7*ten, 3*ten);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameText.getText() != null && userNameText.getText().length() >= 1 && passwordText.getText() != null && passwordText.getText().length() >= 1) {
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add(userNameText.getText());
                    parameters.add(passwordText.getText());
                    parameters.add("true");
                    Request request = new Request(Controller.getInstance().getClient().getToken(), "login", parameters, "  ");
                    Controller.getInstance().getClient().getSender().send(request);

                } else {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "there isnt a username or password", "loginError", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(login);
    }

    private void initCreate(){
        create = new JButton("new Account");
        create.setBounds(20*ten, 35*ten, 15*ten, 3*ten);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameText.getText() != null && userNameText.getText().length() >= 1 && passwordText.getText() != null && passwordText.getText().length() >= 1) {
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add(userNameText.getText());
                    parameters.add(passwordText.getText());
                    parameters.add("false");
                    Request request = new Request(Controller.getInstance().getClient().getToken(), "login", parameters, "");
                    Controller.getInstance().getClient().getSender().send(request);
                } else {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(), "there isnt a username or password", "loginError", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(create);
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, null);
    }
}
