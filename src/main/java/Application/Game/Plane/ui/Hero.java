package Application.Game.Plane.ui;

public class Hero extends FlyObject{


    public Hero(){
        img = App.getImg("/Plane/img/hero.png");
        x = 200;
        y = 500;
        w = img.getWidth();
        h = img.getHeight();
    }

    void moveToMouse(int mx,int my){
        x = mx-w/2;
        y = my-h/2;
    }

    void moveUp(){y-=10;}
    void moveDown(){y+=10;}
    void moveLeft(){x-=10;}
    void moveRight(){x+=10;}


}
