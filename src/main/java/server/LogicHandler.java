package server;

import logic.CollectionManager;
import logic.PlayerManager;
import logic.ShopManager;

//for seprating logic and network layer and third layerd design
public class LogicHandler {

    private PlayerManager playerManager;
    private ShopManager shopManager;
    private CollectionManager collectionManager;
    private GameServer gameServer;

    public LogicHandler(GameServer gameServer) {
        this.gameServer=gameServer;
        playerManager=new PlayerManager(this.gameServer);
    }

    public ShopManager getShopManager() {
        return shopManager;
    }

    public void setShopManager(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
}
