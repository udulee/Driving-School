module lk.ijse.drivingschool {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.drivingschool to javafx.fxml;
    exports lk.ijse.drivingschool;
}