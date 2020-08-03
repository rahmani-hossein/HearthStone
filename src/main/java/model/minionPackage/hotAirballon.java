package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;

import java.util.Map;

@JsonTypeName("hotAirballon")
public class hotAirballon  extends Minion {
    public hotAirballon() {
    }

    public hotAirballon(Map<String,Object>map){
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitHotAirbaloon(this,freind,enemy,target);
    }


}
