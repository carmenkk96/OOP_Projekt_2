module com.example.projekt_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projekt_2 to javafx.fxml;
    exports com.example.projekt_2;
}