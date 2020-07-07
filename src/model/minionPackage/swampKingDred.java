package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("swampKingDred")
public class swampKingDred extends Minion {

    public swampKingDred(Map<String, Object> map) {
        super(map);
    }

    public swampKingDred() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitSwampKingDred(this,freind,enemy,target);
    }


}
