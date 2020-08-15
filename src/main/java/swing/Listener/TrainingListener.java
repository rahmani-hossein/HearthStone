package swing.Listener;

import swing.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TrainingListener extends GameListener {

    public TrainingListener(GamePanel gamePanel) {
        super(gamePanel);
    }

    public TrainingListener(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gamePanel.getGameState().isTurn()) {// nobat friend
            gamePanel.freindClicked(e);

        } else {//nobat enemy
            gamePanel.enemyClicked(e);
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
