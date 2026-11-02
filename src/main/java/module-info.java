module com.example.taberna {
    requires javafx.controls;
    requires javafx.fxml;


    opens taberna to javafx.fxml;
    exports taberna;
}