package org.svja.top.practica1_sevj.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreditsController {
    @FXML
    private ImageView imgProfile;
    @FXML private Label lblName;
    @FXML private Label lblEmail;
    @FXML private Label lblSocial;

    @FXML
    private void initialize() {
        loadProfileInfo();
    }

    private void loadProfileInfo() {
        imgProfile.setImage(new Image(getClass().getResourceAsStream("/images/profile.png")));
        lblName.setText("Jaime Alejandro Serrano Vázquez");
        lblEmail.setText("24031004@itcelaya.edu.mx");
        lblSocial.setText("github.com/Ridoud-s");
    }
}
