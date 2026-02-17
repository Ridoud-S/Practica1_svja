module org.svja.top.practica1_sevj {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;

    opens org.svja.top.practica1_sevj to javafx.fxml, javafx.graphics;
    opens org.svja.top.practica1_sevj.controller to javafx.fxml;
    opens org.svja.top.practica1_sevj.model to javafx.fxml;

    exports org.svja.top.practica1_sevj;
}