package dao;

import domain.User;
import exceptions.WrongDataException;
import util.DbPool;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    private void inject(PreparedStatement ps, User user) throws SQLException {
        if (user.getUsername() == null)
            ps.setNull(1, Types.VARCHAR);
        else
            ps.setString(1, user.getUsername());
        if (user.getPass() == null)
            ps.setNull(2, Types.VARCHAR);
        else ps.setString(2, user.getPass());
    }

    @Override
    public void add(User user) throws WrongDataException {
        try (Connection connection = DbPool.getConnection()) {
            User byUsername = findByUsername(user.getUsername());
            if (byUsername != null) {
                String sql = "update user set username=?,pass=? where username=?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    inject(ps, user);
                    ps.setString(3, user.getUsername());
                    ps.executeUpdate();
                }
            } else {
                String sql = "insert into user values(?,?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    inject(ps, user);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrongDataException();
        }
    }

    private Object[] getWhereStatement(User user) {
        String username = user.getUsername();
        String pass = user.getPass();
        String sql = " where 1=1 ";
        ArrayList<String> params = new ArrayList<>();
        if (username != null) {
            sql += " and username = ? ";
            params.add(username);
        }
        if (pass != null) {
            sql += " and password = ? ";
            params.add(pass);
        }
        Object[] ret = new Object[2];
        ret[0] = sql;
        ret[1] = params;
        return ret;
    }

    @Override
    public void remove(User user) throws WrongDataException {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "delete from user WHERE username=?";
            Object[] whereStatement = getWhereStatement(user);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "select * from user where username = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (!resultSet.next()) return null;
                    String pass = resultSet.getString("password");
                    return new User(username, pass);
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void clear() {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "delete from user";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error();
        }
    }
}

