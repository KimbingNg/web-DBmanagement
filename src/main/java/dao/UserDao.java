package dao;

import domain.User;
import exceptions.WrongDataException;

public interface UserDao {
    public void add(User user) throws WrongDataException;

    public void remove(User user) throws WrongDataException;

    User findByUsername(String username);

    void clear();
}
