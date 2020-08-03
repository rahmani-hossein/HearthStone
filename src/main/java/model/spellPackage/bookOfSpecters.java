package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("bookOfSpecters")
public class bookOfSpecters extends spell {

    public bookOfSpecters(Map<String,Object>map){super(map);}

    public bookOfSpecters() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBookOfSpecters(this,freind,enemy,target);
    }


}
