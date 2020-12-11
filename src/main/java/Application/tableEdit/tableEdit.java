package Application.tableEdit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tableEdit {
    public static void main(String args[]) throws IOException {
        //读入数据
        File file = new File("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\file2.xlsx");
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        Sheet sheet = wb.getSheetAt(0); //取得“测试.xlsx”中的第一个表单
        //首尾行号
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        //写入数据
        FileWriter filewriter=new FileWriter("G:\\WorkSpace\\MyMatlabSpace\\Exam19_D\\test.xls");
        //存放读入的数据
        ArrayList<String[]> arrayList= new ArrayList();
        //读取一行
        Row row;
        //开始记录数据的时间
        String startTime = null;
        //开始记录数据的结点
        int startId = 1;
        //存放计算平均数数据的数组
        String[] strAddDatas = new String[12];
        for(int i = 0;i < 12;i++){
            strAddDatas[i] = "";
        }
        //存放当前读取的记录的数组
        String[] strCurData;
        //定义日期格式
        SimpleDateFormat dataformatter = new SimpleDateFormat( "yyyy/MM/dd HH:mm");
        //日期转换
        Calendar cal = Calendar.getInstance();

        Date date;

        try {
            for (int i = firstRowNum+1; i <= lastRowNum; i++) {
                strCurData = new String[12];
                row = sheet.getRow(i);//取得第i行 （从第二行开始取，因为第一行是表头）
                //读取数据
                for(int j = 0;j < 11;j++){
                    try{
                        strCurData[j] = String.valueOf(row.getCell(j).getNumericCellValue());
                    }catch (Exception e){
                        return;
                    }
                }
                strCurData[11] = row.getCell(11).getDateCellValue().toString();
                //将当前读入的数据写入总数据表
                arrayList.add(strCurData);

                //如果没有开始下一个时间区间平均数计算，就开始
                if(startTime == null){
                    startTime = strCurData[11];
                    continue;
                }else if((!startTime.substring(0,14).equals(strCurData[11].substring(0,14)))||(!startTime.substring(19).equals(strCurData[11].substring(19)))){
                    //当时间区间发生变化时，输出时间点
//                    System.out.println(strCurData[11]);
                    //开始统计上个区间总数并除得平均值
                    for(int k = 0;k < 11;k++){
                        for(int l = startId-1;l < i-1;l++){
                            //数组被初始化为空时，不能进行加操作，要先赋值0
                            if(strAddDatas[k].isEmpty()){
                                strAddDatas[k] = "0";
                            }
                            //累加区间内某个属性的所有值
                            strAddDatas[k] = String.valueOf(Double.parseDouble(strAddDatas[k]) + Double.parseDouble(arrayList.get(l)[k]));
                        }
                    }
                    //输出平均值
                    System.out.println(startTime.substring(24)+" "+startTime.substring(4,13)+"时的平均值:");
                    for(int k = 0;k < 11;k++){
                        //每个总和值/数量 = 平均数
                        strAddDatas[k] = String.valueOf(Double.parseDouble(strAddDatas[k])/(i-startId));
                        //输出该属性的平均数
                        System.out.print(strAddDatas[k]+"\t");
                        //写入平均数
                        filewriter.write(strAddDatas[k]+"\t");
                    }
                    //写入时间区间
                    date = new Date(startTime);
                    cal.setTime(date);
                    cal.add(Calendar.HOUR, -14);// 24小时制
                    date = cal.getTime();
                    date.setMinutes(0);
                    filewriter.write(dataformatter.format(date)+"\r\n");
                    System.out.println("");

                    //重置各种参数
                    startId = i;
                    strAddDatas = new String[12];
                    for(int l = 0;l < 12;l++){
                        strAddDatas[l] = "";
                    }
                    startTime =null;
                }
            }
            filewriter.flush();
            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }
}