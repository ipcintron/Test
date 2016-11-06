package sample;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbHelper2
{
    private String className, username, password, url;
    private DataSource dataSource;
    private Connection connection;

    public void connect() throws SQLException {
        if ( dataSource == null ) {
            final BasicDataSource source;

            try { Class.forName(className); }
            catch (ClassNotFoundException e) { throw new RuntimeException(e); }

            source = new BasicDataSource();
            source.setDriverClassName(className);
            source.setUsername(username);
            source.setPassword(password);
            source.setUrl(url);
            dataSource = source;
        }
        if ( connection == null ) {
            connection = dataSource.getConnection();
        }
    }

    public String execute(String statement) throws SQLException {
        connection.prepareStatement(statement).executeQuery();
        return statement;
    }

    public void disconnect() throws SQLException {
        if ( connection != null ) {
            connection.close();
            connection = null;
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setURL(String url) {
        this.url = url;
    }
}
