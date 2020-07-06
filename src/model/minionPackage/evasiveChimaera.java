package model.minionPackage;

import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("evasiveChimaera")
public class evasiveChimaera  extends Minion {

    public evasiveChimaera(Map<String,Object> map){
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitEvasiveChimaera(this,freind,enemy,target);
    }


}
