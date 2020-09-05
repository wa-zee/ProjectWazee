/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinebookingsystem;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

/**
 * FXML Controller class
 *
 * @author Awazi
 */
public class BookingController implements Initializable {

    @FXML
    private VBox bookingvbox;
    @FXML
    private RadioButton rtriprad;
    @FXML
    private RadioButton onewayrad;
    @FXML
    private ComboBox<String> fromcombo;
    @FXML
    private ComboBox<String> tocombo;
    @FXML
    private DatePicker departdate;
    @FXML
    private ComboBox<Integer> adultcombo;
    @FXML
    private ComboBox<Integer> childcombo;
    @FXML
    private ComboBox<Integer> infantcombo;
    @FXML
    private Button flightsearch;
    @FXML
    private AnchorPane flightdetailspane;
    @FXML
    private Label routelabel;
    @FXML
    private Label flightdatelabel;
    @FXML
    private Button btncontinue;
    @FXML
    private ToggleGroup radiobutton;
    @FXML
    private DatePicker returndate;
    @FXML
    private Button btnreturn;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Button returnbtn;
    @FXML
    private Label departdatetimecity;
    @FXML
    private Label arrivaldatetimecity;
    @FXML
    private Label fareclassselect;
    @FXML
    private Label nooftickets;
    @FXML
    private Label totalpricelabel;
    @FXML
    private TableColumn<FlightView, String> deptcitycol;
    @FXML
    private TableColumn<FlightView, String> arrivalcitycol;
    @FXML
    private TableColumn<FlightView, Date> deptdatecol;
    @FXML
    private TableColumn<FlightView, Time> depttimecol;
    @FXML
    private TableColumn<FlightView, Date> arrivaldatecol;
    @FXML
    private TableColumn<FlightView, Time> arrivaltimecol;
    @FXML
    private TableColumn<FlightView, String> fareclasscol;
    @FXML
    private TableColumn<FlightView, Double> pricecol;
    @FXML
    private TableView<FlightView> treetable;
    @FXML
    private TableView<FlightView> treetable1;
    @FXML
    private TableColumn<FlightView, String> deptcitycol1;
    @FXML
    private TableColumn<FlightView, String> arrivalcitycol1;
    @FXML
    private TableColumn<FlightView, Date> deptdatecol1;
    @FXML
    private TableColumn<FlightView, Time> depttimecol1;
    @FXML
    private TableColumn<FlightView, Time> arrivaldatecol1;
    @FXML
    private TableColumn<FlightView, Time> arrivaltimecol1;
    @FXML
    private TableColumn<FlightView, Time> fareclasscol1;
    @FXML
    private TableColumn<FlightView, Time> pricecol1;
    @FXML
    private HBox infobox;
    @FXML
    private TextField nametxt;
    @FXML
    private TextField snametxt;
    @FXML
    private ComboBox<Integer> daycombo;
    @FXML
    private ComboBox<String> monthcombo;
    @FXML
    private ComboBox<Integer> yearcombo;
    @FXML
    private TextField memberidtxt;
    @FXML
    private TextField phonetxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_continue;
    @FXML
    private Label selectedradio;
    @FXML
    private Label selectednotickets;
    @FXML
    private Label selectedfrom;
    @FXML
    private Label selectedfromtime;
    @FXML
    private Label selectedprice;
    @FXML
    private ComboBox<String> gendercombo;
    @FXML
    private Label passengername;
    @FXML
    private Button reservationbtn;
    @FXML
    private AnchorPane infopane;
    @FXML
    private HBox selectionhbox;
    @FXML
    private Button cardpaymentbtn;
    @FXML
    private Button Directdebitbtn;
    @FXML
    private Button interswitchbtn;
    @FXML
    private HBox selectionhbox1;
    @FXML
    private Label selectedradio1;
    @FXML
    private Label selectednotickets1;
    @FXML
    private Label selectedfrom1;
    @FXML
    private Label selectedfromtime1;
    @FXML
    private Label selectedprice1;
    @FXML
    private VBox flightsummary;
    @FXML
    private Label tickettype;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private VBox informationbox;

    public static ObservableList<String> cities = FXCollections.observableArrayList();
    public static ObservableList<Integer> number = FXCollections.observableArrayList();
    public static ObservableList<String> citiesto = FXCollections.observableArrayList();
    public static ObservableList<FlightView> fview = FXCollections.observableArrayList();
    public static ObservableList<FlightView> fview1 = FXCollections.observableArrayList();
    public static ObservableList<String> months = FXCollections.observableArrayList();
    public static ObservableList<Integer> days = FXCollections.observableArrayList();
    public static ObservableList<Integer> years = FXCollections.observableArrayList();
    public static ObservableList<String> gender = FXCollections.observableArrayList();

    JavaDBC jc = new JavaDBC();
    FlightView fv;
    Alert alert = new Alert(AlertType.NONE);
UserDashboardController uc = new UserDashboardController();
    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        bookingvbox.setVisible(true);
        flightdetailspane.setVisible(false);
        infopane.setVisible(false);
        treetable1.setVisible(false);
        selectionhbox.setVisible(false);
        selectionhbox1.setVisible(false);
        flightsummary.setVisible(false);

        //treetable1.setVisible(false);
        //setting combo values for adults, children and infants**************************
        for (int count = 0; count <= 100; count++) {
            number.add(count);
            adultcombo.setItems(number);
            childcombo.setItems(number);
            infantcombo.setItems(number);
        }

        //populating arrival city from departure city
        fromcombo.setOnAction(event -> {
            populateCombo();
        });

//      setting cities**********************************
        cities.add("Abuja");
        cities.add("Lagos");
        cities.add("Port-Harcourt");
        cities.add("Kaduna");
        cities.add("Jos");
        cities.add("Enugu");
        cities.add("Kano");
        fromcombo.setItems(cities);

        //setting combo for month****
        for (int day = 0; day < 32; day++) {
            days.add(day);
            daycombo.setItems(days);
        }
        //SETTING COMBO FOR MONTHS
        months.addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        monthcombo.setItems(months);
        //SETTING FOR YEAR        
        for (int year = 1910; year < 2020; year++) {
            years.add(year);
            yearcombo.setItems(years);
        }
        //SETTING FOR GENDER
        gender.addAll("Male", "Female");
        gendercombo.setItems(gender);

        //restricting Datepicker       
        DatePicker minDate = new DatePicker();
        minDate.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(minDate.getValue())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #f3f3f3;");
                }
            }
        };
        departdate.setDayCellFactory(dayCellFactory);
        returndate.setDayCellFactory(dayCellFactory);

        //searchflight*************************
        flightsearch.setOnAction((ActionEvent event) -> {
            bookingvbox.setVisible(false);
            flightdetailspane.setVisible(true);
            try {
                if (this.radiobutton.getSelectedToggle().equals(rtriprad)) {
                    treetable.setVisible(true);
                    initTable();
                } else {
                    treetable1.setVisible(true);
                    initTable();
                    initTable1();
                }
            } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
                //JOptionPane.showMessageDialog(null, ex.toString());
            }
            routelabel.setText(fromcombo.getValue() + " to " + tocombo.getValue());
            flightdatelabel.setText(departdate.getValue().toString());
        });

        btnreturn.setOnAction((ActionEvent event) -> {
            try {
                uc.switchWindow("UserDashboard.fxml", "Welocme");
            } catch (IOException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            btnreturn.getScene().getWindow().hide();
        });
        btncontinue.setOnAction(event -> {
            flightdetailspane.setVisible(false);
            infopane.setVisible(true);
        });

        reservationbtn.setOnAction((ActionEvent event) -> {
            try {
                if (this.radiobutton.getSelectedToggle().equals(rtriprad)) {
                    bookTickets();
                    jc.updatePlane();
                    alert.setAlertType(AlertType.INFORMATION);
                    alert.setContentText("Booking Successfull, Pay in 30minutes or the tickets shall be reversed");
                    alert.show();
                } else {
                    bookTickets1();
                    jc.updatePlane();
                    alert.setAlertType(AlertType.INFORMATION);
                    alert.setContentText("Booking Successfull, Pay in 30minutes or the tckets shall be reversed");
                    alert.show();
                }
            } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
                //JOptionPane.showMessageDialog(null, ex.toString());
            }
        });
        btn_back.setOnAction(event -> {
            flightdetailspane.setVisible(true);
            infopane.setVisible(false);
        });
        btn_continue.setOnAction(event -> {
            passengername.setText(nametxt.getText() + " " + snametxt.getText());
            flightsummary.setVisible(true);
            tickettype.setText(selectedradio.getText());
        });

        //return ticket
        returnbtn.setOnAction(event -> {
            flightdetailspane.setVisible(false);
            bookingvbox.setVisible(true);
            fromcombo.setValue(null);
            tocombo.setValue(null);
            citiesto.clear();
            departdate.setValue(null);
            returndate.setValue(null);
            adultcombo.setValue(null);
            childcombo.setValue(null);
            infantcombo.setValue(null);
            fview.clear();
            fview1.clear();
            treetable.setVisible(false);
            treetable1.setVisible(false);

            //citiesto.removeAll();
        });

    }

    @FXML
    public void selectToggleGroup() {
        if (this.radiobutton.getSelectedToggle().equals(rtriprad)) {
            returndate.setDisable(true);
        } else if (this.radiobutton.getSelectedToggle().equals(onewayrad)) {
            returndate.setDisable(false);
        }
    }
    
    public void populateCombo() {
        String from = fromcombo.getValue();
        Route route = new Route();
        route.setDeparture_city(from);
        jc.populateCombo(route);
        tocombo.setItems(citiesto);
    }

//    public void regCustomer() throws ClassNotFoundException, SQLException {
//        String phone, name, sname, gend, mail;
//        name = nametxt.getText();
//        sname = snametxt.getText();
//        gend = gendercombo.getValue();
//        phone = phonetxt.getText();
//        mail = emailtxt.getText();
//        Customer cust = new Customer(name, sname, phone, gend, mail);
//        jc.regMember(cust);
//    }
    public void viewFlight() throws SQLException, ClassNotFoundException {
        String from = fromcombo.getValue();
        String to = tocombo.getValue();
        Date deptdate = Date.valueOf(departdate.getValue());
        Integer num1 = adultcombo.getValue();
        Integer num2 = childcombo.getValue();
        Integer notickets = num1 + num2;

        fv = new FlightView();
        fv.setDeparture_city(from);
        fv.setDestination_city(to);
        fv.setDeparture_date(deptdate);
        fv.setTickets(notickets);
        JavaDBC.searchFlight(fv, fview);
        treetable.setItems(fview);
    }

    public void returnTicketFlight() throws SQLException, ClassNotFoundException {
        String to = fromcombo.getValue();
        String from = tocombo.getValue();
        Date retdate = Date.valueOf(returndate.getValue());
        Integer num1 = adultcombo.getValue();
        Integer num2 = childcombo.getValue();
        Integer notickets = num1 + num2;
        fv = new FlightView();
        fv.setDeparture_city(from);
        fv.setDestination_city(to);
        fv.setDeparture_date(retdate);
        fv.setTickets(notickets);
        JavaDBC.searchFlight(fv, fview1);
        treetable1.setItems(fview1);
    }

    public void bookTickets() throws SQLException, ClassNotFoundException, NullPointerException {
        String cname = passengername.getText();
        String from = fromcombo.getValue();
        String to = tocombo.getValue();
        Date deptdate = Date.valueOf(departdate.getValue());
        Date retdate = null;
        //retdate = Date.valueOf(returndate.getValue());
        String fareclass = fareclassselect.getText();

        Booking bk = new Booking(cname, from, to, deptdate, retdate, fareclass);
        jc.bookTickets(bk);
    }

    public void bookTickets1() throws SQLException, ClassNotFoundException, NullPointerException {
        String cname = passengername.getText();
        String to = fromcombo.getValue();
        String from = tocombo.getValue();
        Date deptdate = Date.valueOf(departdate.getValue());
        Date retdate = Date.valueOf(returndate.getValue());
        String fareclass = fareclassselect.getText();

        Booking bk = new Booking(cname, to, from, deptdate, retdate, fareclass);
        jc.bookTickets(bk);
    }

    public void initTable() throws SQLException, ClassNotFoundException {
        //one way ticket
//one way cols
        deptcitycol.setCellValueFactory(new PropertyValueFactory<>("departure_city"));
        arrivalcitycol.setCellValueFactory(new PropertyValueFactory<>("destination_city"));
        deptdatecol.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
        depttimecol.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
        arrivaldatecol.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
        arrivaltimecol.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
        fareclasscol.setCellValueFactory(new PropertyValueFactory<>("fare_class"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        treetable.setRowFactory(tv -> {
            TableRow<FlightView> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty() && adultcombo != null && childcombo != null && infantcombo != null) {
                    FlightView rowData = row.getItem();
                    String dcity = rowData.getDeparture_city();
                    selectedfrom.setText(dcity);
                    Time dt = rowData.getDeparture_time();
                    selectedfromtime.setText(dt.toString());
                    Date ddate = rowData.getDeparture_date();
                    String acity = rowData.getDestination_city();
                    Date adate = rowData.getArrival_date();
                    Time at = rowData.getArrival_time();
                    Double pri = rowData.getPrice();
                    Integer num1 = adultcombo.getValue();
                    Integer num2 = childcombo.getValue();
                    Integer notickets = num1 + num2;
                    Double pric = pri * notickets;
                    selectedprice.setText(pric.toString());
                    selectednotickets.setText(notickets.toString());
                    if (radiobutton.getSelectedToggle().isSelected()) {
                        selectedradio.setText("One Way");
                    } else {
                        selectedradio.setText("Return Trip");
                    }
                    departdatetimecity.setText(dcity + ": " + ddate + ": " + dt);
                    arrivaldatetimecity.setText(acity + ": " + adate + ": " + at);
                    fareclassselect.setText(rowData.getFare_class());
                    nooftickets.setText(notickets.toString());
                    totalpricelabel.setText(pric.toString());
                    selectionhbox.setVisible(true);
                }
            });
            return row;
        });
        viewFlight();
    }

    public void initTable1() throws SQLException, ClassNotFoundException {
        //return ticket col
        treetable1.setVisible(true);
        deptcitycol1.setCellValueFactory(new PropertyValueFactory<>("departure_city"));
        arrivalcitycol1.setCellValueFactory(new PropertyValueFactory<>("destination_city"));
        deptdatecol1.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
        depttimecol1.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
        arrivaldatecol1.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
        arrivaltimecol1.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
        fareclasscol1.setCellValueFactory(new PropertyValueFactory<>("fare_class"));
        pricecol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        selectionhbox1.setVisible(false);
        treetable1.setRowFactory(tv -> {
            TableRow<FlightView> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty() && adultcombo != null && childcombo != null && infantcombo != null) {
                    FlightView rowData = row.getItem();
                    String dcity = rowData.getDeparture_city();
                    selectedfrom1.setText(dcity);
                    Time dt = rowData.getDeparture_time();
                    selectedfromtime1.setText(dt.toString());
                    Date ddate = rowData.getDeparture_date();
                    String acity = rowData.getDestination_city();
                    Date adate = rowData.getArrival_date();
                    Time at = rowData.getArrival_time();
                    Integer num1 = adultcombo.getValue();
                    Integer num2 = childcombo.getValue();
                    Integer notickets = num1 + num2;
                    Double pri = rowData.getPrice();
                    Double pric = pri * notickets;
                    selectedprice1.setText(pric.toString());
                    selectednotickets1.setText(notickets.toString());
                    if (radiobutton.getSelectedToggle().isSelected()) {
                        selectedradio.setText("One Way");
                    } else {
                        selectedradio.setText("Return Trip");
                    }
//                    departdatetimecity.setText(dcity + ": " + ddate + ": " + dt);
//                    arrivaldatetimecity.setText(acity + ": " + adate + ": " + at);
//                    fareclassselect.setText(rowData.getFare_class());
//                    nooftickets.setText(notickets.toString());
//                    totalpricelabel.setText(pric.toString());
                    selectionhbox1.setVisible(true);
                }
            });
            return row;
        });
        returnTicketFlight();
    }

//    public void duplicateNode() {
//        Integer num1 = adultcombo.getValue();
//        Integer num2 = childcombo.getValue();
//        Integer notickets = num1 + num2;
//        ScrollPane scroll = new ScrollPane(scrollpane);
//        for (int tickets = 0; tickets < notickets; tickets++) {
//            VBox vbox = new VBox(informationbox);
//            scroll.getContent().accessibleHelpProperty();
//        }
//    }
}
