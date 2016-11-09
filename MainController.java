package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable{


    private static int COUNT = 0;


    @FXML
    private Button newEmployee;

    @FXML
    private Label lblSum;

    @FXML
    private Button problem2Button;

    /**
     * Prints all the employees in the database to the console
     * @param actionEvent
     */


    // Answer to #1

    public void printEmployees(ActionEvent actionEvent) throws SQLException {
        //ObservableList<Employee_Type> list = FXCollections.observableArrayList();

        final DbHelper2 app = new DbHelper2();


        /*app.setClassName("net.sourceforge.jtds.jdbc.Driver");
        app.setUsername("test");
        app.setPassword("1534");
        app.setURL("jdbc:jtds:sqlserver://localhost/DOBI");
        String rss = null;
        app.connect();
        rss = app.execute("SELECT * FROM Employee_Type");
        while (rss.next()) {
            System.out.println(rss.getInt(1));
            System.out.println(" " + rss.getString(2));
        }
            app.disconnect();*/


            // Establishes a connection to the database.
            //Connection conn = DbHelper.getInstance().getConnection();

            // Creates a statement object.
            //Statement statement = conn.createStatement();

            // A query which will show all records from the manager table.
            //String query = "select * from Employee_Type";

            // Makes a the ResultSet "rs" equal to the query from the previous line of code.
            //ResultSet rs = statement.executeQuery(query);


            // The while loop will cycle through the ResultSet "rs" and add "name" and "salary" to the top of the ResultSet.
        /*while (rs.next()) {
            System.out.println(rs.getString());
        }*/

            // Displays the data in dataTable.
            //dataTable.setItems(list);
            //System.out.println(query);

            // Closes the result set.
            //rs.close();

            // Terminates the connection to the database.
            //conn.close();


        }


    // Answer to #2
    public void solved(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource()==problem2Button){
            problem2Button.setText("Problem 2 Solved");
        }
    }

    // Answer to #3

    public void newEmployees(ActionEvent actionEvent) throws SQLException {
        Connection conn = DbHelper.getInstance().getConnection();

        // Inserts data into name and salary columns in the employee table.
        String employeesInsert = "insert into employee(name, salary) values(?,?)";
        PreparedStatement statement = conn.prepareStatement(employeesInsert);

        // Creates a variable name
        String name = "Harry";

        double salary = 100.00;

        // This inputs the name Bill into the "name" column.
        statement.setString(1,name);

        // This sets values to 100 in the "salary" column.
        statement.setFloat(2, (float) salary);


        // Executes the statement object.
        statement.execute();

        System.out.println("The employee, " + name + ", was added and his/her salary is $" + salary);

    }


    // Answer to #4
    public void calculateSum(ActionEvent actionEvent) throws SQLException {
        int sum = 0;
        for(int i = 1; i < 100; i++){

            if (i % 3 == 0){
                sum = sum + i;
                lblSum.setText(String.valueOf(sum));
            }

        }
    }




    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openInstructions(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().open(new File("Exam Review.pdf"));
    }

}
