package Test;
import java.awt.*;
import javax.swing.*;
/**
 * @author 14417
 */
public class Circle extends JFrame                                  //窗口
{
    public static void main(String[]argv)
    {
        JFrame frame=new Circle();
        frame.setSize(1000, 1000);                     //设置窗体大小
        frame.setLocation(450, 20);                           //设置窗体位置
        frame.setBackground(Color.white);                           //设置窗体背景（但是无效，待解决）
        frame.setTitle("随机圆");
        frame.setVisible(true);                                     //显示 设置大小
    }

    @Override
    public void paint(Graphics g)                                   //画图对象
    {
        g.drawString("Circle ", 20, 20);              //名称 横位置 纵位置
        int x0=getSize().width/2;
        int y0=getSize().height/2;//圆心
        for(int r=0;r<getSize().height/2;r+=10)
        {
            g.setColor(Color.white);
            g.drawOval(x0-r,y0-r,r*2,r*2);    //画圆
        }
    }
    Color getRandomColor()                                      //随机数
    {
        return new Color(
                (int)(Math.random()*255),
                (int)(Math.random()*255),
                (int)(Math.random()*255)
        );
    }
}