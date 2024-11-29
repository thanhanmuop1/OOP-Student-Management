package com.example.project_end_term;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentGradeViewController {
    @FXML
    private TableView<studentData> gradeTable;
    @FXML
    private TableColumn<studentData, String> yearCol;
    @FXML
    private TableColumn<studentData, String> courseCol;
    @FXML
    private TableColumn<studentData, Double> firstSemCol;
    @FXML
    private TableColumn<studentData, Double> secondSemCol;
    @FXML
    private TableColumn<studentData, Double> finalCol;
    @FXML
    private Label studentInfo;
    @FXML
    private ImageView studentImage;

    private int studentId;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void displayGrade() {
        try {
            connect = database.connectDb();
            
            // Get student info including image
            String studentQuery = "SELECT firstName, lastName, image FROM student WHERE studentId = ?";
            prepare = connect.prepareStatement(studentQuery);
            prepare.setInt(1, studentId);
            result = prepare.executeQuery();
            
            if (result.next()) {
                studentInfo.setText("Student: " + result.getString("firstName") + " " + 
                                  result.getString("lastName") + " (ID: " + studentId + ")");
                
                // Display student image
                String imagePath = result.getString("image");
                if (imagePath != null && !imagePath.isEmpty()) {
                    try {
                        Image image = new Image("file:" + imagePath);
                        studentImage.setImage(image);
                    } catch (Exception e) {
                        // If image loading fails, set a default image
                        Image defaultImage = new Image(getClass().getResourceAsStream("/com/example/project_end_term/images/default-avatar.png"));
                        studentImage.setImage(defaultImage);
                    }
                } else {
                    // Set default image if no image path is stored
                    Image defaultImage = new Image(getClass().getResourceAsStream("/com/example/project_end_term/images/default-avatar.png"));
                    studentImage.setImage(defaultImage);
                }
            }

            // Get grades
            String gradeQuery = "SELECT * FROM student_grade WHERE studentId = ?";
            prepare = connect.prepareStatement(gradeQuery);
            prepare.setInt(1, studentId);
            result = prepare.executeQuery();

            ObservableList<studentData> gradeList = FXCollections.observableArrayList();
            
            while (result.next()) {
                gradeList.add(new studentData(
                    studentId,
                    result.getString("year"),
                    result.getString("course"),
                    result.getDouble("first_sem"),
                    result.getDouble("second_sem"),
                    result.getDouble("final")
                ));
            }

            yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
            courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
            firstSemCol.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
            secondSemCol.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
            finalCol.setCellValueFactory(new PropertyValueFactory<>("finals"));

            gradeTable.setItems(gradeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("studentView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            gradeTable.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }
} 