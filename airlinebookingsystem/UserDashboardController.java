/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinebookingsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Awazi
 */
public class UserDashboardController implements Initializable {

    private Label label;
    @FXML
    private Button bookingbtn;
    @FXML
    private Button manbookbtn;
    @FXML
    private Button checkinbtn;
    @FXML
    private VBox managebookingsvbox;
    @FXML
    private TextField memberidtxt;
    @FXML
    private TextField lnametxt;
    @FXML
    private Button btnsearchticket;
    @FXML
    private VBox checkinvbox;
    @FXML
    private TextField memberidtxt1;
    @FXML
    private TextField fnametxt1;
    @FXML
    private TextField lnametxt1;
    @FXML
    private Button btnsubmit;
    @FXML
    private Button LOGIN;
    public static VBox affinitywingsvbox;
    @FXML
    private AnchorPane mainpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mainpane.setVisible(true);
        checkinvbox.setVisible(false);
        managebookingsvbox.setVisible(false);
        bookingbtn.setOnAction((ActionEvent event) -> {
            try {
                switchWindow("Booking.fxml", "Booking");
                bookingbtn.getScene().getWindow().hide();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        });

        LOGIN.setOnAction((ActionEvent event) -> {
            try {
                switchWindow("Login.fxml", "Login");
                LOGIN.getScene().getWindow().hide();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        });

        manbookbtn.setOnAction(event -> {

        });
        checkinbtn.setOnAction(event -> {

        });
    }

    public void switchWindow(String url, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.getIcons().add(new Image("img/icon1.png"));
        stage.show();
    }
    

    

}
