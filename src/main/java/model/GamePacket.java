package model;

import Interfaces.Attackable;

public class GamePacket {

    private Attackable target;
    private GamePlayer hasTurn;
    private GamePlayer noTurn;
    private card card;
    public GamePacket(){

    }

    public GamePacket(Attackable target,card card, GamePlayer hasTurn, GamePlayer noTurn) {
        this.target = target;
        this.hasTurn = hasTurn;
        this.noTurn = noTurn;
        this.card=card;
    }


    public model.card getCard() {
        return card;
    }

    public void setCard(model.card card) {
        this.card = card;
    }

    public Attackable getTarget() {
        return target;
    }

    public void setTarget(Attackable target) {
        this.target = target;
    }

    public GamePlayer getHasTurn() {
        return hasTurn;
    }

    public void setHasTurn(GamePlayer hasTurn) {
        this.hasTurn = hasTurn;
    }

    public GamePlayer getNoTurn() {
        return noTurn;
    }

    public void setNoTurn(GamePlayer noTurn) {
        this.noTurn = noTurn;
    }
}
