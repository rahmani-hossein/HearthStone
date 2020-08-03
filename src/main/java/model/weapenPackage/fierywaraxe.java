package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.weapen;

import java.util.Map;

@JsonTypeName("fierywaraxe")
public class fierywaraxe extends weapen {

    public fierywaraxe(Map<String,Object> map){
        super(map);
    }

    public fierywaraxe() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitFierywaraxe(this,freind,enemy,target);
    }


}
