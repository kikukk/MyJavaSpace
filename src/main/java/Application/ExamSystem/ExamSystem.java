package Application.ExamSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ExamSystem extends JFrame{
    private static ArrayList<Exam> exams = new ArrayList<>();
    private static Exam exam;
    private static int curId = 0;
    private static ArrayList<Integer> historyExam = new ArrayList<>();
    private static int curHistory = -1;
    private JLabel labelAnswer;

    public static void main(String[] args) throws IOException {
        new ExamSystem();
    }

    private ExamSystem() throws IOException {
        super("题库系统");
        Font font;
        JLabel label1;
        JLabel labelId;
        JLabel label2;
        JLabel labelNo;
        JLabel label3;
        JTextArea textContent;
        JButton[] buttonsAnswers;
        JButton buttonNextSeqExam;
        JButton buttonNextRandExam;
        JButton buttonHistoryExam;
        JButton buttonPreSeqExam;
        JPanel panelTop_1;
        JPanel panelTop_2;
        JPanel panelTop;
        JPanel panelMiddle;
        JPanel panelButtom;
        JPanel panelLeft;
        readExam(this.getClass().getResource("/App/ExamSystem/SingleChoose.txt"));
        getExam(true,this);
        font = new Font("宋体",Font.BOLD,18);
        label1 = new JLabel("Id",JLabel.CENTER);
        labelId = new JLabel(exam.getId(),JLabel.CENTER);
        label2 = new JLabel("No",JLabel.CENTER);
        labelNo = new JLabel(exam.getNo(),JLabel.CENTER);
        label3 = new JLabel("题目",JLabel.CENTER);
        //textContent换行配置
        StringBuilder tmp = new StringBuilder();
        String content = exam.getContent();
        for (int i = 0, j = 0; i < content.length(); i++) {
            tmp.append(content.charAt(i));
            j++;
            if (j >= 20) {
                tmp.append("\n");
                j = 0;
            }
        }
        textContent = new JTextArea(tmp.toString());
        textContent.setEditable(false);
        textContent.setOpaque(false);
        buttonsAnswers = new JButton[4];
        buttonNextSeqExam = new JButton("顺序下一题");
        buttonNextRandExam = new JButton("随机下一题");
        buttonHistoryExam = new JButton("抽取的上一题");
        buttonPreSeqExam = new JButton("顺序上一题");
        labelAnswer = new JLabel(exam.getAnswer());
        panelTop_1 = new JPanel();
        panelTop_2 = new JPanel();
        panelTop = new JPanel();
        panelMiddle = new JPanel();
        panelButtom = new JPanel();
        panelLeft = new JPanel();

        //选项按钮配置
        for(int i = 0;i < 4;i++){
            buttonsAnswers[i] = new JButton(exam.getAnswers()[i]);
            buttonsAnswers[i].addActionListener(e -> labelAnswer.setVisible(true));
            buttonsAnswers[i].setContentAreaFilled(false);
            buttonsAnswers[i].setFont(font);
        }
        buttonNextSeqExam.setFont(font);
        buttonNextRandExam.setFont(font);
        buttonHistoryExam.setFont(font);
        buttonPreSeqExam.setFont(font);
        buttonNextSeqExam.setContentAreaFilled(false);
        buttonNextRandExam.setContentAreaFilled(false);
        buttonHistoryExam.setContentAreaFilled(false);
        buttonPreSeqExam.setContentAreaFilled(false);

        buttonNextSeqExam.addActionListener(e -> {
            if(curId<exams.size()-1){
                historyExam.add(exams.indexOf(exam));
                curHistory = historyExam.size();
                curId++;
            }
            try {
                getExam(false,this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonNextRandExam.addActionListener(e -> {
            historyExam.add(exams.indexOf(exam));
            curHistory = historyExam.size();
            curId = new Random().nextInt(exams.size());
            try {
                getExam(false,this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonHistoryExam.addActionListener(e -> {
            if(curHistory>=0) {curId = historyExam.get(curHistory-- -1);}
            try {
                getExam(false,this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonPreSeqExam.addActionListener(e -> {
            if(curId>0){
                historyExam.add(exams.indexOf(exam));
                curHistory = historyExam.size();
                curId--;
            }
            try {
                getExam(false,this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        //组件字体配置
        label1.setFont(font);
        labelId.setFont(font);
        label2.setFont(font);
        labelNo.setFont(font);
        label3.setFont(font);
        textContent.setFont(font);
        labelAnswer.setFont(font);
        //其他配置
        labelAnswer.setVisible(false);
        //panelTop配置
        panelTop_1.setLayout(new GridLayout(2,2));
        panelTop_1.add(label1);
        panelTop_1.add(labelId);
        panelTop_1.add(label2);
        panelTop_1.add(labelNo);
        panelTop_1.setOpaque(false);
        panelTop_2.setLayout(new GridLayout(1,2));
        panelTop_2.add(label3);
        panelTop_2.add(textContent);
        panelTop_2.setOpaque(false);
        panelTop.setLayout(new BorderLayout());
        panelTop.add(new JLabel(" "),BorderLayout.NORTH);
        panelTop.add(panelTop_1,BorderLayout.CENTER);
        panelTop.add(panelTop_2,BorderLayout.SOUTH);
        panelTop.setOpaque(false);

        //panelMiddle配置
        panelMiddle.setLayout(new GridLayout(5,1));
        for(JButton i:buttonsAnswers){
            panelMiddle.add(i);
        }
        panelMiddle.add(labelAnswer);
        panelMiddle.setOpaque(false);

        //panelButtom配置
        panelButtom.setLayout(new GridLayout(2,2));
        panelButtom.add(buttonNextSeqExam);
        panelButtom.add(buttonNextRandExam);
        panelButtom.add(buttonHistoryExam);
        panelButtom.add(buttonPreSeqExam);
        panelButtom.setOpaque(false);

        panelLeft.setLayout(new BorderLayout());
        panelLeft.add(panelTop,BorderLayout.NORTH);
        panelLeft.add(panelMiddle,BorderLayout.CENTER);
        panelLeft.add(panelButtom,BorderLayout.SOUTH);
        panelLeft.setBackground(Color.white);
        panelLeft.setOpaque(false);
        this.setLayout(new FlowLayout());
        add(panelLeft,BorderLayout.CENTER);

        //设置窗口大小
        setSize(1100,500);
        //居中
        setLocationRelativeTo(null);
        ///窗口大小不可更改
        setResizable(true);
        //设置默认关闭选项
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置透明
        {
            JPanel contentPane;
            contentPane = new JPanel(); //指定容器
            setContentPane(contentPane);//设置 contentPane 属性
            contentPane.setOpaque(false);//设置面板背景为透明(这一步很重要）
            ImageIcon img2 = null;
            try {
                img2 = new ImageIcon(ImageIO.read(this.getClass().getResource("/App/ExamSystem/back1.jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ImageIcon img = new ImageIcon("src/App/ExamSystem/back1.jpg");//要设置的背景图片
            JLabel imgLabel = new JLabel(img2);//将背景图放在标签里。
            this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//将背景标签添加到jfram的LayeredPane面板里。
            imgLabel.setBounds(0, 0, img2 != null ? img2.getIconWidth() : 0, img2!=null?img2.getIconHeight():0);
        }
        add(panelLeft);
        setVisible(true);
    }

    private static void getExam(Boolean isFirstStart,ExamSystem examSystem) throws IOException {
        exam = exams.get(curId);
        if(!isFirstStart){
            examSystem.setVisible(false);
            new ExamSystem();
        }
    }

    private static void readExam(URL address) throws IOException {

//        System.out.println("address:"+address);
//        while(addressStr.charAt(0)<'A'||addressStr.charAt(0)>'Z')addressStr = addressStr.substring(1);
//        for(int i = 0;i < addressStr.length();i++){
//            if(addressStr.charAt(i) == '!'){
//                addressStr = addressStr.substring(0,i)+addressStr.substring(i+1);
//            }
//        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(String.valueOf(address))));
        String tmpExam;
        String[] tmpExams = new String[15];
        int len_tmpExams = 0;
        boolean isContinue = true;
        boolean isEmpty = true;
        while(isContinue){
            if(!(tmpExam = bufferedReader.readLine()).equals("")){
                tmpExams[len_tmpExams++] = tmpExam;
                isEmpty = false;
            }else isContinue = false;
            if(!isContinue&&!isEmpty){
                exams.add(new Exam(tmpExams));
                isContinue = true;
                isEmpty = true;
                len_tmpExams = 0;
            }
        }
        bufferedReader.close();
    }

}
