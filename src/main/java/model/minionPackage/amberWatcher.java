package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("amberWatcher")
public class amberWatcher extends Minion {

    public amberWatcher() {
    }

    public amberWatcher(Map<String,Object> map){
       super(map);
    }


    @Override
    public void accept(Visitor visitor,  GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitAmberWatcher(this,freind,enemy,target);
    }


}
