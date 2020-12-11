package Application.Game.Plane.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    //背景图
    BufferedImage bg;
    Hero hero = new Hero();
    ArrayList<Ep> eps = new ArrayList<Ep>();

    ArrayList<Fire> fires = new ArrayList<Fire>();

    int score;

    void action(){
        new Thread(){
            public void run(){
                while(true){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    epEnter();
                    epMove();
                    shoot();
                    fireMove();
                    shootEp();

                    repaint();

                }
            }
        }.start();
    }

    int timesEp = 20;
    void epEnter(){
        timesEp++;
        if(timesEp >= 15){
            Ep e = new Ep();
            eps.add(e);
            timesEp = 0;
        }
    }

    void epMove(){
        for (int i = 0; i < eps.size(); i++) {
            Ep ep = eps.get(i);
            ep.move();
        }
    }

    int timesFire = 0;
    void shoot(){
        timesFire++;
        if(timesFire >= 30){
            Fire fire1 = new Fire(hero.x+(hero.w/5),hero.y,0);
            fires.add(fire1);
            Fire fire2 = new Fire(hero.x+(hero.w/2),hero.y-20,1);
            fires.add(fire2);
            Fire fire3 = new Fire(hero.x+(hero.w/5*4),hero.y,2);
            fires.add(fire3);
            timesFire = 0;
        }
    }

    void fireMove(){
        for(int i = 0;i < fires.size();i++){
            Fire fire = fires.get(i);
            fire.move();
        }
    }

    void shootEp(){
        for(int i = 0;i < fires.size();i++){
            Fire f = fires.get(i);
            bang(f);
        }
    }

    void bang(Fire f){
        for(int i = 0;i < eps.size();i++){
            Ep e = eps.get(i);
            if(e.shootBy(f)){
                eps.remove(e);
                fires.remove(f);
                score+=10;
            }
        }
    }


    public GamePanel(GameFrame frame){
        setBackground(Color.BLACK);
        bg = App.getImg("/Plane/img/bg2.jpg");

        //鼠标监听器
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();
                hero.moveToMouse(mx,my);
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);

        //键盘监听器
        KeyAdapter kd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_UP){
                    hero.moveUp();
                }else if(keyCode == KeyEvent.VK_DOWN){
                    hero.moveDown();
                }else if(keyCode == KeyEvent.VK_LEFT){
                    hero.moveLeft();
                }else if(keyCode == KeyEvent.VK_RIGHT){
                    hero.moveRight();
                }
                repaint();
            }
        };
        frame.addKeyListener(kd);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg,0,0,null);

        for(int i = 0;i < eps.size();i++){
            Ep ep = eps.get(i);
            g.drawImage(ep.img,ep.x,ep.y,ep.w,ep.h,null);
        }

        for(int i = 0;i < fires.size();i++){
            Fire fire = fires.get(i);
            g.drawImage(fire.img,fire.x,fire.y,fire.w,fire.h,null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.drawString("分数："+ score,10,30);

        g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);

    }
}
