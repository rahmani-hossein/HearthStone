package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("arcaneShot")
public class arcaneShot extends spell {

    public arcaneShot(Map<String,Object> map){super(map);}

    public arcaneShot() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitArcaneShot(this,freind,enemy,target);
    }


}
