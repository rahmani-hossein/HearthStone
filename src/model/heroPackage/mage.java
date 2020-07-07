package model.heroPackage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.Hero;

import java.util.Map;
@JsonTypeName("mage")
public class mage extends Hero {

    public mage() {
        super();
    }

    public mage(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void heroPower() {

    }
}
