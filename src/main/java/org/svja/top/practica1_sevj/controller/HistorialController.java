package org.svja.top.practica1_sevj.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.svja.top.practica1_sevj.model.User;
import org.svja.top.practica1_sevj.repository.UserRepository;

public class HistorialController {


    @FXML private TableView<User> tableHistorial;

    @FXML private TableColumn<User, Double> colPeso;
    @FXML private TableColumn<User, Double> colKilos;
    @FXML private TableColumn<User, Double> colVelocidad;
    @FXML private TableColumn<User, Integer> colMinutos;

    @FXML private TableColumn<User, Double> colTotalCal;
    @FXML private TableColumn<User, Double> colCalMin;
    @FXML private TableColumn<User, Double> colCalHora;
    @FXML private TableColumn<User, Double> colHoras;

    @FXML private TableColumn<User, Integer> colS30;
    @FXML private TableColumn<User, Integer> colS45;
    @FXML private TableColumn<User, Integer> colS60;
    @FXML private TableColumn<User, Integer> colS75;
    @FXML private TableColumn<User, Integer> colS90;
    @FXML private TableColumn<User, Integer> colS120;


    @FXML private Label lblTotal;


    @FXML
    private void initialize() {
        configurarColumnas();
        cargarDatos();
    }



    /** Elimina la fila seleccionada del historial. */
    @FXML
    private void handleEliminar() {
        int selectedIndex = tableHistorial.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Sin selección",
                    "Selecciona una sesión de la tabla para eliminarla.");
            return;
        }
        UserRepository.getInstance().eliminar(selectedIndex);
        cargarDatos();
    }

    /** Limpia todo el historial. */
    @FXML
    private void handleLimpiarTodo() {
        if (UserRepository.getInstance().size() == 0) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Deseas eliminar todas las sesiones del historial?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                UserRepository.getInstance().limpiar();
                cargarDatos();
            }
        });
    }

    @FXML
    private void handleRefrescar() {
        cargarDatos();
    }

    //metodos helpers
    private void configurarColumnas() {
        // Datos de entrada
        colPeso.setCellValueFactory(new PropertyValueFactory<>("currentWeight"));
        colKilos.setCellValueFactory(new PropertyValueFactory<>("targetWeightLoss"));
        colVelocidad.setCellValueFactory(new PropertyValueFactory<>("runningSpeed"));
        colMinutos.setCellValueFactory(new PropertyValueFactory<>("minutesPerSession"));

        // Resultados
        colTotalCal.setCellValueFactory(new PropertyValueFactory<>("totalCalories"));
        colCalMin.setCellValueFactory(new PropertyValueFactory<>("caloriesPerMinute"));
        colCalHora.setCellValueFactory(new PropertyValueFactory<>("caloriesPerHour"));
        colHoras.setCellValueFactory(new PropertyValueFactory<>("totalHours"));

        // Sesiones
        colS30.setCellValueFactory(new PropertyValueFactory<>("sessions30"));
        colS45.setCellValueFactory(new PropertyValueFactory<>("sessions45"));
        colS60.setCellValueFactory(new PropertyValueFactory<>("sessions60"));
        colS75.setCellValueFactory(new PropertyValueFactory<>("sessions75"));
        colS90.setCellValueFactory(new PropertyValueFactory<>("sessions90"));
        colS120.setCellValueFactory(new PropertyValueFactory<>("sessions120"));

        // Formato de 2 decimales
        formatDouble(colPeso);
        formatDouble(colKilos);
        formatDouble(colVelocidad);
        formatDouble(colTotalCal);
        formatDouble(colCalMin);
        formatDouble(colCalHora);
        formatDouble(colHoras);
    }

    private void cargarDatos() {
        ObservableList<User> datos = FXCollections.observableArrayList(
                UserRepository.getInstance().getHistorial());
        tableHistorial.setItems(datos);
        lblTotal.setText("Total de sesiones: " + datos.size());
    }

    private <T> void formatDouble(TableColumn<T, Double> col) {
        col.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format("%.2f", value));
            }
        });
    }

    private void mostrarAlerta(Alert.AlertType type, String titulo, String mensaje) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}