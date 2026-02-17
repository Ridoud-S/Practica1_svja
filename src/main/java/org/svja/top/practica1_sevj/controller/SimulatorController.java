package org.svja.top.practica1_sevj.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.svja.top.practica1_sevj.model.Simulator;
import org.svja.top.practica1_sevj.util.CalorieCalculator;

public class SimulatorController {
    private Simulator model;
    @FXML private TextField txtWeight;
    @FXML private TextField txtKilos;
    @FXML private TextField txtSpeed;
    @FXML private TextField txtMinutes;

    @FXML private Label lblTotalCalories;
    @FXML private Label lblCaloriesPerMinute;
    @FXML private Label lblCaloriesPerHour;
    @FXML private Label lblTotalHours;

    @FXML private Label lblSessions30;
    @FXML private Label lblSessions45;
    @FXML private Label lblSessions60;
    @FXML private Label lblSessions75;
    @FXML private Label lblSessions90;
    @FXML private Label lblSessions120;
    @FXML
    private void initialize() {
        model = new Simulator();
        clearResults();
    }

    @FXML
    private void handleCalculate() {
        readUserInput();
        performCalculations();
        updateResultsView();
    }

    private void readUserInput() {
        model.setCurrentWeight(Double.parseDouble(txtWeight.getText()));
        model.setTargetWeightLoss(Double.parseDouble(txtKilos.getText()));
        model.setRunningSpeed(Double.parseDouble(txtSpeed.getText()));
        model.setMinutesPerSession(Integer.parseInt(txtMinutes.getText()));
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
    }

    private void updateResultsView() {
        lblTotalCalories.setText(String.format("%.2f kcal", model.getTotalCalories()));
        lblCaloriesPerMinute.setText(String.format("%.2f kcal", model.getCaloriesPerMinute()));
        lblCaloriesPerHour.setText(String.format("%.2f kcal", model.getCaloriesPerHour()));
        lblTotalHours.setText(String.format("%.2f hrs", model.getTotalHours()));

        lblSessions30.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        30)));

        lblSessions45.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        45)));

        lblSessions60.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        60)));

        lblSessions75.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        75)));

        lblSessions90.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        90)));

        lblSessions120.setText(String.valueOf(
                CalorieCalculator.calculateSessions(
                        model.getTotalCalories(),
                        model.getCaloriesPerMinute(),
                        120)));
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
}


