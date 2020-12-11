package ex9;

class Student {
    private String name;
    private int age;
    private int javaScore;
    private int cScore;

    Student(String[] temp) {
        this.name = temp[0];
        this.age = Integer.parseInt(temp[1]);
        this.javaScore = Integer.parseInt(temp[2]);
        this.cScore = Integer.parseInt(temp[3]);
    }

    int getJavaScore() {
        return javaScore;
    }

    int getcScore() {
        return cScore;
    }

    void showInfo(){
        System.out.println("名字："+ name+"\t年龄："+age+"\tjava成绩："+javaScore+"\tc成绩："+cScore);
    }
    String[] writeInfo(){
        return new String[]{name,String.valueOf(age), String.valueOf(javaScore), String.valueOf(cScore)};
    }
}
