package logic;

import Interfaces.Creatable;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Hero;
import model.weapen;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class HeroCreator implements Creatable {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Hero createHero(String name) {
        return create(getMap(name));

    }


    // ino static kardam ke to method estefade beshe kar behtar ham bood ke masalan be ezai card ono anjam dadam . vali inja barai
    //kholaseh shodan in karo kardam.
    public Constructor suitableConstructor(Class myClass) {
        Constructor constructor = null;
        try {
            constructor = myClass.getConstructor(new Class[]{Map.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }

    private Map<String, Object> getMap(String name) {
        String st = String.format("Json/%s.json", name);
        FileReader fileReader;
        try {
            fileReader = new FileReader(st);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            Map<String, Object> map = objectMapper.readValue(fileReader, typeRef);
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("can not get map of hero");
        return null;
    }

    @Override
    public Hero create(Map<String, Object> map) {
        Class myClass = null;
        Hero hero = null;
        try {
            String st=String.format("model.heroPackage.%s",(String)map.get("name"));
            myClass = Class.forName(st);
            hero = (Hero) suitableConstructor(myClass).newInstance(map);
            return hero;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public enum HeroEnum implements Creatable<Hero> {
//
//        MAGE {
//            @Override
//            public Hero create()  {
//
//                Hero hero = new Hero(30, "mage", "spend 2 mana and can deal 1 damage from a chosen enemy ", "2 mana less than usual for spells");
//                return hero;
//            }
//        },
//        PRIEST {
//            @Override
//            public Hero create() {
//                Hero hero = new Hero(30, "priest", " get 2 mana and restore 4 health", "double influence of restore cards");
//                return hero;
//            }
//        },
//        WARLOCK {
//            @Override
//            public Hero create() {
//                Hero hero = new Hero(35, "warlock", "cost 2hp and randomly do one of these 2 works  if we have minion in ground plus+1 attack and hp to it or get randomly one card from deck and add it to its hand", "he has 35 hp");
//                return hero;
//            }
//        },
//        HUNTER {
//            @Override
//            public Hero create() {
//            Hero  hero = new Hero(30, "hunter", "PASSIVE after your opponent plays a minion deal 1 damage to it", "all minions have rush ");
//
//                return hero;
//            }
//        },
//        ROUGE {
//            @Override
//            public Hero create() {
//                Hero hero = new Hero(30, "rouge", "spend 3 mana and can get 1 card from enemy deck and add this to her hand.", "2 mana less than usual for cards that are not neutral or vip of herself ");
//                return hero;
//            }
//        }
//
//    }
}
