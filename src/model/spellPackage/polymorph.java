package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("polymorph")
public class polymorph extends spell {

    public polymorph(Map<String,Object> map){
        super(map);
    }

    public polymorph() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitPolymorph(this,freind,enemy,target);
    }


}
