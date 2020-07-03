package Interfaces;

import model.card;

import java.util.ArrayList;

public interface Visitable {

    public void accept(Visitor visitor, ArrayList<card> friendlyDeck,ArrayList<card> friendlyHand,ArrayList<card> enemyDeck,ArrayList<card> enemyHand,ArrayList<card> friendlyGround,ArrayList<card> enemyGround,card target);
}
