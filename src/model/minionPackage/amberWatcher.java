package model.minionPackage;

import Interfaces.Visitor;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;

public class amberWatcher extends Minion {


    public amberWatcher(Map<String,Object> map){
       super(map);
    }

    @Override
    public void accept(Visitor visitor, ArrayList<card> friendlyDeck, ArrayList<card> friendlyHand, ArrayList<card> enemyDeck, ArrayList<card> enemyHand, ArrayList<card> friendlyGround, ArrayList<card> enemyGround, card target) {

    }


}
