package sample;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.flywaydb.core.Flyway;


public class DbHelper {

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private BasicDataSource ds;


    private DbHelper(){
        // make the constructor private

    }

    private static DbHelper INSTANCE = new DbHelper();

    public static DbHelper getInstance() {
        return INSTANCE;
    }

    public void init() throws SQLException {
        // configure the basic data source
        /*ds = new BasicDataSource();
        ds.setUrl("jdbc:jtds:sqlserver://DOBI:1433");
        ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
        ds.setUsername("test");
        ds.setPassword("1534");
        ds.getConnection();

        */
        String driverclassname = "net.sourceforge.jtds.jdbc.Driver";
        String servername = "localhost";
        int port = 1433;
        String databasename = "DOBI";
        String username = "test";
        String password = "1534";

        String databaseurl = "jdbc:jtds:sqlserver://"+servername+":"+port;
        databaseurl +="/"+databasename+";user="+username+";password="+password;


        try{
            Class.forName(driverclassname);
            System.out.println("Database Driver loaded in DbHelper...");
        }catch(Exception driverLoadEx){
            System.out.println("ERROR!! - The database driver could not be loaded. Error trace appears below.");
            driverLoadEx.printStackTrace();
            System.exit(0);
        }

        Connection c = null;
        try{
            c = DriverManager.getConnection(databaseurl);
            System.out.println("Connection opened in DbHelper...");
        }catch(SQLException connectEx){
            System.out.println("ERROR!! - Could not open connection.");
            connectEx.printStackTrace();
            System.exit(0);
        }





    }

    public void close() {

        if (ds != null) {
            try {
                ds.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
