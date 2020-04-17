package swing;

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
     private BufferedImage backGround;
     private JLabel subject;
     private JLabel userNameLabel;
     private JLabel passwordLabel;
    private  JTextField userNameText;
    private JTextField passwordText;
    private JButton login;
    public Login(){
        setSize(1000,562);
        subject=new JLabel("LoginForm");
        passwordLabel=new JLabel("password:");
        userNameLabel=new JLabel("userName:");
        userNameText=new JTextField(20);
        passwordText= new JTextField(20);
        login=new JButton("login");
       setLayout(null);
       subject.setBounds(275,150,70,30);
       userNameLabel.setBounds(250,200,90,20);
       userNameText.setBounds(316,200,50 ,20);
       passwordLabel.setBounds(250,250,90,20);
       passwordText.setBounds(316,250,50,20);
       login.setBounds(280,300,50,20);
       //ActionListener
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
       //fonts and color of Jlabels
        //login form
        subject.setForeground(Color.BLACK);

        //userName
        userNameLabel.setForeground(Color.BLACK);

        //password
        passwordLabel.setForeground(Color.BLACK);

        add(subject);
        add(userNameLabel);
        add(userNameText);
        add(passwordLabel);
        add(passwordText);
        add(login);

    init();
    }



    private void init(){
        try{
            String url=String.format("resources\\pics\\login1.jpg");
            File file=new File(url);
            backGround= ImageIO.read(file);
        }
       catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround,0,0,null);
    }
}
