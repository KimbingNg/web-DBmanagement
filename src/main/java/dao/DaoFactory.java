package dao;

import domain.Person;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private static Properties properties = null;

    static {
        //家在配置文件到properties文件中
        try {
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDao getUserDao() {

        try {
            Class clazz = Class.forName(properties.getProperty("dao.UserDao"));
            return (UserDao) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static PersonDao getPersonDao() {
        try {
            Class clazz = Class.forName(properties.getProperty("dao.PersonDao"));
            return (PersonDao) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
