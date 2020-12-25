package Test.Frame2;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Frame2 extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JButton jButton = null;

    private Frame1 f1=null;

    public void actionPerformed(ActionEvent arg0) {
        f1.setVisible(true);
        this.setVisible(false);
    }

    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(0, 0, 105, 39));
            jButton.setText("回到主窗体");
            jButton.addActionListener(this);
        }
        return jButton;
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame2 thisClass = new Frame2();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }
    public Frame2() {
        super();
        initialize();
    }

    public Frame2(Frame1 f) {
        this();
        f1=f;
    }
    private void initialize() {
        this.setContentPane(getJContentPane());
        this.addWindowListener(this);
        //关闭操作
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //设置窗体大小
        this.setSize(1000, 1000);
        //居中
        this.setLocationRelativeTo(null);
        //设置窗体标题
        this.setTitle("随机圆");
        //显示 设置大小
        this.setVisible(true);
    }
    //画图对象
    public void paint(Graphics g)
    {

        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        //名称 横位置 纵位置
        g.drawString("Circle ", 20, 20);
        System.out.println("请输入圆的个数：");
        int cirnum = in.nextInt();
        System.out.println("请输入圆的半径范围（ ~ ）：");
        int r1 = in.nextInt();
        System.out.println("至");
        int r2 = in.nextInt();
        System.out.println("正在为你画圆（-3-）");

        for (int i = 0; i < cirnum; i++)
        {
            //设置圆的随机生成时间
            int seconds = rand.nextInt(2000);
            try {
                Thread.sleep((long) (seconds));
            }catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            //设置圆的随机生成位置

            //圆心横向位置
            int x0 =rand.nextInt(getSize().width + 1 - r2);
            //圆心纵向位置
            int y0 =rand.nextInt(getSize().height + 1 - r2);
            //r将被赋值为一个 r1 和 r2 范围内的随机数
            int r = rand.nextInt(r2 - r1 + 1) + r1;
            //随机颜色
            g.setColor(getRamdomColor());
            //画圆
            g.drawOval(x0 - r, y0 - r, r * 2, r * 2);
        }
    }

    //随机颜色
    Color getRamdomColor()
    {
        return new Color(
                (int)(Math.random()*255),
                (int)(Math.random()*255),
                (int)(Math.random()*255)
        );
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJButton(), null);
        }
        return jContentPane;
    }

    public void windowActivated(WindowEvent arg0) {
    }

    public void windowClosed(WindowEvent arg0) {
    }

    public void windowClosing(WindowEvent arg0) {
        f1.setVisible(true);
    }

    public void windowDeactivated(WindowEvent arg0) {
    }

    public void windowDeiconified(WindowEvent arg0) {
    }

    public void windowIconified(WindowEvent arg0) {
    }

    public void windowOpened(WindowEvent arg0) {
    }
}