package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("sathrovarr")
public class sathrovarr extends Minion {

    public sathrovarr(Map<String, Object> map) {
        super(map);
    }

    public sathrovarr() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitSathrovarr(this,freind,enemy,target);
    }


}
