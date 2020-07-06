package model.minionPackage;

import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("blazingBattlemage")
public class blazingBattlemage  extends Minion {

    public blazingBattlemage(Map<String,Object> map){
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitBlazingBattleMage(this,freind,enemy,target);
    }


}
