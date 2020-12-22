package Application.Game.Plane.ui;

public class Fire extends FlyObject{
    int dir;    //0：左上角 1：中间  2：右上角
    public Fire(int hx,int hy,int dir){
        img = App.getImg("/Plane/img/fire.png");
        w = img.getWidth()/5;
        h = img.getHeight()/5;
        x = hx;
        y = hy;
        this.dir = dir;
    }

    void move(){
        if(dir == 0){
            x -= 1;
            y -= 10;
        }else if(dir == 1){
            y -= 10;
        }else{
            x += 1;
            y -= 10;
        }
    }




}
