package model.spellPackage;

import Interfaces.Visitor;
import model.card;
import model.spell;

import java.util.ArrayList;
import java.util.Map;

public class swarmOfLocusts extends spell {

    public swarmOfLocusts(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void accept(Visitor visitor, ArrayList<card> friendlyDeck, ArrayList<card> friendlyHand, ArrayList<card> enemyDeck, ArrayList<card> enemyHand, ArrayList<card> friendlyGround, ArrayList<card> enemyGround, card target) {

    }
}
