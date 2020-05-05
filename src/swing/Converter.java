package swing;

import logic.Constans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Converter {

    public ArrayList<BufferedImage> convert(ArrayList<String> name) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (int i=0;i<name.size();i++) {
            images.add(find(name.get(i).toLowerCase()));
        }
        return images;
    }

    private BufferedImage find(String name) {
            String url = String.format("resources\\image\\cards\\%s.png", name);
            BufferedImage image = null;
            try {
                File file = new File(url);
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;

    }
}
