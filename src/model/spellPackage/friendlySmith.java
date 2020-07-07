package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("friendlySmith")
public class friendlySmith extends spell {

    public friendlySmith(Map<String,Object> map){
        super(map);
    }

    public friendlySmith() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitFriendlySmith(this,freind,enemy,target);
    }


}
