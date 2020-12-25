package Test.Frame;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;

public class Frame1 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton jButton = null;
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Frame2 f2=new Frame2(3,5,9);
        f2.setVisible(true);
        this.setVisible(false);
    }
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(92, 62, 105, 35));
            jButton.setText("开始");
            jButton.addActionListener(this);
        }
        return jButton;
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
            jContentPane.add(getJButton(), null);
        }
        return jContentPane;
    }
}