package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.weapen;

import java.util.Map;

@JsonTypeName("assassinBlade")
public class assassinBlade extends weapen {

    public assassinBlade(Map<String,Object>map){
        super(map);
    }

    public assassinBlade() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitAssassinBlade(this,freind,enemy,target);
    }


}
