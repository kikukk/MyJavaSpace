package ex10;

import javax.swing.*;
import java.awt.*;
public class ex10_2 {
    static class Calculator extends JFrame{
        Calculator(){
            super("Calculator");
            JTextField result = new JTextField(10);
            result.setEditable(false);
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(4,4));
            String[][] strButton = {
                    {"7","8","9","+"},
                    {"4","5","6","-"},
                    {"1","2","3","*"},
                    {"0",".","=","/"}
            };
            JButton[][] button = new JButton[4][4];
            for(int i = 0;i < 4;i++){
                for(int j = 0;j < 4;j++){
                    button[i][j] = new JButton(strButton[i][j]);
                    panel1.add(button[i][j]);
                }
            }
            JButton CE = new JButton("CE/C");

            setLayout(new BorderLayout());
            add(result,BorderLayout.NORTH);
            add(panel1,BorderLayout.CENTER);
            add(CE,BorderLayout.SOUTH);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(300,300);
            setVisible(true);
        }
        public static void main(String[] args) {
            new Calculator();
        }
    }

}
