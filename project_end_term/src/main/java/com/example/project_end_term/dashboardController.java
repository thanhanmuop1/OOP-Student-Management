package com.example.project_end_term;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class dashboardController implements Initializable {

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private DatePicker addStudents_birthDate;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<?, ?> addStudents_col_birthDate;

    @FXML
    private TableColumn<?, ?> addStudents_col_course;

    @FXML
    private TableColumn<?, ?> addStudents_col_firstName;

    @FXML
    private TableColumn<?, ?> addStudents_col_gender;

    @FXML
    private TableColumn<?, ?> addStudents_col_lastName;

    @FXML
    private TableColumn<?, ?> addStudents_col_status;

    @FXML
    private TableColumn<?, ?> addStudents_col_studentId;

    @FXML
    private TableColumn<?, ?> addStudents_col_year;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<String> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<String> addStudents_status;

    @FXML
    private TextField addStudents_studentId;

    @FXML
    private TableView<studentData> addStudents_tableView;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private ComboBox<String> addStudents_year;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private TableColumn<?, ?> availableCourse_col_course;

    @FXML
    private TableColumn<?, ?> availableCourse_col_degree;

    @FXML
    private TableColumn<?, ?> availableCourse_col_description;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TableView<courseData> availableCourse_tableView;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button availableCourses_btn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<String, Number> home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<String, Number> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<String, Number> home_totalMaleChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_final;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_course;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_studentId;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_search;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private TextField studentGrade_studentId;

    @FXML
    private TableView<studentData> studentGrade_tableView;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Label studentGrade_year;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;



    //home btn
    //homeDisplayTotalEnrolledStudents
    public void homeDisplayTotalEnrolledStudents() {
        String totalEnrolled = "SELECT COUNT(*) FROM student";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(totalEnrolled);
            result = prepare.executeQuery();

            if (result.next()) {
                home_totalEnrolled.setText(result.getString("COUNT(*)"));
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //homeDisplayTotalFemaleEnrolled
    public void homeDisplayTotalFemaleEnrolled() {
        String totalFemale = "SELECT COUNT(*) FROM student WHERE gender = 'Female' AND status = 'Enrolled'";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(totalFemale);
            result = prepare.executeQuery();

            if (result.next()) {
                home_totalFemale.setText(result.getString("COUNT(*)"));
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //homeDisplayTotalMaleEnrolled
    public void homeDisplayTotalMaleEnrolled() {
        String totalMale = "SELECT COUNT(*) FROM student WHERE gender = 'Male' AND status = 'Enrolled'";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(totalMale);
            result = prepare.executeQuery();

            if (result.next()) {
                home_totalMale.setText(result.getString("COUNT(*)"));
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //homeDisplayTotalEnrolledChart 
    public void homeDisplayTotalEnrolledChart() {
        home_totalEnrolledChart.getData().clear();

        String totalEnrolledChart = "SELECT date, COUNT(*) FROM student WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect = database.connectDb();

        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<String, Number>();
            prepare = connect.prepareStatement(totalEnrolledChart);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<String, Number>(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);
        } catch (Exception e) {e.printStackTrace();}
    }

    //homeDisplayFemaleEnrolledChart
    public void homeDisplayFemaleEnrolledChart() {
        home_totalFemaleChart.getData().clear();

        String femaleEnrolledChart = "SELECT date, COUNT(*) FROM student WHERE gender = 'Female' AND status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect = database.connectDb();

        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<String, Number>();
            prepare = connect.prepareStatement(femaleEnrolledChart);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<String, Number>(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);
        } catch (Exception e) {e.printStackTrace();}
    }

    //homeDisplayMaleEnrolledChart
    public void homeDisplayMaleEnrolledChart() {
        home_totalMaleChart.getData().clear();

        String maleEnrolledChart = "SELECT date, COUNT(*) FROM student WHERE gender = 'Male' AND status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect = database.connectDb();

        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<String, Number>();
            prepare = connect.prepareStatement(maleEnrolledChart);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<String, Number>(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);
        } catch (Exception e) {e.printStackTrace();}
    }



    //add student btn
    // dropdown list
    //add student year list
    private String[] yearList = {"First year", "Second year", "Third year", "Fourth year"};

    public void addStudentsYearList() {
        List<String> yearL = new ArrayList<>();

        for(String data: yearList) {
            yearL.add(data);
        }

        ObservableList<String> ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);
    }

    //add student gender list
    private String[] genderList = {"Male", "Female", "Others"};

    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList<String> ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }

    //add student status list
    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList<String> ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }

    //add student ô course list lấy từ database để hiển thị trong addStudents_course
    public void addStudentsCourseList() {
        String listCourse = "SELECT * FROM course";
        connect = database.connectDb();

        try {
            ObservableList<String> listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);
        } catch (Exception e) {e.printStackTrace();}
    }

    //add student insert image
    public void addStudentsInsertImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            getPathImg.path = file.getAbsolutePath();
        }
    }

    //add student to database
    public void addStudentsAdd() {
        String insertData = "INSERT INTO student (studentId, year, course, firstName, lastName, gender, birth, status, image, date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        connect = database.connectDb();

        try {
            Alert alert;
            //check blank field
            if (addStudents_studentId.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthDate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getPathImg.path == null || getPathImg.path.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                //check studentId already exist
                String checkData = "SELECT studentId FROM student WHERE studentId = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, addStudents_studentId.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student ID: " + addStudents_studentId.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addStudents_studentId.getText());
                    prepare.setString(2, addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setDate(7, Date.valueOf(addStudents_birthDate.getValue().toString()));
                    prepare.setString(8, addStudents_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, getPathImg.path);
                    prepare.setDate(10, new Date(new java.util.Date().getTime()));

                    prepare.executeUpdate();

                    //insert student_grade data from add_student form
                    String insertStudentGrade = "INSERT INTO student_grade (studentId, year, course, first_sem, second_sem, final) VALUES(?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertStudentGrade);
                    prepare.setString(1, addStudents_studentId.getText());
                    prepare.setString(2, addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, "0");
                    prepare.setString(5, "0");
                    prepare.setString(6, "0");

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    //update table
                    addStudentsShowListData();
                    //clear text-field
                    addStudentsClear();
                }
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    //add student update information to database
    public void addStudentsUpdate() {
        String updateData = "UPDATE student SET year = ?, course = ?, firstName = ?, lastName = ?, gender = ?, birth = ?, status = ?, image = ?, date = ? WHERE studentId = ?";
        connect = database.connectDb();

        try {
            Alert alert;

            if (addStudents_studentId.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthDate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE StudentId: " + addStudents_studentId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(2, addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addStudents_firstName.getText());
                    prepare.setString(4, addStudents_lastName.getText());
                    prepare.setString(5, addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setDate(6, Date.valueOf(addStudents_birthDate.getValue().toString()));
                    prepare.setString(7, addStudents_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, getPathImg.path);
                    prepare.setDate(9, new Date(new java.util.Date().getTime()));
                    prepare.setString(10, addStudents_studentId.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    addStudentsClear();
                }
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    //add student delete information from database
    public void addStudentsDelete() {
        String deleteData = "DELETE FROM student WHERE studentId = ?";
        connect = database.connectDb();

        try {
            Alert alert;

            if (addStudents_studentId.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a student to delete");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE StudentId: " + addStudents_studentId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, addStudents_studentId.getText());

                    prepare.executeUpdate();


                    String checkData = "SELECT studentId FROM student_grade WHERE studentId = ?";
                    prepare = connect.prepareStatement(checkData);  
                    prepare.setString(1, addStudents_studentId.getText());
                    result = prepare.executeQuery();

                    if (result.next()) {
                        String deleteGrade = "DELETE FROM student_grade WHERE studentId = ?";
                        prepare = connect.prepareStatement(deleteGrade);
                        prepare.setString(1, addStudents_studentId.getText());

                        prepare.executeUpdate();
                    }

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    addStudentsClear();
                }

            }

        } catch (Exception e) {e.printStackTrace();}
    }

    //add student clear field
    public void addStudentsClear() {
        addStudents_studentId.setText("");  
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birthDate.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);
        getPathImg.path = null;
    }

    //add student get list data from database
    public ObservableList<studentData> addStudentsListData() {
        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        connect = database.connectDb();

        try {
            studentData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentId"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("birth"),
                        result.getString("status"),
                        result.getString("image"));

                listStudents.add(studentD);
            }
        } catch (Exception e) {e.printStackTrace();}
        return listStudents;
    }

    //add student show list data to display in TableView
    private ObservableList<studentData> addStudentsListD;
    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);
    }

    //add student select item to display in text-field
    public void addStudentsSelect() {
        studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if((num -1) < -1) {return;}

        //display in text-field
        addStudents_studentId.setText(String.valueOf(studentD.getStudentId()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birthDate.setValue(studentD.getBirth().toLocalDate());

        //display image
        String url = "file:" + studentD.getImage();
        image = new Image(url, 94, 134, false, true);
        addStudents_imageView.setImage(image);

        getPathImg.path = studentD.getImage();
    }

    //add student search
    public void addStudentsSearch() {
        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD, e -> true); //e -> true: hiển thị tất cả d liệu khi không có gì để tìm kiếm

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateStudentData.getStudentId()).toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }




    //available course btn
    //available course list data
    public ObservableList<courseData> availableCourseListData() {
        ObservableList<courseData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";

        connect = database.connectDb();

        try {
            courseData courseD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                courseD = new courseData(result.getString("course"),
                        result.getString("description"),
                        result.getString("degree"));

                listData.add(courseD);
            }
        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }

    private ObservableList<courseData> availableCourseList;

    //available course show list data
    public void availableCourseShowListData() {
        availableCourseList = availableCourseListData();

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        availableCourse_tableView.setItems(availableCourseList);

    }

    //available course select
    public void availableCourseSelect() {
        courseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableCourse_course.setText(courseD.getCourse());
        availableCourse_description.setText(courseD.getDescription());
        availableCourse_degree.setText(courseD.getDegree());

    }


    //available course add
    public void availableCourseAdd() {

        String insertData = "INSERT INTO course (course,description,degree) VALUES(?,?,?)";
        connect = database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                //check course already exist
                String checkData = "SELECT course FROM course WHERE course = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, availableCourse_course.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Course: " + availableCourse_course.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, availableCourse_course.getText());
                    prepare.setString(2, availableCourse_description.getText());
                    prepare.setString(3, availableCourse_degree.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // update table
                    availableCourseShowListData();
                    // clear text-field
                    availableCourseClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //available course update
    public void availableCourseUpdate() {
        String updateData = "UPDATE course SET description = ?, degree = ? WHERE course = ?";
        connect = database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, availableCourse_description.getText());
                    prepare.setString(2, availableCourse_degree.getText());
                    prepare.setString(3, availableCourse_course.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // To update our table view once the data we gave was successful
                    availableCourseShowListData();
                    // To clear the text fields
                    availableCourseClear();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //available course delete
    public void availableCourseDelete() {
        String deleteData = "DELETE FROM course WHERE course = ?";
        connect = database.connectDb();

        try {
            Alert alert;

            if (availableCourse_course.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a course to delete");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, availableCourse_course.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // To update our table view once the data we gave was successful
                    availableCourseShowListData();
                    // To clear the text fields
                    availableCourseClear();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //available course clear
    public void availableCourseClear() {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
    }



    //student grade btn
    //student grade show list data
    public ObservableList<studentData> studentGradeListData() {
        ObservableList<studentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_grade";
        connect = database.connectDb();

        try {
            studentData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentId"),
                                            result.getString("year"),
                                            result.getString("course"),
                                            result.getDouble("first_sem"),
                                            result.getDouble("second_sem"),
                                            result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }

    private ObservableList<studentData> studentGradeList;
    public void studentGradeShowListData() {
        studentGradeList = studentGradeListData();

        studentGrade_col_studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));

        studentGrade_tableView.setItems(studentGradeList);
    }

    //student grade update
    public void studentGradeUpdate() {
        if (studentGrade_studentId.getText().isEmpty()
                || studentGrade_year.getText().isEmpty()
                || studentGrade_course.getText().isEmpty()
                || studentGrade_firstSem.getText().isEmpty() 
                || studentGrade_secondSem.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        try {
            connect = database.connectDb();
            
            // Validate student exists
            String checkData = "SELECT * FROM student_grade WHERE studentId = ?";
            prepare = connect.prepareStatement(checkData);
            prepare.setString(1, studentGrade_studentId.getText());
            result = prepare.executeQuery();

            if (!result.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Student ID not found");
                alert.showAndWait();
                return;
            }

            // Calculate final grade
            double firstSem = Double.parseDouble(studentGrade_firstSem.getText());
            double secondSem = Double.parseDouble(studentGrade_secondSem.getText());
            double finalResult = (firstSem == 0 || secondSem == 0) ? 0 : (firstSem + secondSem) / 2;

            // Confirm update
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Message");
            confirm.setHeaderText(null);
            confirm.setContentText("Are you sure you want to UPDATE Student " + studentGrade_studentId.getText() + "?");
            Optional<ButtonType> option = confirm.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String updateData = "UPDATE student_grade SET year = ?, course = ?, first_sem = ?, second_sem = ?, final = ? WHERE studentId = ?";
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, studentGrade_year.getText());
                prepare.setString(2, studentGrade_course.getText());
                prepare.setDouble(3, firstSem);
                prepare.setDouble(4, secondSem); 
                prepare.setDouble(5, finalResult);
                prepare.setString(6, studentGrade_studentId.getText());

                prepare.executeUpdate();

                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Information Message");
                success.setHeaderText(null);
                success.setContentText("Successfully Updated!");
                success.showAndWait();

                studentGradeShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //student grade clear
    public void studentGradeClear() {
        studentGrade_studentId.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
    }

    //student grade select
    public void studentGradeSelect() {
        studentData studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {return;}

        studentGrade_studentId.setText(String.valueOf(studentD.getStudentId()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourse());
        studentGrade_firstSem.setText(String.valueOf(studentD.getFirstSem()));
        studentGrade_secondSem.setText(String.valueOf(studentD.getSecondSem()));
    }

    //student grade search
    public void studentGradeSearch() {
        FilteredList<studentData> filter = new FilteredList<>(studentGradeList, e -> true);
        
        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateStudentData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if (String.valueOf(predicateStudentData.getStudentId()).contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateStudentData.getFirstSem()).contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateStudentData.getSecondSem()).contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateStudentData.getFinals()).contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        
        SortedList<studentData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);
    }



    private double x = 0;
    private double y = 0;

    //logout
    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure logout?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    stage.setOpacity(0.8f);
                });

                root.setOnMouseReleased(_ -> {
                    stage.setOpacity(1.0f);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //switch form
    public void switchForm (ActionEvent event) {
        if(event.getSource() == home_btn){
            home_form.setVisible(true);
            availableCourse_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayTotalFemaleEnrolled();
            homeDisplayTotalMaleEnrolled();

            homeDisplayTotalEnrolledChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayMaleEnrolledChart();

        }
        else if(event.getSource() == availableCourses_btn){
            home_form.setVisible(false);
            availableCourse_form.setVisible(true);
            addStudents_form.setVisible(false);
            studentGrade_form.setVisible(false);

            availableCourses_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

            availableCourseShowListData();

        }
        else if(event.getSource() == addStudents_btn){
            home_form.setVisible(false);
            availableCourse_form.setVisible(false);
            addStudents_form.setVisible(true);
            studentGrade_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsCourseList();
            addStudentsSearch();
        }
        else if(event.getSource() == studentGrade_btn){
            home_form.setVisible(false);
            availableCourse_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentGrade_form.setVisible(true);

            studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourses_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            studentGradeShowListData();
            studentGradeSearch();
        }

    }

    //close
    public void close() {
        System.exit(0);
    }

    //minimize
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeDisplayTotalEnrolledStudents();
        homeDisplayTotalFemaleEnrolled();
        homeDisplayTotalMaleEnrolled();
        homeDisplayTotalEnrolledChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayMaleEnrolledChart();

        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();

        availableCourseShowListData();

        studentGradeShowListData();
    }
}
