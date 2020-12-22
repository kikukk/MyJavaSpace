package Application.Game.Plane.ui;

import javax.swing.*;

//窗口类
public class GameFrame extends JFrame {
    GamePanel panel = new GamePanel(this);
    public GameFrame(){
        //界面名称
        setTitle("飞机大战");
        //设置窗口大小
        setSize(512,768);
        //居中
        setLocationRelativeTo(null);
        ///窗口大小不可更改
        setResizable(false);
        //设置默认关闭选项
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(panel);
        panel.action();
    }

}
