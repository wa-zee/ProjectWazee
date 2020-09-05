/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinebookingsystem;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import includes.JavaDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author Awazi
 */
//UserDashboardController uc =  new UserDashboardController();
public class AdminDashboardController implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private HBox viewusers;
    @FXML
    private HBox insertschedule;
    @FXML
    private HBox flightdetails;
    @FXML
    private HBox viewbookings;
    @FXML
    private HBox viewroute;
    @FXML
    private TableView<Users> usertable;
    @FXML
    private TableColumn<Users, String> idcol;
    @FXML
    private TableColumn<Users, String> fnamecol;
    @FXML
    private TableColumn<Users, String> lnamecol;
    @FXML
    private TableColumn<Users, String> rolecol;
    @FXML
    private TableColumn<Users, String> phonecol;
    @FXML
    private TableColumn<Users, String> addresscol;
    @FXML
    private TableColumn<Users, String> passwordcol;
    @FXML
    private TableColumn<Users, String> emailcol;
    @FXML
    private VBox tablevbox;
    @FXML
    private HBox back;
    @FXML
    private TableColumn<Users, Button> deletecol;
    @FXML
    private VBox insertschedvbox;
    @FXML
    private JFXDatePicker departdate;
    @FXML
    private JFXTimePicker departtime;
    @FXML
    private JFXDatePicker arrivedate;
    @FXML
    private JFXTimePicker arrivetime;
    @FXML
    private Button scheduleinsert;
    @FXML
    private Button scheduleupdate;
    @FXML
    private Button scheduledelete;
    @FXML
    private JFXTextField scheduleidtxt;
    @FXML
    private TableView<Booking> bookingstable;
    @FXML
    private TableColumn<Booking, Integer> bookingidcol;
    @FXML
    private TableColumn<Booking, String> custnamecol;
    @FXML
    private TableColumn<Booking, String> deptcitycol1;
    @FXML
    private TableColumn<Booking, LocalDate> deptdatecol1;
    @FXML
    private TableColumn<Booking, String> destcitycol1;
    @FXML
    private TableColumn<Booking, LocalDate> returndatecol;
    @FXML
    private TableColumn<Booking, String> fareclasscol;
    @FXML
    private VBox viewbookingsvbox;
//    private TableView<Route> routtable;
    @FXML
    private TableColumn<Route, Integer> routeidcol;
    @FXML
    private TableColumn<Route, String> deptcitycol;
    @FXML
    private TableColumn<Route, String> destcitycol;
    @FXML
    private VBox regvbox;
    @FXML
    private TextField regfname;
    @FXML
    private Label valfnametxt;
    @FXML
    private TextField reglname;
    @FXML
    private Label vallnametxt;
    @FXML
    private TextField regemail;
    @FXML
    private Label valemailtxt;
    @FXML
    private ComboBox<String> regrole;
    @FXML
    private TextField regphone;
    @FXML
    private Label valphonetxt;
    @FXML
    private TextField regaddress;
    @FXML
    private PasswordField regpassword;
    @FXML
    private Label valpasswordtxt;
    @FXML
    private Button btnreg;
    @FXML
    private Label valfnametxt1;
    @FXML
    private TableColumn<Schedule, Integer> schedidcol;
    @FXML
    private TableColumn<Schedule, Date> scheddeptdatecol;
    @FXML
    private TableColumn<Schedule, Time> scheddeparttimecol;
    @FXML
    private TableColumn<Schedule, Date> schedarrivedatecol;
    @FXML
    private TableColumn<Schedule, Time> schedarrivetimecol;
    @FXML
    private TableView<Schedule> schedtable;
@FXML
    private TextField useridtxt;
    @FXML
    private Button deleteuser;
    @FXML
    private AnchorPane schedulepane;
    @FXML
    private TableView<Route> routetable;
    @FXML
    private VBox routetablevbox;

    public static ObservableList<Users> table = FXCollections.observableArrayList();
    public static ObservableList<Booking> bookingtable = FXCollections.observableArrayList();
    public static ObservableList<Route> routtable = FXCollections.observableArrayList();
    public static ObservableList<String> role = FXCollections.observableArrayList();
    public static ObservableList<Schedule> scheduletable = FXCollections.observableArrayList();
    LoginController lc = new LoginController();
    UserDashboardController uc = new UserDashboardController();
    JavaDBC jc = new JavaDBC();
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.addAll("Admin", "Staff");
        regrole.setItems(role);
        tablevbox.setVisible(false);
        insertschedvbox.setVisible(false);
        bookingstable.setVisible(false);
        routetable.setVisible(false);
        regvbox.setVisible(false);
        schedtable.setVisible(false);
        schedulepane.setVisible(false);
//      Users table view***********************************************************************************
        viewusers.setOnMouseClicked((MouseEvent event) -> {
            tablevbox.setVisible(true);
            insertschedvbox.setVisible(false);
            regvbox.setVisible(true);
            bookingstable.setVisible(false);
            routetablevbox.setVisible(false);
            schedtable.setVisible(false);
            schedulepane.setVisible(false);
            usertable.setVisible(true);
            try {
                initTable();
                JavaDBC.viewUsers();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            usertable.setItems(table);
        });
        btnreg.setOnAction(e -> {
            try {
                addUser();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        insertschedule.setOnMouseClicked((MouseEvent event) -> {
            tablevbox.setVisible(false);
            bookingstable.setVisible(false);
            routetablevbox.setVisible(false);
            regvbox.setVisible(false);
            schedtable.setVisible(true);
            schedulepane.setVisible(true);
            schedidcol.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
            scheddeptdatecol.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
            scheddeparttimecol.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
            schedarrivedatecol.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
            schedarrivetimecol.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
            schedtable.setItems(scheduletable);
            insertschedvbox.setVisible(true);
            try {
                JavaDBC.viewSchedule();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        scheduledelete.setOnAction(event -> {
            try {
                delSched();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        scheduleinsert.setOnAction(event -> {
            insertSchedule();
        });
        flightdetails.setOnMouseClicked(event -> {

        });
        deleteuser.setOnAction(event -> {
            try {
                delUser();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showConfirmDialog(null, "User Deleted");
        });

        viewbookings.setOnMouseClicked(event -> {
            tablevbox.setVisible(false);
            insertschedvbox.setVisible(false);
            routetablevbox.setVisible(false);
            regvbox.setVisible(false);
            schedtable.setVisible(false);
            schedulepane.setVisible(false);
            viewbookingsvbox.setVisible(true);
            bookingstable.setVisible(true);
            bookingidcol.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
            custnamecol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            deptcitycol1.setCellValueFactory(new PropertyValueFactory<>("departure_city"));
            destcitycol1.setCellValueFactory(new PropertyValueFactory<>("destination_city"));
            deptdatecol1.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
            returndatecol.setCellValueFactory(new PropertyValueFactory<>("return_date"));
            fareclasscol.setCellValueFactory(new PropertyValueFactory<>("fare_class"));
            try {
                JavaDBC.viewBookings();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
            bookingstable.setItems(bookingtable);
        });

        viewroute.setOnMouseClicked(event -> {
            tablevbox.setVisible(false);
            bookingstable.setVisible(false);
            regvbox.setVisible(false);
            schedtable.setVisible(false);
            schedulepane.setVisible(false);
            routetablevbox.setVisible(true);
            tablevbox.setVisible(false);
            routeidcol.setCellValueFactory(new PropertyValueFactory<>("route_id"));
            deptcitycol.setCellValueFactory(new PropertyValueFactory<>("departure_city"));
            destcitycol.setCellValueFactory(new PropertyValueFactory<>("destination_city"));

            try {
                JavaDBC.viewRoutes();
            } catch (ClassNotFoundException | SQLException e) {
            }
            routetable.setItems(routtable);
        });

        back.setOnMouseClicked(event -> {
            try {
                uc.switchWindow("UserDashboard.fxml", "Welcome");
            } catch (IOException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            back.getScene().getWindow().hide();
        });

    }

    public void insertSchedule() {
        //Integer route = Integer.parseInt(routeidtxt.getText());
        Date ddate = Date.valueOf(departdate.getValue());
        Time dtime = Time.valueOf(departtime.getValue());
        Date adate = Date.valueOf(arrivedate.getValue());
        Time atime = Time.valueOf(arrivetime.getValue());

        Schedule sched = new Schedule(ddate, dtime, adate, atime);
        JavaDBC.insertSchedule(sched);
    }

    //EDITABLE TABLES
    public void initTable() {
        initCols();
    }

    public void initCols() {
        insertschedvbox.setVisible(false);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnamecol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnamecol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));
        deletecol.setCellValueFactory(new PropertyValueFactory<>("delete"));

    }

    public void addUser() throws ClassNotFoundException, SQLException {
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

    public void delUser() throws SQLException, ClassNotFoundException {
        Integer id = Integer.parseInt(useridtxt.getText());
        Users user = new Users(id);
        jc.deleteUser(user);
    }

    public void delSched() throws SQLException, ClassNotFoundException {
        Integer id = Integer.parseInt(scheduleidtxt.getText());
        Schedule sched = new Schedule(id);
        jc.deleteSched(sched);
    }

}
