package model.heroPackage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.Hero;

import java.util.Map;
@JsonTypeName("priest")
public class priest extends Hero {

    public priest() {
        super();
    }

    public priest(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void heroPower() {

    }

    @Override
    public void specialPower() {

    }
}
