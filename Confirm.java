package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Confirm implements Initializable {

    @FXML
    private Text confirmText;

    @FXML
    private JFXButton yesButton;

    @FXML
    private JFXButton noButton;

    @FXML
    boolean submitChanges() {
        return true;
    }

    @FXML
    boolean closeWindow(ActionEvent event) {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

