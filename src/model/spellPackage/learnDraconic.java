package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("learnDraconic")
public class learnDraconic extends spell {

    public learnDraconic(Map<String, Object> map) {
        super(map);
    }

    public learnDraconic() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitLearnDraconic(this,freind,enemy,target);
    }


}
