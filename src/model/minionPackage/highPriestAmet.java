package model.minionPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;

public class highPriestAmet extends Minion {

    public highPriestAmet(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitHighPriestAmet(this,freind,enemy,target);
    }


}
