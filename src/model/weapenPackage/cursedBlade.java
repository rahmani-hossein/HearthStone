package model.weapenPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;

public class cursedBlade  extends weapen {


    public cursedBlade(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitCursedBlade(this,freind,enemy,target);
    }


}
