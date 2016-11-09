package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class client implements Initializable {

    @FXML
    private TextField clid;

    @FXML
    private TextField zip;

    @FXML
    private TextField firstname;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<?> clst;

    @FXML
    private TextField city;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton clear;

    @FXML
    private TextField middlename;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phone;

    @FXML
    private TextField company;

    @FXML
    private ComboBox<?> state;

    @FXML
    private ComboBox<?> cltype;

    @FXML
    private TextField email;

    @FXML
    void makesave(ActionEvent event) throws Exception{


        Parent confirm_load = FXMLLoader.load(getClass().getResource("Confirm.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(confirm_load));
        primaryStage.show();

        Confirm confirmation = new Confirm();

        confirm_load.getOnMouseClicked();











            String driverclassname = "net.sourceforge.jtds.jdbc.Driver";
            String servername = "localhost";
            int port = 1433;
            String databasename = "DOBI";
            String username = "test";
            String password = "1534";
    /* the actual construction of the URL to be used will change from driver to driver and DB to DB.
       Check the driver specs for the URL format for your driver. */
            String databaseurl = "jdbc:jtds:sqlserver://" + servername + ":" + port;
            databaseurl += "/" + databasename + ";user=" + username + ";password=" + password;
            // here we load the driver. if the driver doesn't load then we quit because everything else is pointless
            try {
                Class.forName(driverclassname);
                System.out.println("Database Driver loaded...");
            } catch (Exception driverLoadEx) {
                System.out.println("ERROR!! - The database driver could not be loaded. Error trace appears below.");
                driverLoadEx.printStackTrace();
                System.exit(0);
            }
            // here we open a connection to the database
            Connection c = null;
            try {
                c = DriverManager.getConnection(databaseurl);
                System.out.println("Connection opened...");
            } catch (SQLException connectEx) {
                System.out.println("ERROR!! - Could not open connection.");
                connectEx.printStackTrace();
                System.exit(0);
            }
            // it is better practice to get into using PreparedStatements over Statements
            PreparedStatement ps = null;

            // USED TO GRAB THE ID FROM THE DATABASE TO SET THE ID NUMBER CORRECTLY IN THE FORM
            try {
                ps = c.prepareStatement("SELECT TOP 1 Cl_ID FROM Client ORDER BY Cl_ID DESC");
                System.out.println("Statement prepared...");
            } catch (SQLException prepareEx) {
                System.out.println("ERROR!! - Could not prepare statement.");
                prepareEx.printStackTrace();
                try {
                    c.close();
                } catch (SQLException cCloseEx) {
                    System.out.println("ERROR!! - In closing connection.");
                    cCloseEx.printStackTrace();
                }
            }
            ResultSet rs = null;
            try {
                rs = ps.executeQuery();
                System.out.println("Query executed...");

                // SETS THE ID NUMBER IN THE FORM TO AN ELEMENT
                if (rs.next()) {
                    int holder = rs.getInt(1);
                    holder += 1;
                    clid.setText(String.valueOf(holder));
                }
            } catch (SQLException rsEx) {
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
            try {
                rs.close();
            } catch (SQLException rsCloseEx) {
                System.out.println("ERROR!! - In closing result set.");
                rsCloseEx.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException psCloseEx) {
                System.out.println("ERROR!! - In closing prepared statement.");
                psCloseEx.printStackTrace();
            }
            // now prepare a new statement to add a new record...
            String fname = firstname.getText();
            String lname = lastname.getText();
            String mname = middlename.getText();
            String com = company.getText();
            String addr = address.getText();
            String Phone = phone.getText();
            String mail = email.getText();
            String zi = zip.getText();

            try {
                ps = c.prepareStatement("INSERT INTO Employee_Type(Cl_Com_Name, Cl_fName, Cl_mini, Cl_lName, Cl_eAddress, Cl_pNum, Cl_wAdd, Cl_wZip, Cl_Type_ID, Cl_St_ID, SP_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                System.out.println("Statement prepared...");
            } catch (SQLException prepareEx) {
                System.out.println("ERROR!! - Could not prepare statement.");
                prepareEx.printStackTrace();
                try {
                    c.close();
                } catch (SQLException cCloseEx) {
                    System.out.println("ERROR!! - In closing connection.");
                    cCloseEx.printStackTrace();
                }
            }
            // now bind the parameters for the above statement and execute it
            try {
                ps.setString(1, com);
                ps.setString(2, fname);
                ps.setString(3, mname);
                ps.setString(4, lname);
                ps.setString(5, mail);
                ps.setString(6, Phone);
                ps.setString(7, addr);
                ps.setString(8, zi);
                ps.setInt(9, 1);
                ps.setInt(10, 1);
                ps.setInt(11, 18);
                ps.executeUpdate();
                System.out.println("Query executed...");
            } catch (SQLException updateEx) {
                System.out.println("ERROR!! - Could not execute update query.");
                updateEx.printStackTrace();
            }

            // Starting a new prepared statement
            try {
                rs.close();
            } catch (SQLException rsCloseEx) {
                System.out.println("ERROR!! - In closing result set.");
                rsCloseEx.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException psCloseEx) {
                System.out.println("ERROR!! - In closing prepared statement.");
                psCloseEx.printStackTrace();
            }


            try {
                ps = c.prepareStatement("DELETE FROM Client WHERE Cl_ID=25");
                System.out.println("Statement prepared...");
            } catch (SQLException prepareEx) {
                System.out.println("ERROR!! - Could not prepare statement.");
                prepareEx.printStackTrace();
                try {
                    c.close();
                } catch (SQLException cCloseEx) {
                    System.out.println("ERROR!! - In closing connection.");
                    cCloseEx.printStackTrace();
                }
            }
            // now bind the parameters for the above statement and execute it
            try {
                ps.executeUpdate();
                System.out.println("Query executed...");
            } catch (SQLException updateEx) {
                System.out.println("ERROR!! - Could not execute update query.");
                updateEx.printStackTrace();
            }


            try {
                c.close();
            } catch (SQLException cCloseEx) {
                System.out.println("ERROR!! - In closing connection.");
                cCloseEx.printStackTrace();
            }
    }





























        //final DbHelper2 app = new DbHelper2();

        /*app.setClassName("net.sourceforge.jtds.jdbc.Driver");
        app.setUsername("test");
        app.setPassword("1534");
        app.setURL("jdbc:jtds:sqlserver://localhost/DOBI");
        ResultSet rss = null;
        app.connect();
        String fname = firstname.getText();
        String lname = lastname.getText();
        String mname = middlename.getText();
        String com = company.getText();
        String addr = address.getText();
        String cit = city.getText();
        String Phone = phone.getText();
        String mail = email.getText();
        String zi = zip.getText();


        /*rss = app.execute("INSERT INTO Client (Cl_Com_Name, Cl_fName, Cl_mini, Cl_lName, Cl_eAddress, Cl_pNum, Cl_wAdd, Cl_wZip, Cl_Type_ID, Cl_St_ID, SP_ID )\n" +
                "VALUES (‘"+com+"’,‘"+ fname + "’, ‘" + mname + "’,‘" + lname + "’,‘" + mail + "’,‘" + Phone + "’,‘" + addr + "’,‘" + zi + "’,‘2’, ‘2’, ’2’);");*/
        /*while (rss.next()) {
            System.out.println(rss.getInt(1));
            System.out.println(" " + rss.getString(2));
        }*/
        //app.disconnect();

    @FXML
    void makeclear(ActionEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources) {

    }

}
