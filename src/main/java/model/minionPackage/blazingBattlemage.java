package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("blazingBattlemage")
@JsonIgnoreProperties(ignoreUnknown = true)
public class blazingBattlemage  extends Minion {

    public blazingBattlemage(Map<String,Object> map){
        super(map);
    }

    public blazingBattlemage() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBlazingBattleMage(this,freind,enemy,target);
    }


}
