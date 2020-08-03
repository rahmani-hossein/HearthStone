package Interfaces;

import model.GamePlayer;

public interface Visitable {

    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target);

}