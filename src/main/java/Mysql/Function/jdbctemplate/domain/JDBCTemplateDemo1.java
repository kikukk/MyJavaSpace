package Mysql.Function.jdbctemplate.domain;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update account set username  = 'wangwu' where id = ?";
        int account = template.update(sql,3);
        System.out.println(account);
    }
}
