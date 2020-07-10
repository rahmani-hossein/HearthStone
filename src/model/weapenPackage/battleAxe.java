package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("battleAxe")
public class battleAxe  extends weapen {


    public battleAxe(Map<String, Object> map) {
        super(map);
    }

    public battleAxe() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBattleAxe(this,freind,enemy,target);
    }


}
