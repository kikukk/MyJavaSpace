package Mysql.jdbc.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

//封装Emp表数据的JavaBean
public class  Emp {
    private int id;
    private String name;
    private char gender;
    private int salary;
    private Date join_date;
    private int dept_id;

    public Emp(ResultSet rs){
        try {
            id = rs.getInt("id");
            name = rs.getString("name");
            gender = rs.getString("gender").charAt(0);
            salary = rs.getInt("salary");
            join_date = rs.getDate("join_date");
            dept_id = rs.getInt("dept_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                ", join_date=" + join_date +
                ", dept_id=" + dept_id +
                '}';
    }
}
