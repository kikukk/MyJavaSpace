package ex10;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

public class TestPassword {
    private static int num = -1;
    static class Test extends JFrame {
        private int getNum() {
            return num;
        }
        JLabel title = new JLabel("输入密码：");
        JTextField inInt = new JTextField(10);
        JButton check = new JButton("确认");
        JLabel end = new JLabel("");
        Test() {
            setLayout(new FlowLayout());
            getContentPane().add(title);
            getContentPane().add(inInt);
            getContentPane().add(check);
            getContentPane().add(end);
            check.addActionListener(e -> {
                String num2;
                try {
                    num2 = inInt.getText();
                    num = Integer.parseInt(num2);
                } catch (InputMismatchException | NumberFormatException s) {
                    end.setText("格式错误");
                    //System.err.println("*输入的选项格式错误!请重新输入：");
                    inInt.setText("");
                }
                end.setText(String.valueOf(getNum()));
            });
            setSize(400, 100);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }




//    @Override
//    public void init() {
//        setLayout(new FlowLayout());
//        add(title);
//        add(inInt);
//        add(check);
//        add(end);
//
//        check.addActionListener(e -> {
//            //String tmpInt = ((JButton)e.getSource()).getText();
//            String num2;
//            try{
//                //num2.set(((JButton) e.getSource()).getText());
//                num2 = inInt.getText();
//                num = Integer.parseInt(num2);
//            } catch (InputMismatchException |NumberFormatException s) {
//                end.setText("*输入的选项格式错误!请重新输入：");
//                //System.err.println("*输入的选项格式错误!请重新输入：");
//                inInt.setText("");
//            }
//            end.setText(getName());
//
//        });

        /*
        setSize(200,120);

        ActionListener a1 = e -> {
            //String tmpInt = ((JButton)e.getSource()).getText();
            String num2;
            try{
                //num2.set(((JButton) e.getSource()).getText());
                 num2 = inInt.getText();
                num = Integer.parseInt(num2);
            } catch (InputMismatchException |NumberFormatException s) {
                end.setText("*输入的选项格式错误!请重新输入：");
                //System.err.println("*输入的选项格式错误!请重新输入：");
                inInt.setText("");
            }
        };
        inInt.addActionListener(a1);
        end.setText(getName());

    }
*/
    public static void main(String[] args) {
        new Test();
    }
}
/*
* 顶部：标签显示学生信息管理程序
* 设计：输入相应格式数据
*
*
* */
