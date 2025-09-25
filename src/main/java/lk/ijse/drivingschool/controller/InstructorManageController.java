package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.drivingschool.dto.InstructorDTO;
import lk.ijse.drivingschool.tm.InstructorTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InstructorManageController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbSpecialization;

    @FXML
    private TableColumn<InstructorTM, String> colAddress;

    @FXML
    private TableColumn<InstructorTM, String> colDOB;

    @FXML
    private TableColumn<InstructorTM, String> colEmail;

    @FXML
    private TableColumn<InstructorTM, Integer> colExperience;

    @FXML
    private TableColumn<InstructorTM, String> colFirstName;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorId;

    @FXML
    private TableColumn<InstructorTM, String> colLastName;

    @FXML
    private TableColumn<InstructorTM, String> colPhone;

    @FXML
    private TableColumn<InstructorTM, String> colSpecialization;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtExperience;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    private ObservableList<InstructorTM> instructorList = FXCollections.observableArrayList();


    private List<InstructorDTO> instructorDatabase = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        setupComboBox();
        loadTableData();
        generateNextId();
    }

    private void setupTableColumns() {
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colExperience.setCellValueFactory(new PropertyValueFactory<>("experienceYears"));
    }

    private void setupComboBox() {
        ObservableList<String> specializations = FXCollections.observableArrayList(
                "Manual Transmission",
                "Automatic Transmission",
                "Motorcycle",
                "Heavy Vehicle",
                "Defensive Driving"
        );
        cmbSpecialization.setItems(specializations);
    }

    private void loadTableData() {
        instructorList.clear();
        for (InstructorDTO dto : instructorDatabase) {
            InstructorTM tm = new InstructorTM(
                    dto.getInstructorId(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    dto.getPhone(),
                    dto.getAddress(),
                    dto.getDateOfBirth(),
                    dto.getSpecialization(),
                    dto.getExperienceYears()
            );
            instructorList.add(tm);
        }
        tblInstructor.setItems(instructorList);
    }

    private void generateNextId() {
        txtInstructorId.setText("INS" + String.format("%03d", nextId));
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        InstructorTM selectedInstructor = tblInstructor.getSelectionModel().getSelectedItem();
        if (selectedInstructor != null) {
            populateFields(selectedInstructor);
        }
    }

    private void populateFields(InstructorTM instructor) {
        txtInstructorId.setText(instructor.getInstructorId());
        txtFirstName.setText(instructor.getFirstName());
        txtLastName.setText(instructor.getLastName());
        txtEmail.setText(instructor.getEmail());
        txtPhone.setText(instructor.getPhone());
        txtAddress.setText(instructor.getAddress());
        txtExperience.setText(String.valueOf(instructor.getExperienceYears()));
        cmbSpecialization.setValue(instructor.getSpecialization());

        // Parse date string and set to DatePicker
        if (instructor.getDateOfBirth() != null && !instructor.getDateOfBirth().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(instructor.getDateOfBirth());
                datePicker.setValue(date);
            } catch (Exception e) {
                datePicker.setValue(null);
            }
        }
    }

    @FXML
    void handleSaveInstructor(ActionEvent event) {
        try {
            if (validateInput()) {
                InstructorDTO instructor = createInstructorFromInput();

                // Check if instructor ID already exists
                boolean exists = instructorDatabase.stream()
                        .anyMatch(i -> i.getInstructorId().equals(instructor.getInstructorId()));

                if (exists) {
                    showAlert(Alert.AlertType.WARNING, "Duplicate ID", "Instructor ID already exists!");
                    return;
                }

                instructorDatabase.add(instructor);
                loadTableData();
                clearFields();
                nextId++;
                generateNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor saved successfully!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save instructor: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateInstructor(ActionEvent event) {
        try {
            if (validateInput()) {
                String instructorId = txtInstructorId.getText();

                Optional<InstructorDTO> existingInstructor = instructorDatabase.stream()
                        .filter(i -> i.getInstructorId().equals(instructorId))
                        .findFirst();

                if (existingInstructor.isPresent()) {
                    InstructorDTO updatedInstructor = createInstructorFromInput();

                    // Remove old and add updated
                    instructorDatabase.remove(existingInstructor.get());
                    instructorDatabase.add(updatedInstructor);

                    loadTableData();
                    clearFields();
                    generateNextId();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor updated successfully!");
                } else {
                    showAlert(Alert.AlertType.WARNING, "Not Found", "Instructor not found!");
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update instructor: " + e.getMessage());
        }
    }

    @FXML
    void handleDeleteInstructor(ActionEvent event) {
        InstructorTM selectedInstructor = tblInstructor.getSelectionModel().getSelectedItem();

        if (selectedInstructor == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an instructor to delete!");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Instructor");
        confirmAlert.setContentText("Are you sure you want to delete this instructor?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String instructorId = selectedInstructor.getInstructorId();
            instructorDatabase.removeIf(i -> i.getInstructorId().equals(instructorId));
            loadTableData();
            clearFields();
            generateNextId();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor deleted successfully!");
        }
    }

    @FXML
    void handleSearchInstructor(ActionEvent event) {
        String searchId = txtInstructorId.getText().trim();

        if (searchId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Search", "Please enter an Instructor ID to search!");
            return;
        }

        Optional<InstructorDTO> found = instructorDatabase.stream()
                .filter(i -> i.getInstructorId().equalsIgnoreCase(searchId))
                .findFirst();

        if (found.isPresent()) {
            InstructorDTO instructor = found.get();
            InstructorTM tm = new InstructorTM(
                    instructor.getInstructorId(),
                    instructor.getFirstName(),
                    instructor.getLastName(),
                    instructor.getEmail(),
                    instructor.getPhone(),
                    instructor.getAddress(),
                    instructor.getDateOfBirth(),
                    instructor.getSpecialization(),
                    instructor.getExperienceYears()
            );
            populateFields(tm);

            // Select item in table
            tblInstructor.getSelectionModel().select(tm);
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No instructor found with ID: " + searchId);
            clearFields();
            generateNextId();
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
        generateNextId();
        tblInstructor.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        if (txtInstructorId.getText().trim().isEmpty()) {
            errors.append("Instructor ID is required.\n");
        }
        if (txtFirstName.getText().trim().isEmpty()) {
            errors.append("First Name is required.\n");
        }
        if (txtLastName.getText().trim().isEmpty()) {
            errors.append("Last Name is required.\n");
        }
        if (txtEmail.getText().trim().isEmpty()) {
            errors.append("Email is required.\n");
        } else if (!isValidEmail(txtEmail.getText().trim())) {
            errors.append("Please enter a valid email address.\n");
        }
        if (txtPhone.getText().trim().isEmpty()) {
            errors.append("Phone number is required.\n");
        }
        if (txtAddress.getText().trim().isEmpty()) {
            errors.append("Address is required.\n");
        }
        if (datePicker.getValue() == null) {
            errors.append("Date of Birth is required.\n");
        }
        if (cmbSpecialization.getValue() == null) {
            errors.append("Specialization is required.\n");
        }
        if (txtExperience.getText().trim().isEmpty()) {
            errors.append("Experience is required.\n");
        } else {
            try {
                int experience = Integer.parseInt(txtExperience.getText().trim());
                if (experience < 0) {
                    errors.append("Experience cannot be negative.\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Please enter a valid number for experience.\n");
            }
        }

        if (errors.length() > 0) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", errors.toString());
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    }

    private InstructorDTO createInstructorFromInput() {
        return new InstructorDTO(
                txtInstructorId.getText().trim(),
                txtFirstName.getText().trim(),
                txtLastName.getText().trim(),
                txtEmail.getText().trim(),
                txtPhone.getText().trim(),
                txtAddress.getText().trim(),
                datePicker.getValue().toString(),
                cmbSpecialization.getValue(),
                Integer.parseInt(txtExperience.getText().trim())
        );
    }

    private void clearFields() {
        txtInstructorId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtExperience.clear();
        datePicker.setValue(null);
        cmbSpecialization.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}