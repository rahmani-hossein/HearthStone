package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("malygosFlamestrike")
public class malygosFlamestrike extends spell {

    public malygosFlamestrike(Map<String, Object> map) {
        super(map);
    }

    public malygosFlamestrike() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitMalygosFlamestrike(this,freind,enemy,target);
    }


}
