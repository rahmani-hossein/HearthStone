package Interfaces;

import model.Minion;
import model.spell;
import model.weapen;

import java.util.Map;

public interface ExactFactory {

    public Minion createMinion(Map<String,Object>map);
    public spell createSpell(Map<String,Object>map);
    public weapen createWeapen(Map<String,Object>map);
}
