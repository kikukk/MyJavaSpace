package Thread.Demo.window;

import javax.swing.*;
import java.awt.*;

class Start{
    private static class StartUp extends JFrame{
        static int number = 1000;
        static boolean Access = true;
        JTextField label = new JTextField(20);
        JButton button = new JButton("减50");
        JPanel panel = new JPanel();
        StartUp(){
            super("多线程小程序");
            label.setText(String.valueOf(number));
            button.addActionListener(e -> {
                Access = false;
            });
            panel.setLayout(new FlowLayout());
            panel.add(label);
            panel.add(button);
            add(panel);
            setSize(500,500);
            {
                Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
                Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
                int screenWidth = screenSize.width / 2; // 获取屏幕的宽
                int screenHeight = screenSize.height / 2; // 获取屏幕的高
                int height = this.getHeight();
                int width = this.getWidth();
                setLocation(screenWidth - width / 2 - 300 / 4, screenHeight - height / 2 - 130 / 4);
            }
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
        }
        public void restart(){
            label.setText(String.valueOf(number));
        }
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            StartUp.number = number;
        }
        public boolean getAccess() {
            return Access;
        }
        public void setAccess(boolean isAccess) {
            StartUp.Access = isAccess;
        }
    }

    public static void main(String[] args) {
        All all = new All();
        all.start();
    }

    private static class All extends Thread{
        StartUp startUp = new StartUp();
        int number = startUp.getNumber();
        boolean Access = true;
        public All(){}
        @Override
        public void run() {
            while(number>0){
                Access = startUp.getAccess();
                if(Access == false){
                    number-=50;
                    startUp.setAccess(true);
                }
                number -= 1;
                startUp.setNumber(number);
                startUp.restart();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
