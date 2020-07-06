package model.minionPackage;

import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("curioCollector")
public class curioCollector extends Minion {


    public curioCollector(Map<String,Object>map){super(map);}

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitCurioCollector(this,freind,enemy,target);
    }


}
