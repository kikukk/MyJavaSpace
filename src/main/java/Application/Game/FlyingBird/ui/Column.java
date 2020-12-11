package Application.Game.FlyingBird.ui;

import java.util.Random;

public class Column extends Model {
    Random random = new Random();
    int Up;
    int Dn;

    public Column() {
        img = App.getImg("column");
        x = 544;
        y = -random.nextInt(100);
        w = img.getWidth() / 2;
        h = img.getHeight() / 2;
        Up = y + 264;
        Dn = Up + 72;
    }

    void move() {
        x -= 5;
    }

    Boolean isCrash(Bird bird) {
        int right = bird.getX() + bird.getW();
        int left = bird.getX();
        int up = bird.getY();
        int dn = bird.getY() + bird.getH();
        if (right > x && right < x + w && up < Up) return true;
        if (left > x && left < x + w && up < Up) return true;
        if (right > x && right < x + w && dn > Dn) return true;
        if (left > x && left < x + w && dn > Dn) return true;
        return false;
    }

    int getX() {
        return x;
    }
}
