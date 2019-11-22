package dao;

import domain.Person;
import exceptions.WrongDataException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDao {
    Person findByKeyName(String username);

    boolean contains(Person person);

    void add(Person person) throws WrongDataException;

    void remove(Person person) throws WrongDataException;

    void removeByUserName(String username) throws SQLException;

    void clear();
}
