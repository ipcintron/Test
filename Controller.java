package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXButton signup;


    @FXML
    void makeLogin(ActionEvent event) {
        String username = user.getText();
        String pass = password.getText();
        if(username.equals("Isaac")&&pass.equals("coder")){
            System.out.println("Welcome!");
        }
        else if(username.equals("Damneek")&&pass.equals("monkey")){
            System.out.println("Welcome!");
        }
        else
            System.out.println("Wrong Password" + username + " is not in the system");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
