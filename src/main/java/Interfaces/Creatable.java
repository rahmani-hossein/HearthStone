package Interfaces;

import model.Hero;

import java.util.Map;

public interface Creatable {
    public Hero create(Map<String, Object> map);
}
