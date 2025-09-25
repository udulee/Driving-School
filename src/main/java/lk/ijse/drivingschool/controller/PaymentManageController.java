package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.drivingschool.dto.PaymentDTO;
import lk.ijse.drivingschool.tm.PaymentTM;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentManageController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private ComboBox<String> cmbPaymentStatus;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, String> colCourseId;

    @FXML
    private TableColumn<PaymentTM, String> colNotes;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentDate;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentMethod;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentStatus;

    @FXML
    private TableColumn<PaymentTM, String> colReferenceNo;

    @FXML
    private TableColumn<PaymentTM, String> colStudentId;

    @FXML
    private DatePicker datePickerPayment;

    @FXML
    private TableView<PaymentTM> tblPayment;

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

    private ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        setupComboBoxes();
        loadPaymentData();
        generateNextPaymentId();
    }

    private void setupTableColumns() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        colReferenceNo.setCellValueFactory(new PropertyValueFactory<>("referenceNo"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        colAmount.setCellFactory(column -> new TableCell<PaymentTM, Double>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                if (empty || amount == null) {
                    setText("");
                } else {
                    setText(String.format("%.2f LKR", amount));
                }
            }
        });

        tblPayment.setItems(paymentList);
    }

    private void setupComboBoxes() {
        cmbPaymentMethod.setItems(FXCollections.observableArrayList(
                "Cash", "Credit Card", "Debit Card", "Bank Transfer", "Online Payment", "Cheque"
        ));

        cmbPaymentStatus.setItems(FXCollections.observableArrayList("Completed", "Pending", "Failed", "Refunded", "Partial"));

        cmbPaymentMethod.setValue("Cash");
        cmbPaymentStatus.setValue("Completed");
    }

    private void loadPaymentData() {
        paymentList.clear();

        paymentList.add(new PaymentTM("PAY001", "STU001", "CRS001", 15000.00,
                "2024-01-15", "Cash", "Completed", "REF001", "Initial payment"));
        paymentList.add(new PaymentTM("PAY002", "STU002", "CRS002", 12000.00,
                "2024-01-16", "Credit Card", "Completed", "REF002", "Full payment"));
    }

    private void generateNextPaymentId() {
        String nextId = "PAY" + String.format("%03d", paymentList.size() + 1);
        txtPaymentId.setText(nextId);
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            populateFields(selectedPayment);
        }
    }

    private void populateFields(PaymentTM payment) {
        txtPaymentId.setText(payment.getPaymentId());
        txtStudentId.setText(payment.getStudentId());
        txtCourseId.setText(payment.getCourseId());
        txtAmount.setText(String.valueOf(payment.getAmount()));
        txtReferenceNo.setText(payment.getReferenceNo());
        txtNotes.setText(payment.getNotes());

        try {
            LocalDate date = LocalDate.parse(payment.getPaymentDate(), dateFormatter);
            datePickerPayment.setValue(date);
        } catch (Exception e) {
            datePickerPayment.setValue(LocalDate.now());
        }

        cmbPaymentMethod.setValue(payment.getPaymentMethod());
        cmbPaymentStatus.setValue(payment.getPaymentStatus());

        btnUpdate.setDisable(false);
        btnSave.setDisable(true);
    }

    @FXML
    void handleSavePayment(ActionEvent event) {
        if (validateInputs()) {
            try {
                PaymentTM newPayment = createPaymentFromInputs();
                paymentList.add(newPayment);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment saved successfully!");
                clearFields();
                generateNextPaymentId();

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save payment: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {
        PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a payment to update.");
            return;
        }

        if (validateInputs()) {
            try {
                updatePaymentFromInputs(selectedPayment);
                tblPayment.refresh();


                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment updated successfully!");
                clearFields();
                generateNextPaymentId();

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update payment: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a payment to delete.");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete Payment");
        confirmAlert.setContentText("Are you sure you want to delete payment ID: " + selectedPayment.getPaymentId() + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                paymentList.remove(selectedPayment);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment deleted successfully!");
                clearFields();
                generateNextPaymentId();

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete payment: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleSearchPayment(ActionEvent event) {
        String searchId = txtPaymentId.getText().trim();
        if (searchId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter a Payment ID to search.");
            return;
        }

        PaymentTM foundPayment = paymentList.stream()
                .filter(payment -> payment.getPaymentId().equals(searchId))
                .findFirst()
                .orElse(null);

        if (foundPayment != null) {
            tblPayment.getSelectionModel().select(foundPayment);
            tblPayment.scrollTo(foundPayment);
            populateFields(foundPayment);
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No payment found with ID: " + searchId);
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
        generateNextPaymentId();
        tblPayment.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
    }

    @FXML
    void handleGenerateReceipt(ActionEvent event) {
        PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a payment to generate receipt.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Receipt",
                "Receipt generated for Payment ID: " + selectedPayment.getPaymentId() +
                        "\nAmount: " + String.format("%.2f LKR", selectedPayment.getAmount()));
    }

    private boolean validateInputs() {
        StringBuilder errors = new StringBuilder();

        if (txtStudentId.getText().trim().isEmpty()) {
            errors.append("Student ID is required.\n");
        }

        if (txtCourseId.getText().trim().isEmpty()) {
            errors.append("Course ID is required.\n");
        }

        if (txtAmount.getText().trim().isEmpty()) {
            errors.append("Amount is required.\n");
        } else {
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim());
                if (amount <= 0) {
                    errors.append("Amount must be greater than zero.\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Invalid amount format.\n");
            }
        }

        if (datePickerPayment.getValue() == null) {
            errors.append("Payment date is required.\n");
        }

        if (cmbPaymentMethod.getValue() == null) {
            errors.append("Payment method is required.\n");
        }

        if (cmbPaymentStatus.getValue() == null) {
            errors.append("Payment status is required.\n");
        }

        if (errors.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", errors.toString());
            return false;
        }

        return true;
    }

    private PaymentTM createPaymentFromInputs() {
        return new PaymentTM(
                txtPaymentId.getText().trim(),
                txtStudentId.getText().trim(),
                txtCourseId.getText().trim(),
                Double.parseDouble(txtAmount.getText().trim()),
                datePickerPayment.getValue().format(dateFormatter),
                cmbPaymentMethod.getValue(),
                cmbPaymentStatus.getValue(),
                txtReferenceNo.getText().trim(),
                txtNotes.getText().trim()
        );
    }

    private void updatePaymentFromInputs(PaymentTM payment) {
        payment.setStudentId(txtStudentId.getText().trim());
        payment.setCourseId(txtCourseId.getText().trim());
        payment.setAmount(Double.parseDouble(txtAmount.getText().trim()));
        payment.setPaymentDate(datePickerPayment.getValue().format(dateFormatter));
        payment.setPaymentMethod(cmbPaymentMethod.getValue());
        payment.setPaymentStatus(cmbPaymentStatus.getValue());
        payment.setReferenceNo(txtReferenceNo.getText().trim());
        payment.setNotes(txtNotes.getText().trim());
    }

    private PaymentDTO convertToDTO(PaymentTM paymentTM) {
        return new PaymentDTO(
                paymentTM.getPaymentId(),
                paymentTM.getStudentId(),
                paymentTM.getCourseId(),
                paymentTM.getAmount(),
                paymentTM.getPaymentDate(),
                paymentTM.getPaymentMethod(),
                paymentTM.getPaymentStatus(),
                paymentTM.getReferenceNo(),
                paymentTM.getNotes()
        );
    }

    private void clearFields() {
        txtStudentId.clear();
        txtCourseId.clear();
        txtAmount.clear();
        txtReferenceNo.clear();
        txtNotes.clear();
        datePickerPayment.setValue(LocalDate.now());
        cmbPaymentMethod.setValue("Cash");
        cmbPaymentStatus.setValue("Completed");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}