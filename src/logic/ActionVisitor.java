package logic;

import Interfaces.Attackable;
import Interfaces.Visitor;
import model.GamePlayer;
import model.Hero;
import model.Minion;
import model.minionPackage.*;
import model.spellPackage.*;
import model.weapen;
import model.weapenPackage.*;

public class ActionVisitor implements Visitor {


    private void attackMinionToMinion(Minion attacker, Minion defender) {
        if (defender.isDivineSheild()) {
            if (attacker.isDivineSheild()) {
                // nothing to do ;
                attacker.setDivineSheild(false);
                defender.setDivineSheild(false);
            } else {
                attacker.setHealth(Math.max(attacker.getHealth() - defender.getDamage(), 0));
                defender.setDivineSheild(false);
            }
        } else {
            if (attacker.isDivineSheild()) {
                defender.setHealth(Math.max(defender.getHealth() - attacker.getDamage(), 0));
                attacker.setDivineSheild(false);
            } else {
                defender.setHealth(Math.max(defender.getHealth() - attacker.getDamage(), 0));
                attacker.setHealth(Math.max(attacker.getHealth() - defender.getDamage(), 0));
            }
        }
    }

    private void attackMinionToHero(Minion attacker, GamePlayer target) {
        if (target.getMyWeapen() != null) {
            if (attacker.isDivineSheild()) {
                //hero ro mizaneh
                target.getHero().setHP(target.getHero().getHP() - attacker.getDamage());
                attacker.setDivineSheild(false);
            } else {
                target.getHero().setHP(target.getHero().getHP() - attacker.getDamage());
                attacker.setHealth(attacker.getHealth() - target.getMyWeapen().getDamage());//durability kam nemisheh
            }
        } else {
            // hero natoneh hamleh koneh;
            if (attacker.isDivineSheild()) {
                target.getHero().setHP(Math.max(target.getHero().getHP() - attacker.getDamage(), 0));
            } else {
                target.getHero().setHP(Math.max(target.getHero().getHP() - attacker.getDamage(), 0));
            }
        }
    }

    private void attackHeroToMinion(GamePlayer gamePlayer, Minion defender) {
        if (gamePlayer.getMyWeapen() != null) {
            if (defender.isDivineSheild()) {
                defender.setDivineSheild(false);
                gamePlayer.getHero().setHP(Math.max(gamePlayer.getHero().getHP() - defender.getDamage(), 0));
                gamePlayer.getMyWeapen().setDurability(Math.max(gamePlayer.getMyWeapen().getDurability() - 1, 0));
            } else {
                gamePlayer.getHero().setHP(Math.max(gamePlayer.getHero().getHP() - defender.getDamage(), 0));
                gamePlayer.getMyWeapen().setDurability(Math.max(gamePlayer.getMyWeapen().getDurability() - 1, 0));
                defender.setHealth(Math.max(defender.getHealth() - gamePlayer.getMyWeapen().getDamage(), 0));
            }

        } else {
            // vaghti aslaheh nadareh chejuri hamleh koneh?
        }
    }

    private void attackHeroToHero(GamePlayer freind, GamePlayer enemy) {
        // kolan faghat attacker attack mikoneh;
        if (freind.getMyWeapen() != null) {
            enemy.getHero().setHP(Math.max(enemy.getHero().getHP() - freind.getMyWeapen().getDamage(), 0));
        } else {
            //nothing to do here
        }
    }

    //for minions;
    private void visit(Minion minion, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            attackMinionToMinion(minion, (Minion) target);
        }
        if (target instanceof Hero) {
            attackMinionToHero(minion, enemy);
        }
    }

    private void visitWeapen(weapen weapen, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion) {
            attackHeroToMinion(freind, (Minion) target);
        }
        if (target instanceof Hero) {
            attackHeroToHero(freind, enemy);
        }
    }

    @Override
    public void visitAmberWatcher(amberWatcher amberWatcher, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(amberWatcher, enemy, target);
    }

    @Override
    public void visitBlazingBattleMage(blazingBattlemage blazingBattlemage, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(blazingBattlemage, enemy, target);
    }

    @Override
    public void visitCurioCollector(curioCollector curioCollector, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(curioCollector, enemy, target);
    }

    @Override
    public void visitDreadScale(dreadScale dreadScale, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(dreadScale, enemy, target);
    }

    @Override
    public void visitEvasiveChimaera(evasiveChimaera evasiveChimaera, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(evasiveChimaera, enemy, target);
    }

    @Override
    public void visitFireHawk(fireHawk fireHawk, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(fireHawk, enemy, target);
    }

    @Override
    public void visitHighPriestAmet(highPriestAmet highPriestAmet, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(highPriestAmet, enemy, target);
    }

    @Override
    public void visitHotAirbaloon(hotAirballon hotAirballon, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(hotAirballon, enemy, target);
    }

    @Override
    public void visitSathrovarr(sathrovarr sathrovarr, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(sathrovarr, enemy, target);
    }

    @Override
    public void visitSecurityRover(securityRover securityRover, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(securityRover, enemy, target);
    }

    @Override
    public void visitSwampKingDred(swampKingDred swampKingDred, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(swampKingDred, enemy, target);
    }

    @Override
    public void visitTombWarden(tombWarden tombWarden, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(tombWarden, enemy, target);
    }

    @Override
    public void visitVeranus(veranus veranus, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(veranus, enemy, target);
    }

    // not for spells
    @Override
    public void visitArcaneShot(arcaneShot arcaneShot, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBookOfSpecters(bookOfSpecters bookOfSpecters, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitDecimation(decimation decimation, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitDivineHymn(divineHymn divineHymn, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFireBlast(fireBlast fireBlast, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFlameStrike(flamestrike flamestrike, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFriendlySmith(friendlySmith friendlySmith, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitLearnDraconic(learnDraconic learnDraconic, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitMalygosFlamestrike(malygosFlamestrike malygosFlamestrike, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitMalygosFireBall(malygosFireball malygosFireball, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitPharaohsBlessing(pharaohsBlessing pharaohsBlessing, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitPolymorph(polymorph polymorph, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSprint(sprint sprint, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitStrengthInNumbers(strengthInNumbers strengthInNumbers, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSwarmOfLocusts(swarmOfLocusts swarmOfLocusts, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    // weapen
    @Override
    public void visitArcaniteReaper(arcaniteReaper arcaniteReaper, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(arcaniteReaper, freind, enemy, target);
    }

    @Override
    public void visitAshBringer(ashBringer ashBringer, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(ashBringer, freind, enemy, target);
    }

    @Override
    public void visitAssassinBlade(assassinBlade assassinBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(assassinBlade, freind, enemy, target);
    }

    @Override
    public void visitBloodFury(bloodFury bloodFury, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(bloodFury, freind, enemy, target);
    }

    @Override
    public void visitBloodClaw(bloodClaw bloodClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(bloodClaw, freind, enemy, target);
    }

    @Override
    public void visitBloodRazor(bloodRazor bloodRazor, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(bloodRazor, freind, enemy, target);
    }

    @Override
    public void visitBattleAxe(battleAxe battleAxe, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(battleAxe, freind, enemy, target);
    }

    @Override
    public void visitDragonClaw(dragonClaw dragonClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(dragonClaw, freind, enemy, target);
    }

    @Override
    public void visitFierywaraxe(fierywaraxe fierywaraxe, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(fierywaraxe,freind,enemy,target);
    }

    @Override
    public void visitGearBlade(gearBlade gearBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visitWeapen(gearBlade,freind,enemy,target);
    }

    @Override
    public void visitLoctus(loctus loctus, GamePlayer freind, GamePlayer enemy, Attackable target) {
        visit(loctus, enemy, target);
    }
}
