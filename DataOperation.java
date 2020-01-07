package com.bitedu.db;

import com.bitedu.data.Student;
import com.bitedu.data.Take;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseOperation {
    private static final String URL = "jdbc:mysql://localhost/course";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static DataSource getDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(URL);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        return ds;
    }

    public static void insert(Student stu) {
        DataSource ds = getDataSource();
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ds.getConnection();
            String sql = "insert into student values(?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, stu.getId());
            stmt.setString(2, stu.getName());
            stmt.setInt(3, stu.getClass_id());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select(int sn) {
        DataSource ds = getDataSource();
        Connection connection = null;
        PreparedStatement stmt = null;
        Student stu = new Student();
        ResultSet res = null;
        try {
            connection = ds.getConnection();
            String sql = "select * from student where id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sn);
            res = stmt.executeQuery();
            while (res.next()) {
                stu.setId(res.getInt("id"));
                stu.setName(res.getString("name"));
                stu.setClass_id(res.getInt("class_id"));
                System.out.println(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectCount() {
        DataSource ds = getDataSource();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            connection = ds.getConnection();
            String sql = "select count(id) stu_num from student";
            stmt = connection.prepareStatement(sql);
            res = stmt.executeQuery();
            while (res.next()) {
                System.out.println("count=" + res.getInt("stu_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Take selectScore(int score) {
        DataSource ds = getDataSource();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        Take take = new Take();
        try {
            connection = ds.getConnection();
            String sql = "select score,count(score) sc_num from student" +
                    "group by score having score>?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, score);
            res = stmt.executeQuery();
            while (res.next()) {
                take.setScore(res.getInt("score"));
                take.setCount(res.getInt("sc_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return take;
    }
}
