package org.svja.top.practica1_sevj.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Simulator {
    private double currentWeight;
    private double targetWeightLoss;
    private double runningSpeed;
    private int minutesPerSession;

    private double totalCalories;
    private double caloriesPerMinute;
    private double caloriesPerHour;
    private double totalHours;
}
