/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinebookingsystem;

import includes.FormValidation;
import includes.JavaDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author Awazi
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginpane;
    @FXML
    private TextField loginemail;
    @FXML
    private ComboBox<String> loginrole;
    @FXML
    private PasswordField loginpass;
    @FXML
    private Hyperlink registerlink;
    @FXML
    private Button btnlogin;
    @FXML
    private TextField regfname;
    @FXML
    private TextField reglname;
    @FXML
    private TextField regemail;
    @FXML
    private TextField regphone;
    @FXML
    private TextField regaddress;
    @FXML
    private PasswordField regpassword;
    @FXML
    private Button btnreg;
    @FXML
    private Button btnreturn;
    @FXML
    private VBox regvbox;
    @FXML
    private VBox affinitywingsvbox;
    @FXML
    private TextField memberidtxt11;
    @FXML
    private TextField fnametxt11;
    @FXML
    private TextField lnametxt11;
    @FXML
    private Button btnsubmit1;
    @FXML
    private ComboBox<String> regrole;
    @FXML
    private Hyperlink forgotpassword;
    @FXML
    private VBox changepasswordvbox;
    @FXML
    private TextField changemail;
    @FXML
    private PasswordField newpassword;
    @FXML
    private Button submitnewpassword;
    @FXML
    private Label valfnametxt;
    @FXML
    private Label vallnametxt;
    @FXML
    private Label valemailtxt;
    @FXML
    private Label valphonetxt;
    @FXML
    private Label valpasswordtxt;
    @FXML
    private Label valaddresstxt;
    @FXML
    private Button affinityback;

    public static ObservableList<String> roles = FXCollections.observableArrayList();
    JavaDBC jc = new JavaDBC();
    Alert alert = new Alert(Alert.AlertType.NONE);
    UserDashboardController uc = new UserDashboardController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginpane.setVisible(true);
        regvbox.setVisible(false);
        changepasswordvbox.setVisible(false);
        affinitywingsvbox.setVisible(false);

//        setting roles with observables.********************************************
        roles.add("Admin");
        roles.add("User");
        loginrole.setItems(roles);
        regrole.setItems(roles);

//        //register new user****************************************************************************************
        registerlink.setOnAction((ActionEvent event) -> {
            regvbox.setVisible(true);
            loginpane.setVisible(false);
        });

        regfname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!regfname.getText().matches("[a-zA-Z]+")) {
                    regfname.setText("");
                    valfnametxt.setText("Name can contain only alphabets");
                } else  {
                    valfnametxt.setText("");
                }
            }
        });
        
        reglname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!reglname.getText().matches("[a-zA-Z]+")) {
                    reglname.setText("");
                    vallnametxt.setText("Name can contain only alphabets");
                } else  {
                    vallnametxt.setText("");
                }
            }
        });
        
        regemail.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!regemail.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
        +"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]+$")) {
                    regemail.setText("");
                    valemailtxt.setText("Email must be in the format abc@xyz.com");
                } else  {
                    valemailtxt.setText("");
                }
            }
        });        
       
        regphone.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!regphone.getText().matches("^[0-9]{11}+$")) {
                    regphone.setText("");
                    valphonetxt.setText("Phone must be numeric with 11 characters");
                } else  {
                    valphonetxt.setText("");
                }
            }
        });
        btnreg.setOnAction((ActionEvent event) -> {
//            boolean fname = FormValidation.textAlphabets(regfname, valfnametxt, "Name can contain only alphabets");
//            boolean sname = FormValidation.textAlphabets(reglname, vallnametxt, "Name can contain only alphabets");
//            boolean email = FormValidation.emailFormat(regemail, valemailtxt, "Email must be in the format abc@xyz.com");
//            boolean fone = FormValidation.phoneFormat(regphone, valphonetxt, "Phone must be numeric with 11 characters");
//            boolean add = FormValidation.nullFieldValidation(regaddress, valaddresstxt, "field cannot be empty");
//            boolean pass = FormValidation.nullFieldValidation(regpassword, valpasswordtxt, "field cannot be empty");
//            // formValidation();
//            if (fname == true && sname == true && email == true && fone == true && add == true && pass == true) {
                try {
                    regAdmin();
                    JOptionPane.showMessageDialog(null, "Registration Successful");
                    regvbox.setVisible(false);
                    loginpane.setVisible(true);
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            //}
        });

        btnreturn.setOnAction((ActionEvent event) -> {
            regvbox.setVisible(false);
            loginpane.setVisible(true);
        });

        btnlogin.setOnAction(event -> {
            try {
                userLogin();
            } catch (IOException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        });

        forgotpassword.setOnAction(event -> {
//        forgot password***************************
            changepasswordvbox.setVisible(true);
            loginpane.setVisible(false);
        });
        submitnewpassword.setOnAction(event -> {
            try {
                changePassword();
            } catch (IOException | ClassNotFoundException | SQLException e) {
            }
        });
        affinityback.setOnAction(event -> {
            affinitywingsvbox.setVisible(false);
            loginpane.setVisible(true);

        });
    }

//    user registration*************************
    public void regAdmin() throws ClassNotFoundException, SQLException {

        String name = regfname.getText();
        String sname = reglname.getText();
        String mail = regemail.getText();
        String rol = regrole.getValue();
        String phone = regphone.getText();
        String add = regaddress.getText();
        String regpas = regpassword.getText();
        Users users = new Users(name, sname, mail, rol, phone, add, regpas);

        jc.regNewUser(users);

    }

//    user login************************
    public void userLogin() throws IOException, SQLException, ClassNotFoundException {

        String logemail = loginemail.getText();
        String logrole = loginrole.getValue();
        String logpas = loginpass.getText();

        Users users = new Users();
        users.setEmail(logemail);
        users.setRole(logrole);
        users.setPassword(logpas);

        ResultSet rs = jc.getUser(users);

        int counter = 0;
        while (rs.next()) {
            counter++;
            String name = rs.getString("fname");
//            alert.setAlertType(Alert.AlertType.INFORMATION);
//            alert.setContentText("Welcome " + name);
//            alert.show();
            JOptionPane.showMessageDialog(null, "Welcome " + name);
        }
        if (counter == 1 && "Admin".equals(logrole)) {
            uc.switchWindow("AdminDashboard.fxml", "AdminDashboard");
            //switchToAdminWindow();
            btnlogin.getScene().getWindow().hide();
        } else if (counter == 1 && "User".equals(logrole)) {
            affinitywingsvbox.setVisible(true);
            loginpane.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry wrong username or password");
        }
    }

//change password******************************************************
    public void changePassword() throws IOException, SQLException, ClassNotFoundException {
        String newmail = changemail.getText();
        String newpass = newpassword.getText();

        Users user = new Users(newmail, newpass);

        jc.changePassword(user);
    }

//    public void formValidation() {
//        boolean fname = FormValidation.textAlphabets(regfname, valfnametxt, "Name can contain only alphabets");
//        boolean sname = FormValidation.textAlphabets(reglname, vallnametxt, "Name can contain only alphabets");
//        boolean email = FormValidation.emailFormat(fnametxt11, valemailtxt, "Email must be in the format abc@xyz.com");
//        boolean fone = FormValidation.phoneFormat(fnametxt11, valphonetxt, "Phone must be numeric with 11 characters");
//        boolean add = FormValidation.nullFieldValidation(regaddress, valaddresstxt, "field cannot be empty");
//        boolean pass = FormValidation.nullFieldValidation(regpassword, valpasswordtxt, "field cannot be empty");
//    }
}
