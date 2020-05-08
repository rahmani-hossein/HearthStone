package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.imageio.ImageIO.read;

public class Constans {

    public static final int cardWidth = 200;
    public static final int cardHeigth = 285;
    public static final int space = 100;
    public static final int panelHeight = 1000;
    public static final int panelWidth = 1700;
//    public static final int sizeW=200;
//    public static final int sizeH=275;

    public static String[] minions = new String[]{"blazingBattlemage", "hotAirballon", "fireHawk ", "evasiveChimaera", "amberWatcher", "dreadScale", "veranus", "curioCollector", "highPriestAmet", "sathrovarr", "securityRover", "swampKingDred", "tombWarden"};
    public static String[] spells = new String[]{"decimation", "malygosFireball", "malygosFlamestrike", "friendlySmith", "fireBlast", "arcaneShot", "bookOfSpecters", "flamestrike", "learnDraconic", "pharaohsBlessing", "sprint", "strengthInNumbers", "swarmOfLocusts", "divineHymn"};
    public static String[] weapens = new String[]{"bloodRazor", "fieryWaraxe", "bloodClaw", "assassinBlade", "arcaniteReaper", "cursedBlade", "gearBlade", "bloodFury", "dragonClaw", "ashBringer"};
    public static String[] cardNames = new String[]{"blazingBattlemage", "hotAirballon", "fireHawk", "evasiveChimaera", "amberWatcher", "dreadScale", "veranus", "curioCollector", "highPriestAmet", "sathrovarr", "securityRover", "swampKingDred", "tombWarden", "decimation", "malygosFireball", "malygosFlamestrike", "friendlySmith", "fireBlast", "arcaneShot", "bookOfSpecters", "flamestrike", "learnDraconic", "pharaohsBlessing", "sprint", "strengthInNumbers", "swarmOfLocusts", "divineHymn", "bloodRazor", "fierywaraxe", "bloodClaw", "assassinBlade", "arcaniteReaper", "cursedBlade", "gearBlade", "bloodFury", "dragonClaw", "ashBringer"};
    public static int[] cost = new int[]{4, 5, 5, 6, 9, 6, 10, 8, 9, 10, 6, 6, 8, 10, 7, 10, 6, 6, 5, 6, 10, 6, 10, 9, 6, 9, 7, 8, 8, 6, 8, 8, 5, 6, 10, 8, 8};
    public static HashMap<String, Integer> costsMap = new HashMap<>();
    public static HashMap<String, String> types = new HashMap<>();
    public static HashMap<String, BufferedImage> images = new HashMap<>();
    public static String[] rarity = {"common", "rare", "epic", "legendary"};
    public static String[] HeroClass = new String[]{"mage", "rouge", "warlock", "hunter", "priest","neutral"};
    public static String[] warlock = {"fireHawk", "dreadScale"};
    public static String[] mage = {"fireBlast", "polymorph"};
    public static String[] rouge = {"friendlySmith", "fierrywaraxe"};
    public static String[] hunter = {"arcaneShot", "swampKingDred"};
    public static String[] priest = {"hightPriestAmet", "flamestrike"};
    public static String[] heros = {"mage", "rouge", "warlock", "hunter", "priest"};

    public static void fill() {
        for (int i = 0; i < cardNames.length; i++) {
            costsMap.put(cardNames[i], cost[i]);
        }
        for (int i = 0; i < cardNames.length; i++) {
            if (i <= 12) {
                types.put(cardNames[i], "minion");
            } else if (i <= 26) {
                types.put(cardNames[i], "spell");

            } else {
                types.put(cardNames[i], "weapen");

            }
        }
    }

    public static BufferedImage amberWatcher;
    public static BufferedImage arcanitereaper;
    public static BufferedImage assassinBlade;
    public static BufferedImage blazingBattlemage;
    public static BufferedImage bloodClaw;
    public static BufferedImage bloodRazor;
    public static BufferedImage bloodFury;
    public static BufferedImage bookOfSpecters;
    public static BufferedImage curioCollector;
    public static BufferedImage cursedBlade;
    public static BufferedImage decimation;
    public static BufferedImage dragonClaw;
    public static BufferedImage evasiveChimaera;
    public static BufferedImage fireBlast;
    public static BufferedImage fireHawk;
    public static BufferedImage dreadScale;
    public static BufferedImage divineHymn;
    public static BufferedImage fierywaraxe;
    public static BufferedImage flamestrike;
    public static BufferedImage friendlySmith;
    public static BufferedImage gearBlade;
    public static BufferedImage highPriestAmet;
    public static BufferedImage hotAirballon;
    //  public static BufferedImage koboldstickyfinger;
    public static BufferedImage learnDraconic;
    public static BufferedImage malygosFireball;
    public static BufferedImage malygosFlamestrike;
    public static BufferedImage arcaneShot;

    // public  static BufferedImage lightforgedblessing;
    public static BufferedImage pharaohsBlessing;
    public static BufferedImage polymorph;
    // public static BufferedImage sandbreath;
    public static BufferedImage sathrovarr;
    public static BufferedImage securityRover;
    // public static BufferedImage silversword;
    public static BufferedImage sprint;
    public static BufferedImage strengthInNumbers;
    public static BufferedImage swampKingDred;
    public static BufferedImage swarmOfLocusts;
    //  public static BufferedImage tastyflyfish;
    public static BufferedImage tombWarden;
    public static BufferedImage veranus;
    public static BufferedImage ashBringer;


    {

        try {
            amberWatcher = read(new File("resources\\Image\\cards\\amberWacher.png"));
            arcaneShot = read(new File("resources\\Image\\cards\\arcaneshot.png"));

            arcanitereaper = read(new File("resources\\Image\\cards\\arcanitereaper.png"));
            assassinBlade = read(new File("resources\\Image\\cards\\assassinblade.png"));
            blazingBattlemage = read(new File("resources\\Image\\cards\\blazingbattlemage.png"));
            bloodClaw = read(new File("resources\\Image\\cards\\bloodclaw.png"));
            bloodRazor = read(new File("resources\\Image\\cards\\bloodrazor.png"));

            bloodFury = read(new File("resources\\Image\\cards\\bloodfury.png"));
            bookOfSpecters = read(new File("resources\\Image\\cards\\bookofspecters.png"));
            // cobaltspellkin = read(new File("resources\\Image\\cards\\cobaltspellkin.png"));
            curioCollector = read(new File("resources\\Image\\cards\\curiocollector.png"));
            cursedBlade = read(new File("resources\\Image\\cards\\cursedblade.png"));
            decimation = read(new File("resources\\Image\\cards\\decimation.png"));
            dragonClaw = read(new File("resources\\Image\\cards\\dragonclaw.png"));
            evasiveChimaera = read(new File("resources\\Image\\cards\\evasivechimaera.png"));
            fireBlast = read(new File("resources\\Image\\cards\\fireblast.png"));
            fireHawk = read(new File("resources\\Image\\cards\\firehawk.png"));
            divineHymn = read(new File("resources\\Image\\cards\\divinehymn.png"));
            dreadScale = read(new File("resources\\Image\\cards\\dreadscale.png"));
            fierywaraxe = read(new File("resources\\Image\\cards\\fierywaraxe.png"));
            flamestrike = read(new File("resources\\Image\\cards\\flamestrike.png"));
            friendlySmith = read(new File("resources\\Image\\cards\\friendlysmith.png"));
            gearBlade = read(new File("resources\\Image\\cards\\gearblade.png"));
            highPriestAmet = read(new File("resources\\Image\\cards\\highPriestAmet.png"));
            hotAirballon = read(new File("resources\\Image\\cards\\hotairballon.png"));
            // koboldstickyfinger = read(new File("resources\\Image\\cards\\koboldstickyfinger.png"));
            learnDraconic = read(new File("resources\\Image\\cards\\learndraconic.png"));
            malygosFireball = read(new File("resources\\Image\\cards\\malygosfireball.png"));
            malygosFlamestrike = read(new File("resources\\Image\\cards\\malygosflamestrike.png"));

            // lightforgedblessing = read(new File("resources\\Image\\cards\\lightforgedblessing.png"));
            pharaohsBlessing = read(new File("resources\\Image\\cards\\pharaohsblessing.png"));
            polymorph = read(new File("resources\\Image\\cards\\polymorph.png"));
            //  sandbreath = read(new File("resources\\Image\\cards\\sandbreath.png"));
            sathrovarr = read(new File("resources\\Image\\cards\\sathrovarr.png"));
            securityRover = read(new File("resources\\Image\\cards\\securityrover.png"));
            // silversword = read(new File("resources\\Image\\cards\\silversword.png"));
            sprint = read(new File("resources\\Image\\cards\\sprint.png"));
            strengthInNumbers = read(new File("resources\\Image\\cards\\strengthinnumbers.png"));
            swampKingDred = read(new File("resources\\Image\\cards\\swampkingdred.png"));
            swarmOfLocusts = read(new File("resources\\Image\\cards\\swarmoflocusts.png"));
            // tastyflyfish = read(new File("resources\\Image\\cards\\tastyflyfish.png"));
            tombWarden = read(new File("resources\\Image\\cards\\tombwarden.png"));
            veranus = read(new File("resources\\Image\\cards\\veranus.png"));
            ashBringer = read(new File("resources\\Image\\cards\\ashbringer.png"));
            // truesilverchampion = read(new File("resources\\Image\\cards\\truesilverchampion.png"));
            //  umbralskulker = read(new File("resources\\Image\\cards\\umbralskulker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        images.put("amberWatcher",amberWatcher);
//        images.put(,arca)
//       images .put("arcanitereaper", arcanitereaper);
//      images.put("gearblade", gearBlade);
//           images.put("ashbringer", ashBringer);
//        images.put("bloodfury", bloodFury);
//        images.put("bookofspecters", bookOfSpecters);
//        images.put("curiocollector", curioCollector);
//        images.put("divinehymn", divineHymn);
//        images.put("dreadscale", dreadScale);
//        images.put("fierywaraxe", fierywaraxe);
//        images.put("flamestrike", flamestrike);
//        images.put("friendlysmith", friendlySmith);
//       images.put("highpriestamet", highPriestAmet);
//        images.put("holylight", holylight);
//        images.put("koboldstickyfinger", koboldstickyfinger);
//        images.put("learndraconic", learndraconic);
//        images.put("lightforgedblessing", lightforgedblessing);
//        images.put("pharaohblessing", pharaohblessing);
//        images.put("polymorph", polymorph);
//        images.put("sandbreath", sandbreath);
//        images.put("sathrovarr", sathrovarr);
//        images.put("securityrover", securityRover);
//        images.put("silversword", silversword);
//        images.put("sprint", sprint);
//        images.put("strengthinnumbers", strengthinnumbers);
//        images.put("swampkingdred", swampKingDred);
//        images.put("swarmoflocusts", swarmoflocusts);
//        images.put("tastyflyfish", tastyflyfish);
//        images.put("tombwarden", tombWarden);

    }
}
