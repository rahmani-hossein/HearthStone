package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("tombWarden")
public class tombWarden extends Minion {

    public tombWarden(Map<String, Object> map) {
        super(map);
    }

    public tombWarden() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitTombWarden(this,freind,enemy,target);
    }


}
