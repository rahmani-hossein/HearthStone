package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("cursedBlade")
public class cursedBlade  extends weapen {


    public cursedBlade(Map<String, Object> map) {
        super(map);
    }

    public cursedBlade() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitCursedBlade(this,freind,enemy,target);
    }


}
