package Application.Game.FlyingBird.ui;

import javax.swing.*;

//窗口类
public class GameFrame extends JFrame {
    GamePanel panel = new GamePanel(this);
    public GameFrame(){
        //界面名称
        setTitle("FlyBird");
        //设置窗口大小
        setSize(432,644);
        //居中
        setLocationRelativeTo(null);
        ///窗口大小不可更改
        setResizable(false);
        //设置默认关闭选项
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置Logo
        setIconImage(App.getImg("0"));
        add(panel);
        panel.action();
    }

}
