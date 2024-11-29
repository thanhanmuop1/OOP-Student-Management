package com.example.project_end_term;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentView {
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField studentId;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginStudent() {
        String sql = "SELECT * FROM student WHERE studentId = ? AND status = 'Enrolled'";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentId.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (studentId.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (!result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Student ID not found or not enrolled");
                alert.showAndWait();
            } else if (!password.getText().equals("1")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password");
                alert.showAndWait();
            } else {
                // Load student grade view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("studentGradeView.fxml"));
                Parent root = loader.load();
                StudentGradeViewController controller = loader.getController();
                controller.setStudentId(Integer.parseInt(studentId.getText()));
                controller.displayGrade();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

                loginBtn.getScene().getWindow().hide();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }
}
