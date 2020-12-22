package Application.tableEdit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tableEdit3 {
    public static void main(String args[]) throws IOException, ParseException {
        //读入数据1
        File file1 = new File("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\file1.xlsx");
        FileInputStream inFile1 = new FileInputStream(file1);
        XSSFWorkbook wbFile1 = new XSSFWorkbook(inFile1);
        Sheet sheetFile1 = wbFile1.getSheetAt(0); //取得“file1.xlsx”中的第一个表单
        //首尾行号
        int firstRowNum1 = sheetFile1.getFirstRowNum();
        int lastRowNum1 = sheetFile1.getLastRowNum();

        //读入数据1
        File file2 = new File("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\file2_ave.xlsx");
        FileInputStream inFile2 = new FileInputStream(file2);
        XSSFWorkbook wbFile2 = new XSSFWorkbook(inFile2);
        Sheet sheetFile2 = wbFile2.getSheetAt(0); //取得“file1.xlsx”中的第一个表单
        //首尾行号
        int firstRowNum2 = sheetFile1.getFirstRowNum();
        int lastRowNum2 = sheetFile1.getLastRowNum();

        //写入数据
        FileWriter filewriter1=new FileWriter("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\test1.xls");

        //写入数据
        FileWriter filewriter2=new FileWriter("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\test2.xls");
        //存放读入的数据
        ArrayList<String[]> arrayFile1= new ArrayList();
        ArrayList<String[]> arrayFile2= new ArrayList();

        //读取一行
        Row row1;
        Row row2;

        //定义日期格式
        SimpleDateFormat dataformatter = new SimpleDateFormat( "yyyy/MM/dd HH:mm");
        //日期转换
        Calendar cal = Calendar.getInstance();

        //记录缺失数据
        ArrayList<String> arrayLost1 = new ArrayList<>();
        ArrayList<String> arrayLost2 = new ArrayList<>();


        //存放当前读取的一行数据
        String[] strCurData;
        try {
            for (int i = firstRowNum1+1; i <= lastRowNum1; i++) {
                //重置数组
                strCurData = new String[7];
                row1 = sheetFile1.getRow(i);//取得第i行 （从第二行开始取，因为第一行是表头）
                //读取数据
                for(int j = 0;j < 6;j++){
                    try{
                        strCurData[j] = String.valueOf(row1.getCell(j).getNumericCellValue());
                    }catch (Exception e){
                        return;
                    }
                }
                cal.setTime(row1.getCell(6).getDateCellValue());
                cal.add(Calendar.HOUR, -14);// 24小时制
                strCurData[6] = dataformatter.format(new Date(cal.getTime().toString()));
                //将当前读入的数据写入总数据表
                arrayFile1.add(strCurData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            for (int i = firstRowNum2+1; i <= lastRowNum2; i++) {
                //重置数组
                strCurData = new String[12];
                row2 = sheetFile2.getRow(i);//取得第i行 （从第二行开始取，因为第一行是表头）
                //读取数据
                for(int j = 0;j < 11;j++){
                    try{
                        strCurData[j] = String.valueOf(row2.getCell(j).getNumericCellValue());
                    }catch (Exception e){
                        return;
                    }
                }
                cal.setTime(row2.getCell(11).getDateCellValue());
                cal.add(Calendar.HOUR, -14);// 24小时制
                strCurData[11] = dataformatter.format(new Date(cal.getTime().toString()));

                //将当前读入的数据写入总数据表
                arrayFile2.add(strCurData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        for(int i = 0,j = 0;i < arrayFile1.size()&&j < arrayFile2.size();){
            if(arrayFile1.get(i)[6].equals(arrayFile2.get(j)[11])){
                i++;j++;
            }else if(arrayFile1.get(i)[6].compareTo(arrayFile2.get(j)[11])>0){
//                System.out.println("1数据缺失");
//                System.out.println("1:"+arrayFile1.get(i)[6]);
//                System.out.println("2:"+arrayFile2.get(j)[11]);
                arrayLost1.add(arrayFile2.get(j)[11]);
                arrayFile2.remove(j);
            }else if(arrayFile1.get(i)[6].compareTo(arrayFile2.get(j)[11])<0){
//                System.out.println("2数据缺失");
//                System.out.println("1:"+arrayFile1.get(i)[6]);
//                System.out.println("2:"+arrayFile2.get(j)[11]);
                arrayLost2.add(arrayFile1.get(i)[6]);
                arrayFile1.remove(i);
            }
        }

        for(int i = 0;i < arrayFile1.size();i++){
            for(int j = 0;j < 7;j++){
                filewriter1.write(arrayFile1.get(i)[j]+"\t");
            }
            filewriter1.write("\n");
        }

        for(int i = 0;i < arrayFile2.size();i++){
            for(int j = 0;j < 12;j++){
                filewriter2.write(arrayFile2.get(i)[j]+"\t");
            }
            filewriter2.write("\n");
        }

        filewriter1.flush();
        filewriter1.close();
        filewriter2.flush();
        filewriter2.close();

        //汇报缺失
        System.out.println("1数据缺失"+arrayLost1.size());
        System.out.println("2数据缺失"+arrayLost2.size());
        for(int i = 0;i < arrayLost1.size();i++){
            System.out.println(arrayLost1.get(i));
        }
        System.out.println("_________________________________________");
        for(int i = 0;i < arrayLost2.size();i++){
            System.out.println(arrayLost2.get(i));
        }
    }
}