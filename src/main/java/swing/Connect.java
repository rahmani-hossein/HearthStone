package swing;

import client.Client;
import client.ClientConstants;
import client.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Connect extends JPanel {

    private ClientConstants clientConstants = Controller.getInstance().getClientConstants();
    private JLabel portLabel;
    private JLabel ipLabel;
    private JTextField ipField;
    private JTextField portField;
    private JButton connect;
    private JButton exit;
    private int similiar = clientConstants.getPanelHeight();
    private int ten = similiar / 100;
    private int portId=clientConstants.getPort();// baiad hatman ghabl az vasl shodan port ro bedonim.

    public Connect() {
        setSize(clientConstants.getPanelWidth(),clientConstants.getPanelHeight());
        setLayout(null);
        createIpField();
        createPortField();
        createIpLabel();
        createPortLabel();
        createExit();
        createConnect();
        setBackground(Color.BLACK);

    }

    private void createIpField() {
        ipField = new JTextField(similiar/(5*ten));
        ipField.setBounds(31*ten+ten/2, 20*ten, 5*ten, 2*ten);
        add(ipField);
    }

    private void createPortField() {
        portField = new JTextField(similiar/(5*ten));
        portField.setBounds(31*ten+ten/2, 25*ten, 5*ten, 2*ten);
        add(portField);
    }
    private void createPortLabel(){
        portLabel = new JLabel("password:");
        portLabel.setBounds(2*ten, 25*ten, 9*ten, 2*ten);
        portLabel.setForeground(Color.WHITE);
        add(portLabel);
    }
    private void createIpLabel(){
        ipLabel = new JLabel("ip:");
        ipLabel.setBounds(25*ten, 20*ten, 9*ten, 2*ten);
        ipLabel.setForeground(Color.WHITE);
        add(ipLabel);
    }

    private void createExit() {
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

    private void createConnect() {
        connect = new JButton("connect");
        connect.setBounds(28*ten, 30*ten, 7*ten, 3*ten);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (portField.getText()!=null&&portField.getText().length()>=1){
                    if (Integer.parseInt(portField.getText())==portId){
                        createClient();
                        createLoginPanel();
                    }
                    else {
                        JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"your port is invalid","wrongPort",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"your port doesnt exist","noPort",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(connect);
    }

    private void createClient(){
        Client client = null;
        try {
            client = new Client(portId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.start();
        Controller.getInstance().setClient(client);


    }
    private void createLoginPanel(){
        Login loginPanel=new Login();
        Controller.getInstance().getMyFrame().add(loginPanel,"login");
        Controller.getInstance().setLogin(loginPanel);
        Controller.getInstance().getMyFrame().setPanel("login");
    }
}
