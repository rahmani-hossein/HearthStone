package model.minionPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;

public class amberWatcher extends Minion {


    public amberWatcher(Map<String,Object> map){
       super(map);
    }

    @Override
    public void accept(Visitor visitor,  GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitAmberWatcher(this,freind,enemy,target);
    }


}
