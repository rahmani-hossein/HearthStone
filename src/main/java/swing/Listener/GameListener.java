package swing.Listener;

import CLI.LogicMapper;
import model.*;
import swing.GamePanel;
import swing.button.Picture;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameListener implements MouseListener {

    protected GamePanel gamePanel;


    public GameListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GameListener() {

    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
