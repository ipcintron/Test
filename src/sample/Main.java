package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import static javafx.application.Application.launch;

/** This is just a sample Database connection and JDBC usage class.
 The design is no good and simply used for understanding what is happening.
 */
public class Main extends Application{

    public static void main(String args[]){
    /* These variables I am just listing out so you get an idea of all the bits you need for a connection
       in this example everything is sort of hard-coded. */

        String driverclassname = "net.sourceforge.jtds.jdbc.Driver";
            String servername = "localhost";
            int port = 1433;
            String databasename = "DOBI";
            String username = "test";
            String password = "1534";
    /* the actual construction of the URL to be used will change from driver to driver and DB to DB.
       Check the driver specs for the URL format for your driver. */
            String databaseurl = "jdbc:jtds:sqlserver://"+servername+":"+port;
            databaseurl +="/"+databasename+";user="+username+";password="+password;
            // here we load the driver. if the driver doesn't load then we quit because everything else is pointless
            try{
                Class.forName(driverclassname);
                System.out.println("Database Driver loaded...");
            }catch(Exception driverLoadEx){
                System.out.println("ERROR!! - The database driver could not be loaded. Error trace appears below.");
                driverLoadEx.printStackTrace();
                System.exit(0);
            }
            // here we open a connection to the database
            Connection c = null;
            try{
                c = DriverManager.getConnection(databaseurl);
                System.out.println("Connection opened...");
            }catch(SQLException connectEx){
                System.out.println("ERROR!! - Could not open connection.");
                connectEx.printStackTrace();
                System.exit(0);
        }
        // it is better practice to get into using PreparedStatements over Statements
        PreparedStatement ps = null;
        try{
            ps = c.prepareStatement("SELECT Em_Type_ID,Em_Type FROM Employee_Type");
            System.out.println("Statement prepared...");
        }catch(SQLException prepareEx){
            System.out.println("ERROR!! - Could not prepare statement.");
            prepareEx.printStackTrace();
            try{
                c.close();
            }catch(SQLException cCloseEx){
                System.out.println("ERROR!! - In closing connection.");
                cCloseEx.printStackTrace();
            }
        }
        ResultSet rs = null;
        try{
            rs = ps.executeQuery();
            System.out.println("Query executed...");
        }catch(SQLException rsEx){
            System.out.println("ERROR!! - Could not execute query.");
            rsEx.printStackTrace();
        }
        if(rs!=null){
            try{
                System.out.println("Contents of Employee_Type");
                while(rs.next()){
                    System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
                }
            }catch(SQLException processEx){
                System.out.println("ERROR!! - in processing result set.");
                processEx.printStackTrace();
            }
        }
        // WE ALWAYS close every result set, statement and connection we open. in reverse order of how we opened them
        try{
            rs.close();
        }catch(SQLException rsCloseEx){
            System.out.println("ERROR!! - In closing result set.");
            rsCloseEx.printStackTrace();
        }
        try{
            ps.close();
        }catch(SQLException psCloseEx){
            System.out.println("ERROR!! - In closing prepared statement.");
            psCloseEx.printStackTrace();
        }
        // now prepare a new statement to add a new record...
        try{
            ps = c.prepareStatement("INSERT INTO Employee_Type(Em_Type) VALUES(?)");
            System.out.println("Statement prepared...");
        }catch(SQLException prepareEx){
            System.out.println("ERROR!! - Could not prepare statement.");
            prepareEx.printStackTrace();
            try{
                c.close();
            }catch(SQLException cCloseEx){
                System.out.println("ERROR!! - In closing connection.");
                cCloseEx.printStackTrace();
            }
        }
        // now bind the parameters for the above statement and execute it
        try{
            ps.setString(1,"Desk Tech");
            ps.executeUpdate();
            System.out.println("Query executed...");
        }catch(SQLException updateEx){
            System.out.println("ERROR!! - Could not execute update query.");
            updateEx.printStackTrace();
        }

        // Starting a new prepared statement
        try{
            rs.close();
        }catch(SQLException rsCloseEx){
            System.out.println("ERROR!! - In closing result set.");
            rsCloseEx.printStackTrace();
        }
        try{
            ps.close();
        }catch(SQLException psCloseEx){
            System.out.println("ERROR!! - In closing prepared statement.");
            psCloseEx.printStackTrace();
        }


        try{
            ps = c.prepareStatement("DELETE FROM Employee_Type WHERE Em_Type_ID=11");
            System.out.println("Statement prepared...");
        }catch(SQLException prepareEx){
            System.out.println("ERROR!! - Could not prepare statement.");
            prepareEx.printStackTrace();
            try{
                c.close();
            }catch(SQLException cCloseEx){
                System.out.println("ERROR!! - In closing connection.");
                cCloseEx.printStackTrace();
            }
        }
        // now bind the parameters for the above statement and execute it
        try{
            ps.executeUpdate();
            System.out.println("Query executed...");
        }catch(SQLException updateEx){
            System.out.println("ERROR!! - Could not execute update query.");
            updateEx.printStackTrace();
        }


        try{
            c.close();
        }catch(SQLException cCloseEx){
            System.out.println("ERROR!! - In closing connection.");
            cCloseEx.printStackTrace();
        }
launch(args);
    }



    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

















/*package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/DOBI;instance=");
        System.out.println("test");
        /*Statement sta = conn.createStatement();
        String Sql = "select * from Client";
        ResultSet rs = sta.executeQuery(Sql);
        while (rs.next()) {
            System.out.println(rs.getString("Cl_fName"));
        }*/
        //launch(args);
/*
        Statement statement = conn.createStatement();
        ResultSet rss=statement.executeQuery("SELECT * FROM Client");
    }
}*/
