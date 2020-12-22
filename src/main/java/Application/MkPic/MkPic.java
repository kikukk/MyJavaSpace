package Application.MkPic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * @ClassName MkPic
 * @Description TODO
 * @date 2020/8/19 13:29
 */
public class MkPic extends JFrame {
    static int strPicLen = 1080;
    static int strTextSize = 20;
    static String[] strTextColor = {"黑","白","灰","红","蓝","绿","黄","紫"};
    static String[] strBackColor = {"黑","白","灰","红","蓝","绿","黄","紫"};
    static String strTextInfo;
    static String strData = getData();
    static int infoX = 500;
    static int infoY = 500;


    Font font = new Font("Dialog",1,16);
    Color buttonColor = Color.WHITE;

    //左排
    JButton picLen = new JButton("图片宽度");
    JButton textSize = new JButton("文字大小");
    JButton textSizeUp = new JButton("+");
    JButton textSizeDown = new JButton("-");
    JButton textColor = new JButton("文字颜色");
    JButton backColor = new JButton("背景颜色");
    JButton textInfo = new JButton("文字内容");
    JButton data = new JButton("日期");
    JButton up = new JButton("↑");
    JButton down = new JButton("↓");
    JButton left = new JButton("←");
    JButton right = new JButton("→");
    JButton Null1 = new JButton("");
    JButton Null2 = new JButton("");


    //右排
    JTextField picLen_ = new JTextField(String.valueOf(strPicLen));
    JLabel textSize_ = new JLabel(String.valueOf(strTextSize),SwingConstants.CENTER);
    static JComboBox textColor_ = new JComboBox(strTextColor);
    static JComboBox backColor_ = new JComboBox(strBackColor);
    JTextField textInfo_ = new JTextField(strTextInfo);
    JTextField data_ = new JTextField(strData);

    JPanel textSizePanel = new JPanel();
    JPanel panel = new JPanel();
    JPanel directionPanel = new JPanel();



    MkPic(){
        super("好句图片生成器");
        restart();
        //设置字体
        picLen.setFont(font);
        textSize.setFont(font);
        textSizeUp.setFont(font);
        textSizeDown.setFont(font);
        textColor.setFont(font);
        backColor.setFont(font);
        textInfo.setFont(font);
        data.setFont(font);
        textColor_.setFont(font);
        backColor_.setFont(font);
        picLen_.setFont(font);
        textSize_.setFont(font);
        textInfo_.setFont(font);
        data_.setFont(font);
        up.setFont(font);
        down.setFont(font);
        left.setFont(font);
        right.setFont(font);
        Null1.setFont(font);
        Null2.setFont(font);
        //设置按钮背景
        picLen.setBackground(buttonColor);
        textSize.setBackground(buttonColor);
        textSizeUp.setBackground(buttonColor);
        textSizeDown.setBackground(buttonColor);
        textColor.setBackground(buttonColor);
        backColor.setBackground(buttonColor);
        textInfo.setBackground(buttonColor);
        data.setBackground(buttonColor);
        up.setBackground(buttonColor);
        down.setBackground(buttonColor);
        left.setBackground(buttonColor);
        right.setBackground(buttonColor);
        Null1.setBackground(buttonColor);
        Null2.setBackground(buttonColor);
        //居中
        picLen_.setHorizontalAlignment(SwingConstants.CENTER);

        //按钮添加功能
        textSizeUp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strTextSize++;
                System.out.println("TextSize++");
            }
        });
        textSizeDown.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strTextSize--;
                System.out.println("TextSize--");
            }
        });
        up.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoY -= 10;
                System.out.println("Info up");
            }
        });
        down.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoY += 10;
                System.out.println("Info down");
            }
        });
        left.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoX -= 10;
                System.out.println("Info left");
            }
        });

        right.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoX += 10;
                System.out.println("Info right");
            }
        });

        //面板添加组件
        textSizePanel.setLayout(new GridLayout(1,3));
        textSizePanel.add(textSizeDown);
        textSizePanel.add(textSize_);
        textSizePanel.add(textSizeUp);

        directionPanel.setLayout(new GridLayout(2,3));
        directionPanel.add(Null1);
        directionPanel.add(up);
        directionPanel.add(Null2);
        directionPanel.add(left);
        directionPanel.add(down);
        directionPanel.add(right);

        panel.setLayout(new GridLayout(6,2));
        panel.add(picLen);panel.add(picLen_);
        panel.add(textSize);panel.add(textSizePanel);
        panel.add(textColor);panel.add(textColor_);
        panel.add(backColor);panel.add(backColor_);
        panel.add(textInfo);panel.add(textInfo_);
        panel.add(data);panel.add(data_);

        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        this.add(directionPanel,BorderLayout.SOUTH);

        this.setSize(300,320);
        this.setLocation(1360,440);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);//设置窗口为不可缩放
        this.setVisible(true);
    }

    private void restart(){
        Runnable r1 = ()->{
            while(true){
                strPicLen = Integer.parseInt(picLen_.getText());
                textSize_.setText(String.valueOf(strTextSize));
                strTextInfo = textInfo_.getText();
                strData = data_.getText();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(r1);
        t2.start();
    }

    static class Write extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(new File("src/main/java/Application/MkPic/test.txt")));
                    bf.write(strPicLen+"\n");
                    bf.write(strTextSize+"\n");
                    bf.write(textColor_.getSelectedIndex()+"\n");
                    bf.write(backColor_.getSelectedIndex()+"\n");
                    bf.write(strTextInfo+"\n");
                    bf.write(strData+"\n");
                    bf.write(infoX+"\n");
                    bf.write(infoY+"\n");
                    bf.close();
                    sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getData(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date)+(int)(Math.random()*1000);
    }

    private static void read() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(new File("src/main/java/Application/MkPic/test.txt")));
        String[] strings = new String[10];
        for(int i = 0;i < 10;i++){
            strings[i] = bf.readLine();
        }
        strPicLen = Integer.parseInt(strings[0]);
        strTextSize = Integer.parseInt(strings[1]);
        textColor_.setSelectedIndex(Integer.parseInt(strings[2]));
        backColor_.setSelectedIndex(Integer.parseInt(strings[3]));
        strTextInfo = strings[4];
        infoX = Integer.parseInt(strings[6]);
        infoY = Integer.parseInt(strings[7]);
        bf.close();
    }

    private static void startPython(){
        Runtime runtime = Runtime.getRuntime();
        String path ="src/main/java/Application/MkPic/MkPic.exe";
        try {
            runtime.exec(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new MkPic();
        read();
        Write write = new Write();
        write.start();
        startPython();
    }
}
