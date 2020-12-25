package Test.Frame2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame1 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton jButton = new JButton("开始");
    private JLabel labelCount = new JLabel("画圆个数：");
    private JTextField textCount = new JTextField(15);


    public void actionPerformed(ActionEvent arg0) {
        Frame2 f2=new Frame2(this);
        f2.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame1 thisClass = new Frame1();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    public Frame1() {
        super();
        initialize();
    }
    private void initialize() {
        //居中
        this.setLocationRelativeTo(null);
        //窗体大小
        this.setSize(300, 200);
        //画布
        this.setContentPane(getJContentPane());
        //窗体标题
        this.setTitle("随机圆");
    }
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jButton.setBounds(new Rectangle(92, 62, 105, 35));
            jButton.addActionListener(this);
            labelCount.setBounds(new Rectangle(20,20,100,20));
            textCount.setBounds(new Rectangle(150,20,100,20));
            jContentPane.add(labelCount,null);
            jContentPane.add(textCount,null);
            jContentPane.add(jButton,null);
        }
        return jContentPane;
    }
}