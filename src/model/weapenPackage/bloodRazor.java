package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.card;
import model.weapen;

import java.util.ArrayList;
import java.util.Map;
@JsonTypeName("bloodRazor")
public class bloodRazor extends weapen {

    public  bloodRazor(Map<String,Object> map){
        super(map);
    }

    public bloodRazor() {
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBloodRazor(this,freind,enemy,target);
    }


}
