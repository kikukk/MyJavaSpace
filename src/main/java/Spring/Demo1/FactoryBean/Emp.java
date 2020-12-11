package Spring.Demo1.FactoryBean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName emp
 * @Description TODO
 * @date 2020/9/4 10:18
 */
class Emp {
    private String ename;
    private String gender;
    private Dept dept;
    private String address;
    private String other;

    public Dept getDept() {
        return dept;
    }

    public void show(){
        System.out.println("ename:"+ename);
        System.out.println("gender:"+gender);
        System.out.println("dept:"+dept.getDname());
        System.out.println("address:"+address);
        System.out.println("other:"+other);
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOther(String other) {
        this.other = other;
    }

    private String[] courses;
    private List<String> list;
    private Map<String,String> maps;
    private Set<String> sets;

    private List<Course> courseList;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void showArray(){
        System.out.println(Arrays.toString(courses));
        System.out.println(list);
        System.out.println(maps);
        System.out.println(sets);
        System.out.println(courseList);
    }

}
