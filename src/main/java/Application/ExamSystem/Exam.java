package Application.ExamSystem;

class Exam {
    private String id;
    private String no;
    private String content = "";
    private String[] answers = new String[15];
    private String answer;

    Exam(String[] tmp){
        id = tmp[0].substring(0,5).trim();
        no = tmp[0].substring(6).trim();
        int i;
        for(i = 1;i < tmp.length;i++){
            if(tmp[i].charAt(0)!='A'){
                content+=tmp[i];
            }else {
                break;
            }
        }
        answers[0] = tmp[i++];
        answers[1] = tmp[i++];
        answers[2] = tmp[i++];
        answers[3] = tmp[i++];
        answer = tmp[i];
    }
    void show(){
        System.out.println("id:"+id);
        System.out.println("no:"+no);
        System.out.println("content:"+content);
        for(int j = 0;j < 4;j++){
            System.out.println(answers[j]);
        }
        System.out.println(answer);
    }

    String getId() {
        return id;
    }

    String getNo() {
        return no;
    }

    String getContent() {
//        String tmp = "";
//        for(int i = 0;i < content.length();i++){
//            tmp +=  content.charAt(i);
//            if(i+1 % 10 == 0){
//                tmp += '\n';
//            }
//        }
//        System.out.println(tmp);
        return content;
    }

    String[] getAnswers() {
        return answers;
    }

    String getAnswer() {
        return answer;
    }
}
