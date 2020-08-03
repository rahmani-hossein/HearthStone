package model.heroPackage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.Hero;

import java.util.Map;

@JsonTypeName("warlock")
public class warlock extends Hero {
    public warlock() {
        super();
    }

    public warlock(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void heroPower() {

    }

    @Override
    public void specialPower() {
// we do it . we increase the health;
    }
}
