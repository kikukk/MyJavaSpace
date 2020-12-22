package Application.ExamSystem;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class test extends JFrame {
    JTextArea textArea = new JTextArea();
    JPanel panel = new JPanel();
    public test() throws IOException {
        String address = "src\\App\\ExamSystem\\SingleChoose.properties";
        //G:\WorkSpace\MyjavaSpace\idea\test\src\App\ExamSystem\SingleChoose.txt
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(address)));
        String s1 = this.getClass().getResource("SingleChoose.txt").getPath();
        System.out.println(s1);

//        String tmpExam;
//        String[] tmpExams = new String[15];
//        int len_tmpExams = 0;
//        Boolean isContinue = true;
//        Boolean isEmpty = true;
//        while(isContinue||!isEmpty){
//            if(!(tmpExam = bufferedReader.readLine()).equals("")){
//                tmpExams[len_tmpExams++] = tmpExam;
//                isEmpty = false;
//            }else isContinue = false;
//            if(!isContinue&&!isEmpty){
//
//                isContinue = true;
//                isEmpty = true;
//                len_tmpExams = 0;
//            }
//        }
//        bufferedReader.close();



    }


    public static void main(String[] args) throws IOException {

//        new test();
        String address = "asdfagasdf";
        address = address.substring(0,4)+address.substring(4+1);
        System.out.println(address);
    }

}