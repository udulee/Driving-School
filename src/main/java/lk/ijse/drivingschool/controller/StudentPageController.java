package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.drivingschool.BO.custom.StudentBO;
import lk.ijse.drivingschool.BO.custom.impl.StudentBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class StudentPageController {

    private final StudentBO studentBO = new StudentBOImpl();
    public Button btnBackToDashboard;

    @FXML
    private Button btnClear, btnDelete, btnSave, btnUpdate;

    @FXML
    private TableColumn<StudentDTO, String> colStudentId, colName, colEmail, colPhone, colAddress, colRegisterFee, colRegDate;

    @FXML
    private DatePicker dpRegistrationDate;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TextField txtStudentId, txtName, txtEmail, txtPhone, txtAddress, txtRegisterFee;

    @FXML
    public void initialize() throws SQLException {
        setCellValueFactory();

        loadTable();
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRegisterFee.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }


    private void loadTable() throws SQLException {
        ArrayList<StudentDTO> studentList = (ArrayList<StudentDTO>) studentBO.getAllStudent();
        ObservableList<StudentDTO> data = FXCollections.observableArrayList(studentList);
        System.out.println(data);
        tblStudent.setItems(data);
    }

    @FXML
    void handleSaveStudent(ActionEvent event) throws SQLException {
        System.out.println("sdfghj");
        try {
            StudentDTO student = new StudentDTO(
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    dpRegistrationDate.getValue().toString()


            );

            if (studentBO.save(student)) {
                loadTable();
//                setNextId();
                new Alert(Alert.AlertType.INFORMATION, "Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Saving Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdateStudent(ActionEvent event) throws SQLException {
        try {
            StudentDTO student = new StudentDTO(
                    Long.parseLong(txtStudentId.getText()),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    dpRegistrationDate.getValue().toString()
            );

            if (studentBO.update(student)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDeleteStudent(ActionEvent event) throws Exception {
        String id = txtStudentId.getText();
        if (id == null || id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a student to delete.").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete student with ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (studentBO.delete(id)) {
                    loadTable();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully!").show();



                } else {
                    new Alert(Alert.AlertType.ERROR, "Deleting Failed!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtRegisterFee.clear();
        dpRegistrationDate.setValue(null);
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        StudentDTO selected = tblStudent.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtStudentId.setText(String.valueOf(selected.getStudentId()));
            txtName.setText(selected.getName());
            txtEmail.setText(selected.getEmail());
            txtPhone.setText(selected.getPhone());
            txtAddress.setText(selected.getAddress());
            txtRegisterFee.setText(selected.getRegisterFee());
            dpRegistrationDate.setValue(LocalDate.parse(selected.getRegistrationDate()));
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}