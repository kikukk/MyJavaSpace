package ex11;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
public class ex11_2 {
    static class cauculator extends JFrame {
        cauculator(){
            super("加减乘除运算游戏！！");
            JTextField num1 = new JTextField(10);
            JTextField num2 = new JTextField(10);
            JTextField result = new JTextField(10);
            result.setEditable(false);
            String[] strType = {"+","-","*","/"};
            JComboBox comboBoxType = new JComboBox(strType);
            JButton out = new JButton("=");
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(1,5));
            panel1.add(num1);
            panel1.add(comboBoxType);
            panel1.add(num2);
            panel1.add(out);
            panel1.add(result);
            out.addActionListener(e -> {
                String strNum1 = num1.getText();
                String strNum2 = num2.getText();
                double douNum1 = Double.parseDouble(strNum1);
                double douNum2 = Double.parseDouble(strNum2);
                if(Objects.equals(comboBoxType.getSelectedItem(), "+")){
                    result.setText(String.valueOf(douNum1+douNum2));
                }
                else if(Objects.equals(comboBoxType.getSelectedItem(), "-")){
                    result.setText(String.valueOf(douNum1-douNum2));
                }
                else if(Objects.equals(comboBoxType.getSelectedItem(), "*")){
                    result.setText(String.valueOf(douNum1*douNum2));
                }
                else if(Objects.equals(comboBoxType.getSelectedItem(), "/")){
                    result.setText(String.valueOf(douNum1/douNum2));
                }
            });
            setLayout(new FlowLayout());
            add(panel1);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(620,90);
            setVisible(true);
        }
    }
    public static void main(String[] args) {
        new cauculator();
    }
}
