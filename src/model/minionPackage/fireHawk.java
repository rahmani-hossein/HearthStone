package model.minionPackage;

import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("fireHawk")
public class fireHawk  extends Minion {

    public fireHawk(Map<String,Object>map){
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitFireHawk(this,freind,enemy,target);
    }




}
