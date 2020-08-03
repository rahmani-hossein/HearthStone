package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("sprint")
public class sprint extends spell {

    public sprint(Map<String, Object> map) {
        super(map);
    }

    public sprint() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitSprint(this,freind,enemy,target);
    }


}
