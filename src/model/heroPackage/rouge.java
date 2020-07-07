package model.heroPackage;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.Hero;

import java.util.Map;
@JsonTypeName("rouge")
public class rouge  extends Hero {
    public rouge() {
        super();
    }

    public rouge(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void heroPower() {

    }
}
