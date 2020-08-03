package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.weapen;

import java.util.Map;

@JsonTypeName("dragonClaw")
public class dragonClaw extends weapen {

    public dragonClaw(Map<String,Object>map){
        super(map);
    }

    public dragonClaw() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitDragonClaw(this,freind,enemy,target);
    }


}
