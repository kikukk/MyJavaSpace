package Application.tableEdit.C_2012;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * 读取表格 File->FileInputStream->XSSFWorkbook读取表格->sheet读取分页
 * 读取行列 getRow(行数) getCell(列数) .get...
 *
 * */

public class data5_2012C_2 {
    public static void main(String args[]) throws IOException {
        //读入数据
        FileInputStream in = new FileInputStream(new File("src\\main\\java\\Application\\tableEdit\\C_2012\\data5.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(in);
        Sheet sheet;

        //写入数据
        FileWriter filewriter=new FileWriter("src/main/java//Application/tableEdit/C_2012/test2.xls");
        Sheet sheet1 = wb.getSheet("data");
        int day = 0;
        String cmpDate = "";
        double data[] = new double[8];
        //High pres	Low pres	Aver temp	High temp	Low temp	Aver RH	Min RH
        for(int i = 1;i < 1462;i++){
            Date date =  sheet1.getRow(i).getCell(0).getDateCellValue();
            String strDate = (date.getYear()+1900)+"/"+(date.getMonth()+1);

            if(cmpDate == "") {
                cmpDate = strDate;
                for(int j = 1;j < 9;j++){
                    data[j-1] = sheet1.getRow(i).getCell(j).getNumericCellValue();
                }
                day++;
                continue;
            }
            if(cmpDate.equals(strDate)){
                for(int j = 1;j < 9;j++){
                    data[j-1] += sheet1.getRow(i).getCell(j).getNumericCellValue();
                }
                day++;
                continue;
            }else{
                //System.out.println(cmpDate+"\t"+day);
                //System.out.println(cmpDate);
//                for(int j = 0;j < 8;j++){
//                    data[j] = data[j]/day;
//                    data[j] = Double.parseDouble(new DecimalFormat("0.000").format(data[j]));
//                    //System.out.println((j+1)+"\t"+(data[j]));
//                }

                int j = 7;
                data[j] = data[j]/day;
                data[j] = Double.parseDouble(new DecimalFormat("0.000").format(data[j]));
                System.out.println(data[j]);


                data = new double[8];
                day = 0;
                cmpDate = "";
            }
            if(cmpDate == "") {
                cmpDate = strDate;
                for(int j = 1;j < 9;j++){
                    data[j-1] = sheet1.getRow(i).getCell(j).getNumericCellValue();
                }
                day++;
                continue;
            }


        }
        filewriter.flush();
        filewriter.close();
    }
}