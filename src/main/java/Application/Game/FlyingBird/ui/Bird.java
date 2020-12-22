package Application.Game.FlyingBird.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bird extends Model{
    ArrayList<BufferedImage> imgs = new ArrayList<>();
    int times = 0;
    public Bird(){
        for(int i = 0;i < 8;i++){
            imgs.add(App.getImg(String.valueOf(i)));
        }
        img = App.getImg(String.valueOf(0));
        x = 50;
        y = 250;
        w = img.getWidth()/2;
        h = img.getHeight()/2;
    }

    void jump(){
        y -= 40;
    }

    void fall(){
        y += 2;
    }

    void redraw(){
        times = (times+1)%8;
        img = imgs.get(times);
    }

    int getX(){return x;}
    int getY(){return y;}
    int getW(){return w;}
    int getH(){return h;}

}
