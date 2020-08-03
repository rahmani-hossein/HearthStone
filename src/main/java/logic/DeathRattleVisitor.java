package logic;

import Interfaces.Attackable;
import Interfaces.Visitor;
import model.GamePlayer;
import model.Minion;
import model.minionPackage.*;
import model.spellPackage.*;
import model.weapenPackage.*;

public class DeathRattleVisitor implements Visitor {

    public void dealDamageToMinion(Minion minion, int damage) {
        if (minion.getHealth() < damage) {
            minion.setHealth(0);
        } else {
            minion.setHealth(minion.getHealth() - damage);
        }
    }


    @Override
    public void visitAmberWatcher(amberWatcher amberWatcher, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBlazingBattleMage(blazingBattlemage blazingBattlemage, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitCurioCollector(curioCollector curioCollector, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitDreadScale(dreadScale dreadScale, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitEvasiveChimaera(evasiveChimaera evasiveChimaera, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFireHawk(fireHawk fireHawk, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitHighPriestAmet(highPriestAmet highPriestAmet, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitHotAirbaloon(hotAirballon hotAirballon, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSathrovarr(sathrovarr sathrovarr, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSecurityRover(securityRover securityRover, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSwampKingDred(swampKingDred swampKingDred, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitTombWarden(tombWarden tombWarden, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitVeranus(veranus veranus, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

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

    @Override
    public void visitArcaniteReaper(arcaniteReaper arcaniteReaper, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitAshBringer(ashBringer ashBringer, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitAssassinBlade(assassinBlade assassinBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBloodFury(bloodFury bloodFury, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBloodClaw(bloodClaw bloodClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBloodRazor(bloodRazor bloodRazor, GamePlayer freind, GamePlayer enemy, Attackable target) {
//
        for (int i = 0; i < freind.getGround().size(); i++) {
            dealDamageToMinion(freind.getGround().get(i),1);
        }
        for (int i = 0; i < enemy.getGround().size(); i++) {
            dealDamageToMinion(enemy.getGround().get(i),1);
        }
    }

    @Override
    public void visitBattleAxe(battleAxe battleAxe, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitDragonClaw(dragonClaw dragonClaw, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFierywaraxe(fierywaraxe fierywaraxe, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitGearBlade(gearBlade gearBlade, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitLoctus(loctus loctus, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }
}
