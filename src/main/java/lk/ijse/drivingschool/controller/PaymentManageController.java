package lk.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentManageController {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbPaymentMethod;

    @FXML
    private ComboBox<?> cmbPaymentStatus;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colNotes;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private TableColumn<?, ?> colReferenceNo;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker datePickerPayment;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextArea txtNotes;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtReferenceNo;

    @FXML
    private TextField txtStudentId;

    @FXML
    void OnTableClicked(MouseEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {

    }

    @FXML
    void handleGenerateReceipt(ActionEvent event) {

    }

    @FXML
    void handleSavePayment(ActionEvent event) {

    }

    @FXML
    void handleSearchPayment(ActionEvent event) {

    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {

    }

}
