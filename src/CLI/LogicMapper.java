package CLI;

import Interfaces.Execution;
import logic.CollectionManager;
import logic.ShopManager;
import swing.Controller;

public class LogicMapper {
    private Controller controller;
    private CollectionManager collectionManager;
    private ShopManager shopManager;

    public LogicMapper(Controller controller) {
        this.controller = controller;
        collectionManager = new CollectionManager(controller.getGameState().getPlayer());
        shopManager = new ShopManager(controller.getGameState().getPlayer());
    }

    public <T> T execute(String request,) {
        switch (request) {
            case "getDeck":
                collectionManager.getDeck()
        }
    }
}
//    public  enum LogicRequest implements Execution  {
//
//}
