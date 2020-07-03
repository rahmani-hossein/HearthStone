package model.weapenPackage;

import Interfaces.Visitor;
import model.card;
import model.weapen;

import java.util.ArrayList;

public class bloodFury extends weapen {

    @Override
    public void accept(Visitor visitor, ArrayList<card> friendlyDeck, ArrayList<card> friendlyHand, ArrayList<card> enemyDeck, ArrayList<card> enemyHand, ArrayList<card> friendlyGround, ArrayList<card> enemyGround, card target) {

    }
}
