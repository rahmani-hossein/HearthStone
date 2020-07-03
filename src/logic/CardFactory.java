package logic;

import Interfaces.ExactFactory;
import Interfaces.ObjectFactory;
import model.Minion;
import model.card;
import model.spell;
import model.weapen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CardFactory implements ExactFactory {

    Class myClass=null;

    //because they have different actions i prefere to seperate them;
    // and to avoid using instance of keyword very much:)))

    @Override
    public Minion createMinion(Map<String, Object> map) {
        Minion minion=null;
        try {
            myClass=Class.forName("model.minionPackage"+(String)map.get("name"));
        } catch (ClassNotFoundException e) {
            System.out.println("sorry we cant load minion with name  "+map.get("name"));
            e.printStackTrace();
        }
        try {
             minion = (Minion) suitableConstructor(myClass).newInstance(map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return minion;
    }

    @Override
    public spell createSpell(Map<String, Object> map) {
        spell spell=null;
        try {
            myClass=Class.forName("model.spellPackage"+(String) map.get("name"));
        } catch (ClassNotFoundException e) {
            System.out.println("cant load spell with name "+map.get("name"));
            e.printStackTrace();
        }
        try {
            spell = (spell) suitableConstructor(myClass).newInstance(map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return spell;
    }

    @Override
    public weapen createWeapen(Map<String, Object> map) {
        weapen weapen=null;
        try {
            myClass=Class.forName("model.weapenPackage"+(String)map.get("name"));
        } catch (ClassNotFoundException e) {
            System.out.println("sorry we cant load weapen"+map.get("name"));
            e.printStackTrace();
        }
        try {
            weapen = (weapen) suitableConstructor(myClass).newInstance(map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return weapen;
    }

    private Constructor suitableConstructor(Class myClass){
        Constructor constructor = null;
        try {
            constructor = myClass.getConstructor(new Class[]{Map.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }

}
