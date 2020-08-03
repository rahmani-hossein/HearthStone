package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("strengthInNumbers")
public class strengthInNumbers extends spell {

    public strengthInNumbers(Map<String, Object> map) {
        super(map);
    }

    public strengthInNumbers() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitStrengthInNumbers(this,freind,enemy,target);
    }


}
