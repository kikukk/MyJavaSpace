package ex12;

import javax.swing.*;
import java.awt.*;
public class ex12_1 {
    static class Calculator extends JFrame{
        double num1 = 0;
        double num2 = 0;
        char chType = ' ';
        boolean isClear = false;
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JTextField result = new JTextField(10);
        String[][] strButton = {
                {"7","8","9","+"},
                {"4","5","6","-"},
                {"1","2","3","*"},
                {"0",".","=","/"}
        };
        JButton[][] button = new JButton[4][4];
        JButton CE = new JButton("CE/C");
        Calculator(){
            super("Calculator");
            CE.addActionListener(e -> {
                result.setText("");
            });

            panel1.setLayout(new GridLayout(4,4));
            result.setBackground(Color.green);
            result.setEditable(false);
            for(int i = 0;i < 4;i++){
                for(int j = 0;j < 4;j++){
                    button[i][j] = new JButton(strButton[i][j]);
                    panel1.add(button[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    button[i][j].addActionListener(e -> {
                        act(finalI, finalJ);
                    });

                }
            }
            panel2.setLayout(new BorderLayout());
            panel2.add(result,BorderLayout.NORTH);
            panel2.add(panel1,BorderLayout.CENTER);
            panel2.add(CE,BorderLayout.SOUTH);
            setLayout(new FlowLayout());
            add(panel2);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(220,210);
            setVisible(true);
        }
        void act(int a,int b){
            if((a < 3&&b < 3)||(a == 3&&b < 2)){
                if(isClear == true){
                    result.setText("");
                    isClear = false;
                }
                result.setText(result.getText()+strButton[a][b]);
            }
            char[] chars = {'+','-','*','/'};
            if(b == 3){
                num1 = Double.parseDouble(result.getText());
                chType = chars[a];
                isClear = true;
            }
            if(a == 3&& b == 2){
                num2 = Double.parseDouble(result.getText());
                result.setText(String.valueOf(getResult(num1,num2,chType)));
                isClear = true;
            }
        }
        double getResult(double a,double b,char chType){
            switch(chType){
                case '+':return a+b;
                case '-':return a-b;
                case '*':return a*b;
                case '/':return a/b;
            }
            return 0;
        }
        public static void main(String[] args) {
            new Calculator();
        }
    }
}
