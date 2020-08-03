package logic;

import Interfaces.Attackable;
import Interfaces.Visitor;
import client.Controller;
import model.GamePlayer;
import model.Minion;
import model.card;
import model.minionPackage.*;
import model.spellPackage.*;
import model.weapenPackage.*;

import javax.swing.*;
import java.util.List;

public class BizareVisitor implements Visitor {


    private boolean canAdd(List<? extends card>cards, int size){
        if (cards.size()<size){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean find(List<Minion> list, Minion minion){
        boolean finded=false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(minion.getName())&&list.get(i).getLiveInRound()==minion.getLiveInRound()&&list.get(i).getHealth()==minion.getHealth()&&list.get(i).getDamage()==minion.getDamage()){
                finded =true;
                break;
            }
        }
        return finded;
    }

    @Override
    public void visitAmberWatcher(amberWatcher amberWatcher, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitBlazingBattleMage(blazingBattlemage blazingBattlemage, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitCurioCollector(curioCollector curioCollector, GamePlayer freind, GamePlayer enemy, Attackable target) {
        curioCollector.setHealth(curioCollector.getHealth()+1);
        curioCollector.setDamage(curioCollector.getDamage()+1);
    }

    @Override
    public void visitDreadScale(dreadScale dreadScale, GamePlayer freind, GamePlayer enemy, Attackable target) {
        for (int i = 0; i < enemy.getGround().size(); i++) {
            enemy.getGround().get(i).setHealth(enemy.getGround().get(i).getHealth()-1);
        }
        for (int i = 0; i < freind.getGround().size(); i++) {
            if ((freind.getGround().get(i).getName().equalsIgnoreCase(dreadScale.getName()))&&freind.getGround().get(i).getLiveInRound()==dreadScale.getLiveInRound()){
                // in hamoon minion hastesh.
            }
            else {
                freind.getGround().get(i).setHealth(freind.getGround().get(i).getHealth()-1);
            }
        }
    }

    @Override
    public void visitEvasiveChimaera(evasiveChimaera evasiveChimaera, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitFireHawk(fireHawk fireHawk, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitHighPriestAmet(highPriestAmet highPriestAmet, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (target instanceof Minion){
            if (find(freind.getGround(), (Minion) target)){
                ((Minion) target).setHealth(highPriestAmet.getHealth());
            }
            else {
                System.out.println(" you choose bad thing for target");
                JOptionPane.showMessageDialog(Controller.getInstance().getMyFrame(),"you cant choose this for target of priestAmet","ErrorChoose",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void visitHotAirbaloon(hotAirballon hotAirballon, GamePlayer freind, GamePlayer enemy, Attackable target) {
        hotAirballon.setHealth(hotAirballon.getHealth()+1);
    }

    @Override
    public void visitSathrovarr(sathrovarr sathrovarr, GamePlayer freind, GamePlayer enemy, Attackable target) {

    }

    @Override
    public void visitSecurityRover(securityRover securityRover, GamePlayer freind, GamePlayer enemy, Attackable target) {
        if (canAdd(freind.getGround(),7)){
            loctus loctus=new loctus(3,2,0,"mech","","minion","neutral","common",false,false,false,false,4,false,false,false,true);
                freind.getGround().add(loctus);
        }
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
