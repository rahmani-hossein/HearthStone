package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("veranus")
public class veranus extends Minion {

    public veranus(Map<String, Object> map) {
        super(map);
    }

    public veranus() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitVeranus(this,freind,enemy,target);
    }


}
