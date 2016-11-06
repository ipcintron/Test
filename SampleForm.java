package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleForm implements Initializable {

    @FXML
    private JFXButton AddEmployeeType;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXPasswordField EmployeeType;

    @FXML
    void AddEmployeeType(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
