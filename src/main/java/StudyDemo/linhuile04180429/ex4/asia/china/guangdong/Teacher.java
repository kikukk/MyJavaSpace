package ex4.asia.china.guangdong;

import ex4.asia.china.Employee;

public class Teacher extends Employee {
    private int workYears;
    private String jobTitle;
    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }
    public int getWorkYears() {
        return workYears;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public Teacher(){};
    public Teacher(int a,String b){
        workYears = a;
        jobTitle = b;
    }
    public Teacher(String _name,int _age,int _workYears,String _jobTitle){
        super.setName(_name);
        super.setAge(_age);
        setWorkYears(_workYears);
        setJobTitle(_jobTitle);
    }
    public void showInfo(){
        System.out.println("姓名:" + super.getName());
        System.out.println("年龄:" + super.getAge());
        System.out.println("工作年限:" + workYears);
        System.out.println("职称:" + jobTitle);
    }
}
