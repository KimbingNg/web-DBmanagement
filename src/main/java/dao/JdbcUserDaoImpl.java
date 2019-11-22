//package dao;
//
//import domain.Person;
//import util.DbPool;
//
//import java.sql.*;
//
//
//public class JdbcUserDaoImpl implements UserDao {
//
//    @Override
//    public Person findByKeyName(String username) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Person user = new Person();
//        try {
//            connection = DbPool.getConnection();
//            String sql = "SELECT * FROM user WHERE username=?";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if (rs == null) return null;
//            if (rs.next()) {
//                user.setUserName(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setAge(rs.getInt("age"));
//                user.setGender(rs.getString("gender"));
//                return user;
//            } else {
//                return null;
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (connection != null) connection.close();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    @Override
//    public void add(Person user) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        try {
//            connection = DbPool.getConnection();
//            String sql = "insert into user values(?,?,?,?)";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setInt(3, user.getAge());
//            ps.setString(4, user.getGender());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (connection != null) connection.close();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//}
