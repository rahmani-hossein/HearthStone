package model.spellPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;

public class swarmOfLocusts extends spell {

    public swarmOfLocusts(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitSwarmOfLocusts(this,freind,enemy,target);
    }


}
