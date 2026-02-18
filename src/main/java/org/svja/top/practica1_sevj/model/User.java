package org.svja.top.practica1_sevj.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    // Datos de entrada
    private double currentWeight;
    private double targetWeightLoss;
    private double runningSpeed;
    private int minutesPerSession;

    // Resultados calculados
    private double totalCalories;
    private double caloriesPerMinute;
    private double caloriesPerHour;
    private double totalHours;

    // Sesiones calculadas para cada duración
    private int sessions30;
    private int sessions45;
    private int sessions60;
    private int sessions75;
    private int sessions90;
    private int sessions120;

    public static User fromSimulator(Simulator sim,
                                     int s30, int s45, int s60,
                                     int s75, int s90, int s120) {
        User u = new User();
        u.setCurrentWeight(sim.getCurrentWeight());
        u.setTargetWeightLoss(sim.getTargetWeightLoss());
        u.setRunningSpeed(sim.getRunningSpeed());
        u.setMinutesPerSession(sim.getMinutesPerSession());
        u.setTotalCalories(sim.getTotalCalories());
        u.setCaloriesPerMinute(sim.getCaloriesPerMinute());
        u.setCaloriesPerHour(sim.getCaloriesPerHour());
        u.setTotalHours(sim.getTotalHours());
        u.setSessions30(s30);
        u.setSessions45(s45);
        u.setSessions60(s60);
        u.setSessions75(s75);
        u.setSessions90(s90);
        u.setSessions120(s120);
        return u;
    }
}