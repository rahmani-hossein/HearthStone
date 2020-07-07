package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("bloodClaw")
public class bloodClaw  extends weapen {

    public bloodClaw(Map<String,Object>map){super(map);}

    public bloodClaw() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBloodClaw(this,freind,enemy,target);
    }


}
