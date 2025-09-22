package lk.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CourseManageController {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbStatus;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<?> tblCourse;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDeleteCourse(ActionEvent event) {

    }

    @FXML
    void handleSaveCourse(ActionEvent event) {

    }

    @FXML
    void handleSearchCourse(ActionEvent event) {

    }

    @FXML
    void handleUpdateCourse(ActionEvent event) {

    }

    @FXML
    void onTableClick(MouseEvent event) {

    }

}
