package Mysql.Function.jdbctemplate.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

public class JDBCTemplateDemo2 {
    //Junit单元测试，可以让方法独立执行

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //1.修改1号数据的salary为10000
    public void test1(){
        String sql = "update emp set salary = 6300 where id = 1";
        int count = template.update(sql);
        System.out.println(count);
    }

    //2.添加一条记录
    public void test2(){
        String sql = "insert into account values(null,?,?)";
        int count = template.update(sql,"huangliu",200);
        System.out.println(count);
    }
    //3.删除刚才添加的记录
    public void test3(){
        String sql = "delete from account where id = ?";
        int count = template.update(sql,4);
        System.out.println(count);
    }

    //4.查询id为1的记录，将其封装为Map集合
    //注意：这个方法查询的结果集长度只能是1
    public void test4(){
        String sql = "select * from account where id = ?";
        Map<String,Object> map = template.queryForMap(sql,1);
        System.out.println(map);
        //{id=1, username=zhangsan, balance=450}
    }

    //5.查询所有记录，将其封装为List
    public void test5(){
        String sql = "select * from account";
        List<Map<String,Object>> list = template.queryForList(sql);
        for(Map<String,Object> StringObjectMap : list){
            System.out.println(StringObjectMap);
        }
    }

    //6.查询所有记录，将其封装为Emp对象的List集合
    public void test6(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, (rs, i) -> {
            Emp emp = new Emp(rs);
            return emp;
        });
        Emp emp = new Emp();
        for(Emp emp1: list){
            System.out.println(emp1);
        }
    }

    //7.查询总记录数
    public void test7(){
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql,Long.class);
        System.out.println(total);
    }

}
