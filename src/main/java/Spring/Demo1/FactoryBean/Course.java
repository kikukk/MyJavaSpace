package Spring.Demo1.FactoryBean;

/**
 * @ClassName Course
 * @Description TODO
 * @date 2020/9/7 9:51
 */
class Course {
    private String cname;

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
