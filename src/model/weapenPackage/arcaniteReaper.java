package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("arcaniteReaper")
public class arcaniteReaper extends weapen {

    public arcaniteReaper(Map<String,Object>map){
        super(map);
    }

    public arcaniteReaper() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitArcaniteReaper(this,freind,enemy,target);
    }


}
