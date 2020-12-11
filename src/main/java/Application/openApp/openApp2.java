package Application.openApp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class openApp2{
    static class StartUp extends JFrame{
        private boolean Right = true;
        private boolean start = false;
        private int numOfInfo = 0;
        private int maxNumOfInfo = 5;
        private Info[] infos = new Info[maxNumOfInfo];
        private int curInfo = 0;

        JLabel labelTimes = new JLabel("次数",SwingConstants.CENTER);

        JLabel label1 = new JLabel("null",SwingConstants.CENTER);
        JLabel labelTime = new JLabel("时间",SwingConstants.CENTER);
        JLabel label2 = new JLabel("null",SwingConstants.CENTER);

        JButton buttonUp = new JButton("上一条");
        JButton buttonDn = new JButton("下一条");

        JButton buttonStart = new JButton("启动");
        JPanel panel = new JPanel();

        public void restart(){
            panel.setVisible(false);
            label1.setText(String.valueOf(infos[curInfo].getTimes()));
            label2.setText(infos[curInfo].getTime());
            panel.setVisible(true);
        }

        private void addInfo(){
            if(numOfInfo >= maxNumOfInfo -1) {resize();}
            infos[numOfInfo] = new Info(numOfInfo);
            numOfInfo++;

        }

        private void alterCurInfo(int num){
            if(num == 1){
                curInfo = (curInfo+numOfInfo-1)%(numOfInfo);
            }else{
                curInfo = (curInfo+1)%(numOfInfo);
            }
        }

        private boolean isStart() {
            return start;
        }

        private void setStart(boolean start) {
            this.start = start;
        }

        StartUp(){
            super("HQ启动器");
            /*按键透明*/{
                panel.setOpaque(false);
                buttonUp.setContentAreaFilled(false);
                buttonDn.setContentAreaFilled(false);
                buttonStart.setContentAreaFilled(false);
            }

            /*按钮字体设置*/{
                labelTimes.setFont(new Font("Dialog",1,16));
                labelTime.setFont(new Font("Dialog",1,16));
                label1.setFont(new Font("Dialog",1,16));
                label2.setFont(new Font("Dialog",1,16));
                buttonUp.setFont(new Font("Dialog",1,16));
                buttonDn.setFont(new Font("Dialog",1,16));
                buttonStart.setFont(new Font("Dialog",1,16));
            }

            /*panel的配置*/{
                panel.setLayout(new GridLayout(4, 2));
                panel.add(labelTimes);
                panel.add(label1);
                panel.add(labelTime);
                panel.add(label2);
                panel.add(buttonUp);
                panel.add(buttonDn);
                panel.add(buttonStart);
                panel.setBounds(100,20,300,200);
            }

            /*按键的配置*/{
                buttonUp.addActionListener(e -> {
                    alterCurInfo(1);
                    panel.setVisible(false);
                    restart();
                    panel.setVisible(true);
                });
                buttonDn.addActionListener(e -> {
                    alterCurInfo(0);
                    panel.setVisible(false);
                    restart();
                    panel.setVisible(true);
                });
                buttonStart.addActionListener(e -> {
                    setRight(true);
                    All.startApp();
                    setStart(true);
                });
            }



            JPanel  contentPane;
            contentPane = new JPanel(); //指定容器
            setContentPane(contentPane);//设置 contentPane 属性
            contentPane.setOpaque(false);//设置面板背景为透明(这一步很重要）
            ImageIcon img = new ImageIcon("E:\\0Pictures\\kiku\\wall\\pic.jpg");//要设置的背景图片
            JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
            this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//将背景标签添加到jfram的LayeredPane面板里。
            imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

            setLayout(null);
            add(panel);
            setSize(500,284);
            setLocation(1018,544);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);//设置窗口为不可缩放
            setVisible(true);


        }


        public boolean isRight() {
            return Right;
        }

        public void setRight(boolean right) {
            Right = right;
        }

        private void resize(){
            Info[] tmp = new Info[maxNumOfInfo*2];
            for(int i = 0;i < maxNumOfInfo;i++){
                tmp[i] = infos[i];
            }
            infos = tmp;
            maxNumOfInfo *= 2;
        }

    }

    public static void main(String[] args) {
        All all = new All();
        all.start();
    }

    static class All extends Thread{
        static StartUp startUp = new StartUp();
        public All(){}
        @Override
        public void run() {
            while(true){
                if(startUp.isRight()){
                    try {

                        startApp();
                        Thread.sleep(20000);

                        //Thread.sleep(1800000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void startApp(){
                open();
                startUp.addInfo();
                startUp.restart();
        }

        private static void open(){
            Runtime runtime = Runtime.getRuntime();
            Process p = null;
            //String path ="D:\\software\\useful\\HQPlayer 4 Desktop\\HQPlayer4Desktop.exe";
            String path ="D:\\tool\\useful\\IDM.6.35.1\\IDMan.exe";
            try {
                p = runtime.exec(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Info{
    private int times = 0;
    private String time;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
    Info(int num){
        times = num+1;
        String tmp = formatter.format(date);
        time = tmp.substring(14,22);
    }

    public int getTimes() {
        return times;
    }

    public String getTime() {
        return time;
    }
}
