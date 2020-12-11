package Application.Game.FlyingBird.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {
    //读取指定位置上的图片的工具类
    static BufferedImage getImg(String path){
        try {
            return ImageIO.read(App.class.getResource("/FlyingBird/img/"+path+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
