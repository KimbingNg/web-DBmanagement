package dao;

import domain.Person;
import exceptions.WrongDataException;
import util.DbPool;

import java.sql.*;
import java.util.ArrayList;

public class PersonDaoImpl implements PersonDao {

    private Object[] getWhereStatement(Person person) {
        String userName = person.getUserName();
        String name = person.getName();
        Integer age = person.getAge();
        String telenum = person.getTelenum();
        String sql = " where 1=1 ";
        ArrayList<String> params = new ArrayList<>();
        if (userName != null) {
            sql += " and username = ? ";
            params.add(userName);
        }
        if (name != null) {
            sql += " and name=? ";
            params.add(name);
        }
        if (age != null) {
            sql += " and age=? ";
            params.add(age.toString());
        }
        if (telenum != null) {
            sql += " and teleno=? ";
            params.add(telenum);
        }
        Object[] ret = new Object[2];
        ret[0] = sql;
        ret[1] = params;
        return ret;
    }

    /**
     * 单条件查找
     */
    @Override
    public Person findByKeyName(String name) {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "select * from person where name = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (!resultSet.next()) return null;
                    String username = resultSet.getString("username");
                    Integer age = resultSet.getInt("age");
                    String teleno = resultSet.getString("teleno");
                    return new Person(username, name, age, teleno);
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除的时候调用的查找方法, 全条件查找
     */
    @Override
    public boolean contains(Person person) {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "select * from person";
            Object[] whereStatement = getWhereStatement(person);
            sql += (String) whereStatement[0];
            ArrayList<String> params = (ArrayList<String>) whereStatement[1];
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < params.size(); ++i)
                    ps.setString(i + 1, params.get(i));
                System.out.println(ps + "  PersonDaoImpl.contains");
                ResultSet resultSet = ps.executeQuery();
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void add(Person person) throws WrongDataException {
        try (Connection connection = DbPool.getConnection()) {
            Person byUsername = findByKeyName(person.getName());
            if (byUsername != null) {
                String sql = "update person set username=?, name=? ,age=?,teleno=? where name=?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    inject(ps, person);
                    ps.setString(5, person.getName());
                    ps.executeUpdate();
                }
            } else {
                String sql = "insert into person values(?,?,?,?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    inject(ps, person);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrongDataException();
        }
    }

    private void inject(PreparedStatement ps, Person person) throws SQLException {
        if (person.getUserName() == null)
            ps.setNull(1, Types.VARCHAR);
        else
            ps.setString(1, person.getUserName());
        if (person.getName() == null)
            ps.setNull(2, Types.VARCHAR);
        else
            ps.setString(2, person.getName());
        if (person.getAge() == null)
            ps.setNull(3, Types.VARCHAR);
        else
            ps.setString(3, person.getAge().toString());
        if (person.getTelenum() == null)
            ps.setNull(4, Types.VARCHAR);
        else
            ps.setString(4, person.getTelenum());
    }

    @Override
    public void remove(Person person) throws WrongDataException {
        // TODO: 11/21/19 implement
        try (Connection connection = DbPool.getConnection()) {
            String sql = "delete from person";
            Object[] whereStatement = getWhereStatement(person);
            sql += (String) whereStatement[0];
            ArrayList<String> params = (ArrayList<String>) whereStatement[1];
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < params.size(); ++i)
                    ps.setString(i + 1, params.get(i));
                System.out.println(ps + "  PersonDaoImpl.contains");
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeByUserName(String username) throws SQLException {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "delete from person where username=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.executeUpdate();
            }
        }
    }

    public void clear() {
        try (Connection connection = DbPool.getConnection()) {
            String sql = "delete from person";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error();
        }
    }
}
