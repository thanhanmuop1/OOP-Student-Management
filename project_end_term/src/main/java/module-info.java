module com.example.project_end_term {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;

    opens com.example.project_end_term to javafx.fxml;
    exports com.example.project_end_term;
}