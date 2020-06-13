package logic;

import Interfaces.Creatable;
import model.Hero;

public class HeroCreator {

    public HeroCreator() {

    }

    public Hero create(String name) {
      Hero hero=HeroEnum.valueOf(name.toUpperCase()).create();
      return hero;
    }

    public enum HeroEnum implements Creatable<Hero> {
        MAGE {
            @Override
            public Hero create() {
                Hero hero = new Hero(30, "mage", "spend 2 mana and can deal 1 damage from a chosen enemy ", "2 mana less than usual for spells");
                return hero;
            }
        },
        PRIEST {
            @Override
            public Hero create() {
                Hero hero = new Hero(30, "priest", " get 2 mana and restore 4 health", "double influence of restore cards");
                return hero;
            }
        },
        WARLOCK {
            @Override
            public Hero create() {
                Hero hero = new Hero(35, "warlock", "cost 2hp and randomly do one of these 2 works  if we have minion in ground plus+1 attack and hp to it or get randomly one card from deck and add it to its hand", "he has 35 hp");
                return hero;
            }
        },
        HUNTER {
            @Override
            public Hero create() {
            Hero  hero = new Hero(30, "hunter", "PASSIVE after your opponent plays a minion deal 1 damage to it", "all minions have rush ");

                return hero;
            }
        },
        ROUGE {
            @Override
            public Hero create() {
                Hero hero = new Hero(30, "rouge", "spend 3 mana and can get 1 card from enemy deck and add this to her hand.", "2 mana less than usual for cards that are not neutral or vip of herself ");
                return hero;
            }
        }

    }
}
