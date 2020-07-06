package model.spellPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;

public class bookOfSpecters extends spell {

    public bookOfSpecters(Map<String,Object>map){super(map);}

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitBookOfSpecters(this,freind,enemy,target);
    }


}
