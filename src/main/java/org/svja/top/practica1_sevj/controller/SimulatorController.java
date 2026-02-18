package org.svja.top.practica1_sevj.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.svja.top.practica1_sevj.model.Simulator;
import org.svja.top.practica1_sevj.model.User;
import org.svja.top.practica1_sevj.repository.UserRepository;
import org.svja.top.practica1_sevj.util.CalorieCalculator;

import java.io.IOException;

public class SimulatorController {

    private Simulator model;

    // ── Inputs ──────────────────────────────────────────────
    @FXML private TextField txtWeight;
    @FXML private TextField txtKilos;
    @FXML private TextField txtSpeed;
    @FXML private TextField txtMinutes;

    // ── Result labels ────────────────────────────────────────
    @FXML private Label lblTotalCalories;
    @FXML private Label lblCaloriesPerMinute;
    @FXML private Label lblCaloriesPerHour;
    @FXML private Label lblTotalHours;

    // ── Session labels ───────────────────────────────────────
    @FXML private Label lblSessions30;
    @FXML private Label lblSessions45;
    @FXML private Label lblSessions60;
    @FXML private Label lblSessions75;
    @FXML private Label lblSessions90;
    @FXML private Label lblSessions120;

    // caches para el historial de usuarios
    private int s30, s45, s60, s75, s90, s120;
    private boolean calculated = false;


    @FXML
    private void initialize() {
        model = new Simulator();
        clearResults();
    }

    // manejadores

    @FXML
    private void handleCalculate() {
        if (!readUserInput()) return;   // validation failed → stop
        performCalculations();
        updateResultsView();
        calculated = true;
    }

    /** Guarda la sesión actual en el historial y muestra confirmación. */
    @FXML
    private void handleGuardar() {
        if (!calculated) {
            showAlert(Alert.AlertType.WARNING,
                    "Sin calcular",
                    "Primero presiona 'Calcular' para obtener resultados.");
            return;
        }

        User user = User.fromSimulator(model, s30, s45, s60, s75, s90, s120);
        UserRepository.getInstance().agregar(user);

        showAlert(Alert.AlertType.INFORMATION,
                "Sesión guardada",
                "La sesión #" + UserRepository.getInstance().size() +
                        " ha sido guardada en el historial.");
    }

    /** Abre la ventana del historial de sesiones. */
    @FXML
    private void handleVerHistorial() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/svja/top/practica1_sevj/view/historial-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Historial de Sesiones");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo abrir el historial: " + e.getMessage());
        }
    }



    /**
     * Lee y valida los campos del usuario.
     * @return true si todos los campos son válidos.
     */
    private boolean readUserInput() {
        try {
            double weight  = Double.parseDouble(txtWeight.getText().trim());
            double kilos   = Double.parseDouble(txtKilos.getText().trim());
            double speed   = Double.parseDouble(txtSpeed.getText().trim());
            int    minutes = Integer.parseInt(txtMinutes.getText().trim());

            if (weight <= 0 || kilos <= 0 || speed <= 0 || minutes <= 0) {
                showAlert(Alert.AlertType.WARNING,
                        "Valores inválidos",
                        "Todos los valores deben ser mayores que cero.");
                return false;
            }

            model.setCurrentWeight(weight);
            model.setTargetWeightLoss(kilos);
            model.setRunningSpeed(speed);
            model.setMinutesPerSession(minutes);
            return true;

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Formato incorrecto",
                    "Por favor ingresa sólo números válidos en todos los campos.");
            return false;
        }
    }

    private void performCalculations() {
        double totalCalories = CalorieCalculator
                .calculateTotalCalories(model.getTargetWeightLoss());

        double met = CalorieCalculator
                .getMETBySpeed(model.getRunningSpeed());

        double caloriesPerMinute = CalorieCalculator
                .calculateCaloriesPerMinute(model.getCurrentWeight(), met);

        double caloriesPerHour = CalorieCalculator
                .calculateCaloriesPerHour(caloriesPerMinute);

        double totalHours = CalorieCalculator
                .calculateTotalHours(totalCalories, caloriesPerHour);

        model.setTotalCalories(totalCalories);
        model.setCaloriesPerMinute(caloriesPerMinute);
        model.setCaloriesPerHour(caloriesPerHour);
        model.setTotalHours(totalHours);

        // Cache session counts
        s30  = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 30);
        s45  = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 45);
        s60  = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 60);
        s75  = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 75);
        s90  = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 90);
        s120 = CalorieCalculator.calculateSessions(totalCalories, caloriesPerMinute, 120);
    }

    private void updateResultsView() {
        lblTotalCalories.setText(String.format("%.2f kcal",  model.getTotalCalories()));
        lblCaloriesPerMinute.setText(String.format("%.2f kcal", model.getCaloriesPerMinute()));
        lblCaloriesPerHour.setText(String.format("%.2f kcal",   model.getCaloriesPerHour()));
        lblTotalHours.setText(String.format("%.2f hrs",          model.getTotalHours()));

        lblSessions30.setText(String.valueOf(s30));
        lblSessions45.setText(String.valueOf(s45));
        lblSessions60.setText(String.valueOf(s60));
        lblSessions75.setText(String.valueOf(s75));
        lblSessions90.setText(String.valueOf(s90));
        lblSessions120.setText(String.valueOf(s120));
    }

    private void clearResults() {
        lblTotalCalories.setText("-");
        lblCaloriesPerMinute.setText("-");
        lblCaloriesPerHour.setText("-");
        lblTotalHours.setText("-");

        lblSessions30.setText("-");
        lblSessions45.setText("-");
        lblSessions60.setText("-");
        lblSessions75.setText("-");
        lblSessions90.setText("-");
        lblSessions120.setText("-");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}