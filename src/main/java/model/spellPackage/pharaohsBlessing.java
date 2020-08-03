package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("pharaohsBlessing")
public class pharaohsBlessing extends spell {


    public pharaohsBlessing(Map<String, Object> map) {
        super(map);
    }

    public pharaohsBlessing() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitPharaohsBlessing(this,freind,enemy,target);
    }


}
