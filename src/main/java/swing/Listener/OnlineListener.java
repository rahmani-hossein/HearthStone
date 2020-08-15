package swing.Listener;

import swing.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnlineListener extends GameListener {

    public OnlineListener(GamePanel gamePanel) {
        super(gamePanel);
    }

    public OnlineListener(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gamePanel.getGameState().isTurn()) {// nobat friend
           gamePanel. freindClicked(e);

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
