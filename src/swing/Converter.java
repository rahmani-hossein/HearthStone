package swing;

import logic.Constans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Converter {


    public Converter() {
        loadImages();
    }


    private HashMap<String, BufferedImage> imageMap = new HashMap<>();


    public ArrayList<BufferedImage> convert(ArrayList<String> name) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {
            images.add(getImage(name.get(i)));
            // images.add(find(name.get(i)));
        }
        return images;
    }

    private BufferedImage find(String name) {
        String url = String.format("resources/image/cards/%s.png", name);
        BufferedImage image = null;
        try {
            File file = new File(url);
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public BufferedImage getImage(String imageName) {
        return imageMap.get(imageName);
    }

    private void loadImages() {
        imageMap.put("amberWatcher", find("amberWatcher"));
        imageMap.put("arcaneShot", find("arcaneShot"));
        imageMap.put("arcaniteReaper", find("arcaniteReaper"));
        imageMap.put("ashBringer", find("ashBringer"));
        imageMap.put("assassinBlade", find("assassinBlade"));
        imageMap.put("blazingBattlemage", find("blazingBattlemage"));
        imageMap.put("bloodClaw", find("bloodClaw"));
        imageMap.put("bloodFury", find("bloodFury"));
        imageMap.put("bloodRazor", find("bloodRazor"));
        imageMap.put("bookOfSpecters", find("bookOfSpecters"));
        imageMap.put("curioCollector", find("curioCollector"));
        imageMap.put("battleAxe", find("battleAxe"));
        imageMap.put("decimation", find("decimation"));
        imageMap.put("divineHymn", find("divineHymn"));
        imageMap.put("dragonClaw", find("dragonClaw"));
        imageMap.put("dreadScale", find("dreadScale"));
        imageMap.put("evasiveChimaera", find("evasiveChimaera"));
        imageMap.put("fierywaraxe", find("fierywaraxe"));
        imageMap.put("fireBlast", find("fireBlast"));
        imageMap.put("fireHawk", find("fireHawk"));
        imageMap.put("flamestrike", find("flamestrike"));
        imageMap.put("friendlySmith", find("friendlySmith"));
        imageMap.put("gearBlade", find("gearBlade"));
        imageMap.put("highPriestAmet", find("highPriestAmet"));
        imageMap.put("hotAirballon", find("hotAirballon"));
        imageMap.put("learnDraconic", find("learnDraconic"));
        imageMap.put("malygosFireball", find("malygosFireball"));
        imageMap.put("malygosFlamestrike", find("malygosFlamestrike"));
        imageMap.put("pharaohsBlessing", find("pharaohsBlessing"));
        imageMap.put("polymorph", find("polymorph"));
        imageMap.put("sathrovarr", find("sathrovarr"));
        imageMap.put("securityRover", find("securityRover"));
        imageMap.put("sprint", find("sprint"));
        imageMap.put("strengthInNumbers", find("strengthInNumbers"));
        imageMap.put("swampKingDred", find("swampKingDred"));
        imageMap.put("swarmOfLocusts", find("swarmOfLocusts"));
        imageMap.put("tombWarden", find("tombWarden"));
        imageMap.put("veranus", find("veranus"));
        imageMap.put("skin", find("skin"));
        imageMap.put("loctus",find("loctus"));

    }
}
