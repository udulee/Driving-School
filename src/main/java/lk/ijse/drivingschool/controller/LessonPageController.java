package lk.ijse.drivingschool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.drivingschool.dto.LessonDTO;
import lk.ijse.drivingschool.tm.LessonTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPageController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<LessonTM, String> colCourseId;

    @FXML
    private TableColumn<LessonTM, String> colDate;

    @FXML
    private TableColumn<LessonTM, String> colInstructorId;

    @FXML
    private TableColumn<LessonTM, String> colLessonId;

    @FXML
    private TableColumn<LessonTM, String> colStatus;

    @FXML
    private TableColumn<LessonTM, String> colStudentId;

    @FXML
    private TableColumn<LessonTM, String> colTime;

    @FXML
    private TableView<LessonTM> tblLesson;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtLessonId;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtTime;

    private ObservableList<LessonTM> lessonList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellValueFactory();

        loadLessonsToTable();

        generateNextLessonId();
    }

    private void setCellValueFactory() {
        colLessonId.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
    }

    private void loadLessonsToTable() {

        lessonList.clear();
        List<LessonDTO> lessons = getAllLessons();

        for (LessonDTO lessonDTO : lessons) {
            LessonTM lessonTM = new LessonTM(
                    lessonDTO.getLessonId(),
                    lessonDTO.getDate(),
                    lessonDTO.getTime(),
                    lessonDTO.getStatus(),
                    lessonDTO.getStudentId(),
                    lessonDTO.getCourseId(),
                    lessonDTO.getInstructorId()
            );
            lessonList.add(lessonTM);
        }

        tblLesson.setItems(lessonList);
    }

    private List<LessonDTO> getAllLessons() {

        List<LessonDTO> lessons = new ArrayList<>();
        lessons.add(new LessonDTO("L001", "2024-01-15", "09:00", "Scheduled", "S001", "C001", "I001"));
        lessons.add(new LessonDTO("L002", "2024-01-16", "10:30", "Completed", "S002", "C002", "I002"));
        return lessons;
    }

    private void generateNextLessonId() {
        // Generate next lesson ID (L003, L004, etc.)
        int nextId = lessonList.size() + 1;
        String lessonId = String.format("L%03d", nextId);
        txtLessonId.setText(lessonId);
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        LessonTM selectedLesson = tblLesson.getSelectionModel().getSelectedItem();
        if (selectedLesson != null) {
            populateFields(selectedLesson);
        }
    }

    private void populateFields(LessonTM lesson) {
        txtLessonId.setText(lesson.getLessonId());
        txtDate.setText(lesson.getDate());
        txtTime.setText(lesson.getTime());
        txtStatus.setText(lesson.getStatus());
        txtStudentId.setText(lesson.getStudentId());
        txtCourseId.setText(lesson.getCourseId());
        txtInstructorId.setText(lesson.getInstructorId());
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
        generateNextLessonId();
    }

    private void clearFields() {
        txtLessonId.clear();
        txtDate.clear();
        txtTime.clear();
        txtStatus.clear();
        txtStudentId.clear();
        txtCourseId.clear();
        txtInstructorId.clear();

        // Clear table selection
        tblLesson.getSelectionModel().clearSelection();
    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        LessonTM selectedLesson = tblLesson.getSelectionModel().getSelectedItem();

        if (selectedLesson == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a lesson to delete.");
            return;
        }


        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete Confirmation");
        confirmation.setHeaderText("Delete Lesson");
        confirmation.setContentText("Are you sure you want to delete lesson: " + selectedLesson.getLessonId() + "?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            try {
                // Delete from database
                boolean deleted = deleteLesson(selectedLesson.getLessonId());

                if (deleted) {
                    lessonList.remove(selectedLesson);
                    clearFields();
                    generateNextLessonId();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Lesson deleted successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete lesson.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleSavePayment(ActionEvent event) {
        if (!validateFields()) {
            return;
        }

        try {
            LessonDTO lessonDTO = new LessonDTO(
                    txtLessonId.getText(),
                    txtDate.getText(),
                    txtTime.getText(),
                    txtStatus.getText(),
                    txtStudentId.getText(),
                    txtCourseId.getText(),
                    txtInstructorId.getText()
            );

            // Save to database
            boolean saved = saveLesson(lessonDTO);

            if (saved) {
                // Add to table
                LessonTM lessonTM = new LessonTM(
                        lessonDTO.getLessonId(),
                        lessonDTO.getDate(),
                        lessonDTO.getTime(),
                        lessonDTO.getStatus(),
                        lessonDTO.getStudentId(),
                        lessonDTO.getCourseId(),
                        lessonDTO.getInstructorId()
                );

                lessonList.add(lessonTM);
                clearFields();
                generateNextLessonId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Lesson saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save lesson.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while saving: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {
        LessonTM selectedLesson = tblLesson.getSelectionModel().getSelectedItem();

        if (selectedLesson == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a lesson to update.");
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            LessonDTO lessonDTO = new LessonDTO(
                    txtLessonId.getText(),
                    txtDate.getText(),
                    txtTime.getText(),
                    txtStatus.getText(),
                    txtStudentId.getText(),
                    txtCourseId.getText(),
                    txtInstructorId.getText()
            );

            // Update in database
            boolean updated = updateLesson(lessonDTO);

            if (updated) {
                // Update table
                selectedLesson.setDate(txtDate.getText());
                selectedLesson.setTime(txtTime.getText());
                selectedLesson.setStatus(txtStatus.getText());
                selectedLesson.setStudentId(txtStudentId.getText());
                selectedLesson.setCourseId(txtCourseId.getText());
                selectedLesson.setInstructorId(txtInstructorId.getText());

                tblLesson.refresh();
                clearFields();
                generateNextLessonId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Lesson updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update lesson.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (txtDate.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Date cannot be empty.");
            return false;
        }
        if (txtTime.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Time cannot be empty.");
            return false;
        }
        if (txtStatus.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Status cannot be empty.");
            return false;
        }
        if (txtStudentId.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Student ID cannot be empty.");
            return false;
        }
        if (txtCourseId.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Course ID cannot be empty.");
            return false;
        }
        if (txtInstructorId.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Instructor ID cannot be empty.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean saveLesson(LessonDTO lessonDTO) {
        return true;
    }

    private boolean updateLesson(LessonDTO lessonDTO) {
        return true;
    }

    private boolean deleteLesson(String lessonId) {
        return true;
    }
}