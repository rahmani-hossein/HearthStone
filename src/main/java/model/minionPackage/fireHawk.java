package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("fireHawk")
public class fireHawk  extends Minion {

    public fireHawk(Map<String,Object>map){
        super(map);
    }

    public fireHawk() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitFireHawk(this,freind,enemy,target);
    }




}
