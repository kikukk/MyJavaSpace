package ex9;
import java.io.*;
import java.util.Scanner;

public class TestChengji {
    private static Student[] stu = new Student[3];
    public static void main(String[] args) {
        String[] temp = new String[4];
        Scanner input = new Scanner(System.in);
        char tempCh;
        do{
            System.out.println("选择：\n1:写入\t2:读取");
            int choice = input.nextInt();
            if(choice==1){
                for(int i = 0;i < 3;i++){
                    System.out.println("开始为第"+(i+1)+"个学生录入信息：");
                    System.out.println("姓名：");
                    temp[0] = input.next();
                    System.out.println("年龄：");
                    temp[1] = input.next();
                    System.out.println("java成绩：");
                    temp[2] = input.next();
                    System.out.println("c成绩：");
                    temp[3] = input.next();
                    stu[i] = new Student(temp);
                }
                for(int i = 0;i < 3;i++){
                    stu[i].showInfo();
                }
                recovery(1);
            }
            else if(choice == 2){
                recovery(2);
                getAverage(stu);
            }else{
                System.out.println("无此选项！");
            }

            System.out.println("是否继续：(Y/N)");
            tempCh = input.next().charAt(0);
        }while(tempCh == 'Y'||tempCh == 'y');
    }
    private static void recovery(int choice){
        try//使用Buffer进行存储及读取
        {
            String m_fileName = "src/ex9/chengji.txt";
            if(choice == 1){
                System.out.println("开始写入！");
                BufferedWriter bf = new BufferedWriter(new FileWriter(new File(m_fileName)));
                String tempStr;
                for(int i = 0;i < 3;i++){
                    for(int j = 0;j < 4;j++){
                        tempStr = stu[i].writeInfo()[j];
                        bf.write(tempStr+'\n');
                    }
                }
                bf.close();
                System.out.println("写入完成！");

            }
            else if(choice == 2){
                System.out.println("开始读取！");
                BufferedReader br = new BufferedReader(new FileReader(new File(m_fileName)));
                String[][] tempS = new String[3][4];
                for(int i = 0;i < 3;i++){
                    for(int j = 0;j < 4;j++){
                        tempS[i][j] = br.readLine();
                    }
                }
                for(int i = 0;i < 3;i++){
                    stu[i] = new Student(tempS[i]);
                    stu[i].showInfo();
                }
                System.out.println("读取完毕！");
                br.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void getAverage(Student[] stu){
        double aver_j = 0;
        double aver_c = 0;
        for(int i = 0;i < 3;i++){
            aver_j += stu[i].getJavaScore();
            aver_c += stu[i].getcScore();
        }
        aver_j/=3;
        aver_c/=3;
        System.out.println("三位同学的Java成绩平均值是："+aver_j);
        System.out.println("三位同学的C成绩平均值是："+aver_c);
    }
}
