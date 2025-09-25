package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.drivingschool.bo.custom.CourseBO;
import lk.ijse.drivingschool.bo.BOFactory;
import lk.ijse.drivingschool.dto.CourseDTO;
import lk.ijse.drivingschool.tm.CourseTM;
import lk.ijse.drivingschool.tm.StudentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseManageController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<StudentTM, Long> colCourseId;

    @FXML
    private TableColumn<StudentTM, String> colCourseName;

    @FXML
    private TableColumn<StudentTM, String> colDescription;

    @FXML
    private TableColumn<StudentTM, String> colDuration;

    @FXML
    private TableColumn<StudentTM, String> colFee;

    @FXML
    private TableColumn<StudentTM, String> colStatus;

    @FXML
    private TableView<CourseTM> tblCourse;

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

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    void handleClear(ActionEvent event) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDescription.clear();
        txtDuration.clear();
        txtFee.clear();
        cmbStatus.setValue(null);
    }

    @FXML
    void handleDeleteCourse(ActionEvent event) {
        try{
            String courseId = txtCourseId.getText();

            if (courseId.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please select a course to delete.").show();
                return;
            }

            boolean isDeleted = courseBO.deleteCourse(courseId);

            if (isDeleted) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Not Deleted").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error"+e.getMessage()).show();
        }
    }

    @FXML
    void handleSaveCourse(ActionEvent event) throws Exception {
        try {
//            String courseId = txtCourseId.getText();
            String courseName = txtCourseName.getText();
            String description = txtDescription.getText();
            String duration = txtDuration.getText();
            String free = txtFee.getText();
            String status = cmbStatus.getSelectionModel().getSelectedItem().toString();

            if (courseName.isEmpty() || description.isEmpty() || duration.isEmpty() || free.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please Fill the all field");
                return;
            }

            CourseDTO course = new CourseDTO(courseName, duration, Double.parseDouble(free), status, description);

            boolean isSave = courseBO.saveCourse(course);

            if (isSave) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Not Saved").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSearchCourse(ActionEvent event) {

    }

    @FXML
    void handleUpdateCourse(ActionEvent event) throws Exception {
        try {
            String courseId = txtCourseId.getText();
            String courseName = txtCourseName.getText();
            String description = txtDescription.getText();
            String duration = txtDuration.getText();
            String free = txtFee.getText();
            String status = cmbStatus.getSelectionModel().getSelectedItem().toString();

            if (courseId.isEmpty() || courseName.isEmpty() || description.isEmpty() || duration.isEmpty() || free.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please Fill the all field");
                return;
            }

            CourseDTO course = new CourseDTO(courseId,courseName, duration, Double.parseDouble(free), status, description);

            boolean isUpdate = courseBO.updateCourse(course);

            if (isUpdate) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Not Update").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error"+e.getMessage()).show();
        }
    }

    @FXML
    void onTableClick(MouseEvent event) {
        CourseTM selectedItem = (CourseTM) tblCourse.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        txtCourseId.setText(selectedItem.getCourseId());
        txtCourseName.setText(selectedItem.getCourseName());
        txtDescription.setText(selectedItem.getDescription());
        txtDuration.setText(selectedItem.getDuration());
        txtFee.setText(String.valueOf(selectedItem.getFree()));
        cmbStatus.getSelectionModel().select(selectedItem.getStatus());
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        try {
            setCellValueFactory();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }

    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("free"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void loadTable() {
        ArrayList<CourseDTO> courseList = (ArrayList<CourseDTO>) courseBO.getAllCourse();
        ArrayList<CourseTM> courseTMS = new ArrayList<>();
        for (CourseDTO c : courseList) {
            courseTMS.add(new CourseTM(c.getCourseId(), c.getCourseName(), c.getDuration(), c.getFee(), c.getStatus(), c.getDescription()));
        }
        tblCourse.setItems(FXCollections.observableArrayList(courseTMS));
    }
}
