package swing;

import client.ClientConstants;
import client.Controller;
import model.Request;
import model.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Rank extends JPanel {

    private JButton exit;
    private JButton refresh;
    private JButton back;
    private JTable top10;
    private JTable me;
    private JScrollPane jScrollPane10;
    private JScrollPane jScrollPaneMe;
    private ArrayList<Score> scoresMe = new ArrayList<>();
    private ArrayList<Score> scoresTop10 = new ArrayList<>();
    private ClientConstants clientConstants =Controller.getInstance().getClientConstants();


    public Rank(){
        setLayout(null);
        setBackground(Color.pink);
        initExit();
        initRefresh();
        initBack();
    }



    private void initExit() {
        exit = new JButton("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().exitGame();
            }
        });
        exit.setBounds(1200, 100, 150, 50);
        add(exit);
    }

    private void initRefresh() {
        refresh = new JButton("refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request1 = new Request(Controller.getInstance().getClient().getToken(),"top10",null,"");
                Controller.getInstance().getClient().getSender().send(request1);
                Request request2 = new Request(Controller.getInstance().getClient().getToken(),"me",null,"");
                Controller.getInstance().getClient().getSender().send(request2);
            }
        });
        refresh.setBounds(1400, 100, 150, 50);
        add(refresh);
    }
    private void initBack(){
        back=new JButton("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getMyFrame().setPanel("menu");
            }
        });
        back.setBounds(1000,100,150,50);
        add(back);
    }
    public void initTop10Table(){
        Collections.sort(scoresTop10);
        String columon[] = {"userName:", "status:", "score:"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columon, 0);

        for (int i = 0; i < scoresTop10.size(); i++) {
            Object[] objects = {"  " + scoresTop10.get(i).getName(), "  " + scoresTop10.get(i).getState(), "  " + scoresTop10.get(i).getCupNumber()};
            defaultTableModel.addRow(objects);
        }

        top10 = new JTable(defaultTableModel);
        top10.setFont(new Font("Serif", Font.BOLD, 15));
        top10.setRowHeight(45);
        top10.setPreferredSize(new Dimension(clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY()));
        initScrollTop10();

    }
    public void initTableMe(){
        Collections.sort(scoresMe);
        String columon[] = {"userName:", "status:", "score:"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columon, 0);

        for (int i = 0; i < scoresMe.size(); i++) {
            Object[] objects = {"  " + scoresMe.get(i).getName(), "  " + scoresMe.get(i).getState(), "  " + scoresMe.get(i).getCupNumber()};
            defaultTableModel.addRow(objects);
        }

        me = new JTable(defaultTableModel);
        me.setFont(new Font("Serif", Font.BOLD, 15));
        me.setRowHeight(45);
        me.setPreferredSize(new Dimension(clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY()));
       initScrollMe();
    }
    private void initScrollMe() {
        jScrollPaneMe = new JScrollPane(me);
        jScrollPaneMe.setWheelScrollingEnabled(true);
        jScrollPaneMe.setPreferredSize(new Dimension(clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY()));
        jScrollPaneMe.setBounds(clientConstants.getSad(), 3*clientConstants.getSad(),clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY() );
        add(jScrollPaneMe);
    }
    private void initScrollTop10() {
        jScrollPane10 = new JScrollPane(top10);
        jScrollPane10.setWheelScrollingEnabled(true);
        jScrollPane10.setPreferredSize(new Dimension(clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY()));
        jScrollPane10.setBounds(65*(clientConstants.getSad()/10), 3*clientConstants.getSad(), clientConstants.getSizeBoardX(), clientConstants.getSizeBoardY());
        add(jScrollPane10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Serif",Font.BOLD,40));
        g.drawString("ME",300,250);
        g.drawString("TOP10",800,250);
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public ArrayList<Score> getScoresMe() {
        return scoresMe;
    }

    public void setScoresMe(ArrayList<Score> scoresMe) {
        this.scoresMe = scoresMe;
    }

    public ArrayList<Score> getScoresTop10() {
        return scoresTop10;
    }

    public void setScoresTop10(ArrayList<Score> scoresTop10) {
        this.scoresTop10 = scoresTop10;
    }
}



