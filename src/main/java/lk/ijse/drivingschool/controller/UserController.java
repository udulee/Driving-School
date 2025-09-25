package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.drivingschool.bo.BOFactory;
import lk.ijse.drivingschool.bo.custom.UserBO;
import lk.ijse.drivingschool.dto.UserDTO;
import lk.ijse.drivingschool.entity.UserRole;
import lk.ijse.drivingschool.tm.UserTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private ImageView backButton;

    @FXML
    private Button btnSave, btnUpdate;

    @FXML
    private TableColumn<UserTM, String> colUserId;
    @FXML
    private TableColumn<UserTM, String> colUserName;
    @FXML
    private TableColumn<UserTM, String> colPassword;
    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TableView<UserTM> tblUsers;

    @FXML
    private TextField txtUserId, txtUserName, txtPassword, txtRole;

    @FXML
    private HBox imagehbox;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    // ---------------------- BUTTON HANDLERS ----------------------

    @FXML
    void backButton(MouseEvent event) {
        // Implement navigation logic here (e.g. switch scene)
        System.out.println("Back button clicked!");
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void handleDeleteUser(ActionEvent event) {
        try {
            String userId = txtUserId.getText();

            if (userId.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please select a user to delete!").show();
                return;
            }

            boolean isDeleted = userBO.deleteUser(userId);
            if (isDeleted) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void handleSaveUser(ActionEvent event) {
        try {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            String roleText = txtRole.getText();

            if (username.isEmpty() || password.isEmpty() || roleText.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill all required fields!").show();
                return;
            }

            // Convert string to UserRole enum safely
            UserRole role;
            try {
                role = UserRole.valueOf(roleText.toUpperCase());
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid role. Use ADMIN or RECEPTIONIST").show();
                return;
            }

            UserDTO userDTO = new UserDTO(username, password, "", role, "ACTIVE");
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void handleUpdateUser(ActionEvent event) {
        try {
            String userId = txtUserId.getText();
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            String roleText = txtRole.getText();

            if (userId.isEmpty() || username.isEmpty() || password.isEmpty() || roleText.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill all fields before updating!").show();
                return;
            }

            UserRole role;
            try {
                role = UserRole.valueOf(roleText.toUpperCase());
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid role. Use ADMIN or RECEPTIONIST").show();
                return;
            }

            UserDTO userDTO = new UserDTO(userId, username, password, "", role, "ACTIVE", null, null);
            boolean isUpdated = userBO.updateUser(userDTO);

            if (isUpdated) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        UserTM selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedUser == null) return;

        txtUserId.setText(selectedUser.getUserId());
        txtUserName.setText(selectedUser.getUsername());
        txtPassword.setText(selectedUser.getPassword());
        txtRole.setText(selectedUser.getRole());
    }

    // ---------------------- INITIALIZATION ----------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadTable();
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadTable() {
        try {
            ArrayList<UserDTO> users = (ArrayList<UserDTO>) userBO.getAllUser();
            ArrayList<UserTM> userTMS = new ArrayList<>();

            for (UserDTO u : users) {
                userTMS.add(new UserTM(
                        u.getUserId(),
                        u.getUsername(),
                        u.getPassword(),
                        u.getEmail(),
                        "", "", // firstName, lastName if you don't use them
                        u.getRole().toString(),
                        u.getStatus(),
                        u.getCreatedDate(),
                        u.getLastLoginDate()
                ));
            }
            tblUsers.setItems(FXCollections.observableArrayList(userTMS));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading users: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();
    }
}
