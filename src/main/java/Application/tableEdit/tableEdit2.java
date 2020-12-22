package Application.tableEdit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tableEdit2 {
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
        String[] strCurData = new String[12];
        //记录中位数的临时数组
        ArrayList<Double> arrayCount = new ArrayList<>();
        //某一列中位数
        Double numMiddle;

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
                    //开始统计上个区间中位数
                    for(int l = 0;l < 11;l++) {
                        for (int j = startId-1; j < i-1; j++) {
                            arrayCount.add(Double.parseDouble(arrayList.get(j)[l]));
                        }

                        arrayCount.sort(new Comparator<Double>() {
                            @Override
                            public int compare(Double o1, Double o2) {
                                return o1.compareTo(o2);
                            }
                        });

                        if(arrayCount.size()%2 == 1){
                            numMiddle = arrayCount.get((arrayCount.size()+1)/2-1);
                        }else{
                            numMiddle = (arrayCount.get(arrayCount.size()/2-1)+arrayCount.get(arrayCount.size()/2))/2;
                        }
                        filewriter.write(numMiddle+"\t");
                        arrayCount = new ArrayList<>();
                    }

                    //写入时间区间
                    filewriter.write(startTime.substring(24)+" "+startTime.substring(4,13)+"h\r\n");
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