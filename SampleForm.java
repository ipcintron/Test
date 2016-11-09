package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SampleForm implements Initializable {

    @FXML
    private JFXTextField EmID;

    @FXML
    private JFXButton AddEmployeeType;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXTextField EmployeeType;

    @FXML
    void AddEmployeeType(ActionEvent event) throws SQLException {
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

        // USED TO GRAB THE ID FROM THE DATABASE TO SET THE ID NUMBER CORRECTLY IN THE FORM
        try{
            ps = c.prepareStatement("SELECT TOP 1 Em_Type_ID FROM Employee_Type ORDER BY Em_Type_ID DESC");
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

            // SETS THE ID NUMBER IN THE FORM TO AN ELEMENT
            if(rs.next()){
                int holder = rs.getInt(1);
                holder += 1;
                EmID.setText(String.valueOf(holder));
            }
        }catch(SQLException rsEx){
            System.out.println("ERROR!! - Could not execute query.");
            rsEx.printStackTrace();
        }
        /*if(rs!=null){
            try{
                System.out.println("Contents of Employee_Type");
                while(rs.next()){
                    System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
                }
            }catch(SQLException processEx){
                System.out.println("ERROR!! - in processing result set.");
                processEx.printStackTrace();
            }
        }*/
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
        String Em = EmployeeType.getText();

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
            ps.setString(1,Em);
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





        /*final DbHelper2 app = new DbHelper2();
        app.setClassName("net.sourceforge.jtds.jdbc.Driver");
        app.setUsername("test");
        app.setPassword("1534");
        app.setURL("jdbc:jtds:sqlserver://localhost/DOBI");
        String Employee = EmployeeType.getText();
        app.connect();
        //String last = app.execute("SELECT TOP 1 Em_Type_ID FROM Employee_Type ORDER BY Em_Type_ID DESC");
        //System.out.println(last);
        //EmID.setText(String.valueOf(last));
        app.execute("INSERT INTO Employee_Type(Em_Type) VALUES ('"+Employee+"')");
        app.disconnect();*/



    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
