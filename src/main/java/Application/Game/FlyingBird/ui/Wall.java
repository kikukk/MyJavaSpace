package Application.Game.FlyingBird.ui;

public class Wall extends Model{
    public Wall(){
        img = App.getImg("ground");
        x = 0;
        y = 644-img.getHeight();
        w = img.getWidth();
        h = img.getHeight();
    }

    void move(){
        if(x < -100)x += 100;
        x -= 10;
    }
}
