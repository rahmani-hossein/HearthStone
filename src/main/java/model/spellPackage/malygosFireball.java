package model.spellPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.spell;

import java.util.Map;

@JsonTypeName("malygosFireball")
public class malygosFireball extends spell {

    public malygosFireball(Map<String,Object> map){
        super(map);
    }

    public malygosFireball() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitMalygosFireBall(this,freind,enemy,target);
    }


}
