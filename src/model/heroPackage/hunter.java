package model.heroPackage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.Hero;

import java.util.Map;
@JsonTypeName("hunter")
public class hunter extends Hero {

    public hunter(){
        super();
    }
    public hunter(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void heroPower() {

    }

    @Override
    public void specialPower() {

    }
}
