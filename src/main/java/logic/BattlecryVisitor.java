package logic;

import Interfaces.Attackable;
import Interfaces.Visitor;
import model.*;
import model.minionPackage.*;
import model.spellPackage.*;
import model.weapenPackage.*;

import java.util.List;

public class BattlecryVisitor implements Visitor {

    private CardManager cardManager = new CardManager();

    public void restore(Hero hero, int restore) {
        if (hero.getHP() + restore > hero.getMaxHp()) {
            hero.setHP(hero.getMaxHp());
        } else {
            hero.setHP(hero.getHP() + restore);
        }
    }

    public void restore(Minion minion, int restore) {
        if (minion.getHealth() + restore > minion.getMaxHp()) {
            minion.setHealth(minion.getMaxHp());
        } else {
            minion.setHealth(minion.getHealth() + restore);
        }
    }

    public void dealDamageToHero(Hero hero, int damage) {
        if (hero.getHP() < damage) {
            hero.setHP(0);
        } else {
            hero.setHP(hero.getHP() - damage);
        }
    }

    public void dealDamageToMinion(Minion minion, int damage) {
        if (minion.getHealth() < damage) {
            minion.setHealth(0);
        } else {
            minion.setHealth(minion.getHealth() - damage);
        }
    }

    public void gainAttack(int gain, Minion minion) {
        minion.setDamage(minion.getDamage() + gain);
    }

    public card getCard(List<card> cardArrayList) {
        if (cardArrayList.size() >= 1) {
            card card = cardArrayList.remove(0);
            return card;
        }
        return null;
    }


    public Minion copy(Minion intrinsic) {
        Minion minion = cardManager.createM(intrinsic.getName());
        minion.setDamage(intrinsic.getDamage());
        minion.setHealth(intrinsic.getHealth());
        minion.setLiveInRound(intrinsic.getLiveInRound());
        minion.setManaCost(intrinsic.getManaCost());
        return minion;
    }

    public boolean allowToAdd(card card, List<? extends card> ground, int maxSize) {
        if (ground.size() >= maxSize) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void visitAmberWatcher(amberWatcher amberWatcher, GamePlayer freind, GamePlayer enemy, Attackable target) {
        restore(freind.getHero(), 8);
    }

    @Override
    public void visitBlazingBattleMage(blazingBattlemage blazingBattlemage, GamePlayer freind, GamePlayer enemy, Attackable target) {
//it doesnt have any battlecry;

    }

    @Override
    public void visitCurioCollector(curioCollector curioCollector, GamePlayer freind, GamePlayer enemy, Attackable target) {
//nothing to do in battlecry we do it in action

    }

    @Override
    public void visitDreadScale(dreadScale dreadScale, GamePlayer freind, GamePlayer enemy, Attackable target) {
//nothing to do in battlecry we do its  action in the action.

    }

    @Override
    public void visitEvasiveChimaera(evasiveChimaera evasiveChimaera, GamePlayer freind, GamePlayer enemy, Attackable target) {
        dealDamageToHero(enemy.getHero(), 2);
    }

    @Override
    public void visitFireHawk(fireHawk fireHawk, GamePlayer freind, GamePlayer enemy, Attackable target) {
        int num = enemy.getHand().size();
        gainAttack(num, fireHawk);
    }

    @Override
    public void visitHighPriestAmet(highPriestAmet highPriestAmet, GamePlayer freind, GamePlayer enemy, Attackable target) {
/// in action
    }

    @Override
    public void visitHotAirbaloon(hotAirballon hotAirballon, GamePlayer freind, GamePlayer enemy, Attackable target) {
// in action
    }

    @Override
    public void visitSathrovarr(sathrovarr sathrovarr, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            Minion minion = copy((Minion) target);
            if (allowToAdd(minion, freind.getHand(), 12)) {
                freind.getHand().add(minion);
            }

            if (allowToAdd(minion, freind.getGround(), 7)) {
                freind.getGround().add(minion);
            }

            freind.getDeck().add(minion);

        }
    }

    @Override
    public void visitSecurityRover(securityRover securityRover, GamePlayer freind, GamePlayer enemy, Attackable target) {
// we do it later in action.
    }

    @Override
    public void visitSwampKingDred(swampKingDred swampKingDred, GamePlayer freind, GamePlayer enemy, Attackable target) {
// nothing here
    }

    @Override
    public void visitTombWarden(tombWarden tombWarden, GamePlayer freind, GamePlayer enemy, Attackable target) {
        model.minionPackage.tombWarden tombWarden1 = (model.minionPackage.tombWarden) copy(tombWarden);
        if (allowToAdd(tombWarden1, freind.getGround(), 7)) {
            freind.getGround().add(tombWarden1);
        }

    }

    @Override
    public void visitVeranus(veranus veranus, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < enemy.getGround().size(); i++) {
            enemy.getGround().get(i).setHealth(1);
        }
    }

    @Override
    public void visitArcaneShot(arcaneShot arcaneShot, GamePlayer freind, GamePlayer enemy, Attackable target) {
        target.minusHealth(2);

    }

    @Override
    public void visitBookOfSpecters(bookOfSpecters bookOfSpecters, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < 3; i++) {
            card card = getCard(freind.getDeck());
            if ((card != null) && (card instanceof Minion || card instanceof weapen)) {
                if (allowToAdd(card, freind.getHand(), 12)) {
                    freind.getHand().add(card);
                }

            }
        }
    }

    @Override
    public void visitDecimation(decimation decimation, GamePlayer freind, GamePlayer enemy, Attackable target) {
        restore(freind.getHero(), 5);
        dealDamageToHero(enemy.getHero(), 5);
    }

    @Override
    public void visitDivineHymn(divineHymn divineHymn, GamePlayer freind, GamePlayer enemy, Attackable target) {
        restore(freind.getHero(), 6);
        for (int i = 0; i < freind.getGround().size(); i++) {
            restore(freind.getGround().get(i), 6);
        }
    }

    @Override
    public void visitFireBlast(fireBlast fireBlast, GamePlayer freind, GamePlayer enemy, Attackable target) {
        target.minusHealth(1);
    }

    @Override
    public void visitFlameStrike(flamestrike flamestrike, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < enemy.getGround().size(); i++) {
            dealDamageToMinion(enemy.getGround().get(i), 4);
        }
    }

    // sakht
    @Override
    public void visitFriendlySmith(friendlySmith friendlySmith, GamePlayer freind, GamePlayer enemy, Attackable target) {
// i dont know exactly

    }

    @Override
    public void visitLearnDraconic(learnDraconic learnDraconic, GamePlayer freind, GamePlayer enemy, Attackable target) {
//fghjkl;

    }

    @Override
    public void visitMalygosFlamestrike(malygosFlamestrike malygosFlamestrike, GamePlayer freind, GamePlayer enemy, Attackable target) {
        dealDamageToHero(enemy.getHero(), 8);
    }

    @Override
    public void visitMalygosFireBall(malygosFireball malygosFireball, GamePlayer freind, GamePlayer enemy, Attackable target) {
        target.minusHealth(6);
    }

    @Override
    public void visitPharaohsBlessing(pharaohsBlessing pharaohsBlessing, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            ((Minion) target).setDamage(((Minion) target).getDamage() + 4);
            ((Minion) target).setHealth(((Minion) target).getHealth() + 4);
            ((Minion) target).setTaunt(true);
            ((Minion) target).setDivineSheild(true);
        }
    }

    @Override
    public void visitPolymorph(polymorph polymorph, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            ((Minion) target).setHealth(1);
            ((Minion) target).setDamage(1);
        }
    }

    @Override
    public void visitSprint(sprint sprint, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < 4; i++) {
            card card = getCard(freind.getDeck());
            if (card != null) {
                if (allowToAdd(card, freind.getHand(), 12)) {
                    freind.getHand().add(card);
                }
            }
        }
    }

    @Override
    public void visitStrengthInNumbers(strengthInNumbers strengthInNumbers, GamePlayer freind, GamePlayer enemy, Attackable target) {
// i dont know ;
    }

    @Override
    public void visitSwarmOfLocusts(swarmOfLocusts swarmOfLocusts, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < 7; i++) {
            loctus loctus = new loctus(1, 1, 0, "loctus", "", "minion", "neutral", "common", false, false, false, false, 0, false, false, true, false);
            if (allowToAdd(loctus, freind.getGround(), 7)) {
                freind.getGround().add(loctus);
            }
        }
    }

    @Override
    public void visitArcaniteReaper(arcaniteReaper arcaniteReaper, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitAshBringer(ashBringer ashBringer, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitAssassinBlade(assassinBlade assassinBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitBloodFury(bloodFury bloodFury, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitBloodClaw(bloodClaw bloodClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {
        dealDamageToHero(freind.getHero(), 5);
    }

    @Override
    public void visitBloodRazor(bloodRazor bloodRazor, GamePlayer freind, GamePlayer enemy, Attackable target) {
// Battlecry and deathRattle
        for (int i = 0; i < freind.getGround().size(); i++) {
            dealDamageToMinion(freind.getGround().get(i),1);
        }
        for (int i = 0; i < enemy.getGround().size(); i++) {
            dealDamageToMinion(enemy.getGround().get(i),1);
        }
    }

    @Override
    public void visitBattleAxe(battleAxe battleAxe, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }


    @Override
    public void visitDragonClaw(dragonClaw dragonClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitFierywaraxe(fierywaraxe fierywaraxe, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitGearBlade(gearBlade gearBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here
    }

    @Override
    public void visitLoctus(loctus loctus, GamePlayer freind, GamePlayer enemy, Attackable target) {
        // nothing here .
    }
}
