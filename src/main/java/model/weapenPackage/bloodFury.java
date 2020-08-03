package model.weapenPackage;

import Interfaces.Attackable;
import Interfaces.Visitor;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.GamePlayer;
import model.weapen;

import java.util.Map;

@JsonTypeName("bloodFury")
public class bloodFury extends weapen {

    public bloodFury() {
    }

    public bloodFury(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void accept(Visitor visitor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitor.visitBloodFury(this,freind,enemy,target);
    }
}
