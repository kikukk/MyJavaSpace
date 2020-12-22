package Application.Game.FlyingBird.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
//    JButton startGame = new JButton("开始游戏");
    //背景图
    BufferedImage bg;
    BufferedImage start;
    BufferedImage fail;
    Bird bird = new Bird();

    Wall wall = new Wall();

    ArrayList<Column> columns = new ArrayList<>();

    int score;

    boolean gameOver = true;

    boolean firstStart = true;

    void action(){
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5*(10-score/2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!gameOver){
                    bird.fall();
                    wall.move();
                    bird.redraw();
                    getColumn();
                    moveColumn();
                    columnCheck();
                    crashCheck();
                    repaint();
                }
            }
        }).start();
    }

    int timesColumn = 60;
    private void getColumn(){
        timesColumn++;
        if(timesColumn>=60){
            Column column = new Column();
            columns.add(column);
            timesColumn = 0;
        }
    }

    private void moveColumn(){
        for(int i = 0;i < columns.size();i++){
            columns.get(i).move();
        }
    }

    private void columnCheck(){
        for(int i = 0;i < columns.size();i++){
            Column column = columns.get(i);
            if(bird.getX()-column.getX() >= 50){
                score++;
                columns.remove(column);
            }
        }
    }

    private void crashCheck(){
        for(int i = 0;i < columns.size();i++){
            Column column = columns.get(i);
            if(column.isCrash(bird))gameOver = true;
        }
    }

    public GamePanel(GameFrame frame){
        setBackground(Color.BLACK);
        bg = App.getImg("bg");
        start = App.getImg("start");
        fail = App.getImg("gameover");
        this.setLayout(null);
//        startGame.setBounds(190,400,100,100);

        //鼠标监听器
        MouseAdapter adapter1 = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(firstStart){
                    gameOver = false;
                    firstStart = false;
                }
                if(gameOver){
                    bird = new Bird();
                    columns.clear();
                    score = 0;
                    gameOver = false;
                    repaint();
                }
                bird.jump();
            }
        };
        addMouseListener(adapter1);
        addMouseMotionListener(adapter1);

        //键盘监听器
        KeyAdapter kd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == 32){
                    bird.jump();
                }
                if(keyCode == KeyEvent.VK_UP)score += 5;
            }
        };
        frame.addKeyListener(kd);

//        startGame.addActionListener(e -> {
//            gameOver = false;
//            firstStart = false;
//        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //背景
        g.drawImage(bg,0,0,null);
        //柱子
        for(int i = 0;i < columns.size();i++){
            Column column = columns.get(i);
            g.drawImage(column.img,column.x,column.y,column.w,column.h,null);
        }
        //分数
        g.setColor(Color.white);
        g.setFont(new Font("楷体",Font.BOLD,20));
        if(!firstStart)g.drawString("分数："+ score,10,30);
        //地板
        g.drawImage(wall.img,wall.x,wall.y,wall.w,wall.h,null);
        //鸟
        if(!firstStart)g.drawImage(bird.img,bird.x,bird.y,bird.w,bird.h,null);
        if(firstStart)g.drawImage(start,0,0,null);
        if(!firstStart&&gameOver)g.drawImage(fail,0,0,null);
//        if(gameOver&&firstStart)add(startGame);
    }
}
