package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.weapen;

import java.util.Map;

@JsonTypeName("gearBlade")
public class gearBlade extends weapen {

    public gearBlade(Map<String, Object> map) {
        super(map);
    }

    public gearBlade() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitGearBlade(this,freind,enemy,target);
    }


}
