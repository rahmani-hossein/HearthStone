package model.weapenPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;

public class bloodFury extends weapen {


    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitBloodFury(this,freind,enemy,target);
    }
}
