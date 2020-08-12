package client;

import logic.CardManager;
import model.Player;

public class LogicManagerClient {
    private Player player;

    public LogicManagerClient(Player player) {
        this.player = player;
    }

    private CardManager cardManager = new CardManager();

    public boolean isKnocked(String name) {
        if (cardManager.number(player, name) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
