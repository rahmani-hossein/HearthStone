package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("divineHymn")
public class divineHymn extends spell {

    public divineHymn(Map<String, Object> map) {
        super(map);
    }

    public divineHymn() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitDivineHymn(this,freind,enemy,target);
    }


}
