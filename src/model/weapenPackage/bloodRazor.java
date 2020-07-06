package model.weapenPackage;

import Interfaces.Visitor;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;

public class bloodRazor extends weapen {

    public  bloodRazor(Map<String,Object> map){
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, card target) {
        visitor.visitBloodRazor(this,freind,enemy,target);
    }


}
