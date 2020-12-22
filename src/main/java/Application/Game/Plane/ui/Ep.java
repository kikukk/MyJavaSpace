package Application.Game.Plane.ui;

import java.util.Random;

public class Ep extends FlyObject{
    Random random = new Random();
    int index;
    String path;
    public Ep(){
        index = random.nextInt(15)+1;
        path = "/Plane/img/ep"+ (index>=10?"":"0")+index+".png";
        img = App.getImg(path);
        w = img.getWidth();
        h = img.getHeight();
        x = random.nextInt(512-w);
        y = -h;
    }

    void move(){
        y += 17 - index;
    }

    boolean shootBy(Fire f){
        boolean hit = x <= f.x+f.w
                && x >= f.x-w
                &&x <= f.x+f.h
                &&y >=f.y-h;
        return hit;
    }



}
