package ex11;

import javax.swing.*;
import java.awt.*;
public class ex11_1 {
    static class cauculator extends JFrame {
        cauculator(){
            super("plus game!!");
            JTextField num1 = new JTextField(10);
            JTextField num2 = new JTextField(10);
            JTextField result = new JTextField(10);
            result.setEditable(false);
            JLabel labelType = new JLabel("加法运算：");
            JLabel labelAdd = new JLabel("+");
            JButton out = new JButton("=");
            JPanel panel1 = new JPanel();
            panel1.setLayout(new FlowLayout());
            panel1.add(labelType);
            panel1.add(num1);
            panel1.add(labelAdd);
            panel1.add(num2);
            panel1.add(out);
            panel1.add(result);
            out.addActionListener(e -> {
                String strNum1 = num1.getText();
                String strNum2 = num2.getText();
                double douNum1 = Double.parseDouble(strNum1);
                double douNum2 = Double.parseDouble(strNum2);
                result.setText(String.valueOf(douNum1+douNum2));
            });
            setLayout(new FlowLayout());
            add(panel1);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(520,90);
            setVisible(true);
        }
    }
    public static void main(String[] args) {
        new cauculator();
    }
}
