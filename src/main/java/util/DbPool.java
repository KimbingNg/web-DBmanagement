package util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DbPool {
    private static Properties properties = null;

    private static DataSource dataSource;

    static {
        try {
            InputStream in = DbPool.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties = new Properties();
            properties.load(in);
            dataSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName(properties.getProperty("driverClassName"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
