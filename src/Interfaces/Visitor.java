package Interfaces;

import model.GamePlayer;
import model.card;
import model.minionPackage.amberWatcher;
import model.minionPackage.*;
import model.spellPackage.*;
import model.weapenPackage.*;

public interface Visitor {

    public void visitAmberWatcher(amberWatcher amberWatcher, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitBlazingBattleMage( blazingBattlemage blazingBattlemage, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitCurioCollector( curioCollector curioCollector, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitDreadScale( dreadScale dreadScale, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitEvasiveChimaera( evasiveChimaera evasiveChimaera, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitFireHawk( fireHawk fireHawk, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitHighPriestAmet( highPriestAmet highPriestAmet, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitHotAirbaloon( hotAirballon hotAirballon, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitSathrovarr( sathrovarr sathrovarr, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitSecurityRover( securityRover securityRover, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitSwampKingDred( swampKingDred swampKingDred, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitTombWarden( tombWarden tombWarden, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitVeranus(veranus veranus, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitArcaneShot(arcaneShot arcaneShot, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitBookOfSpecters(bookOfSpecters bookOfSpecters, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitDecimation(decimation decimation, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitDivineHymn(divineHymn divineHymn, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitFireBlast(fireBlast fireBlast, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitFlameStrike(flamestrike flamestrike, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitFriendlySmith(friendlySmith friendlySmith, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitLearnDraconic(learnDraconic learnDraconic, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitMalygosFlamestrike(malygosFlamestrike malygosFlamestrike, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitMalygosFireBall(malygosFireball malygosFireball, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitPharaohsBlessing(pharaohsBlessing pharaohsBlessing, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitPolymorph(polymorph polymorph, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitSprint(sprint sprint, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitStrengthInNumbers(strengthInNumbers strengthInNumbers, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitSwarmOfLocusts(swarmOfLocusts swarmOfLocusts, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitArcaniteReaper(arcaniteReaper arcaniteReaper, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitAshBringer(ashBringer ashBringer, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitAssassinBlade(assassinBlade assassinBlade, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitBloodFury(bloodFury bloodFury, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitBloodClaw(bloodClaw bloodClaw, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitBloodRazor(bloodRazor bloodRazor, GamePlayer freind, GamePlayer enemy,Attackable target);

    public void visitBattleAxe(battleAxe battleAxe, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitDragonClaw(dragonClaw dragonClaw, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitFierywaraxe(fierywaraxe fierywaraxe, GamePlayer freind, GamePlayer enemy, Attackable target);

    public void visitGearBlade(gearBlade gearBlade, GamePlayer freind, GamePlayer enemy, Attackable target);

public void visitLoctus(loctus loctus,GamePlayer freind, GamePlayer enemy,Attackable target);


}
