module org.svja.top.practica1_sevj {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.svja.top.practica1_sevj to javafx.fxml;
    opens org.svja.top.practica1_sevj.controller to javafx.fxml;


    opens org.svja.top.practica1_sevj.model to javafx.base;


    exports org.svja.top.practica1_sevj;
    exports org.svja.top.practica1_sevj.controller;
    exports org.svja.top.practica1_sevj.model;
}