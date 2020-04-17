package swing;

import javax.swing.*;

public class dasd {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setVisible(true);
        System.out.println(frame.getSize());
    }
}
