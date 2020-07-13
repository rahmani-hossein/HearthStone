package CLI;

import Interfaces.Attackable;
import Interfaces.Execution;
import logic.CollectionManager;
import logic.GameManager;
import logic.ShopManager;
import model.GamePlayer;
import model.Minion;
import model.card;
import model.weapen;
import swing.Controller;

public class LogicMapper {
    private Controller controller;
    private CollectionManager collectionManager;
    private ShopManager shopManager;
    private GameManager gameManager;

    public LogicMapper(Controller controller) {
        this.controller = controller;
        collectionManager = new CollectionManager(this.controller.getGameState().getPlayer());
        shopManager = new ShopManager(this.controller.getGameState().getPlayer());
        gameManager = new GameManager(this.controller.getGameState());
    }

    public void responce(LogicRequest logicRequest, GamePlayer hasTurn, GamePlayer noTurn, card selected, Attackable target) {
        switch (logicRequest) {

            case ATTACKWEAPEN:
                if (selected instanceof weapen) {
                    gameManager.attackWithWeapen(hasTurn,noTurn,target);
                }
                break;
            case ATTACKWITHMINION:
                if (selected instanceof Minion){
                    gameManager.attackWithMinion((Minion) selected,hasTurn,noTurn,target);
                }
                break;
            case DRAWHAND:
                gameManager.drawCardFromHand(hasTurn,noTurn,selected,target);
                break;
        }
    }
    public void responce(LogicRequest logicMapper){
        switch (logicMapper){
            case TURN:
                gameManager.nextRound();
                break;
        }
    }
//    public <T> T execute(String request,) {
//        switch (request) {
//            case "getDeck":
//                collectionManager.getDeck()
//        }
//    }

    public enum LogicRequest {
        ATTACKWITHMINION,
        ATTACKWEAPEN,
        TURN,
        DRAWHAND,

    }
}
