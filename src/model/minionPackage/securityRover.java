package model.minionPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.Minion;
import model.card;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("securityRover")
public class securityRover extends Minion {

    public securityRover(Map<String, Object> map) {
        super(map);
    }


    public securityRover() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitSecurityRover(this,freind,enemy,target);
    }

}
