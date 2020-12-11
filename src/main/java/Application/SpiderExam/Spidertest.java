package Application.SpiderExam;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Spidertest {
    //写入文件地址
    private static File writeToPath = new File("src\\App\\SpiderExam\\exam.txt");
    private static File errorLogPath = new File("src\\App\\SpiderExam\\beta.txt");
    //写入缓冲器
    private static BufferedWriter bufferedWriter;
    private static BufferedWriter bufferedErrorWriter;
    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(writeToPath));
            bufferedErrorWriter = new BufferedWriter(new FileWriter(errorLogPath));
        } catch (IOException e) {
            System.out.println(bufferedWriter + "初始化失败");
        }
    }

    //cookie
    private static String cookie = "DWRSESSIONID=5DQV5rIOpuRSqzMYOij8TZ1u9an; JSESSIONID=31372A5708CFD81A456EF901B23CC08D.TM2; SF_cookie_2=67313298";


    public static void main(String[] args) throws Exception {
        getId();
        bufferedWriter.close();
        bufferedErrorWriter.close();
    }

    private static void getId() throws IOException {
        OutputStreamWriter out;
        BufferedReader reader;
        int courseId = 24571;
        //题量
        int questionNumber = 600;
        URL url = new URL("http://wlkc.jluzh.com/meol/common/question/test/student/list.jsp?sortColumn=title&pagingNumberPer=" + questionNumber + "&status=0&tagbug=client&sortDirection=1&perm=3840&strStyle=new03&cateId=" + courseId +"&pagingPage=1&");
        String responseMessage;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        {
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Host", "wlkc.jluzh.com");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0");
        }
        conn.connect();
        out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
//        out.write("id="+id);
        out.flush();
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            while ((responseMessage = reader.readLine()) != null) {
                if (!responseMessage.contains("<!--<a href=\"detail.jsp?id=")&&responseMessage.contains("<a href=\"detail.jsp?id=")) {
                    getQuestion(reader.readLine().trim()+"\t"+responseMessage.substring(49,55).replaceAll("\"", ""));
                }
            }
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        out.close();
    }

    private static void getQuestion(String temp) throws IOException {
        String id = temp.substring(temp.indexOf('\t')).trim();
        OutputStreamWriter out;
        BufferedReader reader;
        URL url;
        //url读取
        try {
            url = new URL("http://wlkc.jluzh.com/meol/common/question/questionbank/student/detail.jsp?id=" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(id + "url出错");
            bufferedErrorWriter.write(id + "\turl出错\n");
            return;
        }
        String responseMessage;
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(id + "建立连接失败");
            bufferedErrorWriter.write(id + "\t建立连接失败\n");
            return;
        }
        conn.setReadTimeout(1000);
        {
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Host", "wlkc.jluzh.com");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0");
        }
        conn.connect();
        out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        out.flush();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(temp).append("\n");
        boolean isContinue = true;
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            while ((responseMessage = reader.readLine()) != null && isContinue) {
                if (responseMessage.contains("<input type='hidden'")) {
                    stringBuilder.append(responseMessage.substring(responseMessage.indexOf("value='") + 7, responseMessage.indexOf("'><iframe"))
                            .replaceAll("&lt;p&gt;", "").replaceAll("&lt;/p&gt;", "\n")
                            .replaceAll("&amp;nbsp;", " ").replace("&lt;br/&gt;","\n")
                            .replace("&lt;br/&gt;&amp;#9332;","(1)").replace("&lt;br/&gt;&amp;#9333;","(1)")
                            .replace("&amp;#9332; ","(1)").replace("&amp;#9333;","(2)")
                    );
                    stringBuilder.append("\n");
                    //问答题、填空题、其他 直接得答案
                    if(temp.charAt(0) == 'W'||temp.charAt(0) == 'T'||temp.charAt(0) == '0')
                    {
                        while ((responseMessage = reader.readLine()) != null) {
                            if (responseMessage.contains("<input type='hidden'")) {
                                stringBuilder.append(responseMessage.substring(7 + responseMessage.indexOf("value='"), responseMessage.indexOf("'><iframe"))
                                        .replaceAll("&amp;nbsp;", " ").replace("&lt;br/&gt;", "\n")
                                        .replaceAll("&lt;p&gt;", "").replaceAll("&lt;/p&gt;", "\n")
                                        .replace("&lt;br/&gt;&amp;#9332;", "(1)").replace("&lt;br/&gt;&amp;#9333;", "(1)")
                                        .replace("&amp;gt;", ">").replace("&amp;lt;", "<").replace("&amp;#160;", "")
                                        .replace("&#12288;", "").replace("&amp;#9312;", "(1)")
                                        .replace("&amp;#9313;", "(2)").replace("&amp;#9314;", "(3)")
                                        .replace("&amp;#9315;", "(4)").replace("&amp;#9334;","(3)")
                                        .replace("&amp;#9332; ", "(1)").replace("&amp;#9333; ","(2)")
                                        .replace("&amp;#61619;", ">").replace("&amp;#61603;","<")
                                        .replace("&amp;#9335;", "(4)").replace("&amp;amp;","&")
                                );
                            }
                        }
                        stringBuilder.append("\n");
                    }
                    //判断题查找checked>
                    if(temp.charAt(0) == 'P'){
                        while ((responseMessage = reader.readLine()) != null) {
                            if (responseMessage.contains("checked>")) {
                                stringBuilder.append(responseMessage.substring(responseMessage.length()-2));
                            }
                        }
                        stringBuilder.append("\n\n");
                    }
                    //单项选择题、不定项选择题 输入选项并查找check
                    if(temp.charAt(0) == 'S'||temp.charAt(0) == 'M'){
                        String[] answer = new String[10];
                        int i = 0;
                        boolean isAnswer = false;
                        while ((responseMessage = reader.readLine()) != null) {
                            if (responseMessage.contains("answer")) {
                                if(responseMessage.contains("checked")){
                                    isAnswer = true;
                                }
                                reader.readLine();
                                responseMessage = reader.readLine().trim();
                                stringBuilder.append(responseMessage).append('\n');
                                if(isAnswer){
                                    answer[i++] = responseMessage;
                                    isAnswer = false;
                                }
                            }
                        }
                        for(int j = 0;j < i;j++){
                            stringBuilder.append("answer:").append(answer[j]).append('\n');
                        }
                        stringBuilder.append('\n');
                    }
                    //W/T/P/S/M/0
                    isContinue = false;
                }
            }
//            stringBuilder.append("\n");
            bufferedWriter.write(new String(stringBuilder));
            System.out.print(stringBuilder);
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(id + "下载出错.");
            bufferedErrorWriter.write(id + "\t下载出错\n");
        }
        conn.disconnect();
        out.close();
    }
}