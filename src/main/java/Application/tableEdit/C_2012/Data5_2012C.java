package Application.tableEdit.C_2012;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
* 读取表格 File->FileInputStream->XSSFWorkbook读取表格->sheet读取分页
* 读取行列 getRow(行数) getCell(列数) .get...
*
* */

public class Data5_2012C {
    public static void main(String args[]) throws IOException {
        //读入数据
        File file = new File("src/main/java//Application/tableEdit/data5.xlsx");
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        Sheet sheet;

        //写入数据
        FileWriter filewriter=new FileWriter("src/main/java//Application/tableEdit/test.xls");

        double tmp;

        int year = 2007;
        int month = 1;
        int date = 1;
        String strDate;


        for(int k = 0;k < 4;k++){
            sheet = wb.getSheetAt(k);
            for(int i = 2;i < 100;i+=8){//100
                for (int j = 3; j <= 33; j++) {
                    try {
                        tmp = sheet.getRow(j).getCell(i).getNumericCellValue();
                        if(tmp == 0)continue;
                        strDate = year+"/"+month+"/"+date;
                        System.out.println(strDate+"\t"+tmp);
                        filewriter.write(strDate+"\t"+tmp+"\n");
                    } catch (NullPointerException e) {
                        break;
                    }
                    date+=1;
                }
                date = 1;
                month+=1;
            }
            year+=1;
            month = 1;
        }


        filewriter.flush();
        filewriter.close();

    }
}